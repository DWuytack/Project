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
import model.Instellingen;

/**
 * Servlet voor alle handelingen van doelstellingen.
 *
 * @author Ewout Phlips
 */
@WebServlet(name = "DoelstellingenServlet", urlPatterns = {"/DoelstellingenServlet"})
public class DoelstellingenServlet extends HttpServlet {

    DoelstellingDAO doelstellingDAO = new DoelstellingDAO();

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
        ArrayList<Doelstelling> doelstellingen = null;

        try {
            String actie = "";
            String zoekterm = request.getParameter("zoekterm");
            String editID = request.getParameter("idEdit");
            String cancelID = request.getParameter("idCancel");
            String saveID = request.getParameter("idSave");
            String deleteID = request.getParameter("idDelete");
            String eerste = request.getParameter("Eerste");
            String vorige = request.getParameter("Vorige");
            String volgende = request.getParameter("Volgende");
            String laatste = request.getParameter("Laatste");
            int bladz = (int) session.getAttribute("bladzijde");
            int aantalDoelstellingen = (int) session.getAttribute("aantalRecords");

            if (eerste != null) {
                bladz = 1;
                session.setAttribute("bladzijde", bladz);
                int getoondeDoelstellingen = Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeDoelstellingen > aantalDoelstellingen) {
                    getoondeDoelstellingen = aantalDoelstellingen;
                }
                session.setAttribute("getoondeDoelstellingen", getoondeDoelstellingen);
                doelstellingen = doelstellingDAO.doelstellingenLaden(bladz);
                session.setAttribute("lijstDoelstellingen", doelstellingen);
                response.sendRedirect("DoelstellingenOverzicht.jsp");
            }

            if (vorige != null) {
                bladz--;
                if (bladz < 1) {
                    bladz = 1;
                }
                int getoondeDoelstellingen = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeDoelstellingen > aantalDoelstellingen) {
                    getoondeDoelstellingen = aantalDoelstellingen;
                }
                session.setAttribute("getoondeDoelstellingen", getoondeDoelstellingen);
                session.setAttribute("bladzijde", bladz);
                doelstellingen = doelstellingDAO.doelstellingenLaden(bladz);
                session.setAttribute("lijstDoelstellingen", doelstellingen);
                response.sendRedirect("DoelstellingenOverzicht.jsp");
            }

            if (volgende != null) {
                bladz++;
                if (((aantalDoelstellingen / Instellingen.AANTAL_RECORDS_PER_PAGE) + 1) < bladz) {
                    bladz--;
                }
                int getoondeDoelstellingen = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeDoelstellingen > aantalDoelstellingen) {
                    getoondeDoelstellingen = aantalDoelstellingen;
                }
                session.setAttribute("getoondeDoelstellingen", getoondeDoelstellingen);
                session.setAttribute("bladzijde", bladz);
                doelstellingen = doelstellingDAO.doelstellingenLaden(bladz);
                session.setAttribute("lijstDoelstellingen", doelstellingen);
                response.sendRedirect("DoelstellingenOverzicht.jsp");
            }

            if (laatste != null) {
                bladz = aantalDoelstellingen / Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (aantalDoelstellingen % Instellingen.AANTAL_RECORDS_PER_PAGE != 0) {
                    bladz++;
                }
                int getoondeDoelstellingen = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeDoelstellingen > aantalDoelstellingen) {
                    getoondeDoelstellingen = aantalDoelstellingen;
                }
                session.setAttribute("getoondeDoelstellingen", getoondeDoelstellingen);
                session.setAttribute("bladzijde", bladz);
                doelstellingen = doelstellingDAO.doelstellingenLaden(bladz);
                session.setAttribute("lijstDoelstellingen", doelstellingen);
                response.sendRedirect("DoelstellingenOverzicht.jsp");
            }

            if (zoekterm != null) {
                actie = "Zoeken";
            }
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

            Doelstelling doelstelling = new Doelstelling();

            switch (actie) {
                //case "Zoeken":
                    //session.setAttribute("zoekterm", zoekterm);
                    //doelstellingen = doelstellingDAO.doelstellingenZoeken(zoekterm);
                    //session.setAttribute("lijstDoelstellingen", doelstellingen);
                    //response.sendRedirect("DoelstellingenOverzicht.jsp");
                    
                    //break;
                    
                case "Edit doelstelling":
                    session.setAttribute("editID", editID);
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");

                    doelstellingDAO.doelstellingAanpassen(doelstelling);
                    doelstellingen = doelstellingDAO.doelstellingenLaden(1);
                    session.setAttribute("lijstDoelstellingen", doelstellingen);
                    response.sendRedirect("DoelstellingenOverzicht.jsp");
                    break;

                case "Delete doelstelling":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");
                    doelstellingDAO.doelstellingVerwijderen(doelstellingDAO.doelstellingLaden(Integer.parseInt(deleteID)));
                    doelstellingen = doelstellingDAO.doelstellingenLaden(1);
                    aantalDoelstellingen = doelstellingDAO.geefAantalDoelstellingen();
                    session.setAttribute("aantalRecords", aantalDoelstellingen);
                    session.setAttribute("lijstDoelstellingen", doelstellingen);
                    response.sendRedirect("DoelstellingenOverzicht.jsp");
                    break;

                case "Cancel doelstelling":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("DoelstellingenOverzicht.jsp");
                    break;

                case "Save doelstelling":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("DoelstellingenOverzicht.jsp");
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
