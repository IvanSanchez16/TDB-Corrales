package Views;

import Controllers.CCria;
import Resource.JNumberField;
import com.toedter.calendar.JCalendar;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class UIRegistroCria extends JDialog {

    private PanelFondo PPrincipal;
    private Font FontCajas;
    private Font FontTitulos;
    private JNumberField TxtId;
    private JLabel TxtFecha;
    private JButton BtnFecha,BtnCFecha,BtnRegistrar;
    private JComboBox ComboEstado;
    private JNumberField TxtPeso;
    private JComboBox<String> TxtColor;
    private JNumberField TxtGrasa,TxtCorral;
    private JTable TbCorrales;
    private JScrollPane SPCorrales;
    private JCalendar Fecha;
    private DefaultTableModel dm;
    private int dia,mes,anio;

    public UIRegistroCria(){
        setTitle("Registrar cría");
        setModal(true);
        setSize(550,395);
        setResizable(false);
        setLocationRelativeTo(null);
        definirInterfaz();
    }

    public void asignarControladores(CCria c){
        BtnFecha.addActionListener(c);
        BtnRegistrar.addActionListener(c);
        TxtId.addKeyListener(c);
    }

    public void setTextId(String id){
        TxtId.setText(id);
    }

    public void mostrarDatePicker(){
        JDialog jd=new JDialog(this,"Fecha",true);
        Fecha=new JCalendar();
        Fecha.setWeekOfYearVisible(false);
        Fecha.setMaxSelectableDate(Calendar.getInstance().getTime());
        BtnCFecha=new JButton("Confirmar");
        BtnCFecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar c=Fecha.getCalendar();
                TxtFecha.setText(c.get(Calendar.DATE)+" / "+(c.get(Calendar.MONTH)+1)+" / "+c.get(Calendar.YEAR));
                jd.dispose();
            }
        });
        jd.setLocation(420,315);
        jd.setLayout(new BorderLayout());
        jd.add(Fecha,BorderLayout.CENTER);
        jd.add(BtnCFecha,BorderLayout.SOUTH);
        jd.pack();
        jd.setVisible(true);
    }

    public JButton getBtnFecha() {
        return BtnFecha;
    }

    public JButton getBtnRegistrar() {
        return BtnRegistrar;
    }

    public int getId(){
        return (int) TxtId.ObtenerCantidad();
    }

    public JNumberField getTxtId() {
        return TxtId;
    }

    public String getFecha(){
        String aux="",fecha=TxtFecha.getText();
        char c;
        int cont=0;
        for(int i=0 ; i<fecha.length() ; i++){
            c=fecha.charAt(i);
            if(c!=' ' && c!='/') {
                aux = aux + c;
                cont++;
                continue;
            }
            if(cont<2 && cont!=0){
                aux=aux.substring(0,i-1)+'0'+aux.charAt(i-1);
            }
            cont=0;
        }
        aux=aux.substring(4)+aux.substring(2,4)+aux.substring(0,2);
        return aux;
    }

    public String getEstado(){
        return (String) ComboEstado.getSelectedItem();
    }

    public int getPeso(){
        return (int) TxtPeso.ObtenerCantidad();
    }

    public String getCMusculo(){
        return (String) TxtColor.getSelectedItem();
    }

    public int getCGrasa(){
        return (int) TxtGrasa.ObtenerCantidad();
    }

    public int getCorral(){
        return (int) TxtCorral.ObtenerCantidad();
    }

    public void mostrarModal(String msg){
        boolean band=true;
        if(msg.equals("")){
            msg="La cría fue registrada correctamente";
            band=false;
        }
        JOptionPane.showMessageDialog(this,msg,"Registro de cría",msg.equals("La cría fue registrada correctamente")?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
        if(!band)
            dispose();
    }

    private void definirInterfaz(){
        PPrincipal=new PanelFondo("fondoRegistrar.jpg",550,395);
        PPrincipal.setLayout(null);

        FontCajas=new Font("Cambria",0,14);
        FontTitulos=new Font("Candara",1,14);

        TxtId = new JNumberField();
        TxtId.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"ID", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        TxtId.setBounds(5,5,135,40);
        TxtId.setFont(new Font("Cambria",1,14));
        TxtId.setForeground(Color.white);
        TxtId.setOpaque(false);
        PPrincipal.add(TxtId);

        Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DATE);
        mes=c.get(Calendar.MONTH)+1;
        anio=c.get(Calendar.YEAR);

        TxtFecha = new JLabel(dia+" / "+mes+" / "+anio);
        TxtFecha.setFont(new Font("Cambria",1,14));
        TxtFecha.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Fecha de ingreso", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        TxtFecha.setBounds(5,50,135,50);
        TxtFecha.setForeground(Color.white);
        PPrincipal.add(TxtFecha);

        BtnFecha=new JButton("Escoger fecha");
        BtnFecha.setFont(new Font("Candara",1,12));
        BtnFecha.setBounds(142,62,110,30);
        PPrincipal.add(BtnFecha);

        String[]nombreEstados= {
                "Aguascalientes","Baja California","Baja California Sur"
                ,"Campeche","Chiapas","Chihuahua", "CDMX","Coahuila",
                "Colima","Durango","Guanajuato","Guerrero","Hidalgo",
                "Jalisco","México","Michoacan","Morelos","Nayarit",
                "Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo",
                "San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas",
                "Tlaxcala","Veracruz","Yucatán","Zacatecas"
        };
        ComboEstado=new JComboBox(nombreEstados);
        ComboEstado.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Estado de procedencia", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        ComboEstado.setBounds(5,105,180,50);
        ComboEstado.setFont(FontCajas);
        ComboEstado.setOpaque(false);
        PPrincipal.add(ComboEstado);

        TxtPeso=new JNumberField();
        TxtPeso.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Peso en kg", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        TxtPeso.setBounds(5,165,135,45);
        TxtPeso.setFont(FontCajas);
        TxtPeso.setForeground(Color.white);
        TxtPeso.setOpaque(false);
        PPrincipal.add(TxtPeso);


        String[] aux={"Rojo vivo","Rojo púrpura","Rojo pardo"};
        TxtColor=new JComboBox<>(aux);
        TxtColor.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Color del músculo", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        TxtColor.setBounds(5,220,135,45);
        TxtColor.setFont(FontCajas);
        TxtColor.setOpaque(false);
        PPrincipal.add(TxtColor);

        TxtGrasa=new JNumberField();
        TxtGrasa.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"% de grasa", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        TxtGrasa.setBounds(5,270,135,45);
        TxtGrasa.setFont(FontCajas);
        TxtGrasa.setOpaque(false);
        TxtGrasa.setForeground(Color.white);
        PPrincipal.add(TxtGrasa);

        String [][] m={};
        String [] columnas={"Id","Tipo","N# de crías"};
        dm=new DefaultTableModel(m,columnas);
        TbCorrales=new JTable(dm);
        TbCorrales.setFont(new Font("Cambria",1,12));
        TbCorrales.setEnabled(false);
        TbCorrales.setOpaque(false);
        SPCorrales=new JScrollPane(TbCorrales);
        SPCorrales.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Corrales", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        SPCorrales.setBounds(255,5,275,260);
        SPCorrales.setOpaque(false);
        PPrincipal.add(SPCorrales);

        TxtCorral=new JNumberField();
        TxtCorral.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Id del corral", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.white));
        TxtCorral.setFont(FontCajas);
        TxtCorral.setBounds(255,270,120,40);
        TxtCorral.setOpaque(false);
        TxtCorral.setForeground(Color.white);
        PPrincipal.add(TxtCorral);

        BtnRegistrar=new JButton("Registrar");
        BtnRegistrar.setBounds(5,320,530,35);
        BtnRegistrar.setFont(FontTitulos);
        PPrincipal.add(BtnRegistrar);

        add(PPrincipal);
    }

    public void llenarCorrales(ArrayList<String[]> datos){
        for(int i=0 ; i<datos.size() ; i++)
            dm.addRow(datos.get(i));
        setVisible(true);
    }

}
