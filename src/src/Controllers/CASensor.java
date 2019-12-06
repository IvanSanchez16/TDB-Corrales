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
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
