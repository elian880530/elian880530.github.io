package servelets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CarroDAO;
import DAO.ClienteDAO;
import Domain.Carro;
import Domain.Cliente;

/**
 * Servlet implementation class insertarCliente
 */
public class insertarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertarCliente() {
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
		String sexo = request.getParameter("menu2");
		String carn = request.getParameter("textfield4");
		String estadoCivil = request.getParameter("menu1");
		String direccion = request.getParameter("textfield6");
		
		
		request.getRequestDispatcher("pagina3.htm").forward(request, response);
	
		
		//validar y hacer una contraaccion
		
		int carnet = 0;
		
		boolean sex;
		if(sexo.equals("masculino"))
			sex =true;
		else 
			sex=false;
		
		
		try{
			carnet = Integer.parseInt(carn);
			
		}catch (Exception e) {
			// TODO: handle exception
			String mensaje = "debio haber puesto solo numeros en la parte de carnet";
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("insertarClienteError.jsp").forward(request, response);
			
			
		}
		
		
		Cliente A = new Cliente(carnet, nombre, primerApellido, segundoApellido, direccion, sex, estadoCivil);
		ClienteDAO B  =  new ClienteDAO(); 
		try {
			B.insertarCliente(A);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}

}
