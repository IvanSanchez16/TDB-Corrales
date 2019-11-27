package Controllers;

import Models.MDietas;
import Views.UIDietas;

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
        view.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

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
