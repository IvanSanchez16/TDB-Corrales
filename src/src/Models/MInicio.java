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

    public ArrayList<Integer> evaluarCrias(){
        ArrayList<String[]> crias=obtenerCrias(),datos;
        ArrayList<Integer> criasEnRiesgo=new ArrayList<>();
        Float varianzat,varianzap,varianzar,sumt,sump,sumr;
        for (String[] cria : crias) {
            datos=obtenerDatosCria(cria[1]);
            varianzat=sumt=varianzap=sump=varianzar=sumr=0f;
            for (String[] dato : datos) {
                sumt=sumt+(float)(Math.pow( (Float.parseFloat(dato[1])-38.5f) ,2));
                sump=sump+(float)(Math.pow( (Float.parseFloat(dato[2])-50f) ,2));
                sumr=sumr+(float)(Math.pow( (Float.parseFloat(dato[3])-100f) ,2));
            }
            int cont=0;
            varianzat=sumt/10;
            varianzap=sump/10;
            varianzar=sumr/10;
            if(varianzat>=1.5)
                cont++;
            if(varianzap>=2500)
                cont++;
            if(varianzar>=1500)
                cont++;
            if(cont>=2)
                criasEnRiesgo.add(Integer.parseInt(cria[0]));
        }
        return criasEnRiesgo;
    }

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
