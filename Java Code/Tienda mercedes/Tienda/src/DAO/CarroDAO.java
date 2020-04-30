package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Domain.Carro;
import Domain.Cliente;



public class CarroDAO {
	
	private static Connection c = null;
	private static Statement st = null;
	private PreparedStatement stt = null;
	private static ResultSet rs = null;
	
	
	
	public CarroDAO() {
	
	}

	public void insertarCarro(Carro A) throws SQLException{
		c = conection.getConnection();
		stt = c.prepareStatement(
				"insert into carro values(?,?,?,?)");    
		stt.setString(1,A.getNombre());
		stt.setInt(2,A.getId());
		stt.setString(3,A.getColor());
		stt.setInt(4,A.getCosto());
                      
        stt.executeUpdate();
        c.close();
	}
	
	public ResultSet todo_carro() throws SQLException{
        c=conection.getConnection();
        st=c.createStatement();
        String sql="Select * From carro";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }
	
	public void modificarCostoCarro(int costo,String nombre)throws SQLException{
        c=conection.getConnection();
        stt = c.prepareStatement(
                "Update carro Set costo=? where nombre = ?");
        stt.setInt(1, costo);
        stt.setString(2, nombre);       
        stt.executeUpdate();
        c.close();
}

	public static ResultSet ciCarro() throws SQLException{
        c=conection.getConnection();
        st=c.createStatement();
        String sql="Select identificador From carro";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }
	
	public static Vector<String> Carros()throws SQLException{		
		Vector<String> a = new Vector<String>();
		ResultSet rs = ciCarro();
		while(rs.next()){
			a.add(rs.getString("identificador"));
		}
		return a;
	}
	
	public static ResultSet marcaCarro() throws SQLException{
        c=conection.getConnection();
        st=c.createStatement();
        String sql="Select nombre From carro";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }
	
	public static Vector<String> Carrosmarca()throws SQLException{		
		Vector<String> a = new Vector<String>();
		ResultSet rs = marcaCarro();
		while(rs.next()){
			a.add(rs.getString("nombre"));
		}
		return a;
	}
	
}
