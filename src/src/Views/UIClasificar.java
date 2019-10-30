package Views;

import Controllers.CClasificar;
import Resource.JNumberField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UIClasificar extends JDialog {
    private JButton BtnClasificar;
    private JPanel PCajas;
    private JScrollPane SPTabla;
    private JTable TbCrias;
    private DefaultTableModel Dm;
    private Font FontCajas;
    private Font FontTitulos;
    private JNumberField TxtId,TxtPeso,TxtGrasa;

    public UIClasificar(){
        setTitle("Clasificar");
        setModal(true);
        setSize(520,360);
        setLocationRelativeTo(null);
        setResizable(false);
        defineInterfaz();
    }

    public JButton getBtnClasificar() {
        return BtnClasificar;
    }

    public void asignarControladores(CClasificar C){
        BtnClasificar.addActionListener(C);
        TbCrias.addMouseListener(C);
    }

    private void defineInterfaz(){
        setLayout(null);

        FontCajas=new Font("Dubai",0,13);
        FontTitulos=new Font("Candara",1,13);

        String [][] m={};
        String [] columnas={"Id","Peso (kg)","Color de músculo","% de grasa","Clasificación"};
        Dm=new DefaultTableModel(m,columnas);
        TbCrias =new JTable(Dm);
        TbCrias.setFont(new Font("Dubai",1,12));
        TbCrias.setEnabled(false);
        SPTabla=new JScrollPane(TbCrias);
        SPTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Crías", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPTabla.setBounds(5,5,500,200);
        SPTabla.setBackground(new Color(242,242,242));
        add(SPTabla);

        PCajas=new JPanel();
        PCajas.setLayout(new GridLayout(0,3,10,0));

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

        PCajas.setBounds(5,210,500,65);
        PCajas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Editar datos", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        add(PCajas);

        BtnClasificar=new JButton("Actualizar clasificaciones");
        BtnClasificar.setFont(new Font("Candara",1,15));
        BtnClasificar.setBounds(5,285,500,40);
        add(BtnClasificar);
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
        setVisible(true);
    }

    public void mostrarModal(ArrayList<String> al){
        boolean band=true;
        String msg="";
        if(al.size()==0){
            msg="Se actualizaron correctamente las clasificaciones";
            band=false;
        }else {
            for (String msg2:al)
                JOptionPane.showMessageDialog(this,msg2,"Registro de corral",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,msg,"Registro de corral",JOptionPane.INFORMATION_MESSAGE);
        if(!band)
            dispose();
    }
}