/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.ServletConfig;

/**
 *
 * @author Erick
 */
public class ConsultarAlumnos extends HttpServlet {
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    public void init(ServletConfig scg) throws ServletException{
        String url="jdbc:mysql:3306//localhost/alumnos";
        String username="root";
        String password="velaz142310";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            url="jdbc:mysql://localhost/alumnos";
            con=DriverManager.getConnection(url, username, password);
            set=con.createStatement();
            
            System.out.println("Se conecto a la base de Datos");
        }catch (Exception e){
            System.out.println("No se conecto a la base de Datos");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Tabla de Alumnos de Batiz</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Alumnos Batiz</h1>"
                        + "<br>"
                        + "<table border='2'>"
                            + "<tr>"
                                + "<th>Boleta</th>"
                                + "<th>Nombre del Alumno</th>"
                                + "<th>Telefono</th>"
                            + "</tr>");
            try{
                int bol;
                String nom, apellidopaterno,apellidomaterno,tel;
                String q="select *from alumnosbatiz ";
                set=con.createStatement();
                rs=set.executeQuery(q);
                
                while(rs.next()){
                    bol= rs.getInt("boleta");
                    nom=rs.getString("nombre");
                    apellidopaterno=rs.getString("appat");
                    apellidomaterno=rs.getString("apmat");
                    tel=rs.getString("telefono");
                    
                    out.println("<tr>"
                                + "<td>"+bol+"</td>"
                                + "<td>"+nom+" "+apellidopaterno+" "+apellidomaterno+"</td>"
                                + "<td>"+tel+"</td>"        
                            + "</tr>");
                           
                                
                }
                
                rs.close();
                set.close();
                     
            }catch(Exception e){
                System.out.println("Error al conectar la tabla");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void destroy(){
        try{
            con.close();
        }catch(Exception e){
            super.destroy();
        }
    }
}
