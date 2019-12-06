package Models;

import Database.ComandosSQL;

import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MClasificar {
    private ArrayList<String> errores;
    private ArrayList<Integer> gc2=new ArrayList<>();

    public ArrayList<String[]> obtenerCrias(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from CriasClasificacionesView order by Id",null);
            String[] tuplas;
            matriz=new ArrayList<>();
            //"Id","Peso","Color de músculo","Porcentaje de grasa","Clasificación"
            while(rs.next()){
                tuplas=new String[5];
                tuplas[0]=rs.getString("Id");
                tuplas[1]=rs.getInt("Peso")+"";
                tuplas[2]=rs.getString("Color de músculo");
                tuplas[3]=rs.getInt("Porcentaje de grasa")+"";
                String aux=rs.getString("Clasificación");
                tuplas[4]=aux==null?"Sin clasificación":aux;
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<String> actualizarClasificaciones(String fechaA){
        ArrayList<String> errores=new ArrayList<>();
        ResultSet rs=ComandosSQL.consulta("Select * from CLASIFICACIONES",null);
        ArrayList<Clasificacion> al= new ArrayList<>();
        ArrayList<int[]> ac= new ArrayList<>();
        String n;
        int p,g,i;
        try {
            while(rs.next()){
                i=rs.getInt("Clasificacion_id");
                n=rs.getString("Nombre");
                p=rs.getInt("PesoMax");
                g=rs.getInt("GrasaMax");
                al.add(new Clasificacion(i,n,p,g));
            }
        } catch (SQLException e) {
            errores.add(e.getMessage());
        }
        if(errores.size()!=0)
            return errores;
        rs=ComandosSQL.consulta("Select Crias_id,Peso,Cant_Grasa from CRIAS where Clasificacion_id is null",null);
        String msg;
        int[] aux;
        try {
            while (rs.next()) {
                aux=new int[3];
                aux[0]=rs.getInt("Crias_id");
                aux[1]=rs.getInt("Peso");
                aux[2]=rs.getInt("Cant_Grasa");
                ac.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int a,b;
        boolean band,band2;
        for (int[] cria:ac) {
            band=band2=true;
            a=b=0;
            for(int j=0;j<al.size();j++){
                if(cria[1]<=al.get(j).Peso && band){
                    a=j;
                    band=false;
                }
                if(cria[2]<=al.get(j).Grasa && band2){
                    b=j;
                    band2=false;
                }
                if(!band && !band2)
                    break;
            }
            if(a==b){
                if (clasificar(cria[0]+"",fechaA,(a+1)+""))
                    gc2.add(cria[0]);
                continue;
            }
            if(b-a==1 || a-b==1){
                if (clasificar(cria[0]+"",fechaA,(b+1)+""))
                    gc2.add(cria[0]);
                continue;
            }
            int prom=(a+b)/2;
            if (clasificar(cria[0]+"",fechaA,(prom+1)+""))
                gc2.add(cria[0]);
        }
        return errores;
    }

    private boolean clasificar(String cria,String fecha,String clas){
        String[] p2={cria,fecha,clas};
        String msg=ComandosSQL.ejecutar("call dbo.SPActualizarClasificacion(?,?,?)",p2);
        if(msg.equals("Error"))
            errores.add(msg);
        if(msg.equals("1"))
            return true;
        return false;
    }

    public ArrayList<String[]> obtenerCrias(ArrayList<String[]> a, int criterio, String filtro){
        int crit=criterio==0?0:4;
        ArrayList<String[]> al=new ArrayList<>();
        String [] aux;
        for(int i=0 ; i<a.size() ; i++) {
            aux = a.get(i);
            if (aux[crit].contains(filtro))
                al.add(aux);
        }
        return al;
    }

    public ArrayList<String> obtenerSensores(){
        ArrayList<String> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select Sensor_id from EstatusSensoresView where Estatus='Sin usarse'",null);
            String[] tuplas;
            matriz=new ArrayList<>();
            while(rs.next())
                matriz.add(rs.getString("Sensor_id"));
            return matriz;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Integer> getGc2() {
        return gc2;
    }

    public void asignarSensor(String cria,String sensor){
        String[] p={cria,sensor};
        ComandosSQL.ejecutar("call SPAsignarSensor(?,?)",p);
    }
}
