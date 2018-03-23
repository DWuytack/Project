package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Doelstelling;
import model.DoelstellingDAO;
import model.Gebruiker;
import model.GebruikerDAO;
import model.Module;
import model.ModuleDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.ScoreDAO;
import model.Taak;
import model.TaakDAO;

/**
 *
 * @author Dirk
 */
public class MenuServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        try {

            String actie = request.getParameter("actie");
            HttpSession session = request.getSession(true);
            switch (actie) {

                case "Overzicht cursisten":

                    //laden van cursisten uit database
                    GebruikerDAO gebruikerDAO = new GebruikerDAO();
                    ArrayList<Gebruiker> cursisten = gebruikerDAO.cursistenLaden();

                    session.setAttribute("lijstCursisten", cursisten);

                    response.sendRedirect("CursistenOverzicht.jsp");
                    break;
                case "Overzicht gebruikers":

                    //laden van gebruikers uit database
                    gebruikerDAO = new GebruikerDAO();
                    ArrayList<Gebruiker> gebruikers = gebruikerDAO.gebruikersLaden(1,5);
                    session.setAttribute("bladzijde", 1);
                    session.setAttribute("lijstGebruikers", gebruikers);
                    response.sendRedirect("GebruikersOverzicht.jsp");
                    break;
                    
                case "Overzicht doelstellingen":
                    DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
                    ArrayList<Doelstelling> doelstellingen = doelstellingDAO.doelstellingenLaden();
                    
                    session.setAttribute("lijstDoelstellingen", doelstellingen);
                    response.sendRedirect("DoelstellingenOverzicht.jsp");
                    break;
                    
                case "Overzicht taken":
                    TaakDAO taakDAO = new TaakDAO();
                    ArrayList<Taak> taken = taakDAO.takenLaden();
                    session.setAttribute("lijstTaken", taken);
                    response.sendRedirect("Taken.jsp");
                    
                    break;
                case "Overzicht opleidingen":
                    OpleidingDAO opleidingDAO2 = new OpleidingDAO();
                    ArrayList<Opleiding> opleidingen = opleidingDAO2.opleidingenLaden();
                    session.setAttribute("lijstOpleidingen", opleidingen);
                    response.sendRedirect("Opleiding.jsp");
                    break;
                case "Overzicht modules":
                    ModuleDAO moduleDAO2 = new ModuleDAO();
                    ArrayList<Module> modules = moduleDAO2.modulesLaden();
                    session.setAttribute("lijstModules", modules);

                    response.sendRedirect("Module.jsp");
                    break;
                case "Overzicht scores":
                    
                    //laden schooljaren uit database en in het geheugen plaatsen
                    ScoreDAO scoreDAO = new ScoreDAO();
                    
                    
            
                    //laden semester uit database en in het geheugen plaatsen
                    
                    
                    //laden modules uit database en in het geheugen plaatsen
                    
                    
                    response.sendRedirect("Score.jsp");
                  break;
                case "Evaluatieformulieren":
                    response.sendRedirect("EvaluatieFormulier.jsp");
                    break;
                case "Rapport":
                    response.sendRedirect("Rapport.jsp");
                    break;
                case "Type score aanpassen":
                    //laden van de types scores
                    
                    
                    response.sendRedirect("TypeScore.jsp");
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
