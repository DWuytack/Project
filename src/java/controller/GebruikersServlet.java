
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GebruikerDAO;

/**
 *  Deze klasse bevat alle mogelijke knoppen naar gebruikersbewerkingen.
 *  @author Gil
 */
@WebServlet(name = "GebruikersServlet", urlPatterns = {"/GebruikersServlet"})
public class GebruikersServlet extends HttpServlet {

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
        String actie = request.getParameter("actie");
        HttpSession session = request.getSession(true);
        GebruikerDAO gebruikerDAO = new GebruikerDAO();

        switch (actie) {

            case "Cursist toevoegen":
                
                gebruikerDAO.cursistAanmaken();

                //session.setAttribute("ToegevoegdeCursist");
                response.sendRedirect("CursistenOverzicht.jsp");
                break;
                
            case "Cursist aanpassen":
              
                //gebruikerDAO.cursistAanpassen();

                //session.setAttribute("AangemaakteCursist");
                response.sendRedirect("CursistenOverzicht.jsp");
                break;
                
            case "Cursist verwijderen":
                
                //gebruikerDAO.cursistVerwijderen(gebruiker);

                //session.setAttribute("VerwijderdeCursist");
                response.sendRedirect("CursistenOverzicht.jsp");
                break;
                
            case "Gebruiker toevoegen":
                
                gebruikerDAO.gebruikerAanmaken();

                //session.setAttribute("AangemaakteCursist");
                response.sendRedirect("GebruikersOverzicht.jsp");
                break;
           
            case "Gebruiker aanpassen":
                
                //gebruikerDAO.gebruikerAanpassen(gebruiker);

                //session.setAttribute("AangepasteGebruiker");
                response.sendRedirect("GebruikersOverzicht.jsp");
                break;
                
            case "Gebruiker verwijderen":
                
                //gebruikerDAO.gebruikerVerwijderen(gebruiker);

                //session.setAttribute("VerwijderdeGebruiker");
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
