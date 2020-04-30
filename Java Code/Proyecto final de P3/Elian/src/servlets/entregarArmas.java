package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArmaOficialDao;
import dao.OficialDao;
import domain.ArmaOficial;
import domain.Entrega;
import domain.Oficial;

/**
 * Servlet implementation class entregarArmas
 */
public class entregarArmas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public entregarArmas() {
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
		String usuario = request.getParameter("usuarios");
		String nroserie = request.getParameter("nombres");
		System.out.println(usuario);
		System.out.println(nroserie);
		//validar los datos
		if(usuario.equals("0") || nroserie.equals("0")){
			request.getRequestDispatcher("dontAcces.jsp").forward(request, response);
			return;
		}
		
		//aqui se entrega armas
		try{
			Entrega entreg = new Entrega(usuario,nroserie);
			ArmaOficialDao DAO = new ArmaOficialDao();
			DAO.entregarArma(entreg);
			request.getRequestDispatcher("Entregar arma.jsp").forward(
					request, response);
		}catch(Exception e)
		{
		request.getRequestDispatcher("dontAcces.jsp").forward(
					request, response);
		}		
	}

}
