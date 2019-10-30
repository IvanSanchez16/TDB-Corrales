package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCria {

    public ArrayList<String[]> obtenerCorrales(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from NumeroCriasPorCorralView where Tipo='N'");
            String[] tuplas;
            matriz=new ArrayList<String[]>();
            while(rs.next()){
                tuplas=new String[3];
                tuplas[0]=rs.getString("Corral_id");
                tuplas[1]=rs.getString("Tipo");
                String aux=rs.getString("Numero de crias");
                tuplas[2]=aux==null?"0":aux;
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String insertar(int id,String fecha,String fechaActual,String estado,int peso,String color,int grasa,int corral){
        return ComandosSQL.insertar("exec SPInsertarCria "+id+",'"+fecha+"','"+fechaActual+"','"+estado+"',"+peso+",'"+color+"',"+grasa+","+corral);
    }
}
