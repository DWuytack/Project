/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gebruiker;
import model.Instellingen;
import model.BeoordelingssoortDAO;
import model.Beoordelingssoort;
/**
 *
 * @author CURSIST
 */
@WebServlet(name = "BeoordelingssoortServlet", urlPatterns = {"/BeoordelingssoortServlet"})
public class BeoordelingssoortServlet extends HttpServlet {

    BeoordelingssoortDAO beoordelingssoortDAO = new BeoordelingssoortDAO();
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

        HttpSession session = request.getSession(true);
        ArrayList<Beoordelingssoort> beoordelingssoorten = null;

        try {
            String actie = "";
            String editID = request.getParameter("idEdit");
            String addID = request.getParameter("addID");
            String cancelID = request.getParameter("idCancel");
            String saveID = request.getParameter("idSave");
            String deleteID = request.getParameter("idDelete");

            
            if (editID != null) {
                actie = "Edit gebruiker";
            }
            if (cancelID != null) {
                actie = "Cancel gebruiker";
            }
            if (saveID != null) {
                actie = "Save gebruiker";
            }
            if (deleteID != null) {
                actie = "Delete gebruiker";
            }
            if (addID != null) {
                actie = "Add gebruiker";
            }

            Beoordelingssoort beoordelingssoort = new Beoordelingssoort();

            switch (actie) {
                case "Edit beoordelingssoort":
                    session.setAttribute("editID", editID);
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    beoordelingssoorten = beoordelingssoortDAO.beoordelingssoortenLaden();
                    session.setAttribute("beoordelingssoorten", beoordelingssoort);
                    response.sendRedirect("TypeScoreOverzicht.jsp"); //logged-in page 
                    break;

                case "Delete beoordelingssoort":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");
                    beoordelingssoortDAO.beoordelingssoortVerwijderen(beoordelingssoort);
                    beoordelingssoorten = beoordelingssoortDAO.beoordelingssoortenLaden();   

                    session.setAttribute("beoordelingssoorten", beoordelingssoort);
                    
                    response.sendRedirect("TypeScoreOverzicht.jsp");
                    break;

                case "Cancel beoordelingssoort":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("TypeScoreOverzicht.jsp"); //logged-in page 
                    break;

                case "Save beoordelingssoort":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    int id = Integer.parseInt(saveID);
                    beoordelingssoort.setNaam(request.getParameter("naam"));
                    beoordelingssoort.setBeschrijving(request.getParameter("beschrijving"));
                    beoordelingssoort.setWaarde(Integer.parseInt("Waarde"));
                    beoordelingssoortDAO.beoordelingssoortAanpassen(id, beoordelingssoort);
                    beoordelingssoorten = beoordelingssoortDAO.beoordelingssoortenLaden();
                    session.setAttribute("lijstGebruikers", beoordelingssoorten);
                    response.sendRedirect("TypeScoreOverzicht.jsp");
                    session.removeAttribute("saveID");
                    break;

                case "add beoordelingssoort":
                    session.setAttribute("addId", addID);
                    beoordelingssoortDAO.beoordelingssoortAanmaken(beoordelingssoort);
                    beoordelingssoorten = beoordelingssoortDAO.beoordelingssoortenLaden();
                    session.setAttribute("lijstGebruikers", beoordelingssoorten);
                    response.sendRedirect("TypeScoreOverzicht.jsp");
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
