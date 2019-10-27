package Database;

import javax.swing.*;
import java.sql.*;

public class ComandosSQL {
    private static Statement con;

    public ComandosSQL(){
        con=ConexionSingletonSQL.getConexion(1433,"GRANJA");
        if(con==null){
            JOptionPane.showMessageDialog(null,"Conexión no realizada","Error en la conexión",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null,"Conexión realizada correctamente","Conexión exitosa",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void insertar(){

    }

    public static ResultSet consulta(String comando){
        try {
            ResultSet rs=con.executeQuery(comando);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean estadoConexion(){
        return con!=null;
    }
}
