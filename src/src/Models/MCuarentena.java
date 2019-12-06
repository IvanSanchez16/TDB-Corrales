package Models;

import Database.ComandosSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCuarentena {

    public ArrayList<String[]> obtenerDatosCria(String cria){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consultaSP("exec dbo.SPDatosSensores @Cria="+cria);
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next()){
                tuplas=new String[4];
                tuplas[0]=rs.getInt("Clave")+"";
                tuplas[1]=rs.getFloat("Temperatura")+"";
                tuplas[2]=rs.getInt("Presion")+"";
                tuplas[3]=rs.getInt("Ritmo")+"";
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String[]> obtenerCorralesEn(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select Corral_id,[Numero de crias] from NumeroCriasPorCorralView where Tipo='Cuarentena'",null);
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next()){
                tuplas=new String[2];
                tuplas[0]=rs.getString("Corral_id");
                String aux=rs.getString("Numero de crias");
                tuplas[1]=aux==null?"0":aux;
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String[]> obtenerCriasCuarentena(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from CriasEnCuarentenaView",null);
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next()){
                tuplas=new String[3];
                tuplas[0]=rs.getString("Cria");
                tuplas[1]=rs.getString("Corral");
                tuplas[2]=rs.getString("Días");
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> obtenerDietas(){
        ArrayList<String> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from DietasEnfermasView",null);
            matriz=new ArrayList<>();
            while(rs.next())
                matriz.add(rs.getString("Descripcion"));
            return matriz;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Integer> marcarCrias40D(ArrayList<String[]> datos){
        ArrayList<Integer> aux=new ArrayList<>();
        double dias;
        for (int i = 0; i < datos.size(); i++) {
            dias = Double.parseDouble(datos.get(i)[2]);
            if(dias>=40)
                aux.add(i);
        }
        return aux;
    }

    public String moverACuarentena(String cria,String dieta,String corral,String fechaact){
        String[] p={cria,corral,fechaact,dieta};
        return ComandosSQL.ejecutar("call dbo.SPAgregarACuarentena(?,?,?,?)",p);
    }

    public int comprobarDias(String cria,ArrayList<String[]> datos){
        int i;
        for(i=0;!datos.get(i)[0].equals(cria);i++);
        return Integer.parseInt(datos.get(i)[2]);
    }

    public String sacrificarCria(String cria,String fecha){
        String[] p={cria,fecha};
        return ComandosSQL.ejecutar("call dbo.SPSacrificar (?,?)",p);
    }

    public String darDeAlta(String cria,String fecha){
        String[] p={cria,fecha};
        return ComandosSQL.ejecutar("call dbo.SPDarDeAltaCria(?,?)",p);
    }

}
