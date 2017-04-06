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

public class Banco extends ManejadorBD implements InterfazModelo{
    private Integer idBanco;
    private String codigo;
    private String razonSocial;
    private String pseudonimo;
    private String numeroCta;
    private String moneda;
    private String ruc;
    private String telefono;
    private String paginaweb;
    private String tipoCta;
    private Pais pais;

    public Integer getIdBanco() {
        return idBanco;
    }
    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getPseudonimo() {
        return pseudonimo;
    }
    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }
    public String getNumeroCta() {
        return numeroCta;
    }
    public void setNumeroCta(String numeroCta) {
        this.numeroCta = numeroCta;
    }
    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public String getRuc() {
        return ruc;
    }
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getPaginaweb() {
        return paginaweb;
    }
    public void setPaginaweb(String paginaweb) {
        this.paginaweb = paginaweb;
    }
    public String getTipoCta() {
        return tipoCta;
    }
    public void setTipoCta(String tipoCta) {
        this.tipoCta = tipoCta;
    }
       public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
    public String generarCodigo()throws Exception{
        if(getCampo("SELECT MAX(idBanco) as id FROM banco")!=null){
            return "B"+utilitario.Utilitario.rellenoCerosIzq(Integer.parseInt(getCampo("SELECT MAX(idBanco) as id FROM banco").toString())+1 , 6);
        }else{
            return "B000001";
        }
        
}
    public TableModel getModeloTabla(String nombreCampo, String valorCampo) throws Exception{
        StringBuilder query=new StringBuilder();
        query.append("SELECT banco.idBanco,banco.codigo,banco.razonSocial,banco.pseudonimo,banco.numeroCta,banco.moneda,");
        query.append("banco.tipoCta,banco.ruc,banco.telefono,");
        query.append("banco.paginaweb,pais.nombre ");
        query.append("FROM banco, pais ");
        query.append("WHERE banco.idPais=pais.idPais AND banco."+nombreCampo+" LIKE '%"+valorCampo+"%' ORDER BY banco.idBanco DES C");
        if(valorCampo.equals("")){
            valorCampo="%";
        }else{
            valorCampo=valorCampo.replace("*","%");
        }
        DefaultTableModel modelo = null;
        try {
            ArrayList<Object[]> list = consulta(String.valueOf(query));
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
                System.out.println("lista Bancos VACIA.");
            }
            //Se crea un Vector que contenga los Titulos de las Columnas.
            Vector columnas = new Vector();
            columnas.add("ID");
            columnas.add("Cod");
            columnas.add("Razon Social");
            columnas.add("pseudonimo");
            columnas.add("Nro.Cta");
            columnas.add("Moneda");
            columnas.add("tipoCta");
            columnas.add("ruc");
            columnas.add("Telefono");
            columnas.add("PaginaWeb");
            columnas.add("Nomb.Pais");
            

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
    public void insertar(Object objeto) throws Exception{
        Banco banco=(Banco) objeto;
        String columnas="", valores="";
        
        mantenimiento("INSERT INTO banco(idBanco,codigo,razonSocial,pseudonimo,numeroCta,tipoCta,"
                +"moneda,ruc,telefono,paginaweb,idPis"+columnas+")"
                +"VALUES(null,'"+generarCodigo()+"','"+banco.getRazonSocial()+"','"+banco.getPseudonimo()+"',"
                +"','"+banco.getNumeroCta()+"','"+banco.getTipoCta()+"','"+banco.getMoneda()+"','"+banco.getRuc()+"','"+banco.getTelefono()+"',"
                +"','"+banco.getPaginaweb()+"',"+banco.getPais().getIdPais()+"'" +valores+")");
         
    }
    @Override
    public void editar(Object objeto) throws Exception {
        Banco banco=(Banco) objeto;
        String campos=""; 
        
        mantenimiento("UPDATE banco "
                + "SET razonSocial='"+banco.getRazonSocial()+"',pseudonimo='"+banco.getPseudonimo()+"',numeroCta='"+getNumeroCta()+"',"
                + "moneda='"+banco.getMoneda()+"',tipoCta='"+banco.getTipoCta()+"',ruc='"+getRuc()+"',"
                + "telefono='"+banco.getTelefono()+"',paginaweb='"+getPaginaweb()+"',idPais='"+banco.getPais()+"',"+campos+" WHERE idCliente='"+banco.getIdBanco()+"'");
    }
    @Override
    public void eliminar(int id) throws Exception {
        mantenimiento("DELETE FROM banco WHERE idBanco='"+id+"'");
    }
@Override
    public Object buscar(String campo, String valor) throws Exception {
        Banco banco=new Banco();
        
        Object[] fila=getFila("SELECT *FROM banco WHERE "+campo+"='"+valor+"'");
        if(fila!=null){
            banco.setIdBanco(Integer.parseInt(fila[0].toString()));
            banco.setCodigo(fila[1].toString());
            banco.setPseudonimo(fila[2].toString());
            banco.setNumeroCta(fila[3].toString());
            banco.setMoneda(fila[4].toString());
            if(fila[5]!=null){
                banco.setTipoCta(fila[5].toString());
            }
            if(fila[6]!=null){
                banco.setRuc(fila[6].toString());
            }
            if(fila[7]!=null){
                banco.setTelefono(fila[7].toString());
            }
            if(fila[8]!=null){
                banco.setPaginaweb(fila[8].toString());
            }
            Pais pais=new Pais();
            pais=(Pais) pais.buscar(Integer.parseInt(fila[18].toString()));
            banco.setPais(pais);
            return banco;
        }else{
            return null;
        }
        
    }
}
