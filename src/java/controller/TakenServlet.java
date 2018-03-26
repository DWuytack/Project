package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Instellingen;
import model.TaakDAO;
import model.Taak;

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
                if (bladz > ((aantalTaken / 5) + 1)) {
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
                bladz = (aantalTaken / 5) + 1;
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

            Taak taak = new Taak();

            switch (actie) {

                case "Edit taak":
                    break;

                case "Delete taak":
                    break;

                case "Cancel taak":
                    break;

                case "Save taak":
                    break;

                case "taak toevoegen":
                    break;

                case "taak aanpassen":
                    break;

                case "taak verwijderen":
                    break;

                case "Taak verwijderen":
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
