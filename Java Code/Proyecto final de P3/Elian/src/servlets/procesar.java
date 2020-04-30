package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UsuarioDao;

/**
 * Servlet implementation class procesar
 */
public class procesar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public procesar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario=request.getParameter("user");
		String password=request.getParameter("pass");

		try{
			UsuarioDao DAO = new UsuarioDao();
			ResultSet result  = DAO.procesar(usuario);
		   	result.next();
			String u = result.getString("usuario");
			String p = result.getString("pass");
			String r = result.getString("rol");
			if(u.equals(usuario) && p.equals(password)){
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("rol", r);
				request.getRequestDispatcher("Insertar oficial.jsp").forward(request, response);
			}else{
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("error", "Usuario o contraseña incorrecto. Verifique los datos");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
		}catch (SQLException e){
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().setAttribute("error", "Usuario o contraseña incorrecto. Verifique los datos");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
