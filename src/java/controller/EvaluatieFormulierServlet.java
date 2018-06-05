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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Beoordelingssoort;
import model.BeoordelingssoortDAO;
import model.Doelstelling;
import model.DoelstellingDAO;
import model.Gebruiker;
import model.GebruikerDAO;
import model.InschrijvingDAO;
import model.Module;
import model.ModuleDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.SchooljaarDAO;
import model.SemesterDAO;
import model.StudiegebiedDAO;
import model.Taak;
import model.TaakDAO;

/**
 *
 * @author Dirk
 */
public class EvaluatieFormulierServlet extends HttpServlet {

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
        if (keuze != null) {
            response.sendRedirect("EvaluatieFormulier.jsp");
        }

        String gebruiker = request.getParameter("bewaarCursist");

        if (gebruiker != null) {

            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            SemesterDAO semesterDAO = new SemesterDAO();
            SchooljaarDAO schooljaarDAO = new SchooljaarDAO();
            InschrijvingDAO inschrijvingDAO = new InschrijvingDAO();
            int gebruikerID = gebruikerDAO.geefGebruikerID(gebruiker);
            int moduleID = moduleDAO.geefModuleID(request.getParameter("module"));
            int semesterID = semesterDAO.laadSemesterID(request.getParameter("semester"));
            int schooljaarID = schooljaarDAO.geefSchooljaarID(request.getParameter("schooljaar"));

            //zoek inschrijving
            int inschrijvingID =inschrijvingDAO.geefInschrijvingID(gebruikerID,moduleID,semesterID,schooljaarID);
    
            
        }

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

            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            SchooljaarDAO schooljarenDAO = new SchooljaarDAO();
            SemesterDAO semesterDAO = new SemesterDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            int param1 = schooljarenDAO.geefSchooljaarID(volSchooljaar);
            int param2 = semesterDAO.laadSemesterID(semester);
            int param3 = moduleDAO.laadModuleID(module);
            ArrayList<Gebruiker> gebruikers = gebruikerDAO.gebruikersLaden(param1, param2, param3);

            String json = gson.toJson(gebruikers);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        String test = request.getParameter("taak");

        if (test != null) {

            //laad taken voor module
            TaakDAO taakDAO = new TaakDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            ArrayList<Taak> taken = taakDAO.takenLaden(moduleDAO.geefModuleID(test));
            String json = gson.toJson(taken);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        test = request.getParameter("formTaak");

        if (test != null) {

            //laad taken voor module
            DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
            TaakDAO taakDAO = new TaakDAO();
            ArrayList<Doelstelling> doelstellingen = doelstellingDAO.laadDoelstellingen(taakDAO.geefTaakID(test));
            String json = gson.toJson(doelstellingen);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        test = request.getParameter("scores");
        if (test != null) {

            //laad taken voor module
            BeoordelingssoortDAO scoreDAO = new BeoordelingssoortDAO();
            ArrayList<Beoordelingssoort> scores = scoreDAO.beoordelingssoortenLaden();
            String json = gson.toJson(scores);

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
        return "Dit is de evaluatieServlet!";
    }

}
