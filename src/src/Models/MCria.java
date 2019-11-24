package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCria {

    public String sigCodigo(){
        try {
            ResultSet rs=ComandosSQL.consulta("SELECT IDENT_CURRENT('CRIAS') as Id");
            rs.next();
            int n = Integer.parseInt(rs.getString("Id"));
            if(n==1){
                rs=ComandosSQL.consulta("SELECT count(*) Num from CRIAS");
                rs.next();
                n=Integer.parseInt(rs.getString("Num"));
                return n==0?"1":"2";
            }
            return (n+1)+"";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    public ArrayList<String[]> obtenerCorrales(){
        ArrayList<String[]> matriz;
        try {
            ResultSet rs= ComandosSQL.consulta("Select * from NumeroCriasPorCorralView where Tipo='N'");
            String[] tuplas;
            matriz=new ArrayList<String[]>();
            while(rs.next()){
                tuplas=new String[3];
                tuplas[0]=rs.getString("Corral_id");
                tuplas[1]=rs.getString("Tipo");
                String aux=rs.getString("Numero de crias");
                tuplas[2]=aux==null?"0":aux;
                matriz.add(tuplas);
            }
            return matriz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String insertar(int id,String fecha,String fechaActual,String estado,int peso,String color,int grasa,int corral){
        if(peso<50 || peso>250)
            return "El peso debe estar en el rango de 50 a 250";
        if(grasa<0 || grasa>100)
            return "El porcentaje de grasa no puede estar fuera de 0-100";
        return ComandosSQL.insertar("exec dbo.SPInsertarCria @Id="+id+",@Fecha='"+fecha+"',@FechaActual='"+fechaActual
                +"',@Estado='"+estado+"',@Peso="+peso+",@CMusculo='"+color+"',@CGrasa="+grasa+",@Corral="+corral);
    }
}
