package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MReporte {

    public ArrayList<String[]>  obtenerCriasFuera(){
        ArrayList<String[]> al=new ArrayList<>();
        ResultSet rs= ComandosSQL.consulta("Select * from CriasFueraView",null);
        String[] tupla;
        try {
            while(rs.next()){
                tupla=new String[3];
                tupla[0]=rs.getInt("Cria_id")+"";
                tupla[1]=transformarFecha(rs.getString("Fecha_Salida"));
                tupla[2]=rs.getInt("Razon_Salida")==1?"Enviada al siguiente proceso":"Sacrificada";
                al.add(tupla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<ArrayList<String[]>> obtenerDatos(int cria){
        ArrayList<ArrayList<String[]>> al=new ArrayList<>();
        ResultSet rs=ComandosSQL.consultaSP("exec dbo.SPInformeCriaDatos @Cria="+cria);
        ArrayList<String[]> dato=new ArrayList<>();
        String [] tupla;
        try {
            dato=new ArrayList<>();
            while (rs.next()){
                tupla=new String[7];
                tupla[0]=transformarFecha(rs.getString("Fecha_Entrada"));
                tupla[1]=transformarFecha(rs.getString("Fecha_Salida"));
                tupla[2]=rs.getString("Razon de salida");
                tupla[3]=rs.getInt("Peso")+"";
                tupla[4]=rs.getInt("Cant_Grasa")+"";
                tupla[5]=rs.getString("Color_Musculo");
                tupla[6]=rs.getString("Nombre");
                dato.add(tupla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        al.add(dato);
        rs=ComandosSQL.consultaSP("exec dbo.SPInformeCriaDietas @Cria="+cria);
        try {
            dato=new ArrayList<>();
            while (rs.next()){
                tupla=new String[2];
                tupla[0]=rs.getString("Descripcion");
                tupla[1]=transformarFecha(rs.getString("Fecha de cambio"));
                dato.add(tupla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        al.add(dato);

        rs=ComandosSQL.consultaSP("exec dbo.SPInformeCriaCuarentenas @Cria="+cria);
        try {
            dato=new ArrayList<>();
            while (rs.next()){
                tupla=new String[3];
                tupla[0]=transformarFecha(rs.getString("Fecha_Inicio"));
                tupla[1]=transformarFecha(rs.getString("Fecha_Fin"));
                tupla[2]=rs.getInt("Corral durante cuarentena")+"";
                dato.add(tupla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        al.add(dato);

        rs=ComandosSQL.consultaSP("exec dbo.SPInformeCriaMovimientos @Cria="+cria);
        try {
            dato=new ArrayList<>();
            while (rs.next()){
                tupla=new String[3];
                tupla[0]=rs.getString("Corral origen");
                tupla[1]=rs.getString("Corral destino");
                tupla[2]=transformarFecha(rs.getString("Fecha"));
                dato.add(tupla);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        al.add(dato);

        return al;
    }

    private String transformarFecha(String fecha){
        return fecha.substring(8)+"/"+fecha.substring(5,7)+"/"+fecha.substring(0,4);
    }
}
