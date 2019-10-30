package Controllers;

import Models.MClasificar;
import Views.UIClasificar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CClasificar implements ActionListener, MouseListener {

    UIClasificar view;
    MClasificar model;

    public CClasificar() {
        view = new UIClasificar();
        model = new MClasificar();
        view.asignarControladores(this);
        view.llenarTabla(model.obtenerCrias());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnClasificar()){
            view.mostrarModal(model.actualizarClasificaciones());
            view.llenarTabla( model.obtenerCrias() );
            return;
        }
        if(e.getSource()==view.getBtnEditar()){
            view.mostrarModal( model.actualizarCria( view.getId(),view.getPeso(),view.getGrasa() ));
            view.getTxtId().setText("");
            view.getTxtPeso().setText("");
            view.getTxtPeso().setEnabled(false);
            view.getTxtGrasa().setText("");
            view.getTxtGrasa().setEnabled(false);
            view.llenarTabla( model.obtenerCrias() );
            return;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       view.editarCria(view.getTbCrias().rowAtPoint(e.getPoint()));
       return;
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

