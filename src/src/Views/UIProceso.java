package Views;

import Controllers.CProceso;
import Resource.JNumberField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class UIProceso extends JDialog {

    private PanelFondo PPrincipal;
    private DefaultTableModel Dm;
    private JTable Tb;
    private JScrollPane Sp;
    private JButton BtnMarcar,BtnSalida;
    private JNumberField TxtId;
    private Font FontCajas;
    private Font FontTitulos;
    private Font FontBotones;
    private ArrayList<String[]> datos;

    public UIProceso(){
        setTitle("Procesar");
        setResizable(false);
        setSize(500,350);
        setLocationRelativeTo(null);
        defineInterfaz();
    }

    public void asignarEscuchador(CProceso c){
        BtnSalida.addActionListener(c);
        BtnMarcar.addActionListener(c);
        Tb.addMouseListener(c);
    }

    public void asignarDatos(ArrayList<String[]> d){
        datos=d;
        llenarTabla();
    }

    public void marcarCrias(ArrayList<Integer> sel){
        ColorJTable2 colorRenderer = new ColorJTable2(sel);
        colorRenderer.band=true;
        Tb.setDefaultRenderer(Object.class, colorRenderer);
        Sp.updateUI();
    }

    public void mostrarModal(String msg){
        if(msg.equals(""))
            msg="La cría fue enviada al siguiente proceso";
        JOptionPane.showMessageDialog(this,msg,"Proceso de crías",msg.equals("La cría fue enviada al siguiente proceso")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
    }

    public JNumberField getTxtId() {
        return TxtId;
    }

    public String getId(){
        return TxtId.ObtenerCantidad()+"";
    }

    public ArrayList<String[]> getDatos() {
        return datos;
    }

    public JTable getTb() {
        return Tb;
    }

    public JButton getBtnMarcar() {
        return BtnMarcar;
    }

    public JButton getBtnSalida() {
        return BtnSalida;
    }

    public void seleccionarCria(int row){
        TxtId.setText((String) Tb.getValueAt(row,0));
    }

    private void llenarTabla(){
        String[] col={"Id","Clasificación","Corral","Estado Actual","Días en el proceso"};
        Dm=new DefaultTableModel(null,col);
        Tb.setModel(Dm);
        for (String[] dato : datos)
            Dm.addRow(dato);
    }

    private void defineInterfaz(){
        PPrincipal=new PanelFondo("fondoProceso.jpg",500,350);
        PPrincipal.setLayout(null);

        FontCajas=new Font("Cambria",0,14);
        FontTitulos=new Font("Candara",1,13);
        FontBotones=new Font("Candara",1,15);

        String[] col={"Id","Clasificación","Corral","Estado Actual","Días en el proceso"};
        Dm=new DefaultTableModel(null,col);
        Tb=new JTable(Dm);
        Tb.setFont(new Font("Cambria",1,13));
        TableColumn col1=Tb.getColumn("Id");
        col1.setPreferredWidth(15);
        TableColumn col2=Tb.getColumn("Corral");
        col2.setPreferredWidth(15);
        TableColumn col3=Tb.getColumn("Clasificación");
        col3.setPreferredWidth(90);
        Sp=new JScrollPane(Tb);
        Sp.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Crías", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        Sp.setBounds(5,5,482,225);
        Sp.setOpaque(false);
        PPrincipal.add(Sp);

        BtnMarcar=new JButton("Marcar crías con 150+ días");
        BtnMarcar.setFont(FontBotones);
        BtnMarcar.setBounds(5,230,480,35);
        PPrincipal.add(BtnMarcar);

        TxtId=new JNumberField();
        TxtId.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Cría", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        TxtId.setBounds(5,270,130,40);
        TxtId.setOpaque(false);
        TxtId.setFont(FontCajas);
        TxtId.setForeground(Color.WHITE);
        PPrincipal.add(TxtId);

        BtnSalida=new JButton("Dar salida al siguiente proceso");
        BtnSalida.setFont(FontBotones);
        BtnSalida.setBounds(140,275,340,35);
        PPrincipal.add(BtnSalida);

        add(PPrincipal);
    }


}
