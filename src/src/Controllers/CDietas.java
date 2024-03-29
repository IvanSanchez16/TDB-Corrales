package Controllers;

import Models.MDietas;
import Views.UIDietas;
import Views.UIMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CDietas implements ActionListener, MouseListener {

    private UIDietas view;
    private MDietas model;

    public CDietas(){
        view=new UIDietas();
        model=new MDietas();
        view.asignarEscuchador(this);
        view.setDatos( model.obtenerDatos() );
        view.llenarCBDietas( model.obtenerDietas() );
        view.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnBuscar()){
            if(view.getTxtBusqueda().equals("")){
                view.llenarTabla();
                return;
            }
            view.llenarTabla( model.filtrarDatos( view.getDatos(),view.getTxtBusqueda(),view.getCbBusqueda() ) );
            return;
        }
        if(e.getSource()==view.getBtnAceptar()){
            try {
                view.mostrarModal( model.actualizarDieta( view.getTxtCria()+"",view.getCBDietas(), UIMenu.getFechaActual()) );
                view.setDatos( model.obtenerDatos() );
            } catch (Exception ex) {
                view.mostrarModal("Seleccione una cría");
            }
            return;
        }
        if(e.getSource()==view.getBtnAgregar()){
            view.crearDieta(this);
            return;
        }
        if (view.getTxtDesc().length()>30)
            view.mostrarModal2("La descripción debe tener menos de 30 caracteres");
        model.agregarDieta( view.getTxtDesc(),view.getTipo() );
        view.getJd().dispose();
        view.llenarCBDietas( model.obtenerDietas() );
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==view.getTb()){
            view.seleccionarCria(view.getTb().rowAtPoint(e.getPoint()));
            return;
        }
    }

    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}
