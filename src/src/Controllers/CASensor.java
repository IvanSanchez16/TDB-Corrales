package Controllers;

import Models.MASensor;
import Views.UIASensores;

import java.awt.event.*;

public class CASensor implements ActionListener, MouseListener, KeyListener {

    private UIASensores view;
    private MASensor model;

    public CASensor(){
        view=new UIASensores();
        model=new MASensor();
        view.asignarEscuchadores(this);
        view.llenarCrias( model.obtenerCrias() );
        view.llenarSensores( model.obtenerSensores() );
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==view.getBtnAsignar()){
            view.mostrarModal( model.asignarSensor( view.getCria(),view.getSensor() ) );
            return;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==view.getTbC()){
            view.seleccionarCria((String) view.getTbC().getValueAt( view.getTbC().rowAtPoint(e.getPoint()),0 ));
            return;
        }
        if(e.getSource()==view.getTbS()){
            view.seleccionarSensor((String) view.getTbS().getValueAt(view.getTbS().rowAtPoint(e.getPoint()),0));
            return;
        }
    }

    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
