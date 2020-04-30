package servelets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CarroDAO;
import Domain.Carro;

/**
 * Servlet implementation class insertarCarro
 */
public class insertarCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertarCarro() {
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


		String marca = request.getParameter("textfield4");
		String color = request.getParameter("textfield3");
		String precio = request.getParameter("textfield2");
		String chapa = request.getParameter("textfield");
		
		
		request.getRequestDispatcher("pagina2.htm").forward(request, response);
		
	
		
		//validar y hacer una contraaccion
		
		int prec = 0;
		int chap = 0;
		try{
			prec = Integer.parseInt(precio);
			chap = Integer.parseInt(chapa);
		}catch (Exception e) {
			// TODO: handle exception
			String mensaje = "debio haber puesto solo numeros en la parte de precio y chapa";
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("insertarCarroError.jsp").forward(request, response);
			
			
		}
		
		
		Carro A = new Carro(chap,prec , marca, color);
		CarroDAO B  =  new CarroDAO(); 
		try {
			B.insertarCarro(A);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
