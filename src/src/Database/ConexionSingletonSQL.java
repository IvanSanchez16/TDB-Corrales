package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSingletonSQL {
	static private String url=null;
	static private Statement statement = null;
	static private Connection conn;
	
	private ConexionSingletonSQL()  {}
	
	static synchronized public Connection getConexion(int puerto,String database){
		if(conn==null){
			url= "jdbc:sqlserver://localhost:"+puerto+";databaseName="+database+";integratedSecurity=true";
			try {
				return conn=DriverManager.getConnection(url);
			} catch (SQLException e) {
				return null;
			}
		}
		return conn;
	}
	
	static synchronized public void cierraConexion(){
			try {
				conn.close();
				statement.close();
			} catch(SQLException E){}
		}
	}  