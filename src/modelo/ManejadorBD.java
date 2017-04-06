
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ManejadorBD {
    public String cadConexion;
    public String user;
    public String password;
    public String database;
    public String server;
    public String driver;
    public ManejadorBD(){
        driver="com.mysql.jdbc.Driver";
	server = "localhost:3306";
        database = "gcarga";
        user = "root";
        password = "12345678";
  	cadConexion = "jdbc:mysql://" + server + "/" + database;
    }
    public ManejadorBD(String cadenaConexion,String driver,String user,String password) {
        this.cadConexion=cadenaConexion;
        this.driver=driver;
        this.user=user;
        this.password=password;
}
    public Connection getConnection() throws Exception{   
        Connection con = null;
	try{
	Class.forName(driver);
        con = DriverManager.getConnection(cadConexion, user, password);
	System.out.println("conexion etablecida");
	JOptionPane.showMessageDialog(null,"Conexion establecida");
	}catch(ClassNotFoundException | SQLException e){
	JOptionPane.showMessageDialog(null,"error Conexion"+e);
	}	
        return con;
    } 

 public ArrayList<Object[]> consulta(String sql) throws Exception {
        ArrayList<Object[]> list = null;
        Connection cn = getConnection(); 
        if (cn != null) {     
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rm = rs.getMetaData();
                int ctascol = rm.getColumnCount();
                list = new ArrayList<Object[]>();
                String[] titulo = new String[ctascol];
                for (int i = 0; i < ctascol; i++) {
                    titulo[i] = rm.getColumnLabel(1 + i);
                }
                list.add(titulo);   // elemento 0 tiene los titulos de la consulta
                while (rs.next()) {
                    Object[] fila = new Object[ctascol];
                    for (int i = 0; i < ctascol; i++) {
                        fila[i] = rs.getObject(1 + i);
                    }
                    list.add(fila);
                }
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
        return list;
    }
    
    public String mantenimiento(String sql) throws Exception {// INS, DEL y UPD
        System.out.println(""+sql);
        String msg = null;
        Connection cn = getConnection();
        if (cn != null) {
            try {     
                Statement st = cn.createStatement();
                int ctasfils = st.executeUpdate(sql);
                
                if (ctasfils == 0) {
                    msg = "0 filas afectadas!";
                }else{
                    msg="Operación Satisfactoria.\n"+ctasfils+" fila(s) afectada(s)";
                }
                cn.close();
            } catch (SQLException ex) {
                msg = "Excepción dao Sql: "+ex.getMessage();
            }
        }
        return msg;
    }
    
    public Object[] getFila(String sql) throws Exception {
        Object[] fila = null;
        List<Object[]> list = consulta(sql);
        if ((list != null) && (list.size() > 1)) {
            fila = (Object[]) list.get(1);
        }
        return fila;
    }
    
    public Object[] getColumna(String sql) throws Exception {
        Object[] columna = null;
        List<Object[]> list = consulta(sql);
        if ((list != null) && (list.size() > 1)) {
            int x = list.size();
            columna = new Object[x - 1];
            for (int i = 1; i < x; i++) {
                Object[] fila = (Object[]) list.get(i);
                columna[i - 1] = fila[0];
            }
        }
        return columna;
    }
    public Object getCampo(String sql) throws Exception {
        Object campo = null;
        Object[] fila = getFila(sql);
        if (fila != null) {
            campo = fila[0];
        }
        return campo;
    }
    public List<Object[]> consulta(String sql, long numpag, long ctasfils) throws Exception {
        String limit = " LIMIT " + numpag * ctasfils + "," + ctasfils;
        List<Object[]> list = consulta(sql + limit);
        return list;
    }
    public long ctasFilas(String sql) throws Exception {
        long ctasfils = 0L;
        sql = sql.toLowerCase();
        String from = sql.substring(sql.indexOf(" from "));
        Object obj = getCampo("select count(*)" + from);
        if(obj != null) {
            ctasfils = (Long) obj;
        }else{
            return 0;
        }
        return ctasfils;
    }
    public boolean getExiste(String sql) throws Exception {
        boolean existe = false;
        List<Object[]> list = consulta(sql);

        if ((list != null) && (list.size() > 1)) {
            existe=true;
        }
        return existe;
    }
}