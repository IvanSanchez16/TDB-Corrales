package Views;

import Controllers.CCorral;
import Resource.JNumberField;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class UIRegistroCorral extends JDialog {

    private PanelFondo PPrincipal;
    private JComboBox<String> ComboTipo;
    private Font FontCajas;
    private Font FontTitulos;
    private JButton BtnRegistrar;

    public UIRegistroCorral(){
        setTitle("Registrar corral");
        setModal(true);
        setSize(200,175);
        setResizable(false);
        setLocationRelativeTo(null);
        definirInterfaz();
    }

    public void asignarControlador(CCorral c){
        BtnRegistrar.addActionListener(c);
    }

    public void mostrarModal(String msg){
        boolean band=false;
        if(msg.equals("Error")){
            msg="Error al registrar el corral";
            JOptionPane.showMessageDialog(this,msg,"Registro de corral",JOptionPane.ERROR_MESSAGE);
            band=true;
        }else {
            JOptionPane.showMessageDialog(this,"Corral registrado con éxito, ID= "+msg, "Registro de corral", JOptionPane.INFORMATION_MESSAGE);
        }
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

        PPrincipal=new PanelFondo("fondoCorrales.jpg",200,175);
        PPrincipal.setLayout(null);

        FontCajas=new Font("Dubai",0,13);
        FontTitulos=new Font("Candara",1,14);

        String[] a={"Normal","Cuarentena"};
        ComboTipo = new JComboBox<String>(a);
        ComboTipo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Tipo", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        ComboTipo.setBackground(new Color(242,242,242));
        ComboTipo.setBounds(35,25,120,45);
        ComboTipo.setOpaque(false);
        PPrincipal.add(ComboTipo);

        BtnRegistrar=new JButton("Registrar");
        BtnRegistrar.setFont(FontTitulos);
        BtnRegistrar.setBounds(5,80,180,35);
        PPrincipal.add(BtnRegistrar);

        add(PPrincipal);
    }
}
