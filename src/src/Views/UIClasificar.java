package Views;

import Controllers.CClasificar;
import Resource.JNumberField;
import Resource.Rutinas;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UIClasificar extends JDialog {
    private PanelFondo PPrincipal;
    private JButton BtnClasificar,BtnEditar,BtnBuscar;
    private JPanel PCajas,PBusqueda;
    private JScrollPane SPTabla;
    private JTable TbCrias;
    private DefaultTableModel Dm;
    private Font FontCajas;
    private Font FontTitulos;
    private JNumberField TxtId,TxtPeso,TxtGrasa;
    private JTextField TxtBusqueda;
    private JComboBox<String> ComboBusqueda;
    private ArrayList<String[]> datos;

    public UIClasificar(){
        setTitle("Clasificar");
        setModal(true);
        setSize(520,420);
        setLocationRelativeTo(null);
        setResizable(false);
        defineInterfaz();
    }

    public JButton getBtnBuscar() {
        return BtnBuscar;
    }

    public String getBusqueda(){
        return TxtBusqueda.getText();
    }

    public JButton getBtnClasificar() {
        return BtnClasificar;
    }

    public JTable getTbCrias() {
        return TbCrias;
    }

    public void editarCria(int row) {
        TxtId.setText((String) TbCrias.getValueAt(row, 0));
        TxtPeso.setText((String) TbCrias.getValueAt(row, 1));
        TxtPeso.setEnabled(true);
        TxtGrasa.setText((String) TbCrias.getValueAt(row, 3));
        TxtGrasa.setEnabled(true);
        BtnEditar.setEnabled(true);
    }

    public void asignarControladores(CClasificar C){
        BtnClasificar.addActionListener(C);
        TbCrias.addMouseListener(C);
        BtnEditar.addActionListener(C);
        BtnBuscar.addActionListener(C);
    }

    public int getCriterio(){
        return ComboBusqueda.getSelectedIndex();
    }

    public ArrayList<String[]> getDatos() {
        return datos;
    }

    public void asignarArreglo(ArrayList<String[]> a){
        datos=a;
        llenarTabla();
    }

    public void llenarTabla(ArrayList<String[]> datos){
        String [][] m={};
        String [] columnas={"Id","Peso (kg)","Color de músculo","% de grasa","Clasificación"};
        Dm=new DefaultTableModel(m,columnas);
        TbCrias.setModel(Dm);
        TbCrias.getColumn("Id").setPreferredWidth(10);
        TbCrias.getColumn("Clasificación").setPreferredWidth(125);
        for(int i=0 ; i<datos.size() ; i++)
            Dm.addRow(datos.get(i));
        SPTabla.updateUI();
    }

    private void llenarTabla(){
        llenarTabla(datos);
    }

    public void mostrarModal(ArrayList<String> al){
        boolean band=true;
        String msg="";
        if(al.size()==0){
            msg="Se actualizaron correctamente las clasificaciones";
            band=false;
        }else {
            for (String msg2:al)
                JOptionPane.showMessageDialog(this,msg2,"Clasificar",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,msg,"Clasificar",JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarModal(String msg){
        if(msg==null)
            msg="La cría fue actualizada correctamente";
        JOptionPane.showMessageDialog(this,msg,"Clasificar",msg.equals("La cría fue actualizada correctamente")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
    }

    public JButton getBtnEditar() {
        return BtnEditar;
    }

    public JNumberField getTxtPeso() {
        return TxtPeso;
    }

    public JNumberField getTxtGrasa() {
        return TxtGrasa;
    }

    public JNumberField getTxtId() {
        return TxtId;
    }

    public String getId() {
        return TxtId.getText();
    }

    public String getGrasa() {
        return TxtGrasa.ObtenerCantidad()+"";
    }

    public String getPeso() {
        return TxtPeso.ObtenerCantidad()+"";
    }

    private void defineInterfaz(){
        PPrincipal=new PanelFondo("fondoClasificar.jpg",520,420);
        PPrincipal.setLayout(null);

        FontCajas=new Font("Cambria",0,13);
        FontTitulos=new Font("Candara",1,14);

        PBusqueda=new JPanel();
        PBusqueda.setLayout(new GridLayout(0,3));
        PBusqueda.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Filtrar busqueda", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));

        TxtBusqueda =new JTextField();
        TxtBusqueda.setFont(FontCajas);
        PBusqueda.add(TxtBusqueda);

        String[] a={"Id","Clasificacion"};
        ComboBusqueda=new JComboBox<>(a);
        ComboBusqueda.setFont(FontCajas);
        PBusqueda.add(ComboBusqueda);

        BtnBuscar=new JButton("Filtrar");
        BtnBuscar.setFont(new Font("Candara",1,15));
        BtnBuscar.setIcon(Rutinas.AjustarImagen("src/src/images/filtrar.png",17,17));
        PBusqueda.add(BtnBuscar);

        PBusqueda.setBounds(5,5,500,50);
        PBusqueda.setOpaque(false);
        PPrincipal.add(PBusqueda);

        String [][] m={};
        String [] columnas={"Id","Peso (kg)","Color de músculo","% de grasa","Clasificación"};
        Dm=new DefaultTableModel(m,columnas);
        TbCrias =new JTable(Dm);
        TbCrias.setFont(new Font("Cambria",1,12));
        TbCrias.setEnabled(false);
        SPTabla=new JScrollPane(TbCrias);
        SPTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Crías", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        SPTabla.setBounds(5,65,500,200);
        SPTabla.setBackground(new Color(242,242,242));
        SPTabla.setOpaque(false);
        PPrincipal.add(SPTabla);

        PCajas=new JPanel();
        PCajas.setLayout(new GridLayout(0,4,10,0));

        TxtId=new JNumberField();
        TxtId.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Id", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtId.setFont(FontCajas);
        TxtId.setEnabled(false);
        PCajas.add(TxtId);

        TxtPeso=new JNumberField();
        TxtPeso.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Peso (kg)", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtPeso.setFont(FontCajas);
        TxtPeso.setEnabled(false);
        PCajas.add(TxtPeso);

        TxtGrasa=new JNumberField();
        TxtGrasa.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Grasa", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtGrasa.setFont(FontCajas);
        TxtGrasa.setEnabled(false);
        PCajas.add(TxtGrasa);

        BtnEditar=new JButton("Editar");
        BtnEditar.setFont(new Font("Candara",1,15));
        BtnEditar.setEnabled(false);
        PCajas.add(BtnEditar);

        PCajas.setBounds(5,270,500,68);
        PCajas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Editar datos", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        PCajas.setOpaque(false);
        PPrincipal.add(PCajas);

        BtnClasificar=new JButton("Actualizar clasificaciones");
        BtnClasificar.setFont(new Font("Candara",1,15));
        BtnClasificar.setBounds(5,345,500,40);
        PPrincipal.add(BtnClasificar);

        add(PPrincipal);
    }
}
