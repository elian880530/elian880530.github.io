package servelets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CarroDAO;
import DAO.VentaDAO;
import Domain.Carro;
import Domain.Venta;

/**
 * Servlet implementation class modificarCostoCarro
 */
public class modificarCostoCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarCostoCarro() {
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
		
		String nombre = request.getParameter("menu1");
		String nuevo_valor_venta = request.getParameter("textfield2");
			
		request.getRequestDispatcher("pagina5.jsp").forward(request, response);
		
		//validar y hacer una contraaccion
		
		int nuevo_valor = 0;
		
			
		try{
			nuevo_valor = Integer.parseInt(nuevo_valor_venta);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			String mensaje = "debio haber puesto solo números en la parte de nuevo costo de venta";
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("modificarCostoCarro.jsp").forward(request, response);
			
			
		}	
				
		CarroDAO B  =  new CarroDAO(); 
		try {
			B.modificarCostoCarro(nuevo_valor, nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
