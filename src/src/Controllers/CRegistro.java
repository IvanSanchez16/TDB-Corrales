package Controllers;

import Models.MRegistro;
import Views.UIRegistroCria;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CRegistro implements ActionListener {
    UIRegistroCria view;
    MRegistro model;

    public CRegistro(){
        view=new UIRegistroCria();
        model=new MRegistro();
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
    }

}
