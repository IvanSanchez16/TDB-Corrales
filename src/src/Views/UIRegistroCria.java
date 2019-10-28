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
import java.util.spi.CalendarDataProvider;

public class UIRegistroCria extends JFrame {

    private Font FontCajas;
    private Font FontTitulos;
    private JNumberField TxtId;
    private JLabel TxtFecha;
    private JButton BtnFecha,BtnCFecha,BtnRegistrar;
    private JComboBox ComboEstado;
    private JNumberField TxtPeso;
    private JTextField TxtColor;
    private JNumberField TxtGrasa,TxtCorral;
    private JTable TbCorrales;
    private JScrollPane SPCorrales;
    private JCalendar Fecha;
    private DefaultTableModel dm;
    private int dia,mes,anio;

    public UIRegistroCria(){
        super("Registrar cría");
        setSize(550,395);
        setResizable(false);
        setLocationRelativeTo(null);
        definirInterfaz();
        setVisible(true);
    }

    public void asignarControladores(CCria c){
        BtnFecha.addActionListener(c);
        BtnRegistrar.addActionListener(c);
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
        return TxtColor.getText();
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
        setLayout(null);
        setBackground(new Color(242,242,242));

        FontCajas=new Font("Dubai",0,13);
        FontTitulos=new Font("Candara",1,13);

        TxtId = new JNumberField();
        TxtId.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"ID", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtId.setBackground(new Color(242,242,242));
        TxtId.setBounds(5,5,100,40);
        TxtId.setFont(FontCajas);
        add(TxtId);

        Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DATE);
        mes=c.get(Calendar.MONTH)+1;
        anio=c.get(Calendar.YEAR);

        TxtFecha = new JLabel(dia+" / "+mes+" / "+anio);
        TxtFecha.setFont(new Font("Dubai",1,14));
        TxtFecha.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Fecha de ingreso", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtFecha.setBounds(5,50,135,50);
        add(TxtFecha);

        BtnFecha=new JButton("Escoger fecha");
        BtnFecha.setFont(new Font("Candara",1,12));
        BtnFecha.setBounds(142,62,110,30);
        add(BtnFecha);

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
                ),"Estado de procedencia", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        ComboEstado.setBounds(5,105,150,50);
        ComboEstado.setBackground(new Color(242,242,242));
        add(ComboEstado);

        TxtPeso=new JNumberField();
        TxtPeso.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Peso en kilogramos", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtPeso.setBounds(5,165,130,45);
        TxtPeso.setBackground(new Color(242,242,242));
        add(TxtPeso);

        TxtColor=new JTextField();
        TxtColor.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Color del musculo", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtColor.setBounds(5,220,130,45);
        TxtColor.setBackground(new Color(242,242,242));
        add(TxtColor);

        TxtGrasa=new JNumberField();
        TxtGrasa.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Porcentaje de grasa", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtGrasa.setBounds(5,270,135,45);
        TxtGrasa.setBackground(new Color(242,242,242));
        add(TxtGrasa);

        String [][] m={};
        String [] columnas={"Id","Tipo","N# de crías"};
        dm=new DefaultTableModel(m,columnas);
        TbCorrales=new JTable(dm);
        TbCorrales.setFont(new Font("Dubai",1,12));
        TbCorrales.setEnabled(false);
        SPCorrales=new JScrollPane(TbCorrales);
        SPCorrales.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Corrales", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPCorrales.setBounds(255,5,275,260);
        SPCorrales.setBackground(new Color(242,242,242));
        add(SPCorrales);

        TxtCorral=new JNumberField();
        TxtCorral.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Id del corral", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtCorral.setFont(FontCajas);
        TxtCorral.setBounds(255,270,120,40);
        TxtCorral.setBackground(new Color(242,242,242));
        add(TxtCorral);

        BtnRegistrar=new JButton("Registrar");
        BtnRegistrar.setBounds(5,320,530,35);
        BtnRegistrar.setFont(FontTitulos);
        add(BtnRegistrar);
    }

    public void llenarCorrales(ArrayList<String[]> datos){
        for(int i=0 ; i<datos.size() ; i++)
            dm.addRow(datos.get(i));
    }
}
