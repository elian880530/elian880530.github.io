package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Domain.Cliente;
import Domain.Vendedor;

public class VendedorDAO {

	private static Connection c = null;
	private static Statement st = null;
	private PreparedStatement stt = null;
	private static ResultSet rs = null;
	
	public VendedorDAO() {
		
	}
	
	public void insertarVendedor(Vendedor A) throws SQLException{
		c = conection.getConnection();
		stt = c.prepareStatement(
				"insert into vendedor values(?,?,?,?)");    
		stt.setString(1,A.getNombre());
		stt.setString(2,A.getPrimer_apellido());
		stt.setString(3,A.getSegundo_apellido());
		stt.setInt(4,A.getSolapin());
		
                      
        stt.executeUpdate();
        c.close();
	}
	
	
	
	public void eliminarVendedor(int solapin) throws SQLException{
	    c=conection.getConnection();
             
	        stt = c.prepareStatement("Delete From venta Where solapin = ?");
            stt.setInt(1, solapin);
            stt.executeUpdate();
	        stt = c.prepareStatement("Delete From vendedor Where solapin = ?");
	        stt.setInt(1, solapin);
	        stt.executeUpdate();
	        c.close();   

	}
	public static ResultSet ciVendedor() throws SQLException{
        c=conection.getConnection();
        st=c.createStatement();
        String sql="Select solapin From vendedor";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }
	
	public static Vector<String> Vendedores()throws SQLException{		
		Vector<String> a = new Vector<String>();
		ResultSet rs = ciVendedor();
		while(rs.next()){
			a.add(rs.getString("solapin"));
		}
		return a;
	}
	
}
