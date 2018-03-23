/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Doelstelling;
import model.DoelstellingDAO;

/**
 * Servlet voor alle handelingen van doelstellingen.
 *
 * @author Ewout Phlips
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
            String actie = "";
            String editID = request.getParameter("idEdit");
            String cancelID = request.getParameter("idCancel");
            String saveID = request.getParameter("idSave");
            String deleteID = request.getParameter("idDelete");

            if (editID != null) {
                actie = "Edit doelstelling";
            }
            if (cancelID != null) {
                actie = "Cancel doelstelling";
            }
            if (saveID != null) {
                actie = "Save doelstelling";
            }
            if (deleteID != null) {
                actie = "Delete doelstelling";
            }

            DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
            Doelstelling doelstelling = new Doelstelling();

            switch (actie) {

                case "Edit doelstelling":
                    //doelstelling met id moet aangepast worden in database 
                    HttpSession session = request.getSession(true);
                    session.setAttribute("editID", editID);
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("DoelstellingenOverzicht.jsp"); //logged-in page 
                    break;

                case "Delete doelstelling":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");
                    session.removeAttribute("lijstDoelstellingen");

                    doelstellingDAO.doelstellingVerwijderen(doelstelling);
                    ArrayList<Doelstelling> doelstellingen = doelstellingDAO.doelstellingenLaden(/*1*/);

                    session.setAttribute("lijstDoelstellingen", doelstellingen);
                    response.sendRedirect("DoelstellingenOverzicht.jsp");
                    break;

                case "Cancel doelstelling":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("DoelstellingenOverzicht.jsp"); //logged-in page 
                    break;

                case "Save doelstelling":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("DoelstellingenOverzicht.jsp"); //logged-in page 
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
