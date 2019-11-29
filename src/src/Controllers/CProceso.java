package Controllers;

import Models.MProceso;
import Views.UIMenu;
import Views.UIProceso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CProceso implements ActionListener, MouseListener {
    UIProceso view;
    MProceso model;

    public CProceso(){
        view=new UIProceso();
        model=new MProceso();
        view.asignarEscuchador(this);
        view.asignarDatos( model.obtenerDatos() );
        view.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnMarcar()){
            view.marcarCrias( model.obtenerIndCrias150d( view.getDatos() ) );
            return;
        }
        if(e.getSource()==view.getBtnSalida()){
            String msg=model.validarCria( view.getId(),view.getDatos() );
            if(!msg.equals("")){
                view.mostrarModal(msg);
                return;
            }
            view.mostrarModal( model.darSalidaCria( view.getId(), UIMenu.getFechaActual()) );
            view.asignarDatos( model.obtenerDatos() );
            return;
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==view.getTb()){
            view.seleccionarCria( view.getTb().rowAtPoint(e.getPoint()) );
            return;
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
