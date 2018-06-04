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
import model.GebruikerDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.ScoreDAO;
import model.Module;
import model.ModuleDAO;
import model.SchooljaarDAO;
import model.Score;
import model.SemesterDAO;
import model.StudiegebiedDAO;

/**
 *  Deze klasse bevat de bewerkingen om scores te laden.
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
        
     
        String keuze = request.getParameter("reset");
        
        if (keuze != null) {response.sendRedirect("ModuleScoreOverzicht.jsp");}

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
            
            
            
            
            
        }

        
        
        
        
        
        String module = request.getParameter("module");

        if (module != null) {

            String datum = request.getParameter("schooljaar");
            String semester = request.getParameter("semester");

            String semesterNr = semester.substring(0, 1);

            int schooljaar= Integer.valueOf(datum.substring(0, 4));
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
