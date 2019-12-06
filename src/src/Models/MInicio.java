package Models;

import Database.ComandosSQL;
import Resource.Rutinas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MInicio {
    public String añadirSensor(){
        return ComandosSQL.ejecutar("call dbo.SPRegistrarSensor",null);
    }

    public void actualizarDatosSensores(){
        ArrayList<String[]> crias=obtenerCrias();
        int ritmo,presion;
        float temperatura;
        for (String[] cria : crias) {
            ritmo=generarRitmoAleatorio();
            presion=generarPresionAleatorio();
            temperatura=(float)generarTempAleatoria();
            String[] p={cria[1],cria[0],temperatura+"",presion+"",ritmo+""};
            ComandosSQL.ejecutar("call dbo.SPActualizarDatos(?,?,?,?,?)",p);
        }
    }

    public ArrayList<String[]> obtenerCrias(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from CriasConSensorView",null);
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next()){
                tuplas=new String[2];
                tuplas[0]=rs.getInt("Sensor_id")+"";
                tuplas[1]=rs.getInt("Crias_id")+"";
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Ritmo 40-70(48-62) Presion 80-140(90-120)
    private double generarTempAleatoria(){
        double r= Rutinas.nextDouble();
        if (r<0.15)
            return 38;
        if(r<0.55)
            return 38.5;
        if(r<0.85)
            return 39;
        if (r<0.90)
            return 39.5;
        if(r<0.95)
            return 40;
        if(r<0.975)
            return 40.5;
        return 41;
    }

    private int generarRitmoAleatorio(){
        double r= Rutinas.nextDouble();
        if(r<0.90)
            return Rutinas.nextInt(48,62);
        return Rutinas.nextInt(40,70);
    }

    private int generarPresionAleatorio(){
        double r= Rutinas.nextDouble();
        if(r<0.90)
            return Rutinas.nextInt(90,120);
        return Rutinas.nextInt(80,140);
    }

    private double generarTempAleatoriaEn(){
        double r=Rutinas.nextDouble();
        if (r<0.15)
            return 41;
        if(r<0.55)
            return 40.5;
        if(r<0.85)
            return 40;
        if (r<0.95)
            return 39.5;
        if(r<0.975)
            return 39;
        if(r<0.988)
            return 38.5;
        return 38;
    }
}
