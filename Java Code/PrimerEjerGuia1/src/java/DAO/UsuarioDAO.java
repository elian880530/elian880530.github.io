/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Domain.Usuario;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Elian
 */
public class UsuarioDAO {

    private static Connection c = null;
	private static PreparedStatement st = null;
	private PreparedStatement stt = null;
	private static ResultSet rs = null;

    public UsuarioDAO() {
    }

    public void insertarUsuario(Usuario A) throws SQLException{
		c = conection.getConnection();

                stt = c.prepareStatement("insert into usuario values(?,?)");

                stt.setString(1,A.getUsuario());
		stt.setString(2,A.getPassword());
		stt.executeUpdate();
                c.close();
	}

   /* public static int ContadorUsuario(String usuario) throws SQLException{
        c=conection.getConnection();
        st = c.prepareStatement("SELECT COUNT(usuario) FROM usuario WHERE usuario = ?");
        st.setString(1,usuario);
        rs=st.executeQuery();
        c.close();
        rs.next();
        return Integer.parseInt(rs.getString(0));
   }*/

}
