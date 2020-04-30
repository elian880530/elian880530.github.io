package DAO;
import java.sql.*;

public class conection {
	    private static String driver = "org.postgresql.Driver";

		private static String url = "jdbc:postgresql://localhost:5432/prueba";


		private static Connection con = null;

		private static String user = "postgres";

		private static String password = "elian";


	    public conection() {
	    }

	    public static Connection getConnection(String driver, String url, String user, String password)  throws SQLException {
			try {
				Class.forName(driver);
	                        con = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		public static Connection getConnection() throws SQLException {
			return getConnection(driver, url, user, password);
		}


}

