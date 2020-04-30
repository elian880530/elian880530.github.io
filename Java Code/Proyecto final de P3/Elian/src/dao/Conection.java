package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Conection {
	
	    private static String driver="org.postgresql.Driver";
	    private static String url="jdbc:postgresql://10.36.13.45:5432/P3";
	    private static String user="postgres";
	    private static String pass="lokouci";
	    private static Connection conec;
	    
	    public static Connection getConection(String  driver,String url,String user,String pass)throws SQLException {
	        try{
	            Class.forName(driver);
	            conec= DriverManager.getConnection(url,user,pass);
	            
	        } catch(Exception e){
	            System.out.println("Problema--> "+ e.getMessage());
	        }
	        return  conec;
	    }
	    
	    public static Connection getConection() throws SQLException {
	        return getConection(driver, url, user, pass);
	    }	

}
