package Views;

import Controllers.CCuarentena;
import Resource.JNumberField;
import Resource.Rutinas;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UICuarentena extends JDialog {
    private JTabbedPane TPane;
    private JPanel PAgregar,PSacar,PEvaluar;
    private JScrollPane SPCrSa,SPCrEn,SPCoEn,SPCrG2;
    private JTable TbCrSa,TbCrEn,TbCoEn,TbCrG2;
    private DefaultTableModel DmCrSa,DmCrEn,DmCoEn,DmCrG2;
    private JNumberField TxtCorral1,TxtCria1,TxtCria2;
    private Font FontCajas;
    private Font FontTitulos;
    private JButton BtnMoverCriaA,BtnMarcarSanas,BtnMoverCriaR,BtnSacrificar,BtnMarcarEnRiesgo,BtnMarcarC40;
    private ArrayList<String []>datosEG2,datosSA;
    private Timer t,te;
    private JDialog jd;

    public UICuarentena(){
        setTitle("Cuarentenas");
        setModal(true);
        setSize(500,405);
        setLocationRelativeTo(null);
        setResizable(false);
        defineInterfaz();
    }

    public void asignarControlador(CCuarentena c){
        BtnMoverCriaA.addActionListener(c);
        TbCrEn.addMouseListener(c);
        TbCoEn.addMouseListener(c);
        TbCrSa.addMouseListener(c);
        BtnMarcarEnRiesgo.addActionListener(c);
        BtnMarcarC40.addActionListener(c);
        BtnSacrificar.addActionListener(c);
        BtnMarcarSanas.addActionListener(c);
        BtnMoverCriaR.addActionListener(c);
        t=new Timer(30000,c);
        te=new Timer(30000,c);
        t.start();
        te.start();
    }

    public void seleccionarCria(int row){
        TxtCria1.setText((String) TbCrEn.getValueAt(row,0));
    }

    public void seleccionarCriaSa(int row){
        TxtCria2.setText((String) TbCrSa.getValueAt(row,0));
    }

    public void seleccionarCorral(int row){
        TxtCorral1.setText((String) TbCoEn.getValueAt(row,0));
    }

    public JButton getBtnMoverCriaA() {
        return BtnMoverCriaA;
    }

    public JButton getBtnMoverCriaR() {
        return BtnMoverCriaR;
    }

    public JButton getBtnMarcarSanas() {
        return BtnMarcarSanas;
    }

    public JButton getBtnSacrificar() {
        return BtnSacrificar;
    }

    public JButton getBtnMarcarC40() {
        return BtnMarcarC40;
    }

    public JButton getBtnMarcarEnRiesgo() { return BtnMarcarEnRiesgo; }

    public void mostrarModal(String msg){
        if(msg.equals(""))
            msg="La cuarentena fue agregada correctamente";
        JOptionPane.showMessageDialog(this,msg,"Cuidado de crías",msg.equals("La cuarentena fue agregada correctamente")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarModalSac(String msg){
        if(msg.equals(""))
            msg="La cría fue sacrificada correctamente";
        JOptionPane.showMessageDialog(this,msg,"Cuidado de crías",msg.equals("La cría fue sacrificada correctamente")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarModalAlt(String msg){
        if(msg.equals(""))
            msg="La cría fue dada de alta correctamente";
        JOptionPane.showMessageDialog(this,msg,"Cuidado de crías",msg.equals("La cría fue dada de alta correctamente")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
    }

    public int mostrarAdvertencia(String msg,String title){
        int band=JOptionPane.showConfirmDialog(this,msg,title,JOptionPane.YES_NO_OPTION);
        return band;
    }

    private void defineInterfaz(){
        TPane=new JTabbedPane();
        FontCajas = new Font("Dubai", 0, 13);
        FontTitulos = new Font("Candara", 1, 13);
        TPane.setFont(new Font("Candara",1,17));
        add(TPane);

        crearPEvaluar();

        crearPAgregar();

        crearPSacar();
    }

    public void llenarCriasG2(ArrayList<String[]> datos){
        ColorJTable colorRenderer = new ColorJTable(new ArrayList<Integer>());
        colorRenderer.band=false;
        TbCrG2.setDefaultRenderer(Object.class, colorRenderer);
        datosEG2=datos;
        String [] col1={"Id","Temperatura Actual","Corral"};
        DmCrG2=new DefaultTableModel(null,col1);
        TbCrG2.setModel(DmCrG2);
        for(String[] tupla:datosEG2)
            DmCrG2.addRow(tupla);
        SPCrG2.updateUI();
    }

    public void marcarCriasRiesgo(ArrayList<Integer> num){
        ColorJTable colorRenderer = new ColorJTable(num);
        colorRenderer.band=true;
        TbCrG2.setDefaultRenderer(Object.class, colorRenderer);
        SPCrG2.updateUI();
    }

    public void llenarCriasEn(ArrayList<String[]> datos){
        String[] col={"Id","Corral"};
        DmCrEn=new DefaultTableModel(null,col);
        TbCrEn.setModel(DmCrEn);
        for(String[] tupla:datos)
            DmCrEn.addRow(tupla);
        SPCrEn.updateUI();
    }

    public void llenarCriasSa(ArrayList<String[]> datos){
        ColorJTable colorRenderer = new ColorJTable(new ArrayList<Integer>());
        colorRenderer.band=false;
        TbCrSa.setDefaultRenderer(Object.class, colorRenderer);
        datosSA=datos;
        String [] col1={"Id","Temperatura","Corral","Días"};
        DmCrSa=new DefaultTableModel(null,col1);
        TbCrSa.setModel(DmCrSa);
        for(String[] tupla:datosSA)
            DmCrSa.addRow(tupla);
        SPCrSa.updateUI();
    }

    public void marcarCriasAliviadas(ArrayList<Integer> num){
        ColorJTable colorRenderer = new ColorJTable(num);
        colorRenderer.band=true;
        TbCrSa.setDefaultRenderer(Object.class, colorRenderer);
        SPCrSa.updateUI();
    }

    public void llenarCorralesEn(ArrayList<String[]> datos){
        String[] col={"Id","# de crías"};
        DmCoEn=new DefaultTableModel(null,col);
        TbCoEn.setModel(DmCoEn);
        for(String[] tupla:datos)
            DmCoEn.addRow(tupla);
        SPCoEn.updateUI();
    }

    public String getCria1(){
        return TxtCria1.getText();
    }

    public String getCorral1(){
        return TxtCorral1.getText();
    }

    public String getCria2(){
        return TxtCria2.getText();
    }

    public ArrayList<String[]> getDatosEG2() { return datosEG2; }

    public ArrayList<String[]> getDatosSA() {
        return datosSA;
    }

    public void modalCarga(){
        jd=new JDialog();
        jd.setLayout(new BorderLayout());
        jd.add(new JLabel(Rutinas.AjustarImagen("src/image/cargando.gif",100,100)));
    }

    public void cerrarDialog(){
        jd.dispose();
    }

    public Timer getT() { return t; }

    public Timer getTe() {
        return te;
    }

    public JTable getTbCoEn() { return TbCoEn;}

    public JTable getTbCrEn() {return TbCrEn;}

    public JTable getTbCrSa() {
        return TbCrSa;
    }

    private void crearPEvaluar(){
        PEvaluar=new JPanel();
        PEvaluar.setLayout(null);

        String [][] m1={};
        String [] col1={"Id","Temperatura Actual","Corral"};

        DmCrG2=new DefaultTableModel(m1,col1);
        TbCrG2=new JTable(DmCrG2);
        SPCrG2=new JScrollPane(TbCrG2);
        SPCrG2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Crías con sensor sanas", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPCrG2.setBounds(5,5,475,280);
        PEvaluar.add(SPCrG2);

        BtnMarcarEnRiesgo=new JButton("Marcar crías en riesgo de enfermarse");
        BtnMarcarEnRiesgo.setFont(new Font("Candara",1,15));
        BtnMarcarEnRiesgo.setBounds(5,290,475,35);
        PEvaluar.add(BtnMarcarEnRiesgo);

        TPane.add(PEvaluar,"Evaluar crías");
    }

    private void crearPAgregar(){
        PAgregar=new JPanel();
        PAgregar.setLayout(null);

        String [][] m={};
        String [] columnas={"Id","Corral"};
        DmCrEn=new DefaultTableModel(m,columnas);
        TbCrEn=new JTable(DmCrEn);
        SPCrEn=new JScrollPane(TbCrEn);
        SPCrEn.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Seleccione cría a mover", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPCrEn.setBounds(40,5,200,275);
        PAgregar.add(SPCrEn);

        String[] columnas2={"Id","# de crías"};
        DmCoEn=new DefaultTableModel(m,columnas2);
        TbCoEn=new JTable(DmCoEn);
        SPCoEn=new JScrollPane(TbCoEn);
        SPCoEn.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Seleccione el corral de destino", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPCoEn.setBounds(250,5,200,275);
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
        TxtCria1.setEnabled(false);
        P1.add(TxtCria1);

        TxtCorral1=new JNumberField();
        TxtCorral1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Corral_id", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtCorral1.setFont(FontCajas);
        TxtCorral1.setBackground(new Color(242,242,242));
        TxtCorral1.setEnabled(false);
        P1.add(TxtCorral1);

        BtnMoverCriaA=new JButton("Mover");
        BtnMoverCriaA.setFont(new Font("Candara",1,15));
        P1.add(BtnMoverCriaA);

        P1.setBounds(5,285,770,42);
        PAgregar.add(P1);

        TPane.add(PAgregar,"Mover a cuarentena");
    }

    private void crearPSacar(){
        PSacar=new JPanel();
        PSacar.setLayout(null);

        String[] col={"Id","Temperatura","Corral","Días"};
        DmCrSa=new DefaultTableModel(null,col);
        TbCrSa=new JTable(DmCrSa);
        SPCrSa=new JScrollPane(TbCrSa);
        SPCrSa.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Crías en cuarentena", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPCrSa.setBounds(5,5,475,230);
        PSacar.add(SPCrSa);

        BtnMarcarSanas=new JButton("Marcar crías recuperadas");
        BtnMarcarSanas.setBounds(5,240,235,40);
        BtnMarcarSanas.setFont(new Font("Candara",1,15));
        PSacar.add(BtnMarcarSanas);

        BtnMarcarC40=new JButton("Marcar crías con 40+ días");
        BtnMarcarC40.setBounds(245,240,235,40);
        BtnMarcarC40.setFont(new Font("Candara",1,15));
        PSacar.add(BtnMarcarC40);

        TxtCria2=new JNumberField();
        TxtCria2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Cria id", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtCria2.setBounds(5,285,120,40);
        TxtCria2.setFont(FontCajas);
        TxtCria2.setEnabled(false);
        PSacar.add(TxtCria2);

        BtnMoverCriaR=new JButton("Terminar cuarentena");
        BtnMoverCriaR.setBounds(140,285,160,40);
        BtnMoverCriaR.setFont(new Font("Candara",1,15));
        PSacar.add(BtnMoverCriaR);

        BtnSacrificar=new JButton("Sacrificar");
        BtnSacrificar.setBounds(310,285,160,40);
        BtnSacrificar.setFont(new Font("Candara",1,15));
        PSacar.add(BtnSacrificar);

        TPane.add(PSacar,"En cuarentena");
    }
}
