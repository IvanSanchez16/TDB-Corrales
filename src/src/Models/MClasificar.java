package Models;

import Database.ComandosSQL;

import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MClasificar {
    public ArrayList<String[]> obtenerCrias(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from CriasClasificacionesView order by Id");
            String[] tuplas;
            matriz=new ArrayList<String[]>();
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
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> actualizarClasificaciones(String fechaA){
        ArrayList<String> errores=new ArrayList<>();
        ResultSet rs=ComandosSQL.consulta("Select * from CLASIFICACIONES");
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
        rs=ComandosSQL.consulta("Select Crias_id,Peso,Cant_Grasa from CRIAS where Clasificacion_id is null");
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
                msg=ComandosSQL.actualizar("exec dbo.SPActualizarClasificacion "+cria[0]+",'"+fechaA+"',"+(a+1));
                if(msg!=null)
                    errores.add(msg);
                continue;
            }
            if(b-a==1 || a-b==1){
                msg=ComandosSQL.actualizar("exec dbo.SPActualizarClasificacion "+cria[0]+",'"+fechaA+"',"+(b+1));
                if(msg!=null)
                    errores.add(msg);
                continue;
            }
            int prom=(a+b)/2;
            msg=ComandosSQL.actualizar("exec dbo.SPActualizarClasificacion "+cria[0]+",'"+fechaA+"',"+(prom+1));
            if(msg!=null)
                errores.add(msg);
        }
        return errores;
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

    public String actualizarCria(String id,String peso,String grasa) {
        return ComandosSQL.actualizar("exec dbo.SPActualizarCria "+id+","+peso+","+grasa);
    }

}
