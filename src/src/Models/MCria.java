package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCria {

    public String sigCodigo(){
        try {
            ResultSet rs=ComandosSQL.consulta("Select max(Crias_id) as Id from CRIAS");
            rs.next();
            String num=rs.getString("Id");
            int n = Integer.parseInt(num);
            return (n+1)+"";
        } catch (Exception e) {
            return "1";
        }
    }

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
        if(peso<50 || peso>250)
            return "El peso debe estar en el rango de 50 a 250";
        if(grasa<0 || grasa>100)
            return "El porcentaje de grasa no puede estar fuera de 0-100";
        return ComandosSQL.insertar("exec SPInsertarCria "+id+",'"+fecha+"','"+fechaActual+"','"+estado+"',"+peso+",'"+color+"',"+grasa+","+corral);
    }
}
