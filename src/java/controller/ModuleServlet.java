/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ModuleDAO;


/**
 *
 * @author Davino
 */
@WebServlet(name = "ModuleServlet", urlPatterns = {"/ModuleServlet"})
public class ModuleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try  {
            
        String actie = request.getParameter("actie");
        HttpSession session = request.getSession(true);
        ModuleDAO moduleDAO = new ModuleDAO();

        switch (actie) {

            case "Module aanmaken":
               
               //moduleDAO.moduleAanmaken(module);

                //session.setAttribute("AangemaakteModule");
                
                break;
                
            case "Module aanpassen":
              
                //moduleDAO.moduleAanpassen();

                //session.setAttribute("AangepasteModule");
                response.sendRedirect("Module.jsp");
                break;
                
            case "Module verwijderen":
                
                //moduleDAO.moduleVerwijderen(module);

                //session.setAttribute("VerwijderdeModule");
                response.sendRedirect("Module.jsp");
                break;
                
       
        }
    
           } catch (Throwable theException) {
            
        }

    }
}


