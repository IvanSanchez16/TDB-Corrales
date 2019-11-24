package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MCorral {

    public String registrarCorral(char tipo){
        return ComandosSQL.insertar("exec dbo.SPInsertarCorral @Tipo="+tipo);
    }

    public String sigCodigo(){
        try {
            ResultSet rs=ComandosSQL.consulta("SELECT IDENT_CURRENT('CORRALES') as Id");
            rs.next();
            int n = Integer.parseInt(rs.getString("Id"));
            if(n==1){
                rs=ComandosSQL.consulta("SELECT count(*) Num from CORRALES");
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
}
