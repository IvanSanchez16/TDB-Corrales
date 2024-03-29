package Views;

import Controllers.CDietas;
import Resource.JNumberField;
import Resource.Rutinas;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class UIDietas extends JDialog {

    private PanelFondo PPrincipal;
    private JPanel PBusqueda;
    private DefaultTableModel Dm;
    private JTable Tb;
    private JScrollPane Sp;
    private JNumberField TxtCria;
    private JTextField TxtBusqueda,txtDesc;
    private JComboBox<String> CbDietas,CbBusqueda,comboBox;
    private JButton BtnAceptar,BtnBuscar,BtnAgregar;
    private Font FontCajas;
    private Font FontTitulos;
    private ArrayList<String[]> datos;
    private JDialog jd;

    public UIDietas(){
        setTitle("Dietas");
        setModal(true);
        setSize(550,425);
        setLocationRelativeTo(null);
        setResizable(false);
        defineInterfaz();
    }

    public void asignarEscuchador(CDietas c){
        BtnAceptar.addActionListener(c);
        Tb.addMouseListener(c);
        BtnBuscar.addActionListener(c);
        BtnAgregar.addActionListener(c);
    }

    public void setDatos(ArrayList<String[]> d){
        datos=d;
        llenarTabla();
    }

    public void llenarTabla(ArrayList<String[]> datos){
        String[] col={"Id","Dieta actual","Peso","Grasa","Días en el proceso"};
        Dm=new DefaultTableModel(null,col);
        Tb.setModel(Dm);
        TableColumn colDias=Tb.getColumn("Días en el proceso");
        colDias.setPreferredWidth(120);
        TableColumn colDieta=Tb.getColumn("Dieta actual");
        colDieta.setPreferredWidth(130);
        for(String[] dato:datos)
            Dm.addRow(dato);
        Sp.updateUI();
    }

    public void llenarTabla(){
        String[] col={"Id","Dieta actual","Peso","Grasa","Días en el proceso"};
        Dm=new DefaultTableModel(null,col);
        Tb.setModel(Dm);
        TableColumn colDias=Tb.getColumn("Días en el proceso");
        colDias.setPreferredWidth(120);
        TableColumn colDieta=Tb.getColumn("Dieta actual");
        colDieta.setPreferredWidth(130);
        for(String[] dato:datos)
            Dm.addRow(dato);
        Sp.updateUI();
    }

    public void mostrarModal(String msg){
        if(msg.equals("Error"))
            msg="Error al actualizar la dieta";
        else
            msg="La dieta fue actualizada correctamente";
        JOptionPane.showMessageDialog(this,msg,"Dietas",msg.equals("La dieta fue actualizada correctamente")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarModal2(String msg){
        JOptionPane.showMessageDialog(this,msg,"Agregar dieta",JOptionPane.ERROR_MESSAGE);
    }

    public void llenarCBDietas(ArrayList<String> a){
        CbDietas.removeAllItems();
        for(String dato:a)
            CbDietas.addItem(dato);
    }

    public JButton getBtnBuscar() {
        return BtnBuscar;
    }

    public JButton getBtnAceptar() {
        return BtnAceptar;
    }

    public JTable getTb() {
        return Tb;
    }

    public ArrayList<String[]> getDatos() {
        return datos;
    }

    public void seleccionarCria(int row){
        TxtCria.setText((String) Tb.getValueAt(row,0));
    }

    public String getCbBusqueda(){
        return (String) CbBusqueda.getSelectedItem();
    }

    public int getTxtCria() {
        return (int) TxtCria.ObtenerCantidad();
    }

    public JButton getBtnAgregar() {
        return BtnAgregar;
    }

    public String getTxtBusqueda() {
        return TxtBusqueda.getText();
    }

    public String getCBDietas(){
        return (String) CbDietas.getSelectedItem();
    }

    public JDialog getJd() {
        return jd;
    }

    public void crearDieta(CDietas c){
        jd=new JDialog();
        jd.setTitle("Crear una dieta");
        jd.setSize(200,165);
        jd.setModal(true);
        jd.setResizable(false);
        jd.setLocationRelativeTo(null);

        PanelFondo pf=new PanelFondo("fondoDietas.jpg",200,165);
        pf.setLayout(new BorderLayout());

        txtDesc=new JTextField();
        txtDesc.setFont(FontCajas);
        txtDesc.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Descripción", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        txtDesc.setForeground(Color.white);
        txtDesc.setOpaque(false);
        pf.add(txtDesc,BorderLayout.NORTH);

        String[] d={"Para sanas","Para enfermas"};
        comboBox=new JComboBox<>(d);
        comboBox.setFont(FontCajas);
        comboBox.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Tipo", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        pf.add(comboBox,BorderLayout.CENTER);
        comboBox.setOpaque(false);

        JButton btnAgg=new JButton("Añadir dieta");
        btnAgg.setFont(new Font("Candara",1,15));
        btnAgg.addActionListener(c);
        pf.add(btnAgg,BorderLayout.SOUTH);

        jd.add(pf);
        jd.setVisible(true);
    }

    public String getTxtDesc() {
        return txtDesc.getText();
    }

    public String getTipo(){
        return comboBox.getSelectedIndex()+"";
    }

    private void defineInterfaz(){
        PPrincipal=new PanelFondo("fondoDietas.jpg",550,425);
        PPrincipal.setLayout(null);
        FontCajas = new Font("Cambria", 0, 14);
        FontTitulos = new Font("Candara", 1, 14);

        PBusqueda=new JPanel();
        PBusqueda.setLayout(new GridLayout(0,3));
        PBusqueda.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Filtrar busqueda", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        PBusqueda.setOpaque(false);

        TxtBusqueda =new JTextField();
        TxtBusqueda.setFont(FontCajas);
        PBusqueda.add(TxtBusqueda);

        String[] a={"Id","Dieta actual",">= Peso","< Peso",">= Grasa","< Grasa"};
        CbBusqueda =new JComboBox<>(a);
        CbBusqueda.setFont(FontCajas);
        PBusqueda.add(CbBusqueda);

        BtnBuscar=new JButton("Filtrar");
        BtnBuscar.setFont(new Font("Candara",1,15));
        BtnBuscar.setIcon(Rutinas.AjustarImagen("src/src/images/filtrar.png",17,17));
        PBusqueda.add(BtnBuscar);

        PBusqueda.setBounds(5,5,530,50);
        PPrincipal.add(PBusqueda);

        String[] col={"Id","Dieta actual","Peso","Grasa","Días en el proceso"};
        Dm=new DefaultTableModel(null,col);
        Tb=new JTable(Dm);
        TableColumn colDias=Tb.getColumn("Días en el proceso");
        colDias.setPreferredWidth(120);
        TableColumn colDieta=Tb.getColumn("Dieta actual");
        colDieta.setPreferredWidth(130);
        Tb.setFont(new Font("Cambria",1,13));
        Sp=new JScrollPane(Tb);
        Sp.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Dieta por cría", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        Sp.setBounds(5,60,530,275);
        Sp.setOpaque(false);
        PPrincipal.add(Sp);

        TxtCria=new JNumberField();
        TxtCria.setFont(FontCajas);
        TxtCria.setBounds(5,340,120,45);
        TxtCria.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Cria id", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        TxtCria.setOpaque(false);
        TxtCria.setFont(FontCajas);
        TxtCria.setForeground(Color.WHITE);
        PPrincipal.add(TxtCria);

        CbDietas=new JComboBox<>();
        CbDietas.setFont(FontCajas);
        CbDietas.setBounds(130,340,145,45);
        CbDietas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Nueva dieta", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        CbDietas.setOpaque(false);
        CbDietas.setFont(FontCajas);
        PPrincipal.add(CbDietas);

        BtnAceptar=new JButton("Cambiar dieta");
        BtnAceptar.setFont(new Font("Candara",1,15));
        BtnAceptar.setBounds(280,340,125,45);
        PPrincipal.add(BtnAceptar);

        BtnAgregar=new JButton("Agregar Dieta");
        BtnAgregar.setFont(new Font("Candara",1,15));
        BtnAgregar.setBounds(410,340,125,45);
        PPrincipal.add(BtnAgregar);

        add(PPrincipal);
    }
}
