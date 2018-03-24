package controller;

import java.io.IOException;

import java.text.DateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gebruiker;
import model.GebruikerDAO;
import model.Instellingen;

/**
 * Deze klasse bevat alle mogelijke knoppen naar gebruikersbewerkingen.
 *
 * @author Gil
 */
@WebServlet(name = "GebruikersServlet", urlPatterns = {"/GebruikersServlet"})
public class GebruikersServlet extends HttpServlet {

    GebruikerDAO gebruikerDAO = new GebruikerDAO();

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
        ArrayList<Gebruiker> gebruikers = null;

        try {
            String actie = "";
            String editID = request.getParameter("idEdit");
            String cancelID = request.getParameter("idCancel");
            String saveID = request.getParameter("idSave");
            String deleteID = request.getParameter("idDelete");
            String eerste = request.getParameter("Eerste");
            String vorige = request.getParameter("Vorige");
            String volgende = request.getParameter("Volgende");
            String laatste = request.getParameter("Laatste");
            int bladz = (int) session.getAttribute("bladzijde");
            int aantalGebruikers = (int) session.getAttribute("aantalRecords");

            if (eerste != null) {
                bladz = 1;
                session.setAttribute("bladzijde", bladz);
                int getoondeGebruikers = Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeGebruikers > aantalGebruikers) {
                    getoondeGebruikers = aantalGebruikers;
                }
                session.setAttribute("getoondeGebruikers", getoondeGebruikers);
                gebruikers = gebruikerDAO.gebruikersLaden(bladz);
                session.setAttribute("lijstGebruikers", gebruikers);
                response.sendRedirect("GebruikersOverzicht.jsp");
            }

            if (vorige != null) {
                bladz--;
                if (bladz < 1) {
                    bladz = 1;
                }
                int getoondeGebruikers = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeGebruikers > aantalGebruikers) {
                    getoondeGebruikers = aantalGebruikers;
                }
                session.setAttribute("getoondeGebruikers", getoondeGebruikers);
                session.setAttribute("bladzijde", bladz);
                gebruikers = gebruikerDAO.gebruikersLaden(bladz);
                session.setAttribute("lijstGebruikers", gebruikers);
                response.sendRedirect("GebruikersOverzicht.jsp");
            }

            if (volgende != null) {
                bladz++;
                if (bladz > ((aantalGebruikers / 5) + 1)) {
                    bladz--;
                }
                int getoondeGebruikers = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeGebruikers > aantalGebruikers) {
                    getoondeGebruikers = aantalGebruikers;
                }
                session.setAttribute("getoondeGebruikers", getoondeGebruikers);
                session.setAttribute("bladzijde", bladz);
                gebruikers = gebruikerDAO.gebruikersLaden(bladz);
                session.setAttribute("lijstGebruikers", gebruikers);
                response.sendRedirect("GebruikersOverzicht.jsp");
            }

            if (laatste != null) {
                bladz = (aantalGebruikers / 5) + 1;
                int getoondeGebruikers = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeGebruikers > aantalGebruikers) {
                    getoondeGebruikers = aantalGebruikers;
                }
                session.setAttribute("getoondeGebruikers", getoondeGebruikers);
                System.out.println("bladz: " + bladz);
                session.setAttribute("bladzijde", bladz);
                gebruikers = gebruikerDAO.gebruikersLaden(bladz);
                session.setAttribute("lijstGebruikers", gebruikers);
                response.sendRedirect("GebruikersOverzicht.jsp");
            }

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

            Gebruiker gebruiker = new Gebruiker();

            switch (actie) {

                case "Edit gebruiker":
                    //gebruiker met id moet aangepast worden in database 
                    session.setAttribute("editID", editID);
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("GebruikersOverzicht.jsp"); //logged-in page 
                    break;

                case "Delete gebruiker":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");
                    session.removeAttribute("lijstGebruikers");

                    gebruikerDAO.gebruikerVerwijderen(Integer.parseInt(deleteID));
                    gebruikers = gebruikerDAO.gebruikersLaden(1);

                    session.setAttribute("lijstGebruikers", gebruikers);
                    response.sendRedirect("GebruikersOverzicht.jsp");
                    break;

                case "Cancel gebruiker":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("GebruikersOverzicht.jsp"); //logged-in page 
                    break;

                case "Save gebruiker":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("GebruikersOverzicht.jsp"); //logged-in page 
                    break;

                case "Cursist toevoegen":

                    //gebruikerDAO.cursistAanmaken(gebruiker);
                    //session.setAttribute("ToegevoegdeCursist");
                    break;

                case "Cursist aanpassen":

                    //gebruikerDAO.cursistAanpassen();
                    //session.setAttribute("AangepasteCursist");
                    response.sendRedirect("GebruikersBewerking.jsp");
                    break;

                case "Cursist verwijderen":

                    //gebruikerDAO.cursistVerwijderen(gebruiker);
                    //session.setAttribute("VerwijderdeCursist");
                    response.sendRedirect("GebruikersBewerking.jsp");
                    break;

                case "Gebruiker toevoegen":

                    response.sendRedirect("GebruikerAanmaken.jsp");

                    break;

                case "Gebruiker aanpassen":
                    gebruiker.setVoorNaam(request.getParameter("voornaam"));
                    gebruiker.setAchternaam(request.getParameter("achternaam"));
                    gebruiker.setEmail(request.getParameter("email"));
                    gebruiker.setLogin(request.getParameter("login"));
                    gebruiker.setPaswoord(request.getParameter("wachtwoord"));

                    gebruikerDAO.gebruikerAanpassen(gebruiker);

                    break;

                case "Gebruiker verwijderen":
                    gebruiker.setVoorNaam(request.getParameter("voornaam"));
                    gebruiker.setAchternaam(request.getParameter("achternaam"));
                    gebruiker.setEmail(request.getParameter("email"));
                    gebruiker.setLogin(request.getParameter("login"));
                    gebruiker.setPaswoord(request.getParameter("wachtwoord"));

                    gebruikerDAO.gebruikerVerwijderen(gebruiker);

                    break;

                case "toevoegen":

                    gebruiker.setVoorNaam(request.getParameter("voornaam"));
                    gebruiker.setAchternaam(request.getParameter("achternaam"));
                    gebruiker.setRol(request.getParameter("rol"));

                    String dateString = request.getParameter("geboorteDatum");
                    DateFormat df = new java.text.SimpleDateFormat("yyyy/mm/dd");

                    java.util.Date datum = df.parse(dateString);
                    java.sql.Date sqlDate = new java.sql.Date(datum.getTime());
                    gebruiker.setGeboorteDatum(sqlDate);
                    gebruiker.setEmail(request.getParameter("email"));
                    gebruiker.setLogin(request.getParameter("login"));
                    gebruiker.setPaswoord(request.getParameter("wachtwoord"));

                    gebruikerDAO.cursistAanmaken(gebruiker);

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
