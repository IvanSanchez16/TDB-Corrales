package Views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class UIVCorrales extends JDialog {

    private DefaultTableModel Dm;
    private JTable Tb;
    private JScrollPane Sp;
    private PanelFondo PPrincipal;
    private Font FontTitulos;

    public UIVCorrales(){
        setTitle("Corrales");
        setSize(250,200);
        setLocationRelativeTo(null);
        setResizable(false);
        defineInterfaz();
    }

    public void llenarTabla(ArrayList<String[]> datos){
        String[] col={"Id","Tipo","# de crías"};
        Dm=new DefaultTableModel(null,col);
        Tb.setModel(Dm);
        TableColumn col1=Tb.getColumn("Tipo");
        col1.setPreferredWidth(110);
        for (String[] dato : datos)
            Dm.addRow(dato);
    }

    private void defineInterfaz(){
        PPrincipal=new PanelFondo("fondoCorrales.jpg",250,200);
        PPrincipal.setLayout(null);
        FontTitulos = new Font("Candara", 1, 13);

        Tb=new JTable();
        Tb.setFont(new Font("Cambria",1,13));
        Sp=new JScrollPane(Tb);
        Sp.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()
                ),"Corrales", TitledBorder.DEFAULT_POSITION,TitledBorder.DEFAULT_JUSTIFICATION,FontTitulos));
        Sp.setOpaque(false);
        Sp.setBounds(5,5,230,160);

        PPrincipal.add(Sp);
        add(PPrincipal);
    }
}
