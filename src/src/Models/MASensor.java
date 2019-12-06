package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MASensor {

    public ArrayList<String[]> obtenerCrias(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from CriasConSensorView",null);
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next()){
                tuplas=new String[2];
                tuplas[0]=rs.getInt("Sensor_id")+"";
                tuplas[1]=rs.getInt("Crias_id")+"";
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
