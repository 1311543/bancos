/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Pais extends ManejadorBD implements InterfazModelo{
    private Integer idPais;
    private String codigo;
    private String nombre;
    private String estado;

    /**
     * @return the idPais
     */
    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
        public TableModel getModeloTabla(String nombreCampo, String valorCampo) throws Exception{
        if(valorCampo.equals("")){
            valorCampo="%";
        }else{
            valorCampo=valorCampo.replace("*","%");
        }
        DefaultTableModel modelo = null;
        try {
            ArrayList<Object[]> list = consulta("SELECT idPais,codigo,nombre "
                    + "FROM pais WHERE "+nombreCampo+" LIKE '"+valorCampo+"%' AND estado='VIGENTE'");
            Vector listaDatos = new Vector();
            if (list != null) {
                for (int fil = 1; fil < list.size(); fil++) {
                    Object[] fila = (Object[]) list.get(fil);
                    Vector datos = new Vector();
                    for (int col = 0; col < fila.length; col++) {
                        if(fila[col]!=null)
                            datos.add("" + fila[col].toString());
                        else
                            datos.add("");
                    }
                    listaDatos.add(datos);
                }
            } else {
                System.out.println("lista Proveedores VACIA.");
            }
            //Se crea un Vector que contenga los Titulos de las Columnas.
            Vector columnas = new Vector();
            columnas.add("ID");
            columnas.add("Codigo");
            columnas.add("Nombre");

            modelo = new DefaultTableModel(listaDatos, columnas) {
                @Override
                // ninguna celda sera editable
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
        } catch (Exception e) {
            System.out.println("error obteniendo el modelo Proveedores." + e.getMessage());
        }
        return modelo;
    }

    @Override
    public void insertar(Object objeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Object objeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscar(String campo, String valor) throws Exception {
        Pais pais=null;
        Object[] fila=getFila("SELECT *FROM pais WHERE "+campo+"='"+valor+"'");
        if(fila!=null){
            pais=new Pais();
            pais.setIdPais(Integer.parseInt(fila[0].toString()));
            pais.setCodigo(fila[1].toString());
            pais.setNombre(fila[2].toString());
        }
        return pais;
    }

    @Override
    public Object buscar(int id) throws Exception {
        Pais pais=null;
        Object[] fila=getFila("SELECT *FROM pais WHERE idPais='"+id+"'");
        if(fila!=null){
            pais=new Pais();
            pais.setIdPais(Integer.parseInt(fila[0].toString()));
            pais.setCodigo(fila[1].toString());
            pais.setNombre(fila[2].toString());
        }
        return pais;
    }

    @Override
    public ArrayList<Object[]> listar(String campo, String valor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object objeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
