package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MVCorrales {

    public ArrayList<String[]> obtenerDatos(){
        ArrayList<String[]> al=new ArrayList<>();
        ResultSet rs= ComandosSQL.consulta("Select * from NumeroCriasPorCorralView");
        String[] tupla;
        try {
            while(rs.next()){
                tupla=new String[3];
                tupla[0]=rs.getInt("Corral_id")+"";
                tupla[1]=rs.getString("Tipo");
                tupla[2]=rs.getInt("Numero de crias")+"";
                al.add(tupla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }
}
