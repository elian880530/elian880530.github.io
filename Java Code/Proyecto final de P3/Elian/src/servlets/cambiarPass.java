package servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;

/**
 * Servlet implementation class cambiarPass
 */
public class cambiarPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cambiarPass() {
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
		String usuario = (String) request.getSession().getAttribute("usuario");
		String anterior = request.getParameter("anterior");
		String nueva = request.getParameter("nueva");

		
		try {
			UsuarioDao DAO = new UsuarioDao();
			ResultSet rs = DAO.procesar(usuario);
			rs.next();
			String pass = rs.getString("pass");
			System.out.println(pass);
			
			if(anterior.equals(pass)){
				System.out.println(anterior.equals(pass));
				DAO.cambiarPass(usuario, anterior, nueva);
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("mensaje", "Su contraseña ha sido cambiada con éxito");
				request.getRequestDispatcher("bien.jsp").forward(request, response);
				return;
			}else{
					request.getSession().setAttribute("usuario", usuario);
					request.getSession().setAttribute("error", "Su contraseña no ha podido ser cambiada. No coincide con la anterior");
					request.getRequestDispatcher("error.jsp").forward(request, response);
					return;
					
				}
				
			
		} catch (Exception e) {
			request.getSession().setAttribute("error", "Su contraseña no ha podido ser cambiada");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
	}

}
