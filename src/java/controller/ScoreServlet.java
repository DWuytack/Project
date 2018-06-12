package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Gebruiker;
import model.Doelstelling;
import model.DoelstellingDAO;
import model.GebruikerDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.ScoreDAO;
import model.Module;
import model.ModuleDAO;
import model.SchooljaarDAO;
import model.Score;
import model.ScoreOverzicht;
import model.SemesterDAO;
import model.StudiegebiedDAO;

/**
 *  Deze klasse is een de servlet voor score (request/response met de server)
 * @author gil-_
 */
@WebServlet(name = "ScoreServlet", urlPatterns = {"/ScoreServlet"})
public class ScoreServlet extends HttpServlet {

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

        Gson gson = new Gson();
        String studiegebied = request.getParameter("studiegebied");

        if (studiegebied != null) {

            OpleidingDAO opleidingDAO = new OpleidingDAO();
            StudiegebiedDAO studieGebiedDAO = new StudiegebiedDAO();
            ArrayList<Opleiding> opleidingen = opleidingDAO.opleidingenLaden(studieGebiedDAO.geefStudiegebiedID(studiegebied));

            String json = gson.toJson(opleidingen);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        String opleiding = request.getParameter("opleiding");

        if (opleiding != null) {

            ModuleDAO moduleDAO = new ModuleDAO();
            OpleidingDAO opleidingDAO = new OpleidingDAO();
            ArrayList<Module> modules = moduleDAO.modulesLaden(opleidingDAO.geefOpleidingID(opleiding));

            String json = gson.toJson(modules);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        String moduleDoelstelling = request.getParameter("moduleDoelstelling");

        if (moduleDoelstelling != null) {
            
            DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            
            int param1 = moduleDAO.laadModuleID(moduleDoelstelling);
            ArrayList<ScoreOverzicht> scoreOverzicht = doelstellingDAO.doelstellingenLadenModule(param1);
            
            String json = gson.toJson(scoreOverzicht);

            response.setContentType("application/json");
            response.getWriter().write(json);

        }

        String module = request.getParameter("module");

        if (module != null) {

            String datum = request.getParameter("schooljaar");
            String semester = request.getParameter("semester");

            String semesterNr = semester.substring(0, 1);

            int schooljaar = Integer.valueOf(datum.substring(0, 4));
            int semesterNummer = Integer.valueOf(semesterNr);
            String volSchooljaar = "";
            String volgendSchooljaar = String.valueOf(schooljaar + 1);
            String vorigSchooljaar = String.valueOf(schooljaar - 1);

            switch (semesterNummer) {
                case 1:
                    volSchooljaar = datum.substring(0, 4) + " - " + volgendSchooljaar;
                    break;

                case 2:
                    volSchooljaar = vorigSchooljaar + " - " + datum.substring(0, 4);
                    break;
            }
            

            ScoreDAO scoreDAO = new ScoreDAO();
            SchooljaarDAO schooljarenDAO = new SchooljaarDAO();
            SemesterDAO semesterDAO = new SemesterDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            int param1 = schooljarenDAO.geefSchooljaarID(volSchooljaar);
            int param2 = semesterDAO.laadSemesterID(semester);
            int param3 = moduleDAO.laadModuleID(module);
            ArrayList<Score> moduleScore = scoreDAO.klassikaleScore(param1, param2, param3);

            String json = gson.toJson(moduleScore);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }
        
        String cursisten = request.getParameter("cursisten");

        if (cursisten != null) {
            
            ScoreDAO scoreDAO = new ScoreDAO();           
            ArrayList<Score> scores = scoreDAO.klassikaleScore(0, 0, 0);
            
            String json = gson.toJson(scores);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }
        
        
        String modules = request.getParameter("modules");

        if (modules != null) {

            String datum = request.getParameter("schooljaar");
            String semester = request.getParameter("semester");

            String semesterNr = semester.substring(0, 1);

            int schooljaar = Integer.valueOf(datum.substring(0, 4));
            int semesterNummer = Integer.valueOf(semesterNr);
            String volSchooljaar = "";
            String volgendSchooljaar = String.valueOf(schooljaar + 1);
            String vorigSchooljaar = String.valueOf(schooljaar - 1);

            switch (semesterNummer) {
                case 1:
                    volSchooljaar = datum.substring(0, 4) + " - " + volgendSchooljaar;
                    break;

                case 2:
                    volSchooljaar = vorigSchooljaar + " - " + datum.substring(0, 4);
                    break;
            }

            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            SchooljaarDAO schooljarenDAO = new SchooljaarDAO();
            SemesterDAO semesterDAO = new SemesterDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            int param1 = schooljarenDAO.geefSchooljaarID(volSchooljaar);
            int param2 = semesterDAO.laadSemesterID(semester);
            int param3 = moduleDAO.laadModuleID(modules);
            ArrayList<Gebruiker> gebruikers = gebruikerDAO.gebruikersLaden(param1, param2, param3);

            String json = gson.toJson(gebruikers);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }
        
        String cursistenScore = request.getParameter("cursistenScore");

        if (cursistenScore != null) {
            
            module = request.getParameter("modules");
            
            ScoreDAO scoreDAO = new ScoreDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            
            int moduleID = moduleDAO.laadModuleID(module);
            int gebruikerID = gebruikerDAO.geefGebruikerID(cursistenScore);
            ArrayList<Score> doelstellingScore = scoreDAO.cursistScore(moduleID, gebruikerID);
            
            String json = gson.toJson(doelstellingScore);

            response.setContentType("application/json");
            response.getWriter().write(json);

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
        return "Dit is de scoreServlet";
    }// </editor-fold>
}
