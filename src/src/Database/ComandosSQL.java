package Database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ComandosSQL {
    private static Connection con;

    public ComandosSQL(){
        con= (Connection) ConexionSingletonSQL.getConexion(1433,"GRANJA");
        if(con==null){
            JOptionPane.showMessageDialog(null,"Conexión no realizada","Error en la conexión",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null,"Conexión realizada correctamente","Conexión exitosa",JOptionPane.INFORMATION_MESSAGE);
    }

    public static String ejecutar(String comando,String[] parametros){
        try {
            CallableStatement cstmt = con.prepareCall("{?="+comando+"}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            if (parametros!=null)
                for (int i = 0; i < parametros.length; i++)
                    cstmt.setString(i+2,parametros[i]);
            cstmt.execute();
            return cstmt.getInt(1)+"";
        } catch (SQLException e) {
            return "Error";
        }
    }

    public static ResultSet consulta(String comando,String[] parametros){
        try {
            ResultSet rs;
            CallableStatement cstmt = con.prepareCall(comando);
            if (parametros!=null)
                for (int i = 0; i < parametros.length; i++)
                    cstmt.setString(i+1,parametros[i]);
            rs=cstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }
}
