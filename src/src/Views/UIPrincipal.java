package Views;

import Controllers.CVPrincipal;

import javax.swing.*;

public class UIPrincipal extends JFrame {

    private JPanel PPrincipal;
    private JTabbedPane TPCentro;
    private JPanel PInicio;
    private JPanel PRegistro;
    private JButton BtnRegistrar;

    public UIPrincipal (){
        super("Corrales Ternero");
        setSize(550,350);
        setResizable(false);
        setContentPane(PPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void asignarControladorP(CVPrincipal c){
        BtnRegistrar.addActionListener(c);
    }

    private void createUIComponents() {
       PInicio=new JPanel();
       PInicio.setLayout(null);
       definePRegistro();

    }

    private void definePRegistro(){
        PRegistro=new JPanel();
        PRegistro.setLayout(null);
        JLabel lb = new JLabel("Id");
        lb.setBounds(5,5,40,20);
        PRegistro.add(lb);

        BtnRegistrar=new JButton("Registrar");
        BtnRegistrar.setBounds(5,180,100,30);
        PRegistro.add(BtnRegistrar);
    }
}
