package Views;

import Controllers.CInicio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class UIPrincipal extends JFrame {

    private Font FontTitulos;
    private JPanel PPrincipal,PManipulacion,PConsultas;
    private JButton BtnRegistrarCria,BtnRegistrarCorral,BtnClasificar,BtnIdentificar,BtnDieta;
    private JButton BtnSigProceso;
    private JButton BtnSacrificadas;
    private JButton BtnActivas;
    private JButton BtnTodoCria;
    private JButton BtnCorrales;
    private Font FontBotones;

    public UIPrincipal (){
        super("Corrales Ternero");
        setSize(550,405);
        setResizable(false);
        setContentPane(PPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void asignarControladorP(CInicio c){
        BtnRegistrarCria.addActionListener(c);
        BtnRegistrarCorral.addActionListener(c);
        BtnClasificar.addActionListener(c);
        BtnIdentificar.addActionListener(c);
        BtnDieta.addActionListener(c);

        BtnSigProceso.addActionListener(c);
        BtnSacrificadas.addActionListener(c);
        BtnActivas.addActionListener(c);
        BtnTodoCria.addActionListener(c);
        BtnCorrales.addActionListener(c);
    }

    public JButton getBtnRegistrarCria() {
        return BtnRegistrarCria;
    }

    public JButton getBtnRegistrarCorral() {
        return BtnRegistrarCorral;
    }

    public JButton getBtnIdentificar() {
        return BtnIdentificar;
    }

    public JButton getBtnDieta() {
        return BtnDieta;
    }

    public JButton getBtnClasificar() {
        return BtnClasificar;
    }

    public JButton getBtnSigProceso() {
        return BtnSigProceso;
    }

    public JButton getBtnSacrificadas() {
        return BtnSacrificadas;
    }

    public JButton getBtnActivas() {
        return BtnActivas;
    }

    public JButton getBtnTodoCria() {
        return BtnTodoCria;
    }

    public JButton getBtnCorrales() {
        return BtnCorrales;
    }

    public static String getFechaActual(){
        Calendar c=Calendar.getInstance();
        String dia=(c.get(Calendar.DATE))+"";
        String mes=(c.get(Calendar.MONTH)+1)+"";
        String anio=(c.get(Calendar.YEAR))+"";
        if(dia.length()==1)
            dia="0"+dia;
        if(mes.length()==1)
            mes="0"+mes;
        return anio+mes+dia;
    }

    private void createUIComponents() {
       PPrincipal=new JPanel();
       PPrincipal.setLayout(null);
       definePPrincipal();
    }
    private void definePPrincipal(){
        FontBotones=new Font("Candara",0,17);
        FontTitulos=new Font("Candara",1,13);

        PManipulacion=new JPanel();
        PManipulacion.setLayout(new GridLayout(0,1,0,10));

        BtnRegistrarCria=new JButton("Registrar Cría");
        BtnRegistrarCria.setFont(FontBotones);
        PManipulacion.add(BtnRegistrarCria);

        BtnRegistrarCorral=new JButton("Registrar Corral");
        BtnRegistrarCorral.setFont(FontBotones);
        PManipulacion.add(BtnRegistrarCorral);

        BtnClasificar=new JButton("Clasificar crías");
        BtnClasificar.setFont(FontBotones);
        PManipulacion.add(BtnClasificar);

        BtnIdentificar=new JButton("Cuarentenas");
        BtnIdentificar.setFont(FontBotones);
        PManipulacion.add(BtnIdentificar);

        BtnDieta=new JButton("Asignar una dieta");
        BtnDieta.setFont(FontBotones);
        PManipulacion.add(BtnDieta);

        PConsultas=new JPanel();
        PConsultas.setLayout(new GridLayout(0,1,0,10));

        BtnSigProceso=new JButton("Crías con más de 5 meses");
        BtnSigProceso.setFont(FontBotones);
        PConsultas.add(BtnSigProceso);

        BtnSacrificadas=new JButton("Crías sacrificadas");
        BtnSacrificadas.setFont(FontBotones);
        PConsultas.add(BtnSacrificadas);

        BtnActivas=new JButton("Crías que siguen activas");
        BtnActivas.setFont(FontBotones);
        PConsultas.add(BtnActivas);

        BtnTodoCria=new JButton("Todo sobre una cría");
        BtnTodoCria.setFont(FontBotones);
        PConsultas.add(BtnTodoCria);

        BtnCorrales=new JButton("Corrales");
        BtnCorrales.setFont(FontBotones);
        PConsultas.add(BtnCorrales);


        PManipulacion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Manipular la información", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        PManipulacion.setBounds(15,20,250,340);
        PPrincipal.add(PManipulacion);

        PConsultas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Consultar información", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        PConsultas.setBounds(280,20,250,340);
        PPrincipal.add(PConsultas);
    }

}
