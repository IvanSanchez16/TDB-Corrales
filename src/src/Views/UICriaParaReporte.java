package Views;

import Controllers.CReporte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class UICriaParaReporte extends JDialog {

    private DefaultTableModel Dm;
    private JTable Tb;
    private JScrollPane Sp;

    public UICriaParaReporte(){
        setTitle("Escoge una cría");
        setSize(350,300);
        setResizable(false);
        setLocationRelativeTo(null);
        defineInterfaz();
    }

    public void llenarTabla(ArrayList<String[]> datos){
        for (String[] dato : datos)
            Dm.addRow(dato);
    }

    public void asignarEscuchador(CReporte c){
        Tb.addMouseListener(c);
    }

    public JTable getTb(){
        return Tb;
    }

    private void defineInterfaz(){
        String[] col={"Id","Fecha de salida","Razón de salida"};
        Dm=new DefaultTableModel(null,col);
        Tb=new JTable(Dm);
        Tb.setFont(new Font("Cambria",1,13));
        TableColumn col1=Tb.getColumn("Razón de salida");
        col1.setPreferredWidth(230);
        TableColumn col2=Tb.getColumn("Fecha de salida");
        col2.setPreferredWidth(120);
        Sp=new JScrollPane(Tb);

        add(Sp);
    }
}
