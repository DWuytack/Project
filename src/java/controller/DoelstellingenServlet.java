/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Doelstelling;
import model.DoelstellingDAO;

/**
 *
 * @author CURSIST
 */
@WebServlet(name = "DoelstellingenServlet", urlPatterns = {"/DoelstellingenServlet"})
public class DoelstellingenServlet extends HttpServlet {

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
        

        try {
        String actie="Edit doelstelling" ;
        String id = request.getParameter("idEdit");
        if (id == null) {
            id = request.getParameter("idDelete");
            actie="Delete doelstelling";
        }
        DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
        Doelstelling doelstelling = new Doelstelling();
 
        switch (actie) {
            
            
            case "Edit doelstelling":
               //doelstelling met id moet aangepast worden in database 
                HttpSession session = request.getSession(true);
                session.setAttribute("editID", id);
                response.sendRedirect("DoelstellingenOverzicht.jsp"); //logged-in page 
                break;
                
            case "Delete doelstelling":
                //doelstelling met id moet verwijderd worden in database
                
                break;

            case "Doelstelling toevoegen":
               
               //doelstellingDAO.doelstellingAanmaken(doelstelling);

                //session.setAttribute("ToegevoegdeDoelstelling");
                
                break;
                
            case "Doelstelling aanpassen":
              
                //doelstelling.doelstellingAanpassen();

                //session.setAttribute("AangepasteDoelstelling");
                response.sendRedirect("DoelstellingsBewerking.jsp");
                break;
                
            case "Doelstelling verwijderen":
                
                //doelstellingDAO.doelstellingVerwijderen(doelstelling);

                //session.setAttribute("VerwijderdeDoelstelling");
                response.sendRedirect("DoelstellingsBewerking.jsp");
                break;
            
        }

        } catch (Throwable theException) {
            
        }
        

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
        processRequest(request, response);
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

}
