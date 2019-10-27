package Views;

import Controllers.CRegistro;
import Resource.JNumberField;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

public class UIRegistroCria extends JFrame {

    private Font FontCajas;
    private Font FontTitulos;
    private JNumberField TxtId;
    private JLabel TxtFecha;
    private JButton BtnFecha,BtnCFecha,BtnRegistrar;
    private JComboBox ComboEstado;
    private JNumberField TxtPeso;
    private JTextField TxtColor;
    private JNumberField TxtGrasa;
    private JTable TbCorrales;
    private JScrollPane SPCorrales;
    private JCalendar Fecha;
    private DefaultTableModel dm;

    public UIRegistroCria(){
        super("Registrar cría");
        setSize(550,395);
        setResizable(false);
        setLocationRelativeTo(null);
        definirInterfaz();
        setVisible(true);
    }

    public void asignarControladores(CRegistro c){
        BtnFecha.addActionListener(c);
        BtnRegistrar.addActionListener(c);
    }

    public void mostrarDatePicker(){
        JDialog jd=new JDialog(this,"Fecha",true);
        Fecha=new JCalendar();
        Fecha.setWeekOfYearVisible(false);
        BtnCFecha=new JButton("Confirmar");
        BtnCFecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar c=Fecha.getCalendar();
                TxtFecha.setText(c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR));
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
        int dia=c.get(Calendar.DATE);
        int mes=c.get(Calendar.MONTH);
        int anio=c.get(Calendar.YEAR);

        TxtFecha = new JLabel(dia+"/"+mes+"/"+anio);
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
                ),"Cantidad de grasa", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        TxtGrasa.setBounds(5,270,130,45);
        TxtGrasa.setBackground(new Color(242,242,242));
        add(TxtGrasa);

        String [][] m={};
        String [] columnas={"Id","Tipo","N# de crías"};
        dm=new DefaultTableModel(m,columnas);
        TbCorrales=new JTable(dm);
        TbCorrales.setFont(new Font("Candara",1,11));
        TbCorrales.setEnabled(false);
        SPCorrales=new JScrollPane(TbCorrales);
        SPCorrales.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Corrales", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        SPCorrales.setBounds(255,5,275,310);
        SPCorrales.setBackground(new Color(242,242,242));
        add(SPCorrales);

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
