package Views;

import Controllers.CInicio;

import javax.swing.*;
import java.awt.*;

public class UIPrincipal extends JFrame {

    private JPanel PPrincipal;
    private JButton BtnRegistrarCria,BtnRegistrarCorral;
    private Font FontBotones;

    public UIPrincipal (){
        super("Corrales Ternero");
        setSize(550,405);
        setResizable(false);
        setContentPane(PPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        FontBotones=new Font("Candara",1,17);
    }

    public void asignarControladorP(CInicio c){
        BtnRegistrarCria.addActionListener(c);
        BtnRegistrarCorral.addActionListener(c);
    }

    public JButton getBtnRegistrarCria() {
        return BtnRegistrarCria;
    }

    public JButton getBtnRegistrarCorral() {
        return BtnRegistrarCorral;
    }

    private void createUIComponents() {
       PPrincipal=new JPanel();
       PPrincipal.setLayout(null);
       definePPrincipal();
    }
    private void definePPrincipal(){
        BtnRegistrarCria=new JButton("Registrar Cría");
        BtnRegistrarCria.setBounds(20,40,130,40);
        BtnRegistrarCria.setFont(FontBotones);
        PPrincipal.add(BtnRegistrarCria);

        BtnRegistrarCorral=new JButton("Registrar Corral");
        BtnRegistrarCorral.setBounds(20,90,130,40);
        BtnRegistrarCria.setFont(FontBotones);
        PPrincipal.add(BtnRegistrarCorral);
    }

}
