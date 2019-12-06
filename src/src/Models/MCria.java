package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCria {

    public ArrayList<String[]> obtenerCorrales(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from NumeroCriasPorCorralView where Tipo='Normal'",null);
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

    public String insertar(String fecha,String fechaActual,String estado,int peso,String color,int grasa,int corral){
        if(peso<50 || peso>250)
            return "El peso debe estar en el rango de 50 a 250";
        if(grasa<1 || grasa>40)
            return "El porcentaje de grasa no puede estar fuera de 1-40";
        try {
            String[] p={corral+""};
            ResultSet rs=ComandosSQL.consulta("exec dbo.SPComprobarCorral @Corral_id=?",p);
            rs.next();
            if(rs.getInt("Band")==0)
                return "El corral necesita ser tipo normal";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] p={fecha,fechaActual,estado,peso+"",color,grasa+"",corral+""};
        return ComandosSQL.ejecutar("call dbo.SPInsertarCria(?,?,?,?,?,?,?)",p);
    }
}
