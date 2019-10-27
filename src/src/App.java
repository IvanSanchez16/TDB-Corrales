import Controllers.CInicio;
import Controllers.CRegistro;
import Database.ComandosSQL;
import Models.MInicio;
import Models.MRegistro;
import Views.*;

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

        CInicio CInicio = new CInicio();
        ComandosSQL c=new ComandosSQL();
        //PPrincipal.
    }
}
