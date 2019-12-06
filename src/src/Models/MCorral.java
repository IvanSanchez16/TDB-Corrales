package Models;

import Database.ComandosSQL;

import java.sql.ResultSet;

public class MCorral {

    public String registrarCorral(char tipo){
        String[] p={tipo+""};
        return ComandosSQL.ejecutar("call dbo.SPInsertarCorral(?)",p);
    }

}
