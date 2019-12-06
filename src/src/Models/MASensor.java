package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MASensor {

    public ArrayList<String[]> obtenerCrias(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from CriasSinSensorView",null);
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next()){
                tuplas=new String[2];
                tuplas[0]=rs.getInt("Crias_id")+"";
                tuplas[1]=rs.getInt("Corral_id")+"";
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String[]> obtenerSensores(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select Sensor_id from EstatusSensoresView where Estatus='Sin usarse'",null);
            String[] tupla;
            matriz=new ArrayList<>();
            while(rs.next()) {
                tupla = new String[1];
                tupla[0] = rs.getInt("Sensor_id") + "";
                matriz.add(tupla);
            }
            return matriz;
        } catch (SQLException e) {
        }
        return null;
    }

    public String asignarSensor(String cria,String sensor){
        String[] p={cria,sensor};
        return ComandosSQL.ejecutar("call SPAsignarSensor(?,?)",p);
    }
}
