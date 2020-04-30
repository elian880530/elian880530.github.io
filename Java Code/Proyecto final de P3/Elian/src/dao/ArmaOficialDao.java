package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;
import domain.Arma;
import domain.ArmaOficial;
import domain.Entrega;
import domain.Oficial;
import domain.Usuario;

public class ArmaOficialDao {
	private Connection con;
	private static Connection c = null;
	private static Statement st = null;
	private PreparedStatement stt = null;
	private static ResultSet rs = null;
	
	
	public ArmaOficialDao() {
		super();
			}

	public void Registro(Oficial A,Arma B) throws SQLException{
		c = Conection.getConection();
		stt = c.prepareStatement("insert into registro values(?,?)");    
		stt.setString(1,A.getUsuario());
		stt.setString(2,B.getNroserie());
		stt.executeUpdate();
        c.close();
	}


	public static ResultSet OficialUsuario() throws SQLException{
		c = Conection.getConection();
        st=c.createStatement();
        String sql="select oficial.usuario  from oficial where usuario not in (Select registro.usuario from registro)";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }
	
	public static Vector<String> Usuarios()throws SQLException{		
		Vector<String> a = new Vector<String>();
		ResultSet rs = OficialUsuario();
		while(rs.next()){
			a.add(rs.getString("usuario"));
		}
		return a;
	}


	public static ResultSet ArmaCodigo() throws SQLException{
		c = Conection.getConection();
        st=c.createStatement();
        String sql="select arma.nroserie  from arma where nroserie not in (Select registro.nroserie from registro)";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }
	
	public static Vector<String> Arma()throws SQLException{		
		Vector<String> a = new Vector<String>();
		ResultSet rs = ArmaCodigo();
		while(rs.next()){
			a.add(rs.getString("nroserie"));
		}
		return a;
	}



	public void entregarArma(Entrega entreg) throws SQLException{
		con = Conection.getConection();
		Statement st= con.createStatement();
		
		String sql = "INSERT INTO registro (usuario,nroserie) VALUES('"+entreg.getUsuario()+"','"+entreg.getNroserie()+"')";
		st.execute(sql);
		
		String fecha = Calendar.getInstance().DAY_OF_MONTH + Calendar.getInstance().MONTH + Calendar.getInstance().YEAR + "";
		String hora = Calendar.getInstance().HOUR_OF_DAY + Calendar.getInstance().MINUTE + Calendar.getInstance().SECOND+ "";
		String sql2 = "INSERT INTO registrofecha (usuario,nroserie,fecha,hora) VALUES('"+entreg.getUsuario()+"','"+entreg.getNroserie()+"','"+fecha+"','"+hora+"')";
		st.execute(sql2);
		con.close();
}
	
}
