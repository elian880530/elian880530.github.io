package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Oficial;
import domain.Usuario;


public class UsuarioDao {
	
	private Connection con;

	public UsuarioDao() {
		
	}
	
	public void insertar(Usuario usuario) throws SQLException{
			con = Conection.getConection();
			Statement st= con.createStatement();
			String sql = "INSERT INTO usuario (nombre,solapin,rol,facultad) VALUES('"+usuario.getNombre()+"','"+usuario.getSolapin()+"','"+usuario.getRol()+"','"+usuario.getFacultad()+"')";
			st.execute(sql);
			con.close();	
	}
		

public boolean estaElUsuario(String usuario) throws SQLException{
       	con=Conection.getConection();
       	Statement st= con.createStatement();
        String sql="Select * from oficial where usuario='"+usuario+"'";
        ResultSet rs = st.executeQuery(sql);
        con.close();
        if (rs.next()){
            actualizarDatos(usuario);
            return true;
        }
        return false;
   }





public void actualizarDatos(String nroserie) throws SQLException{
	
	con=Conection.getConection();
   	Statement st= con.createStatement();
   	
	String sql = "delete from registro where nroserie='"+nroserie+"'";
	st.executeUpdate(sql);
	
	con.close();
}


public ResultSet procesar(String usuario)throws SQLException{
	con=Conection.getConection();
   	Statement st= con.createStatement();
   	String sql = "select usuario,pass,rol from "+'"'+"usuariosregistrados"+'"'+" u where u.usuario ='"+usuario+"'";
   	ResultSet rs = st.executeQuery(sql);
  	return rs;
}
	public boolean agregarOficial(Oficial ofic)throws SQLException{
		con=Conection.getConection();
	   	Statement st= con.createStatement();
	   	
	   	String sql = "INSERT INTO usuariosregistrados (usuario,pass,rol) VALUES('"+ofic.getUsuario()+"','"+ofic.getSello()+"','"+ofic.getCargo()+"')";
	   	st.execute(sql);
	   	
	   	con.close();
		
		return false;
	}
	
	public void cambiarPass(String usuario,String anterior,String nueva)throws SQLException{
	con=Conection.getConection();
   	Statement st= con.createStatement();
   	
	String sql = "update usuariosregistrados set pass ='"+nueva+"'where usuario='"+usuario+"'";
	st.execute(sql);
	
	String sql2 = "update oficial set sello ='"+nueva+"'where usuario='"+usuario+"'";
	st.execute(sql2);
	
	con.close();
	}
	

}
