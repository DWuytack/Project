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
import model.Beoordelingssoort;
import model.BeoordelingssoortDAO;
import model.Doelstelling;
import model.DoelstellingDAO;
import model.EvaluatieFormulier;
import model.EvaluatieFormulierDAO;
import model.Gebruiker;
import model.GebruikerDAO;
import model.InschrijvingDAO;
import model.LesnrDAO;
import model.Module;
import model.ModuleDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.SchooljaarDAO;
import model.ScoreDAO;
import model.ScoreOverzicht;
import model.SemesterDAO;
import model.Taak;
import model.TaakDAO;

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
    //Alle DAO die in deze servlet worden gebruikt
    GebruikerDAO gebruikerDAO = new GebruikerDAO();
    ModuleDAO moduleDAO = new ModuleDAO();
    SemesterDAO semesterDAO = new SemesterDAO();
    SchooljaarDAO schooljaarDAO = new SchooljaarDAO();
    InschrijvingDAO inschrijvingDAO = new InschrijvingDAO();
    LesnrDAO lesnrDAO = new LesnrDAO();
    EvaluatieFormulierDAO evaluatieFormulierDAO = new EvaluatieFormulierDAO();
    TaakDAO taakDAO = new TaakDAO();
    BeoordelingssoortDAO beoordelingssoortDAO = new BeoordelingssoortDAO();
    OpleidingDAO opleidingDAO = new OpleidingDAO();
    DoelstellingDAO doelstellingDAO = new DoelstellingDAO();

    Gson gson = new Gson();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String laadOpleidingen = request.getParameter("laadOpleidingen");
        String laadModules = request.getParameter("laadModules");
        String laadCursisten = request.getParameter("laadCursisten");
        String laadTaken = request.getParameter("laadTaken");
        String laadFormulier = request.getParameter("laadFormulier");
        String laadDoelstellingen = request.getParameter("laadDoelstellingen");
        String laadScores = request.getParameter("laadScores");
        String laadOverzicht = request.getParameter("laadOverzicht");
        response.setContentType("application/json");

        if (laadOverzicht != null) {
            response.getWriter().write(laadOverzicht(Integer.parseInt(laadOverzicht), request));
        }

        if (laadOpleidingen != null) {
            response.getWriter().write(laadOpleidingen(Integer.parseInt(laadOpleidingen)));
        }

        if (laadModules != null) {
            response.getWriter().write(laadModules(Integer.parseInt(laadModules)));
        }

        if (laadCursisten != null) {
            response.getWriter().write(laadCursisten(Integer.parseInt(laadCursisten), request));
        }

        if (laadTaken != null) {
            response.getWriter().write(laadTaken(Integer.parseInt(laadTaken)));
        }

        if (laadDoelstellingen != null) {
            response.getWriter().write(laadDoelstellingen(Integer.parseInt(laadDoelstellingen)));
        }
        if (laadScores != null) {
            response.getWriter().write(laadScores());
        }

        if (laadFormulier != null) {
            response.getWriter().write(laadFormulier(Integer.parseInt(laadFormulier), request));
        }
    }

    protected String laadOverzicht(int moduleID, HttpServletRequest request) {

        int schooljaarID = Integer.parseInt(request.getParameter("schooljaar"));
        int semesterID =Integer.parseInt(request.getParameter("semester"));
        int gebruikerID = Integer.parseInt(request.getParameter("cursist"));    
        int inschrijvingID = inschrijvingDAO.geefInschrijvingID(gebruikerID, moduleID, semesterID, schooljaarID);
        ScoreDAO scoreDAO = new ScoreDAO();
        ArrayList<ScoreOverzicht> scores = scoreDAO.geefScoresVoorInschrijvingID(inschrijvingID, moduleID);

       //behandel lege scores
        for (ScoreOverzicht score : scores) {
            if (score.getScore() == null || score.getScore().equalsIgnoreCase("Geen")) {
                score.setScore("");
                score.setWaarde(0);
            }
            if (score.getTaaknaam() == null) {
                score.setTaaknaam("");
            }
        }
        //bereken gemiddelde score per doelstelling
        int exDoelstellingID = 0;
        double totaalScore = 0;
        int aantalTaken = 0;
        for (ScoreOverzicht score : scores) {
            if (score.getDoelstellingID() == exDoelstellingID || exDoelstellingID == 0) {
                if (score.getWaarde() != 0) {
                    aantalTaken = aantalTaken + 1;
                }
                totaalScore = totaalScore + score.getWaarde();
                exDoelstellingID = score.getDoelstellingID();

            } else {
                for (ScoreOverzicht score2 : scores) {
                    if (score2.getDoelstellingID() == exDoelstellingID) {
                        score2.setTotaalScore(totaalScore);
                        if (aantalTaken != 0) {
                            score2.setGemiddeldeScore(totaalScore / aantalTaken);
                        }
                        score2.setAantalTaken(aantalTaken);
                    }
                }
                totaalScore = 0;
                aantalTaken = 0;
                exDoelstellingID = 0;
                if (score.getWaarde() != 0) {
                    aantalTaken = aantalTaken + 1;
                }
                totaalScore = totaalScore + score.getWaarde();
                exDoelstellingID = score.getDoelstellingID();
            }
        }
        return gson.toJson(scores);
    }

    //laad de gegevens van een formulier
    protected String laadFormulier(int formulierID, HttpServletRequest request) {
        ArrayList<ScoreOverzicht> scoreOverzicht = evaluatieFormulierDAO.laadFormulier(formulierID);
        return gson.toJson(scoreOverzicht);
    }

    //vult de gegevens van een formulier op
    protected EvaluatieFormulier laadFormulierGegevens(int cursistID, HttpServletRequest request) {

        int lesnrID = Integer.parseInt(request.getParameter("lesnr"));
        int moduleID = Integer.parseInt(request.getParameter("module"));
        int semesterID = semesterDAO.laadSemesterID(request.getParameter("semester"));
        String formulierNaam = request.getParameter("formname");
        int schooljaarID = schooljaarDAO.geefSchooljaarID(request.getParameter("datum"));
        int inschrijvingID = 10000000;
        if (cursistID != inschrijvingID && cursistID != 0) {
            inschrijvingID = inschrijvingDAO.geefInschrijvingID(cursistID, moduleID, semesterID, schooljaarID);
        }
        EvaluatieFormulier formulier = new EvaluatieFormulier();
        formulier.setNaam(formulierNaam);
        formulier.setInschrijvingID(inschrijvingID);
        formulier.setLesnrID(lesnrID);
        formulier.setModuleID(moduleID);
        formulier.setSchooljaarID(schooljaarID);
        formulier.setSemesterID(semesterID);
        return formulier;
    }

    //laad de opleiding voor een studiegebied
    protected String laadOpleidingen(int studieGebiedID) {
        ArrayList<Opleiding> opleidingen = opleidingDAO.opleidingenLaden(studieGebiedID);
        return gson.toJson(opleidingen);
    }

    //laad de modules voor een opleiding
    protected String laadModules(int opleidingID) {
        ArrayList<Module> modules = moduleDAO.modulesLaden(opleidingID);
        return gson.toJson(modules);
    }

    //laad de cursisten die zijn ingeschreven voor een bepaalde module
    protected String laadCursisten(int moduleID, HttpServletRequest request) {
        int schooljaarID = Integer.parseInt(request.getParameter("datum"));
        int semesterID = semesterDAO.laadSemesterID(request.getParameter("semester"));
        ArrayList<Gebruiker> cursisten = gebruikerDAO.gebruikersLaden(schooljaarID, semesterID, moduleID);
        return gson.toJson(cursisten);
    }

    //laad de taken die bij een module horen
    protected String laadTaken(int moduleID) {
        ArrayList<Taak> taken = taakDAO.takenLaden(moduleID);
        return gson.toJson(taken);
    }

    //laad de doelstellingen voor een bepaalde taak
    protected String laadDoelstellingen(int taakID) {
        ArrayList<Doelstelling> doelstellingen = doelstellingDAO.laadDoelstellingen(taakID);
        return gson.toJson(doelstellingen);
    }

    //laad de mogelijke scores die kunnen worden gekozen
    protected String laadScores() {
        ArrayList<Beoordelingssoort> scores = beoordelingssoortDAO.beoordelingssoortenLaden();
        return gson.toJson(scores);
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
