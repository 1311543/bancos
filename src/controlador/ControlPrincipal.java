
package controlador;

import javax.swing.JFrame;
import vista.UIPrincipal;
import vista.UIMantBanco;
import java.awt.Cursor;
import javax.swing.JFrame;


public class ControlPrincipal {
    UIPrincipal vista;
    public ControlPrincipal(JFrame vista){
        this.vista=(UIPrincipal) vista;
    }
    
    public void abrirMantBanco(){
        vista.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        UIMantBanco iFrame2 = new UIMantBanco();
        UIPrincipal.escritorio.add(iFrame2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        iFrame2.toFront();
        iFrame2.setVisible(true);
        vista.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
     
    }
/*        public void CrearFactura(){
        vista.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        IUFactura iFrame3 = new IUFactura();
        IUPrincipal.desktopPane.add(iFrame3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        iFrame3.toFront();
        iFrame3.setVisible(true);
        vista.jDlgCrearFactura.setVisible(false);
        vista.jDlgCrearFactura.setVisible(false);
        vista.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  } */ 
}
