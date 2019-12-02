package Views;

import Resource.Rutinas;

import javax.swing.*;
import java.awt.*;

public class PanelFondo extends JPanel {

    private String imgFondo;
    private int ancho, alto;

    public PanelFondo(String fondo,int al,int an){
        imgFondo=fondo;
        ancho=al;
        alto=an;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Rutinas.AjustarImagen("src/src/images/"+imgFondo, ancho, alto).getImage(),0,0, null);
    }

}
