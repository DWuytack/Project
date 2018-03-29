package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TaakDAO;
import model.Taak;
import model.Instellingen;

/**
 * Deze klasse bevat alle mogelijke knoppen naar takenbewerkingen.
 *
 * @author Aaron
 */
@WebServlet(name = "TakenServlet", urlPatterns = {"/TakenServlet"})
public class TakenServlet extends HttpServlet {

    TaakDAO taakDAO = new TaakDAO();

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
        ArrayList<Taak> taken = null;

        try {
            String actie = "";
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
            int aantalTaken = (int) session.getAttribute("aantalRecords");

            if (eerste != null) {
                bladz = 1;
                session.setAttribute("bladzijde", bladz);
                int getoondeTaken = Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeTaken > aantalTaken) {
                    getoondeTaken = aantalTaken;
                }
                session.setAttribute("getoondeTaken", getoondeTaken);
                taken = taakDAO.takenLaden(bladz);
                session.setAttribute("lijstTaken", taken);
                response.sendRedirect("Taken.jsp");
            }

            if (vorige != null) {
                bladz--;
                if (bladz < 1) {
                    bladz = 1;
                }
                int getoondeTaken = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeTaken > aantalTaken) {
                    getoondeTaken = aantalTaken;
                }
                session.setAttribute("getoondeTaken", getoondeTaken);
                session.setAttribute("bladzijde", bladz);
                taken = taakDAO.takenLaden(bladz);
                session.setAttribute("lijstTaken", taken);
                response.sendRedirect("Taken.jsp");
            }

            if (volgende != null) {
                bladz++;
                if (((aantalTaken / Instellingen.AANTAL_RECORDS_PER_PAGE) + 1) < bladz) {
                    bladz--;
                }
                int getoondeTaken = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeTaken > aantalTaken) {
                    getoondeTaken = aantalTaken;
                }
                session.setAttribute("getoondeTaken", getoondeTaken);
                session.setAttribute("bladzijde", bladz);
                taken = taakDAO.takenLaden(bladz);
                session.setAttribute("lijstTaken", taken);
                response.sendRedirect("Taken.jsp");
            }

            if (laatste != null) {
                bladz = aantalTaken / Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (aantalTaken % Instellingen.AANTAL_RECORDS_PER_PAGE != 0) {
                    bladz++;
                }
                int getoondeTaken = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeTaken > aantalTaken) {
                    getoondeTaken = aantalTaken;
                }
                session.setAttribute("getoondeTaken", getoondeTaken);
                System.out.println("bladz: " + bladz);
                session.setAttribute("bladzijde", bladz);
                taken = taakDAO.takenLaden(bladz);
                session.setAttribute("lijstTaken", taken);
                response.sendRedirect("Taken.jsp");
            }

            if (editID != null) {
                actie = "Edit taak";
            }

            if (cancelID != null) {
                actie = "Cancel taak";
            }

            if (saveID != null) {
                actie = "Save taak";
            }

            if (deleteID != null) {
                actie = "Delete taak";
            }

            if (addID != null) {
                actie = "Add taak";
            }

            Taak taak = new Taak();

            switch (actie) {

                case "Edit taak":
                    session.setAttribute("editID", editID);
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    int bladzd = (int) session.getAttribute("bladzijde");
                    taken = taakDAO.takenLaden(bladzd);
                    session.setAttribute("lijstTaken", taken);
                    response.sendRedirect("Taken.jsp");
                    break;

                case "Delete taak":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");
                    taakDAO.takenVerwijderen(Integer.parseInt(deleteID));
                    bladzd = (int) session.getAttribute("bladzijde");
                    taken = taakDAO.takenLaden(bladzd);
                    aantalTaken = taakDAO.geefAantalTaken();
                    session.setAttribute("aantalRecords", aantalTaken);
                    session.setAttribute("lijstTaken", taken);
                    int getoondeTaken = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                    if (getoondeTaken > aantalTaken) {
                        getoondeTaken = aantalTaken;
                    }
                    session.setAttribute("getoondeTaken", getoondeTaken);
                    response.sendRedirect("Taken.jsp");
                    break;

                case "Cancel taak":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("Taken.jsp"); //logged-in page 
                    break;

                case "Save taak":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    int id = Integer.parseInt(saveID);
                    taak.setNaam(request.getParameter("naam"));
                    taak.setBeschrijving(request.getParameter("beschrijving"));
                    taakDAO.taakAanpassen(id, taak);
                    bladzd = (int) session.getAttribute("bladzijde");
                    taken = taakDAO.takenLaden(bladzd);
                    aantalTaken = taakDAO.geefAantalTaken();
                    session.setAttribute("aantalRecords", aantalTaken);
                    session.setAttribute("lijstTaken", taken);
                    response.sendRedirect("Taken.jsp");
                    session.removeAttribute("saveID");
                    break;

                case "Add taak":

                    break;
            }

        } catch (Throwable theException) {

        }

    }

}

