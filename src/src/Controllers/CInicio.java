package Controllers;

import Models.MInicio;
import Views.UIPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CInicio implements ActionListener {

    private UIPrincipal view;
    private MInicio model;

    public CInicio(){
        view=new UIPrincipal();
        model=new MInicio();
        view.asignarControladorP(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnRegistrarCria()){
            CCria C=new CCria();
            return;
        }
        if(e.getSource()==view.getBtnRegistrarCorral()){
            CCorral c=new CCorral();
            return;
        }

    }
}
