package Views;

import Controllers.CCorral;
import Resource.JNumberField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class UIRegistroCorral extends JFrame {

    private JNumberField TxtId;
    private JComboBox<String> ComboTipo;
    private Font FontCajas;
    private Font FontTitulos;
    private JButton BtnRegistrar;

    public UIRegistroCorral(){
        super("Registrar corral");
        setSize(200,175);
        setResizable(false);
        setLocationRelativeTo(null);
        definirInterfaz();
        setVisible(true);
    }
    public void asignarControlador(CCorral c){
        BtnRegistrar.addActionListener(c);
    }

    public void setTextId(String id){
        TxtId.setText(id);
    }

    public int getTxtId() {
        return (int) TxtId.ObtenerCantidad();
    }

    public void mostrarModal(String msg){
        boolean band=true;
        if(msg.equals("")){
            msg="El corral fue registrado correctamente";
            band=false;
        }
        JOptionPane.showMessageDialog(this,msg,"Registro de corral",msg.equals("El corral fue registrado correctamente")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
        if(!band)
            dispose();
    }

    public char getComboTipo() {
        String tipo= (String) ComboTipo.getSelectedItem();
        return tipo.charAt(0);
    }

    public JButton getBtnRegistrar() {
        return BtnRegistrar;
    }

    private void definirInterfaz(){
        setLayout(null);

        FontCajas=new Font("Dubai",0,13);
        FontTitulos=new Font("Candara",1,13);

        TxtId = new JNumberField();
        TxtId.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"ID", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtId.setBackground(new Color(242,242,242));
        TxtId.setBounds(5,5,100,40);
        TxtId.setEnabled(false);
        TxtId.setFont(FontCajas);
        add(TxtId);

        String[] a={"Normal","Cuarentena"};
        ComboTipo = new JComboBox<String>(a);
        ComboTipo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Tipo", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        ComboTipo.setBackground(new Color(242,242,242));
        ComboTipo.setBounds(5,50,120,45);
        add(ComboTipo);

        BtnRegistrar=new JButton("Registrar");
        BtnRegistrar.setFont(FontTitulos);
        BtnRegistrar.setBounds(5,100,180,35);
        add(BtnRegistrar);
    }
}
