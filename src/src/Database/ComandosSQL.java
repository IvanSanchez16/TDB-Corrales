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

    public static String ejecutar(String comando){
        try {
            con.execute(comando);
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "";
    }

    public static String actualizar(String comando){
        try {
            con.execute(comando);
        } catch (SQLException e) {
            return e.getMessage();
        }
        return null;
    }

    public static ResultSet consulta(String comando){
        try {
            ResultSet rs=con.executeQuery(comando);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean estadoConexion(){
        return con!=null;
    }
}
