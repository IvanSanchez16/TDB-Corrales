package Controllers;

import Models.MCorral;
import Views.UIRegistroCorral;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CCorral implements ActionListener{

    private UIRegistroCorral view;
    private MCorral model;

    public CCorral(){
        view=new UIRegistroCorral();
        model=new MCorral();
        view.asignarControlador(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnRegistrar()){
            String mensaje=model.registrarCorral(view.getComboTipo());
            view.mostrarModal(mensaje);
        }
    }

}
