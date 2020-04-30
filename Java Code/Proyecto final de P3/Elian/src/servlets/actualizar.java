package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;

/**
 * Servlet implementation class actualizar
 */
public class actualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualizar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//recoger los datos que vienen del formulario
		String nroserie = request.getParameter("nroserie");
		
		
		//aqui se validan los datos pero como cuando la tabla fue llenada los usuarios son existentes  !!!!
		
		
		
		//aqui hacemos lo que queremos hacer
		
		try{
			UsuarioDao DAO = new UsuarioDao();
			DAO.actualizarDatos(nroserie);
			request.getRequestDispatcher("Mostrar registro.jsp").forward(
					request, response);
		}catch(Exception e)
		{
		request.getRequestDispatcher("dontAcces.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger los datos que vienen del formulario
		
		
		
	}

}
