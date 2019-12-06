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
    private JButton BtnClasificar,BtnBuscar;
    private JPanel PBusqueda;
    private JScrollPane SPTabla;
    private JTable TbCrias;
    private DefaultTableModel Dm;
    private Font FontCajas;
    private Font FontTitulos;
    private JTextField TxtBusqueda;
    private JComboBox<String> ComboBusqueda;
    private ArrayList<String[]> datos;
    private ArrayList<String> sensores;
    private JTable tbSensores;
    private JDialog jd;
    private int c;

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

    public void asignarControladores(CClasificar C){
        BtnClasificar.addActionListener(C);
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
        TbCrias.getColumn("Color de músculo").setPreferredWidth(125);
        for(int i=0 ; i<datos.size() ; i++)
            Dm.addRow(datos.get(i));
        SPTabla.updateUI();
    }

    private void llenarTabla(){
        llenarTabla(datos);
    }

    public void mostrarModal(ArrayList<String> al){
        String msg="";
        if(al.size()==0){
            msg="Se actualizaron correctamente las clasificaciones";
            JOptionPane.showMessageDialog(this,msg,"Clasificar",JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(this,"Error al clasificar las crías","Clasificar",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void asignarSensores(ArrayList<String> sensores,ArrayList<Integer> crias,CClasificar c){
        this.sensores=sensores;
        for (Integer cria : crias) {
            this.c=cria;
            if(sensores.size()==0){
                JOptionPane.showMessageDialog(this,"No existen sensores disponibles para la cría: "+cria+", asignarle sensor en cuanto se tengan","Sensores agotados",JOptionPane.INFORMATION_MESSAGE);
                continue;
            }
            jd=crearDialog(cria,c);
            jd.setVisible(true);
            while(jd.isVisible());
        }
    }

    public JDialog getJd() {
        return jd;
    }

    public JTable getTbSensores() {
        return tbSensores;
    }

    public int getC() {
        return c;
    }

    public void quitarSensor(String sensor){
        sensores.remove(sensor);
    }

    private JDialog crearDialog(int cria,CClasificar c){
        JDialog jd=new JDialog();
        jd.setTitle("Asignar sensores");
        jd.setSize(250,200);
        jd.setModal(true);
        jd.setResizable(false);
        jd.setLocationRelativeTo(null);

        String[] col={"Id"};
        DefaultTableModel dm=new DefaultTableModel(null,col );
        tbSensores=new JTable(dm);
        tbSensores.addMouseListener(c);
        tbSensores.setFont(new Font("Cambria",1,12));
        JScrollPane sp=new JScrollPane(tbSensores);
        sp.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Seleccione un sensor para la cría: "+cria,
                TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        jd.add(sp);

        for (String sensor : sensores) {
            String[] row={sensor};
            dm.addRow(row);
        }
        return jd;
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
        SPTabla.setBounds(5,65,500,275);
        SPTabla.setBackground(new Color(242,242,242));
        SPTabla.setOpaque(false);
        PPrincipal.add(SPTabla);

        BtnClasificar=new JButton("Actualizar clasificaciones");
        BtnClasificar.setFont(new Font("Candara",1,15));
        BtnClasificar.setBounds(5,345,500,40);
        PPrincipal.add(BtnClasificar);

        add(PPrincipal);
    }
}
