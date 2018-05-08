package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gebruiker;
import model.GebruikerDAO;
import model.Instellingen;
import model.Module;
import model.ModuleDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.Schooljaar;
import model.SchooljarenDAO;
import model.Score;
import model.ScoreDAO;
import model.SemesterDAO;
import model.Studiegebied;
import model.StudiegebiedDAO;
import model.Semester;


/**
 *
 * @author Dirk
 */
public class MenuServlet extends HttpServlet {

    /**
     *
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
            session.setAttribute("zoekterm", null);

            switch (actie) {

                case "Overzicht cursisten":

                    //laden van cursisten uit database
                    GebruikerDAO gebruikerDAO = new GebruikerDAO();
                    ArrayList<Gebruiker> cursisten = gebruikerDAO.cursistenLaden(1);
                    
                    int aantalCursisten = gebruikerDAO.geefAantalGebruikers();
                    session.setAttribute("aantalRecords", aantalCursisten);
                    session.setAttribute("bladzijde", 1);
                    int getoondeCursisten = Instellingen.AANTAL_RECORDS_PER_PAGE;
                    if (getoondeCursisten > aantalCursisten) {
                        getoondeCursisten = aantalCursisten;
                    }
                    session.setAttribute("getoondeGebruikers", getoondeCursisten);
                    session.setAttribute("lijstCursisten", cursisten);
                    response.sendRedirect("CursistenOverzicht.jsp");
                    break;

                case "Overzicht gebruikers":

                    //laden van gebruikers uit database
                    gebruikerDAO = new GebruikerDAO();
                    ArrayList<Gebruiker> gebruikers = gebruikerDAO.gebruikersLaden(1);

                    int aantalGebruikers = gebruikerDAO.geefAantalGebruikers();
                    session.setAttribute("aantalRecords", aantalGebruikers);
                    session.setAttribute("bladzijde", 1);
                    int getoondeGebruikers = Instellingen.AANTAL_RECORDS_PER_PAGE;
                    if (getoondeGebruikers > aantalGebruikers) {
                        getoondeGebruikers = aantalGebruikers;
                    }
                    session.setAttribute("getoondeGebruikers", getoondeGebruikers);
                    session.setAttribute("lijstGebruikers", gebruikers);
                    response.sendRedirect("GebruikersOverzicht.jsp");
                    break;

                case "Overzicht scores":
                    
                    // laden van schooljaren uit database en in het geheugen plaatsen
                    SchooljarenDAO schooljaarDAO = new SchooljarenDAO();
                    ArrayList<Schooljaar> schooljaren = schooljaarDAO.schooljarenLaden();
                    session.setAttribute("schooljaren", schooljaren);
                                        
                    // laden semester uit database en in het geheugen plaatsen
                    SemesterDAO semesterDAO = new SemesterDAO();
                    ArrayList<Semester> semesters = semesterDAO.semestersLaden();
                    session.setAttribute("semesters", semesters);
                    
                    // laden modules uit database en in het geheugen plaatsen
                    ModuleDAO moduleDAO = new ModuleDAO();
                    ArrayList<Module> modules = moduleDAO.modulesLaden();
                    session.setAttribute("modules", modules);
                    
                    // laden van opleidingen
                    OpleidingDAO opleidingDAO = new OpleidingDAO();
                    ArrayList<Opleiding> opleidingen = opleidingDAO.opleidingenLaden();
                    session.setAttribute("opleidingen", opleidingen);
                    
                    // laden studiegebieden
                    StudiegebiedDAO studiegebiedDAO = new StudiegebiedDAO();
                    ArrayList<Studiegebied> studiegebieden = studiegebiedDAO.studiegebiedenLaden();
                    session.setAttribute("studiegebieden", studiegebieden);
                    response.sendRedirect("Score.jsp");
                    break;
                    
                case "Overzicht studiegebieden":
                    studiegebiedDAO = new StudiegebiedDAO();
                    ArrayList<Studiegebied> studiegebieden2 = studiegebiedDAO.studiegebiedenLaden();
                    session.setAttribute("studiegebieden", studiegebieden2);                   
                    response.sendRedirect("OverzichtStudiegebieden.jsp");                   
                    break;
                    
                case "Evaluatieformulieren":

                    //laden schooljaren
                    schooljaarDAO = new SchooljarenDAO();
                    ArrayList<Schooljaar> schooljaren2 = schooljaarDAO.schooljarenLaden();
                    session.setAttribute("schooljaren", schooljaren2);
                    
                    //laden semesters
                    semesterDAO = new SemesterDAO();
                    semesters = semesterDAO.semestersLaden();
                    session.setAttribute("semesters", semesters);
                    
                    //laden studiegebieden
                    studiegebiedDAO = new StudiegebiedDAO();
                    studiegebieden = studiegebiedDAO.studiegebiedenLaden();
                    session.setAttribute("studiegebieden", studiegebieden);
                    
                    // laden opleidingen
                    opleidingDAO = new OpleidingDAO();
                    opleidingen = opleidingDAO.opleidingenLaden();
                    session.setAttribute("opleidingen", opleidingen);
                    
                    // laden modules
                    moduleDAO = new ModuleDAO();
                    modules = moduleDAO.modulesLaden();
                    session.setAttribute("modules", modules);
                    
                    // laden cursisten
                    // laden lesnr
                    
                    
                    response.sendRedirect("EvaluatieFormulier.jsp");
                    break;
                case "Rapport":
                    response.sendRedirect("Rapport.jsp");
                    break;

                case "Type score aanpassen":
                    //laden van de types scores
                    ScoreDAO scoreDAO = new ScoreDAO();
                    ArrayList<Score> typeScores = scoreDAO.typeScoreLaden();
                    session.setAttribute("lijstBeoordelingssoorten", typeScores);
                    response.sendRedirect("TypeScoreOverzicht.jsp");
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
