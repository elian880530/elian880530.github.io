/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servelet;

import DAO.CorreoDAO;
import Domain.Correo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elian
 */
public class InsertarDatosCorreo extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertarDatosCorreo</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertarDatosCorreo at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      //  processRequest(request, response);

                String De = request.getParameter("De");
		String Para = request.getParameter("Para");
		String CC = request.getParameter("CC");
		String Asunto = request.getParameter("Asunto");
                String Mensaje = request.getParameter("Mensaje");

                 if (De == null || De.equals("") || Para == null || Para.equals("")
                || CC == null || CC.equals("") || Asunto == null || Asunto.equals("")
                || Mensaje == null || Mensaje.equals("")) {
           request.getRequestDispatcher("error.html").forward(request, response);
            return;
        }



		Correo A = new Correo(De, Para, CC, Asunto, Mensaje);
		CorreoDAO B  =  new CorreoDAO();
		try {
			B.insertarCorreo(A);
                        request.getRequestDispatcher("MostrarDatos.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
                       System.out.println(e.getMessage());
		}

                response.sendRedirect("MostrarDatos.jsp");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
