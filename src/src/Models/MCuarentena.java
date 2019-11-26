package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCuarentena {

    public ArrayList<String[]> obtenerCriasEn(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select Id,Corral_id from CriasEnfermasGrasaCobertura2");
            String[] tuplas;
            matriz=new ArrayList<>();
            //"Id","Peso","Color de músculo","Porcentaje de grasa","Clasificación"
            while(rs.next()){
                tuplas=new String[2];
                tuplas[0]=rs.getString("Id");
                tuplas[1]=rs.getString("Corral_id");
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
            ResultSet rs= ComandosSQL.consulta("Select Corral_id,[Numero de crias] from NumeroCriasPorCorralView where Tipo='C'");
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

    public ArrayList<String[]> obtenerCriasG2(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from EstadoCriasG2View");
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next()){
                tuplas=new String[3];
                tuplas[0]=rs.getString("Cria");
                tuplas[1]=rs.getString("Temperatura");
                tuplas[2]=rs.getString("Corral");
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> marcarCriasEnRiesgo(ArrayList<String[]> datos){
        ArrayList<Integer> aux=new ArrayList<>();
        int temp;
        for (int i = 0; i < datos.size(); i++) {
            temp = Integer.parseInt(datos.get(i)[1]);
            if(temp>=40)
                aux.add(i);
        }
        return aux;
    }

    public String moverACuarentena(String cria,String corral,String fechaact,String medicamento,String enfermedad){
        return ComandosSQL.insertar("exec SPAgregarACuarentena "+cria+","+corral+",'"+fechaact+"','"+medicamento+"','"+enfermedad+"'");
    }
}
