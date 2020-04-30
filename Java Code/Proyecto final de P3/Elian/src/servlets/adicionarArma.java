package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArmaDao;
import dao.OficialDao;
import domain.Arma;
import domain.Oficial;

/**
 * Servlet implementation class adicionarArma
 */
public class adicionarArma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adicionarArma() {
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
		//aqui se recogen los datos
		String nombre = request.getParameter("nombres");
		String tipo = request.getParameter("tipos");
		String nroserie = request.getParameter("nroSerie");
		//validacion de los datos en el servidor
		if(nombre.equals("[........Seleccione.........]") || tipo.equals("[........Seleccione.........]") || nroserie.equals("") || nroserie==null){
			request.getRequestDispatcher("dontAcces.jsp").forward(
					request, response);
			return;
		}
		
		
		try{
			Arma arm = new Arma(nombre, tipo, nroserie);
			ArmaDao DAO = new ArmaDao();
			DAO.insertarArma(arm);			
			request.getRequestDispatcher("Insertar arma.jsp").forward(
					request, response);
		}catch(Exception e)
		{
		request.getRequestDispatcher("dontAcces.jsp").forward(
					request, response);
		}
	}
		
	}

