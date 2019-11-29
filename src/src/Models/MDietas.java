package Models;

import Database.ComandosSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MDietas {

    public ArrayList<String[]> obtenerDatos(){
        ArrayList<String[]> al=new ArrayList<>();
        String[] tuplas;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from InfDietasxCriaView");
            while(rs.next()){
                tuplas=new String[5];
                tuplas[0]=rs.getInt("Crias_id")+"";
                tuplas[1]=rs.getString("Dieta actual");
                tuplas[2]=rs.getInt("Peso")+"";
                tuplas[3]=rs.getInt("Grasa")+"";
                tuplas[4]=rs.getInt("Dias en el proceso")+"";
                al.add(tuplas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String> obtenerDietas(){
        ArrayList<String> al=new ArrayList<>();
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from DietasView");
            while(rs.next())
                al.add(rs.getString("Descripcion"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<String[]> filtrarDatos(ArrayList<String[]> datos,String filtro,String cfiltro){
        ArrayList<String[]> al=new ArrayList<>();
        switch (cfiltro) {
            case "Id":
                for (String[] dato:datos)
                    if(dato[0].equals(filtro))
                        al.add(dato);
                break;
            case "Dieta actual":
                for (String[] dato:datos)
                    if(dato[1].contains(filtro))
                        al.add(dato);
                break;
            case ">= Peso":
                int peso,pesoComp=Integer.parseInt(filtro);
                for (String[] dato:datos){
                    peso=Integer.parseInt(dato[2]);
                    if(peso<=pesoComp)
                        al.add(dato);
                }
                break;
            case ">= Grasa":
                int grasa,grasaComp=Integer.parseInt(filtro);
                for (String[] dato:datos){
                    grasa=Integer.parseInt(dato[3]);
                    if(grasa<=grasaComp)
                        al.add(dato);
                }
                break;
            case "< Peso":
                int peso2,pesoComp2=Integer.parseInt(filtro);
                for (String[] dato:datos){
                    peso2=Integer.parseInt(dato[2]);
                    if(peso2>pesoComp2)
                        al.add(dato);
                }
                break;
            case "< Grasa":
                int grasa2,grasaComp2=Integer.parseInt(filtro);
                for (String[] dato:datos){
                    grasa2=Integer.parseInt(dato[3]);
                    if(grasa2>grasaComp2)
                        al.add(dato);
                }
                break;
        }
        return al;
    }

    public String actualizarDieta(String cria,String dieta,String fecha){
        return ComandosSQL.ejecutar("exec dbo.SPCambiarDieta @Cria="+cria+",@Dieta='"+dieta+"',@Fecha='"+fecha+"'");
    }
}
