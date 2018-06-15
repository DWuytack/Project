/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DoelstellingDAO;
import model.Gebruiker;
import model.GebruikerDAO;
import model.InschrijvingDAO;
import model.Module;
import model.ModuleDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.SchooljaarDAO;
import model.ScoreDAO;
import model.ScoreOverzicht;
import model.SemesterDAO;
import model.StudiegebiedDAO;

/**
 *
 * @author dw018
 */
@WebServlet(name = "CursistOverzichtServlet", urlPatterns = {"/CursistOverzichtServlet"})
public class CursistOverzichtServlet extends HttpServlet {

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

        String keuze = request.getParameter("reset");
        if (keuze != null) {
            response.sendRedirect("EvaluatieFormulier.jsp");
            return;
        }

        String studiegebied = request.getParameter("studiegebied");

        if (studiegebied != null) {

            OpleidingDAO opleidingDAO = new OpleidingDAO();
            StudiegebiedDAO studieGebiedDAO = new StudiegebiedDAO();
            ArrayList<Opleiding> opleidingen = opleidingDAO.opleidingenLaden(studieGebiedDAO.geefStudiegebiedID(studiegebied));

            String json = gson.toJson(opleidingen);

            response.setContentType("application/json");
            response.getWriter().write(json);
            return;
        }

        String opleiding = request.getParameter("opleiding");

        if (opleiding != null) {

            ModuleDAO moduleDAO = new ModuleDAO();
            OpleidingDAO opleidingDAO = new OpleidingDAO();
            ArrayList<Module> modules = moduleDAO.modulesLaden(opleidingDAO.geefOpleidingID(opleiding));

            String json = gson.toJson(modules);

            response.setContentType("application/json");
            response.getWriter().write(json);
            return;
        }

        String module = request.getParameter("module");

        if (module != null) {

            String schooljaar = request.getParameter("schooljaar");
            String semester = request.getParameter("semester");
            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            SchooljaarDAO schooljarenDAO = new SchooljaarDAO();
            SemesterDAO semesterDAO = new SemesterDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            int param1 = schooljarenDAO.geefSchooljaarID(schooljaar);
            int param2 = semesterDAO.laadSemesterID(semester);
            int param3 = moduleDAO.laadModuleID(module);
            ArrayList<Gebruiker> gebruikers = gebruikerDAO.gebruikersLaden(param1, param2, param3);

            String json = gson.toJson(gebruikers);

            response.setContentType("application/json");
            response.getWriter().write(json);
            return;

        }

        module = request.getParameter("module2");

        if (module != null) {
            ModuleDAO moduleDAO = new ModuleDAO();
            int moduleID = moduleDAO.laadModuleID(module);
            DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
            SchooljaarDAO schooljaarDAO = new SchooljaarDAO();
            int schooljaarID = schooljaarDAO.geefSchooljaarID(request.getParameter("schooljaar"));
            SemesterDAO semesterDAO = new SemesterDAO();
            int semesterID = semesterDAO.laadSemesterID(request.getParameter("semester"));
            String gebruiker = request.getParameter("cursist");
            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            int gebruikerID = gebruikerDAO.geefGebruikerID(gebruiker);
            InschrijvingDAO inschrijvingDAO = new InschrijvingDAO();
            int inschrijvingID = inschrijvingDAO.geefInschrijvingID(gebruikerID, moduleID, semesterID, schooljaarID);
            ScoreDAO scoreDAO = new ScoreDAO();
            //Alle doelstellingen waar punten zijn op gegeven voor een bepaalde cursist
            ArrayList<ScoreOverzicht> scores = scoreDAO.geefScoresVoorInschrijvingID(inschrijvingID);
            //alle doelstellingen van een module
            ArrayList<ScoreOverzicht> scoreOverzicht = doelstellingDAO.doelstellingenLadenModule(moduleID);

            for (ScoreOverzicht doelstelling : scoreOverzicht) {
                for (ScoreOverzicht score : scores) {
                    if (doelstelling.getDoelstellingID() == score.getDoelstellingID()) {
                        doelstelling.setTotaalScore(doelstelling.getTotaalScore() + score.getWaarde());
                        if (score.getWaarde() != 0) doelstelling.setAantalTaken(doelstelling.getAantalTaken() + 1);
                        if (doelstelling.getTaakID() == score.getTaakID()) {
                            doelstelling.setScore(score.getScore());
                        }
                    }
                }
            }

            for (ScoreOverzicht doelstelling : scoreOverzicht) {
                if (doelstelling.getAantalTaken() != 0) {
                    doelstelling.setGemiddeldeScore(doelstelling.getTotaalScore() / doelstelling.getAantalTaken());
                }
            }

            String json = gson.toJson(scoreOverzicht);
            response.setContentType("application/json");
            response.getWriter().write(json);
            return;
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
