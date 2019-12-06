package Models;

import Database.ComandosSQL;

public class MInicio {
    public String añadirSensor(){
        return ComandosSQL.ejecutar("call dbo.SPRegistrarSensor",null);
    }
}
