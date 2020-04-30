package DAO;

import Domain.Correo;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



public class CorreoDAO {

	private static Connection c = null;
	private static Statement st = null;
	private PreparedStatement stt = null;
	private static ResultSet rs = null;

    public CorreoDAO() {
    }

	

	public void insertarCorreo(Correo A) throws SQLException{
		c = conection.getConnection();

                stt = c.prepareStatement("insert into correo values(?,?,?,?,?)");

                stt.setString(1,A.getDe());
		stt.setString(2,A.getPara());
		stt.setString(3,A.getCc());
		stt.setString(4,A.getAsunto());
                stt.setString(5,A.getMensaje());
		

        stt.executeUpdate();
        c.close();
	}

	public static ResultSet DatosCorreo() throws SQLException{
        c=conection.getConnection();
        st=c.createStatement();
        String sql="Select * From correo";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }

	public static Vector<Correo> ListaCorreos()throws SQLException{
		Vector<Correo> a = new Vector<Correo>();
		ResultSet rs = DatosCorreo();
		while(rs.next()){

		String de=rs.getString("de");
                String para=rs.getString("para");
                String cc=rs.getString("cc");
                String asunto=rs.getString("asunto");
                String mensaje=rs.getString("mensaje");
                Correo correoAux = new Correo(de, para, cc, asunto, mensaje);

                a.add(correoAux);

		}
		return a;
	}

         public void eliminarCorreo(String de) throws SQLException{
	    c=conection.getConnection();

	        stt = c.prepareStatement("Delete From correo Where de = ?");
                stt.setString(1,de);
                stt.executeUpdate();
	        c.close();

	}

         public static ResultSet deCorreo() throws SQLException{
        c=conection.getConnection();
        st=c.createStatement();
        String sql="Select de From correo";
        rs=st.executeQuery(sql);
        c.close();
        return rs;
   }

	public static Vector<String> ListaDe()throws SQLException{
		Vector<String> a = new Vector<String>();
		ResultSet rs = deCorreo();
		while(rs.next()){
			a.add(rs.getString("de"));
		}
		return a;
	}

        public void modificarCorreo(String asunto,String de)throws SQLException{
        c=conection.getConnection();
        stt = c.prepareStatement(
                "Update correo Set asunto=? where de = ?");
        stt.setString(1, asunto);
        stt.setString(2, de);
        stt.executeUpdate();
        c.close();
}

}

