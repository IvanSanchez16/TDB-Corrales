package Views;

import Controllers.CASensor;
import Resource.JNumberField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UIASensores extends JDialog {

    private PanelFondo PPrincipal;
    private DefaultTableModel DmC,DmS;
    private JTable TbC,TbS;
    private JScrollPane SpC,SpS;
    private JNumberField TxtCria,TxtSensor;
    private JButton BtnAsignar;
    private Font FontTitulos;

    public UIASensores(){
        setTitle("Asignar sensor");
        setSize(450,350);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        defineInterfaz();
    }

    public void asignarEscuchadores(CASensor c){
        TbC.addMouseListener(c);
        TbS.addMouseListener(c);
        BtnAsignar.addActionListener(c);
        TxtCria.addKeyListener(c);
        TxtSensor.addKeyListener(c);
    }

    public void llenarCrias(ArrayList<String[]> datos){
        String[] col={"Id","Corral"};
        DmC=new DefaultTableModel(null,col);
        for (String[] dato : datos) {
            DmC.addRow(dato);
        }
    }

    public void llenarSensores(ArrayList<String[]> datos){
        String[] col={"Id"};
        DmS=new DefaultTableModel(null,col);
        for (String[] dato : datos) {
            DmS.addRow(dato);
        }
    }

    private void defineInterfaz(){
        PPrincipal=new PanelFondo("fondoSensores.jpg",450,350);
        PPrincipal.setLayout(null);
        FontTitulos=new Font("Candara",1,15);

        TbC=new JTable();
        TbC.setFont(new Font("Cambria",1,12));
        SpC=new JScrollPane(TbC);
        SpC.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Crías pendientes", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos, Color.white));
        SpC.setOpaque(false);
        SpC.setBounds(10,5,200,260);
        PPrincipal.add(SpC);

        TbS=new JTable();
        TbS.setFont(new Font("Cambria",1,12));
        SpS=new JScrollPane(TbS);
        SpS.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Sensores disponibles", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos, Color.white));
        SpS.setOpaque(false);
        SpS.setBounds(225,5,200,260);
        PPrincipal.add(SpS);

        JPanel p=new JPanel(new GridLayout(0,3,5,0));
        TxtCria=new JNumberField();
        TxtCria.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Cria", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos, Color.white));
        TxtCria.setOpaque(false);
        TxtCria.setForeground(Color.WHITE);
        p.add(TxtCria);

        TxtSensor=new JNumberField();
        TxtSensor.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Sensor", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos, Color.white));
        TxtSensor.setOpaque(false);
        TxtSensor.setForeground(Color.WHITE);
        p.add(TxtSensor);

        BtnAsignar=new JButton("Asignar");
        BtnAsignar.setFont(new Font("Candara",0,17));
        p.add(BtnAsignar);

        p.setBounds(5,270,430,40);
        p.setOpaque(false);
        PPrincipal.add(p);

        add(PPrincipal);
    }
}
