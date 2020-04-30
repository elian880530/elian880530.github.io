package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import domain.Oficial;
import java.util.ArrayList;
import dao.Conection;

public class OficialDao {
	
	
	 private Connection con;

   public OficialDao() {
		
	}
		
	
	
	public void insertarOficial(Oficial ofic) throws SQLException{
		con = Conection.getConection();
		Statement st= con.createStatement();
		String sql = "INSERT INTO oficial (nombre,usuario,grado,cargo,sello) VALUES('"+ofic.getNombre()+"','"+ofic.getUsuario()+"','"+ofic.getGrado()+"','"+ofic.getCargo()+"','"+ofic.getSello()+"')";
		st.execute(sql);
		con.close();	
}
	
	public List<Oficial> obtenerOficiales() throws SQLException{

        List<Oficial> listado = new ArrayList<Oficial>();
        con = Conection.getConection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM oficial");
        
        ResultSet rs = ps.executeQuery();
        Oficial ofic = null;
        while (rs.next()){
            String nombre = rs.getString(1);
            String usuario = rs.getString(2);
            String grado = rs.getString(3);
            String cargo = rs.getString(4);
            String sello = rs.getString(5);
           
            ofic = new Oficial(nombre, usuario, grado, cargo, sello);
            listado.add(ofic);
        }
        return listado;
    }
	
}
