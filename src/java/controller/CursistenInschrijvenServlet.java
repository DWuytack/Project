package controller;

import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CursistenInschrijven;
import model.CursistenInschrijvenDAO;
import model.Gebruiker;
import model.GebruikerDAO;
import model.Instellingen;

/**
 * Deze klasse bevat alle mogelijke knoppen naar gebruikersbewerkingen.
 *
 * @author Aaron
 */
@WebServlet(name = "CursistenInschrijvenServlet", urlPatterns = {"/CursistenInschrijvenServlet"})
public class CursistenInschrijvenServlet extends HttpServlet {

    CursistenInschrijvenDAO cursisteninschrijvenDAO = new CursistenInschrijvenDAO();

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
        ArrayList<CursistenInschrijven> cursisten = null;

        try {
            String actie = "";
            String zoekterm = request.getParameter("zoekterm");

                cursisten = cursisteninschrijvenDAO.cursistenLaden();
                session.setAttribute("lijstCursisten", cursisten);
                response.sendRedirect("CursistenInschrijven.jsp");            

            if (zoekterm != null && !zoekterm.equals("")) {
                actie = "Zoeken";
            }

            CursistenInschrijven cursisteninschrijven = new CursistenInschrijven();

            switch (actie) {
                case "Zoeken":
                    session.setAttribute("zoekterm", zoekterm);
                    cursisten = cursisteninschrijvenDAO.cursistenZoeken(zoekterm);
                    session.setAttribute("lijstCursisten", cursisten);
                    response.sendRedirect("GebruikersOverzicht.jsp");
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
