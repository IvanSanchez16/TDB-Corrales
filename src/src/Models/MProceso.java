package Models;

import Database.ComandosSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MProceso {

    public ArrayList<String[]> obtenerDatos(){
        ArrayList<String[]> al=new ArrayList<>();
        ResultSet rs= ComandosSQL.consulta("Select * from EstadoCriasView",null);
        String[] tupla;
        try {
            while(rs.next()){
                tupla=new String[5];
                tupla[0]=rs.getInt("Crias_id")+"";
                tupla[1]=rs.getString("Clasificacion");
                tupla[2]=rs.getInt("Corral")+"";
                tupla[3]=rs.getString("Estado actual");
                tupla[4]=rs.getInt("Dias en el proceso")+"";
                al.add(tupla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<Integer> obtenerIndCrias150d(ArrayList<String[]> datos){
        ArrayList<Integer> al=new ArrayList<>();
        int dias;
        for (int i=0;i<datos.size();i++) {
            dias=Integer.parseInt(datos.get(i)[4]);
            if(dias>=150)
                al.add(i);
        }
        return al;
    }

    public String validarCria(String cria,ArrayList<String[]> datos){
        boolean band=false;
        for (String[] dato : datos) {
            if(cria.equals(dato[0])){
                band=true;
                int dias=Integer.parseInt(dato[4]);
                if(dias<150)
                    return "La cría necesita estar más de 150 días en el proceso";
                if(!dato[3].equals("Sana"))
                    return  "La cría necesita estar sana";
            }
        }
        return band?"":"Ingrese una cría válida";
    }

    public String darSalidaCria(String cria,String fecha){
        String[] p={cria,fecha};
        return ComandosSQL.ejecutar("call dbo.SPSiguienteProceso(?,?)",p);
    }
}
