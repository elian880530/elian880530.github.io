package servelets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ClienteDAO;
import DAO.VendedorDAO;
import Domain.Cliente;
import Domain.Vendedor;

/**
 * Servlet implementation class insertarVendedor
 */
public class insertarVendedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertarVendedor() {
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
		// TODO Auto-generated method stub
		
		String nombre = request.getParameter("textfield");
		String primerApellido = request.getParameter("textfield2");
		String segundoApellido = request.getParameter("textfield3");
		String solapin = request.getParameter("textfield4");
		
		
		request.getRequestDispatcher("pagina4.htm").forward(request, response);
		
	
		
		//validar y hacer una contraaccion
		
		int sol = 0;
		
			
		try{
			sol = Integer.parseInt(solapin);
			
		}catch (Exception e) {
			// TODO: handle exception
			String mensaje = "debio haber puesto solo numeros en la parte de solapin";
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("insertarVendedorError.jsp").forward(request, response);
			
			
		}
		
		
		Vendedor A = new Vendedor(sol, nombre, primerApellido, segundoApellido);
		VendedorDAO B  =  new VendedorDAO(); 
		try {
			B.insertarVendedor(A);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
