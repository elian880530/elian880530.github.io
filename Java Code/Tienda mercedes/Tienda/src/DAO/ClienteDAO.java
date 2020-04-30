package DAO;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Domain.Cliente;

public class ClienteDAO {
	
	private static Connection c = null;
	private static Statement st = null;
	private PreparedStatement stt = null;
	private static ResultSet rs = null;
	
	public ClienteDAO() {
		
	}
	
	public void insertarCliente(Cliente A) throws SQLException{
		c = conection.getConnection();
		stt = c.prepareStatement(
				"insert into cliente values(?,?,?,?,?,?,?)");    
		stt.setString(1,A.getNombre());
		stt.setString(2,A.getPrimer_apellido());
		stt.setString(3,A.getSegundo_apellido());
		stt.setBoolean(4,A.getSexo());
		stt.setInt(5,A.getCI());
		stt.setString(6,A.getEstado_civil());
		stt.setString(7,A.getDireccion());
                      
        stt.executeUpdate();
        c.close();
	}
	
	public static ResultSet ciCliente() throws SQLException{
        c=conection.getConnection();
        st=c.createStatement();
        String sql="Select cidentidad From cliente";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }
	
	public static Vector<String> Clientes()throws SQLException{		
		Vector<String> a = new Vector<String>();
		ResultSet rs = ciCliente();
		while(rs.next()){
			a.add(rs.getString("cidentidad"));
		}
		return a;
	}

}
