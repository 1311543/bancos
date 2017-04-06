/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JFrame;
import modelo.Banco;
import vista.UIMantBanco;
import vista.UIPrincipal;

/**
 *
 * @author ronal
 */
public class ControlMantBanco {
    UIMantBanco vista;
    Banco banco=new Banco();
    public String accion;
    public ControlMantBanco(JFrame vista){
        this.vista=(UIMantBanco) this.vista;
    }
    /*
    IUPrincipal vista;
    Clientes clientes = new Clientes();
    public String accion;
    public String tipoAsignacion;
    public String ubigeoDestino;//variable que controla si el ubigeo se mostrara en Clientes o en Proveedores

    public ControlClientes(JFrame vista) {
        this.vista = (IUPrincipal) vista;
    }
*/  
       public void cancelar() {
        limpiarControles();
        activarControles(false);
        
        vista.btnNuevo3.setEnabled(true);
        vista.btnEditar1.setEnabled(false);
        vista.btnEliminar3.setEnabled(false);
        vista.btnGrabar3.setEnabled(false);
        vista.btnCancelar1.setEnabled(false);
        vista.btnNuevo3.requestFocus(); 
        
    }
       private void limpiarControles() {
        vista.txtCodigo.setText("");
        vista.txtRuc.setText("");
        vista.txtRazonSocial.setText("");
        vista.txtPseudonimo.setText("");
        vista.txtTelefono.setText("");
        vista.txtPaginaweb.setText("");
        vista.txtPais.setText("");
        vista.cmbMoneda.setSelectedIndex(0);
        vista.cmbTipoCta.setSelectedIndex(0);
        
//        vista.txtcodCusto.setText("");
//        vista.txtCustomer.setText("");
//        vista.txtcodVend.setText("");
//        vista.txtVendedor.setText("");
//        vista.txtCodOpera.setText("");
//        vista.txtOperativo.setText("");
    }
    
}
