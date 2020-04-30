package servelets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VendedorDAO;
import DAO.VentaDAO;
import Domain.Vendedor;
import Domain.Venta;

/**
 * Servlet implementation class insertarVenta
 */
public class insertarVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertarVenta() {
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
		
		String SolapinVendedor = request.getParameter("menu1");
		String CI_Cliente = request.getParameter("menu2");
		String ChapillaCarro = request.getParameter("menu3");
		
		
		request.getRequestDispatcher("paginaVenta.jsp").forward(request, response);
		
		
	
		
		//validar y hacer una contraaccion
		
		int sol = 0;
		int carnet=0;
		int chapilla=0;
			
		try{
			sol = Integer.parseInt(SolapinVendedor);
			carnet=Integer.parseInt(CI_Cliente);
			chapilla=Integer.parseInt(ChapillaCarro);
			
		}catch (Exception e) {
			// TODO: handle exception
			String mensaje = "debio haber puesto solo numeros en la parte de solapin,carnet y chapilla";
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("insertarVentaError.jsp").forward(request, response);
			
			
		}
		/*
		System.out.println(sol);
		System.out.println(carnet);
		System.out.println(chapilla);
		*/
		
		Venta A = new Venta( carnet,sol, chapilla);
		VentaDAO B  =  new VentaDAO(); 
		try {
			B.insertarVenta(A);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}

}
