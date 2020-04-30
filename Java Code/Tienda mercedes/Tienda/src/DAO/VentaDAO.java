package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Domain.Venta;

public class VentaDAO {

	private Connection c = null;
	private Statement st = null;
	private PreparedStatement stt = null;
	private ResultSet rs = null;
	
	public VentaDAO() {
		
	}
	
	public void insertarVenta(Venta A) throws SQLException{
		c = conection.getConnection();
		stt = c.prepareStatement(
				"insert into venta values(?,?,?)");    
		stt.setInt(1,A.getSolapin_trabajador());
		stt.setInt(2,A.getCI_cliente());
		stt.setInt(3,A.getChapilla_carro());
		
		
                      
        stt.executeUpdate();
        c.commit();
        c.close();
	}
	
	public ResultSet todo_venta() throws SQLException{
        c=conection.getConnection();
        st=c.createStatement();
        String sql="Select * From venta";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }
}
