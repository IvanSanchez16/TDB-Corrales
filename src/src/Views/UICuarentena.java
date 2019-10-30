package Views;

import Controllers.CCuarentena;
import Resource.JNumberField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UICuarentena extends JDialog {
    private JTabbedPane TPane;
    private JPanel PAgregar,PSacar;
    private JScrollPane SPCrSa,SPCrEn,SPCoSa,SPCoEn;
    private JTable TbCrSa,TbCrEn,TbCoSa,TbCoEn;
    private DefaultTableModel DmCrSa,DmCrEn,DmCoSa,DmCoEn;
    private JNumberField TxtCorral1,TxtCria1,TxtCorral2,TxtCria2;
    private JTextField TxtEnfermedad,TxtMedicamento;
    private Font FontCajas;
    private Font FontTitulos;
    private JButton BtnMoverCriaA,BtnMoverCriaR;

    public UICuarentena(){
        setTitle("Cuarentenas");
        setModal(true);
        setSize(800,405);
        setLocationRelativeTo(null);
        setResizable(false);
        defineInterfaz();
    }
    public void asignarControlador(CCuarentena c){
        BtnMoverCriaA.addActionListener(c);
    }

    public JButton getBtnMoverCriaA() {
        return BtnMoverCriaA;
    }

    public JButton getBtnMoverCriaR() {
        return BtnMoverCriaR;
    }

    public void mostrarModal(String msg){
        boolean band=true;
        if(msg.equals("")){
            msg="La cuarentena fue agregada correctamente";
            band=false;
        }
        JOptionPane.showMessageDialog(this,msg,"Registro de cría",msg.equals("La cuarentena fue agregada correctamente")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
        if(!band)
            dispose();
    }


    private void defineInterfaz(){
        TPane=new JTabbedPane();
        FontCajas = new Font("Dubai", 0, 13);
        FontTitulos = new Font("Candara", 1, 13);
        TPane.setFont(new Font("Candara",1,17));
        add(TPane);

        PAgregar=new JPanel();
        PAgregar.setLayout(null);

        String [][] m={};
        String [] columnas={"Id","Corral Actual"};
        DmCrEn=new DefaultTableModel(m,columnas);
        TbCrEn=new JTable(DmCrEn);
        SPCrEn=new JScrollPane(TbCrEn);
        SPCrEn.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Crías enfermas sin tratamiento con clas. grasa de cobertura 2", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPCrEn.setBounds(5,5,375,275);
        PAgregar.add(SPCrEn);

        String[] columnas2={"Id","# de crías"};
        DmCoEn=new DefaultTableModel(m,columnas2);
        TbCoEn=new JTable(DmCoEn);
        SPCoEn=new JScrollPane(TbCoEn);
        SPCoEn.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Corrales para crías enfermas", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPCoEn.setBounds(410,5,375,275);
        PAgregar.add(SPCoEn);

        JPanel P1=new JPanel();
        P1.setLayout(new GridLayout(0,5,10,0));

        TxtCria1=new JNumberField();
        TxtCria1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Cria_id", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtCria1.setFont(FontCajas);
        TxtCria1.setBackground(new Color(242,242,242));
        P1.add(TxtCria1);

        TxtCorral1=new JNumberField();
        TxtCorral1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Corral_id", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtCorral1.setFont(FontCajas);
        TxtCorral1.setBackground(new Color(242,242,242));
        P1.add(TxtCorral1);

        TxtEnfermedad=new JTextField();
        TxtEnfermedad.setFont(FontCajas);
        TxtEnfermedad.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Enfermedad", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtEnfermedad.setBackground(new Color(242,242,242));
        P1.add(TxtEnfermedad);

        TxtMedicamento=new JTextField();
        TxtMedicamento.setFont(FontCajas);
        TxtMedicamento.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Medicamento", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtMedicamento.setBackground(new Color(242,242,242));
        P1.add(TxtMedicamento);

        BtnMoverCriaA=new JButton("Mover");
        BtnMoverCriaA.setFont(new Font("Candara",1,15));
        P1.add(BtnMoverCriaA);

        P1.setBounds(5,285,770,42);
        PAgregar.add(P1);

        TPane.add(PAgregar,"Agregar");
    }

    public void llenarCriasEn(ArrayList<String[]> datos){
        for(int i=0 ; i<datos.size() ; i++)
            DmCrEn.addRow(datos.get(i));
    }

    public void llenarCriasSa(ArrayList<String[]> datos){
        for(int i=0 ; i<datos.size() ; i++)
            DmCrSa.addRow(datos.get(i));
    }

    public void llenarCorralesEn(ArrayList<String[]> datos){
        for(int i=0 ; i<datos.size() ; i++)
            DmCoEn.addRow(datos.get(i));
    }

    public void llenarCorralesSa(ArrayList<String[]> datos){
        for(int i=0 ; i<datos.size() ; i++)
            DmCoSa.addRow(datos.get(i));
    }

    public String getCria1(){
        return TxtCria1.getText();
    }

    public String getCorral1(){
        return TxtCorral1.getText();
    }

    public String getEnfermedad(){
        return TxtEnfermedad.getText();
    }
    public String getMedicamento(){
        return TxtMedicamento.getText();
    }

}
