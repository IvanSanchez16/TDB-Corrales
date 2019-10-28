package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MCorral {

    public String registrarCorral(char tipo){
        return ComandosSQL.insertar("Insert into CORRALES values('"+tipo+"')");
    }

    public String sigCodigo(){
        try {
            ResultSet rs=ComandosSQL.consulta("Select max(Corral_id) as Id from CORRALES");
            rs.next();
            String num=rs.getString("Id");
            int n = Integer.parseInt(num);
            return (n+1)+"";
        } catch (Exception e) {
           return "1";
        }
    }
}
