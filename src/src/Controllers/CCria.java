package Controllers;

import Models.MCria;
import Views.UIRegistroCria;

import java.awt.event.*;
import java.util.ArrayList;

public class CCria implements ActionListener {
    UIRegistroCria view;
    MCria model;

    public CCria(){
        view=new UIRegistroCria();
        model=new MCria();
        view.asignarControladores(this);
        ArrayList<String[]> datos=model.obtenerCorrales();
        view.llenarCorrales(datos);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource()==view.getBtnFecha()){
            view.mostrarDatePicker();
            return;
        }
        if(evt.getSource()==view.getBtnRegistrar()){
            String msg=model.insertar( view.getId(),view.getFecha(),view.getFechaActual(),view.getEstado(),view.getPeso(),view.getCMusculo(),view.getCGrasa(),view.getCorral() );
            view.mostrarModal(msg);
        }
    }

}
