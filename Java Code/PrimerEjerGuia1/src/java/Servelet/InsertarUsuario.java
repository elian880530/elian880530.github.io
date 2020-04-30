/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servelet;

import DAO.*;

import Domain.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elian
 */
public class InsertarUsuario extends HttpServlet {
   
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
            out.println("<title>Servlet InsertarUsuario</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertarUsuario at " + request.getContextPath () + "</h1>");
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
    @SuppressWarnings("static-access")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            //processRequest(request, response);
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            UsuarioDAO aux = new UsuarioDAO();
            Usuario u= new Usuario(usuario, password);

            if (usuario.equals("") || usuario == null || password.equals("") || password == null) {
                request.getRequestDispatcher("error.html").forward(request, response);
            }

            aux.insertarUsuario(u);
              
            request.getRequestDispatcher("InsertarUsuario.jsp").forward(request, response);
        
        } catch (SQLException ex) {
            Logger.getLogger(InsertarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
             request.getRequestDispatcher("error.html").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
