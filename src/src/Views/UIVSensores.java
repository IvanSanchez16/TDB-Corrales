package Views;

import Controllers.CVSensores;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UIVSensores extends JDialog {

    private PanelFondo PPrincipal;
    private JScrollPane Sp;
    private JTable Tb;
    private DefaultTableModel Dm;
    private JButton BtnActivos,BtnInactivos;
    private ArrayList<String[]> Activos,Inactivos;
    private Font FontTitulos;
    private Font FontBotones;

    public UIVSensores(){
        setTitle("Sensores");
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(250,350);
        setModal(true);
        defineInterfaz();
    }

    public void setActivos(ArrayList<String[]> activos) {
        Activos = activos;
        llenarTabla(Activos);
    }

    public void setInactivos(ArrayList<String[]> inactivos) {
        Inactivos = inactivos;
        llenarTabla(Inactivos);
    }

    private  void llenarTabla(ArrayList<String[]> datos){
        String [] col={"Id","Estatus"};
        Dm=new DefaultTableModel(null,col);
        Tb.setModel(Dm);
        for (String[] dato : datos) {
            Dm.addRow(dato);
        }
    }

    public void asignarEscuchador(CVSensores c){
        BtnActivos.addActionListener(c);
        BtnInactivos.addActionListener(c);
    }

    public JButton getBtnActivos() {
        return BtnActivos;
    }

    public JButton getBtnInactivos() {
        return BtnInactivos;
    }

    private void defineInterfaz(){
        PPrincipal=new PanelFondo("fondoSensores.jpg",250,350);
        PPrincipal.setLayout(null);
        FontTitulos = new Font("Candara", 1, 13);
        FontBotones =new Font("Candara",1,13);

        String[] col={"Id","Estatus"};
        Dm=new DefaultTableModel(null,col);
        Tb=new JTable(Dm);
        Tb.setFont(new Font("Cambria",1,13));
        Sp=new JScrollPane(Tb);
        Sp.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Sensores", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos, Color.WHITE));
        Sp.setOpaque(false);
        Sp.setBounds(5,40,230,270);
        PPrincipal.add(Sp);

        BtnActivos=new JButton("Mostrar Activos");
        BtnActivos.setBounds(5,5,115,30);
        BtnActivos.setFont(FontBotones);
        PPrincipal.add(BtnActivos);

        BtnInactivos=new JButton("Mostrar Inactivos");
        BtnInactivos.setBounds(125,5,115,30);
        BtnInactivos.setFont(FontBotones);
        PPrincipal.add(BtnInactivos);

        add(PPrincipal);
    }
}
