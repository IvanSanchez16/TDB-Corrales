package Views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class UIReporte extends JDialog {

    private PanelFondo PPrincipal;
    private JPanel PDatos;
    private JLabel LbCria,LbPeso,LbGrasa,LbColor,LbClasificacion,LbFechaIngreso,LbFechaSalida,LbRazonSalida;
    private JScrollPane SpDietas,SpCuarentenas,SpMovimientos;
    private JTable TbDietas,TbCuarentenas,TbMovimientos;
    private DefaultTableModel DmDietas,DmCuarentenas,DmMovimientos;
    private boolean vivo;
    private int cria;
    private Font FontCajas;
    private Font FontTitulos;
    private Font FontTablas;

    public UIReporte(int cria,boolean v){
        vivo=!v;
        this.cria=cria;
        setTitle("Informe");
        setSize(1050,350);
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(null);
        defineInterfaz();
    }

    public void llenarTablas(ArrayList<ArrayList<String[]>> datoss){
        for (int i = 0; i < datoss.size(); i++) {
            switch (i){
                case 0://Datos
                    LbFechaIngreso.setText("Fecha de ingreso: "+datoss.get(i).get(0)[0]);
                    LbFechaSalida.setText("Fecha de salida: "+datoss.get(i).get(0)[1]);
                    LbRazonSalida.setText("<html>Razón de salida:<br/>"+datoss.get(i).get(0)[2]+"</html>");
                    LbPeso.setText("Peso: "+datoss.get(i).get(0)[3]+" kg");
                    LbGrasa.setText("Grasa: "+datoss.get(i).get(0)[4]+" %");
                    LbColor.setText("Color del músculo: "+datoss.get(i).get(0)[5]);
                    LbClasificacion.setText("Clasificación: "+datoss.get(i).get(0)[6]);
                    break;
                case 1://Tabla dietas
                    for (String[] strings : datoss.get(i))
                        DmDietas.addRow(strings);
                    break;
                case 2://Tabla cuarentenas
                    for (String[] strings : datoss.get(i))
                        DmCuarentenas.addRow(strings);
                    break;
                case 3://Tabla movimientos
                    for (String[] strings : datoss.get(i))
                        DmMovimientos.addRow(strings);
                        break;
            }
        }
    }

    public void defineInterfaz(){
        PPrincipal=new PanelFondo("fondoReporte.jpg",1050,350);
        PPrincipal.setLayout(null);

        FontCajas=new Font("Cambria",1,14);
        FontTitulos=new Font("Candara",1,15);
        FontTablas=new Font("Cambria",1,13);

        LbCria=new JLabel("Cría #"+cria);
        LbCria.setFont(new Font("Cambria",1,34));
        LbCria.setForeground(Color.WHITE);
        LbCria.setBounds(5,5,150,50);
        PPrincipal.add(LbCria);

        PDatos=new JPanel(new GridLayout(0,1,0,0));

        LbFechaIngreso=new JLabel("Fecha de ingreso: xx/xx/xxxx");
        LbFechaIngreso.setFont(FontCajas);
        LbFechaIngreso.setForeground(Color.WHITE);
        PDatos.add(LbFechaIngreso);

        LbFechaSalida=new JLabel("Fecha de salida: xx/xx/xxxx");
        LbFechaSalida.setFont(FontCajas);
        LbFechaSalida.setForeground(Color.WHITE);
        PDatos.add(LbFechaSalida);

        LbRazonSalida=new JLabel("Razón de salida: X");
        LbRazonSalida.setFont(FontCajas);
        LbRazonSalida.setForeground(Color.WHITE);
        PDatos.add(LbRazonSalida);

        LbPeso=new JLabel("Peso: X kg");
        LbPeso.setFont(FontCajas);
        LbPeso.setForeground(Color.WHITE);
        PDatos.add(LbPeso);

        LbGrasa=new JLabel("Cantidad de grasa: X %");
        LbGrasa.setFont(FontCajas);
        LbGrasa.setForeground(Color.WHITE);
        PDatos.add(LbGrasa);

        LbColor=new JLabel("Color del músculo: X");
        LbColor.setFont(FontCajas);
        LbColor.setForeground(Color.WHITE);
        PDatos.add(LbColor);

        LbClasificacion=new JLabel("Clasificación: Grasa de cobertura X");
        LbClasificacion.setFont(FontCajas);
        LbClasificacion.setForeground(Color.WHITE);
        PDatos.add(LbClasificacion);

        PDatos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Datos", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        PDatos.setOpaque(false);
        PDatos.setBounds(5,60,245,250);
        PPrincipal.add(PDatos);

        String[] cold={"Dieta","Fecha de cambio"};
        DmDietas=new DefaultTableModel(null,cold);
        TbDietas=new JTable(DmDietas);
        TbDietas.getColumn("Fecha de cambio").setPreferredWidth(100);
        TbDietas.setFont(FontTablas);
        SpDietas=new JScrollPane(TbDietas);
        SpDietas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Historial de dietas", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        SpDietas.setBounds(260,60,210,250);
        SpDietas.setOpaque(false);
        PPrincipal.add(SpDietas);

        String[] colh={"Fecha de Inicio","Fecha de salida","Corral"};
        DmCuarentenas=new DefaultTableModel(null,colh);
        TbCuarentenas=new JTable(DmCuarentenas);
        TableColumn col1=TbCuarentenas.getColumn("Corral");
        col1.setPreferredWidth(20);
        TbCuarentenas.setFont(FontTablas);
        SpCuarentenas=new JScrollPane(TbCuarentenas);
        SpCuarentenas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Historial de cuarentenas", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        SpCuarentenas.setBounds(475,60,275,250);
        SpCuarentenas.setOpaque(false);
        PPrincipal.add(SpCuarentenas);

        String[] colM={"Corral Origen","Corral Destino","Fecha"};
        DmMovimientos=new DefaultTableModel(null,colM);
        TbMovimientos=new JTable(DmMovimientos);
        TbMovimientos.setFont(FontTablas);
        SpMovimientos=new JScrollPane(TbMovimientos);
        SpMovimientos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Historial de movimientos", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos,Color.WHITE));
        SpMovimientos.setBounds(755,60,275,250);
        SpMovimientos.setOpaque(false);
        PPrincipal.add(SpMovimientos);

        add(PPrincipal);
    }
}
