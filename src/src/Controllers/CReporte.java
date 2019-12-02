package Controllers;

import Models.MReporte;
import Views.UICriaParaReporte;
import Views.UIReporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CReporte implements ActionListener, MouseListener {

    UICriaParaReporte view2;
    UIReporte view;
    MReporte model;

    public CReporte(){
        view2=new UICriaParaReporte();
        model=new MReporte();
        view2.asignarEscuchador(this);
        view2.llenarTabla( model.obtenerCriasFuera() );
        view2.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row=view2.getTb().rowAtPoint(e.getPoint() );
        int cria= Integer.parseInt((String) view2.getTb().getValueAt(row,0));
        boolean razon=view2.getTb().getValueAt(row,2).equals("Sacrificada");
        view2.dispose();
        view=new UIReporte( cria , razon );
        view.llenarTablas( model.obtenerDatos(cria) );
        view.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
