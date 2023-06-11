package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
	
	public Connection connectDB() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/gestion_asignaturas","root","");
			return connect;
		
		} catch (Exception e) {
			return null;
		}
	}

}
