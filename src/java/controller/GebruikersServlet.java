package controller;

import java.io.IOException;

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
            String zoekterm = request.getParameter("zoekterm");
            String editID = request.getParameter("idEdit");
            String addID = request.getParameter("addID");
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
                if (((aantalGebruikers / Instellingen.AANTAL_RECORDS_PER_PAGE) + 1) < bladz) {
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
                bladz = aantalGebruikers / Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (aantalGebruikers % Instellingen.AANTAL_RECORDS_PER_PAGE != 0) {
                    bladz++;
                }
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

            if (zoekterm != null && !zoekterm.equals("")) {
                actie = "Zoeken";
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
            if (addID != null) {
                actie = "Add gebruiker";
            }

            Gebruiker gebruiker = new Gebruiker();

            switch (actie) {
                case "Zoeken":
                    session.setAttribute("zoekterm", zoekterm);
                    gebruikers = gebruikerDAO.gebruikersZoeken(zoekterm, bladz);
                    session.setAttribute("lijstGebruikers", gebruikers);
                    response.sendRedirect("GebruikersOverzicht.jsp");
                    break;

                case "Edit gebruiker":
                    session.setAttribute("editID", editID);
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    int bladzd = (int) session.getAttribute("bladzijde");
                    gebruikers = gebruikerDAO.gebruikersLaden(bladzd);
                    session.setAttribute("lijstGebruikers", gebruikers);
                    response.sendRedirect("GebruikersOverzicht.jsp"); //logged-in page 
                    break;

                case "Delete gebruiker":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");
                    gebruikerDAO.gebruikerVerwijderen(Integer.parseInt(deleteID));
                    bladzd = (int) session.getAttribute("bladzijde");
                    gebruikers = gebruikerDAO.gebruikersLaden(bladzd);
                    aantalGebruikers = gebruikerDAO.geefAantalGebruikers();
                    session.setAttribute("aantalRecords", aantalGebruikers);
                    session.setAttribute("lijstGebruikers", gebruikers);
                    int getoondeGebruikers = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                    if (getoondeGebruikers > aantalGebruikers) {
                        getoondeGebruikers = aantalGebruikers;
                    }
                    session.setAttribute("getoondeGebruikers", getoondeGebruikers);
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
                    int id = Integer.parseInt(saveID);
                    gebruiker.setVoorNaam(request.getParameter("voornaam"));
                    gebruiker.setAchternaam(request.getParameter("achternaam"));
                    gebruiker.setRol(request.getParameter("rol"));
                    /*java.sql.Date datum = java.sql.Date.valueOf(request.getParameter("geboorteDatum"));
                    gebruiker.setGeboorteDatum(datum);*/
                    gebruiker.setGeboorteDatum(request.getParameter("geboorteDatum"));
                    gebruiker.setEmail(request.getParameter("email"));
                    gebruiker.setLogin(request.getParameter("login"));
                    gebruikerDAO.gebruikerAanpassen(id, gebruiker);
                    bladzd = (int) session.getAttribute("bladzijde");
                    gebruikers = gebruikerDAO.gebruikersLaden(bladzd);
                    aantalGebruikers = gebruikerDAO.geefAantalGebruikers();
                    session.setAttribute("aantalRecords", aantalGebruikers);
                    session.setAttribute("lijstGebruikers", gebruikers);
                    response.sendRedirect("GebruikersOverzicht.jsp");
                    session.removeAttribute("saveID");
                    break;

                case "add gebruiker":
                    session.setAttribute("addId", addID);
                    gebruikerDAO.gebruikerAanmaken(gebruiker);
                    gebruikers = gebruikerDAO.gebruikersLaden(bladz);
                    session.setAttribute("lijstGebruikers", gebruikers);
                    response.sendRedirect("GebruikersOverzicht.jsp");
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
