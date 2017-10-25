package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	private static DBHelper instance;
	private final static String DBPATH = "jdbc:sqlite:resources/pizza.db";
	
	private DBHelper(){}
	
	public Connection connect() throws ClassNotFoundException {
	       
        Connection conn = null;
        try {
        	Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(DBPATH);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public static DBHelper getInstance() {
		if (instance == null) {
			instance = new DBHelper();
		}
		return instance;
	}
}
