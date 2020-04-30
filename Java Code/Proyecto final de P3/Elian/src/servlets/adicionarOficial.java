package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.OficialDao;
import dao.UsuarioDao;
import domain.Oficial;


/**
 * Servlet implementation class adicionarOficial
 */
public class adicionarOficial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adicionarOficial() {
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
		//recoger los datos
		String nombre = request.getParameter("nombre");
		String usuario = request.getParameter("usuario");
		String grado = request.getParameter("grados");
		String cargo = request.getParameter("cargos");
		String sello = request.getParameter("sello");
		//validacion de los datos
		if(nombre==null || usuario==null || grado=="[........Seleccione.........]" ||cargo=="[........Seleccione.........]"){
			request.getRequestDispatcher("dontAcces.jsp").forward(
					request, response);
			return;
		}
		
		//insertar los datos de un oficial
		try{
			Oficial ofic = new Oficial(nombre, usuario, grado, cargo, sello);
			OficialDao DAO = new OficialDao();
			DAO.insertarOficial(ofic);
			UsuarioDao UDAO = new UsuarioDao();
			UDAO.agregarOficial(ofic);
			request.getRequestDispatcher("Insertar oficial.jsp").forward(
					request, response);
		}catch(Exception e)
		{
		request.getRequestDispatcher("dontAcces.jsp").forward(
					request, response);
		}
	}

}
