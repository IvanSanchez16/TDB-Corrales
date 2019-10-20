package Database;

import javax.swing.*;
import java.sql.*;

public class ComandosSQL {
    public static Statement con;

    public ComandosSQL(){
        con=ConexionSingletonSQL.getConexion(1433,"GRANJA");
        if(con==null){
            JOptionPane.showMessageDialog(null,"Conexión no realizada","Error en la conexión",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null,"Conexión realizada correctamente","Conexión exitosa",JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean estadoConexion(){
        return con!=null;
    }
}
