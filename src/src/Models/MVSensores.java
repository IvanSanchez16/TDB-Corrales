package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MVSensores {

    public ArrayList<String[]> obtenerDatos(boolean band){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from EstatusSensoresView where Estatus="+(band?"'En uso'":"'Sin usarse'"),null);
            String[] tupla;
            matriz=new ArrayList<>();
            while(rs.next()){
                tupla=new String[2];
                tupla[0]=rs.getInt("Sensor_id")+"";
                tupla[1]=rs.getString("Estatus");
                matriz.add(tupla);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
