package Views;

import Controllers.CInicio;
import Resource.Rutinas;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Calendar;

public class UIMenu extends JFrame {

    private Font FontTitulos;
    private JPanel PCrias,PCorrales,PSensores;
    private JButton BtnRegistrarCria,BtnRegistrarCorral,BtnClasificar,BtnIdentificar,BtnDieta,BtnProcesar,BtnGenerarInforme,BtnVerCorrales,BtnRegistrarSensor,BtnVerSensores,BtnAsignarSensor;
    private Font FontBotones;
    private PanelFondo PPrincipal;
    private Timer t;

    public UIMenu (){
        super("Corrales Ternero");
        setSize(550,300);
        setResizable(false);
        PPrincipal=new PanelFondo("fondoPrincipal.jpg",550,300);
        PPrincipal.setLayout(new GridBagLayout());
        definePPrincipal();
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
        BtnProcesar.addActionListener(c);
        BtnVerCorrales.addActionListener(c);
        BtnGenerarInforme.addActionListener(c);
        BtnRegistrarSensor.addActionListener(c);
        BtnVerSensores.addActionListener(c);
        BtnAsignarSensor.addActionListener(c);
        t=new Timer(30000,c);
        t.start();
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

    public JButton getBtnProcesar() {
        return BtnProcesar;
    }

    public JButton getBtnVerCorrales() {
        return BtnVerCorrales;
    }

    public JButton getBtnGenerarInforme() {
        return BtnGenerarInforme;
    }

    public JButton getBtnRegistrarSensor() {
        return BtnRegistrarSensor;
    }

    public JButton getBtnVerSensores() {
        return BtnVerSensores;
    }

    public JButton getBtnAsignarSensor() {
        return BtnAsignarSensor;
    }

    public Timer getT() {
        return t;
    }

    public void mostrarModal(String msg){
        if (msg.equals("Error")){
            JOptionPane.showMessageDialog(this,"Ocurrio un error al dar de alta un sensor","Sensores",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,"El sensor ingresado fue: "+msg,"Sensores",JOptionPane.INFORMATION_MESSAGE);
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

    private void definePPrincipal(){
        setIconImage(Rutinas.AjustarImagen("src/src/images/icono.png",40,40).getImage());

        FontBotones=new Font("Candara",0,17);
        FontTitulos=new Font("Candara",1,15);

        PCrias =new JPanel();
        PCrias.setLayout(new GridLayout(0,1,0,10));

        BtnRegistrarCria=new JButton("Registrar");
        BtnRegistrarCria.setFont(FontBotones);
        BtnRegistrarCria.setIcon(Rutinas.AjustarImagen("src/src/images/Agg.png",20,20));
        PCrias.add(BtnRegistrarCria);

        BtnClasificar=new JButton("Clasificar");
        BtnClasificar.setFont(FontBotones);
        BtnClasificar.setIcon(Rutinas.AjustarImagen("src/src/images/Clas.png",20,20));
        PCrias.add(BtnClasificar);

        BtnDieta=new JButton("Cambiar dieta");
        BtnDieta.setFont(FontBotones);
        BtnDieta.setIcon(Rutinas.AjustarImagen("src/src/images/dieta.png",20,20));
        PCrias.add(BtnDieta);

        BtnProcesar=new JButton("Procesarlas");
        BtnProcesar.setFont(FontBotones);
        BtnProcesar.setIcon(Rutinas.AjustarImagen("src/src/images/proceso.png",20,20));
        PCrias.add(BtnProcesar);

        PCrias.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Crías", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        PCrias.setOpaque(false);

        GridBagConstraints cc=new GridBagConstraints();
        cc.gridheight=2;
        cc.gridwidth=1;
        cc.gridx=0;
        cc.gridy=0;
        cc.insets=new Insets(0,0,0,5);
        cc.fill=GridBagConstraints.HORIZONTAL;
        PPrincipal.add(PCrias,cc);

        PCorrales=new JPanel(new GridLayout(0,1,0,10));

        BtnRegistrarCorral=new JButton("Registrar");
        BtnRegistrarCorral.setFont(FontBotones);
        BtnRegistrarCorral.setIcon(Rutinas.AjustarImagen("src/src/images/Agg.png",20,20));
        PCorrales.add(BtnRegistrarCorral);

        BtnVerCorrales=new JButton("Ver existentes");
        BtnVerCorrales.setFont(FontBotones);
        BtnVerCorrales.setIcon(Rutinas.AjustarImagen("src/src/images/consulta.png",20,20));
        PCorrales.add(BtnVerCorrales);

        PCorrales.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Corrales", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        PCorrales.setOpaque(false);

        GridBagConstraints cco=new GridBagConstraints();
        cco.gridwidth=1;
        cco.gridheight=1;
        cco.gridx=1;
        cco.gridy=0;
        cco.insets=new Insets(0,5,0,5);
        PPrincipal.add(PCorrales,cco);

        PSensores=new JPanel(new GridLayout(0,1,0,3));

        BtnRegistrarSensor=new JButton("Registrar");
        BtnRegistrarSensor.setFont(FontBotones);
        BtnRegistrarSensor.setIcon(Rutinas.AjustarImagen("src/src/images/Agg.png",20,20));
        PSensores.add(BtnRegistrarSensor);

        BtnVerSensores=new JButton("Ver existentes");
        BtnVerSensores.setFont(FontBotones);
        BtnVerSensores.setIcon(Rutinas.AjustarImagen("src/src/images/consulta.png",20,20));
        PSensores.add(BtnVerSensores);

        BtnAsignarSensor=new JButton("Asignar sensor");
        BtnAsignarSensor.setFont(FontBotones);
        BtnAsignarSensor.setIcon(Rutinas.AjustarImagen("src/src/images/sensor.png",20,20));
        PSensores.add(BtnAsignarSensor);

        PSensores.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Sensores", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        PSensores.setOpaque(false);

        GridBagConstraints cs=new GridBagConstraints();
        cs.gridwidth=1;
        cs.gridheight=1;
        cs.gridx=1;
        cs.gridy=1;
        cs.insets=new Insets(0,5,0,5);
        PPrincipal.add(PSensores,cs);

        BtnIdentificar=new JButton("<html><h2>Veterinaria</h2>Monitorear crías<br/>Mover a cuarentenas<br/>Acabar cuarentenas</html>");
        BtnIdentificar.setFont(FontBotones);
        GridBagConstraints cv=new GridBagConstraints();
        cv.gridx=2;
        cv.gridy=0;
        cv.gridwidth=2;
        cv.gridheight=1;
        cv.fill=GridBagConstraints.BOTH;
        cv.insets=new Insets(0,5,5,0);
        PPrincipal.add(BtnIdentificar,cv);

        BtnGenerarInforme=new JButton("<html>Generar informe de<br /> cría ya procesada</html>");
        BtnGenerarInforme.setFont(FontBotones);
        GridBagConstraints cg=new GridBagConstraints();
        cg.gridx=2;
        cg.gridy=1;
        cg.gridwidth=2;
        cg.gridheight=1;
        cg.fill=GridBagConstraints.BOTH;
        cg.insets=new Insets(5,5,0,0);
        PPrincipal.add(BtnGenerarInforme,cg);

    }

}