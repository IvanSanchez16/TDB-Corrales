package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MRegistro {

    public ArrayList<String[]> obtenerCorrales(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from NumeroCriasPorCorralView");
            String[] tuplas;
            matriz=new ArrayList<String[]>();
            while(rs.next()){
                tuplas=new String[3];
                tuplas[0]=rs.getString("Corral_id");
                tuplas[1]=rs.getString("Tipo");
                tuplas[2]=rs.getString("Numero de crias");
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
