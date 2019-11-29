package Views;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * @author ashraf_sarhan
 *
 */
public class ColorJTable2 implements TableCellRenderer {

    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
    public ArrayList<Integer> num;
    public boolean band;

    public ColorJTable2(ArrayList<Integer> n){
        num=n;
        band=true;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);
        if(esta(row) && band)
            c.setBackground(new Color(250, 182, 76  ));
        else {
            c.setBackground(new Color(255, 255, 255));
            c.setForeground(Color.BLACK);
        }
        return c;
    }

    private boolean esta(int row){
        for (int i : num)
            if (i==row)
                return true;
        return false;
    }
}