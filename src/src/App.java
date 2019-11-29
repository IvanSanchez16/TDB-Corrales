import Controllers.CInicio;
import Database.ComandosSQL;

import javax.swing.*;

public class App {
    public static void main(String [] a){
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        ComandosSQL c=new ComandosSQL();
        CInicio CInicio = new CInicio();

    }
}
