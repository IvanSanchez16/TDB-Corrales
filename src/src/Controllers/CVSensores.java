package Controllers;

import Models.MVSensores;
import Views.UIVSensores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CVSensores implements ActionListener {

    private UIVSensores view;
    private MVSensores model;

    public CVSensores(){
        view=new UIVSensores();
        model=new MVSensores();
        view.asignarEscuchador(this);
        view.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnActivos()){
            view.setActivos( model.obtenerDatos(true) );
            return;
        }
        if(e.getSource()==view.getBtnInactivos()){
            view.setInactivos( model.obtenerDatos(false) );
            return;
        }
    }
}
