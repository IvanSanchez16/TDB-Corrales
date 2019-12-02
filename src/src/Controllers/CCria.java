package Controllers;

import Models.MCria;
import Views.UIMenu;
import Views.UIRegistroCria;
import java.awt.event.*;
import java.util.ArrayList;

public class CCria implements ActionListener,KeyListener {
    UIRegistroCria view;
    MCria model;

    public CCria(){
        view=new UIRegistroCria();
        model=new MCria();
        view.asignarControladores(this);
        ArrayList<String[]> datos=model.obtenerCorrales();
        view.setTextId( model.sigCodigo() );
        view.llenarCorrales(datos);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource()==view.getBtnFecha()){
            view.mostrarDatePicker();
            return;
        }
        if(evt.getSource()==view.getBtnRegistrar()){
            try {
                String msg=model.insertar( view.getId(),view.getFecha(), UIMenu.getFechaActual(),view.getEstado(),view.getPeso(),view.getCMusculo(),view.getCGrasa(),view.getCorral() );
                view.mostrarModal(msg);
            } catch (Exception e) {
                view.mostrarModal("Faltan campos por llenar");
            }
        }
    }
    public void keyTyped(KeyEvent e) {
        e.consume();
    }
    public void keyPressed(KeyEvent e) {
        if(e.getSource()==view.getTxtId()){
            e.consume();
            return;
        }
    }
    public void keyReleased(KeyEvent e) {}
}
