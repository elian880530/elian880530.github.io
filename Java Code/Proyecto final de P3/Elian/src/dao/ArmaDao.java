package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Arma;
import domain.Oficial;

public class ArmaDao {

	private Connection con;

	  public ArmaDao() {
		// TODO Auto-generated constructor stub
	}
			
	  public void insertarArma(Arma arm) throws SQLException{
			con = Conection.getConection();
			Statement st= con.createStatement();
			String sql = "INSERT INTO arma (nombre,tipo,nroserie) VALUES('"+arm.getNombre()+"','"+arm.getTipo()+"','"+arm.getNroserie()+"')";
			st.execute(sql);
			con.close();	
	}
	  
	  public List<Arma> obtenerArmas() throws SQLException{

	        List<Arma> listado = new ArrayList<Arma>();
	        con = Conection.getConection();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM arma");
	        
	        ResultSet rs = ps.executeQuery();
	        Arma arm = null;
	        while (rs.next()){
	            String nombre = rs.getString(1);
	            String tipo = rs.getString(2);
	            String nroserie = rs.getString(3);
	            arm = new Arma(nombre, tipo, nroserie);
	            listado.add(arm);
	        }
	        return listado;
	    }
	
}
