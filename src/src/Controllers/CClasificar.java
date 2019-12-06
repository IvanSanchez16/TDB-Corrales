package Controllers;

import Models.MClasificar;
import Views.UIClasificar;
import Views.UIMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CClasificar implements ActionListener,MouseListener {

    UIClasificar view;
    MClasificar model;

    public CClasificar() {
        view = new UIClasificar();
        model = new MClasificar();
        view.asignarControladores(this);
        view.asignarArreglo(model.obtenerCrias());
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnClasificar()){
            view.mostrarModal(model.actualizarClasificaciones( UIMenu.getFechaActual()));
            view.asignarSensores( model.obtenerSensores(),model.getGc2(),this );
            view.asignarArreglo( model.obtenerCrias() );
            return;
        }
        if(e.getSource()==view.getBtnBuscar()){
            view.llenarTabla( model.obtenerCrias( view.getDatos() , view.getCriterio() ,view.getBusqueda() ) );
            return;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = view.getTbSensores().rowAtPoint(e.getPoint());
        String sensor= (String) view.getTbSensores().getValueAt(row,0);
        model.asignarSensor( view.getC()+"",sensor );
        view.quitarSensor(sensor);
        view.getJd().setVisible(false);
        view.getJd().dispose();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}

