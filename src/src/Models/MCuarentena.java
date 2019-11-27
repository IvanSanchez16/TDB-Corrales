package Models;

import Database.ComandosSQL;
import Resource.Rutinas;
import Views.UIPrincipal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCuarentena {

    public ArrayList<String[]> obtenerCriasEn(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select Id,Corral_id from CriasEnRiesgoGrasaCobertura2View");
            String[] tuplas;
            matriz=new ArrayList<>();
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

    public ArrayList<String[]> obtenerCriasCuarentena(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from CriasEnCuarentenaView");
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next()){
                tuplas=new String[4];
                tuplas[0]=rs.getString("Cria");
                tuplas[1]=rs.getString("Temperatura");
                tuplas[2]=rs.getString("Corral");
                tuplas[3]=rs.getString("Días");
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
        double temp;
        for (int i = 0; i < datos.size(); i++) {
            temp = Double.parseDouble(datos.get(i)[1]);
            if(temp>=40)
                aux.add(i);
        }
        return aux;
    }

    public ArrayList<Integer> marcarCriasAliviadas(ArrayList<String[]> datos){
        ArrayList<Integer> aux=new ArrayList<>();
        double temp;
        for (int i = 0; i < datos.size(); i++) {
            temp = Double.parseDouble(datos.get(i)[1]);
            if(temp<40)
                aux.add(i);
        }
        return aux;
    }

    public ArrayList<Integer> marcarCrias40D(ArrayList<String[]> datos){
        ArrayList<Integer> aux=new ArrayList<>();
        double dias;
        for (int i = 0; i < datos.size(); i++) {
            dias = Double.parseDouble(datos.get(i)[3]);
            if(dias>=40)
                aux.add(i);
        }
        return aux;
    }

    public void recuperarTemperaturas(){
        //caso ejemplo
        try {
            ResultSet rs=ComandosSQL.consulta("select * from CriasG2SanasView");
            ArrayList<Integer> crias=new ArrayList<>();
            double tempran;
            while (rs.next())
                crias.add(rs.getInt("crias_id"));
            for (Integer cria : crias) {
                tempran=generarTempAleatoria();
                ComandosSQL.insertar("exec dbo.SPActualizarTemperatura @Cria_id=" + cria + ",@Temperatura="+tempran);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recuperarTemperaturasEn(){
        //caso ejemplo
        try {
            ResultSet rs=ComandosSQL.consulta("select Cria from CriasEnCuarentenaView");
            ArrayList<Integer> crias=new ArrayList<>();
            double tempran;
            while (rs.next())
                crias.add(rs.getInt("Cria"));
            for (Integer cria : crias) {
                tempran=generarTempAleatoriaEn();
                ComandosSQL.insertar("exec dbo.SPActualizarTemperatura @Cria_id=" + cria + ",@Temperatura="+tempran);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String moverACuarentena(String cria,String corral,String fechaact){
        return ComandosSQL.insertar("exec dbo.SPAgregarACuarentena @Cria="+cria+",@Corral="+corral+",@Fecha='"+fechaact+"'");
    }

    public int comprobarDias(String cria,ArrayList<String[]> datos){
        int i;
        for(i=0;!datos.get(i)[0].equals(cria);i++);
        return Integer.parseInt(datos.get(i)[3]);
    }

    public float comprobarTemperatura(String cria,ArrayList<String[]> datos){
        int i;
        for(i=0;!datos.get(i)[0].equals(cria);i++);
        return Float.parseFloat(datos.get(i)[1]);
    }

    public String sacrificarCria(String cria){
        return ComandosSQL.insertar("exec SPSacrificar @Cria="+cria+",@Fecha='"+ UIPrincipal.getFechaActual()+"'");
    }

    public String darDeAlta(String cria){
        return ComandosSQL.insertar("exec SPDarDeAltaCria @Cria="+cria+",@Fecha='"+UIPrincipal.getFechaActual()+"'");
    }

    private double generarTempAleatoria(){
        double r=Rutinas.nextDouble();
        if (r<0.15)
            return 38;
        if(r<0.55)
            return 38.5;
        if(r<0.8)
            return 39;
        if (r<0.9)
            return 39.5;
        if(r<0.933)
            return 40;
        if(r<0.966)
            return 40.5;
        return 41;
    }

    private double generarTempAleatoriaEn(){
        double r=Rutinas.nextDouble();
        if (r<0.15)
            return 41;
        if(r<0.55)
            return 40.5;
        if(r<0.8)
            return 40;
        if (r<0.9)
            return 39.5;
        if(r<0.933)
            return 39;
        if(r<0.966)
            return 38.5;
        return 38;
    }
}
