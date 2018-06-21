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
import model.ScoreOverzicht;
import model.SemesterDAO;
import model.Taak;
import model.TaakDAO;

/**
 * @author Dirk
 */
public class EvaluatieFormulierServlet extends HttpServlet {

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
        String laadTakenVanFormulier = request.getParameter("laadTakenVanFormulier");
        String laadDoelstellingen = request.getParameter("laadDoelstellingen");
        String laadScores = request.getParameter("laadScores");
        String bewaarFormulier = request.getParameter("bewaarFormulier");
        String bewaarCommentaarVoorTaak = request.getParameter("bewaarCommentaarVoorTaak");
        String bewaarDoelstellingScore = request.getParameter("bewaarDoelstellingScore");
        String bestaatFormulierAl = request.getParameter("bestaatFormulierAl");
     
        response.setContentType("application/json");

        if (bewaarDoelstellingScore != null) {
            bewaarDoelstellingScore(Integer.parseInt(bewaarDoelstellingScore), request);
        }

        if (bewaarFormulier != null) {   
            response.getWriter().write(bewaarFormulier(Integer.parseInt(bewaarFormulier), request));
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

        if (bewaarCommentaarVoorTaak != null) {
            response.getWriter().write(bewaarCommentaarVoorTaak(Integer.parseInt(bewaarCommentaarVoorTaak), request));
        }

        if (laadDoelstellingen != null) {
            response.getWriter().write(laadDoelstellingen(Integer.parseInt(laadDoelstellingen)));
        }
        if (laadScores != null) {
            response.getWriter().write(laadScores());
        }
        if (bestaatFormulierAl != null) {
            response.getWriter().write(bestaatFormulierAl(Integer.parseInt(bestaatFormulierAl), request));
        }
        if (laadTakenVanFormulier != null) {
            response.getWriter().write(laadTakenVanFormulier(Integer.parseInt(laadTakenVanFormulier), request));
        }
    }
    
    //laad de gegevens van een formulier
    protected String laadTakenVanFormulier(int formulierID, HttpServletRequest request) {
        ArrayList<ScoreOverzicht> scoreOverzicht =evaluatieFormulierDAO.laadFormulier(formulierID);
        return gson.toJson(scoreOverzicht); 
    }

    //formulier wordt niet gecontroleerd op datum maar op basis van de gegevens die het bevat
    protected String bestaatFormulierAl(int cursistID, HttpServletRequest request) {
        EvaluatieFormulier formulier = laadFormulierGegevens(cursistID, request);
        int evaluatieFormulierID = evaluatieFormulierDAO.laadformulierID(formulier);
        return gson.toJson(evaluatieFormulierID);
    }

    //vult de gegevens van een formulier op
    protected EvaluatieFormulier laadFormulierGegevens(int cursistID, HttpServletRequest request) {
      
        int lesnrID = Integer.parseInt(request.getParameter("lesnr"));
        int moduleID = Integer.parseInt(request.getParameter("module"));
        int semesterID = semesterDAO.laadSemesterID(request.getParameter("semester"));
        String formulierNaam=request.getParameter("formname");
        int schooljaarID = schooljaarDAO.geefSchooljaarID(maakSchooljaarString(request));
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

    //bewaart de score van een doelstelling
    protected void bewaarDoelstellingScore(int taakID, HttpServletRequest request) {
        int evaluatieFormID = Integer.parseInt(request.getParameter("evaluatieFormID"));
        int doelstellingID = Integer.parseInt(request.getParameter("doelstellingID"));
        int scoreID = Integer.parseInt(request.getParameter("score"));
        evaluatieFormulierDAO.saveDoelstellingScore(evaluatieFormID, taakID, doelstellingID, scoreID);
    }

    //bewaart een formulier (overschrijft als het formulier al bestaat)
    protected String bewaarFormulier(int cursistID, HttpServletRequest request) {
        EvaluatieFormulier formulier = laadFormulierGegevens(cursistID, request);
        int evaluatieFormulierID = evaluatieFormulierDAO.laadformulierID(formulier);
        if (evaluatieFormulierID != 0) {
            evaluatieFormulierDAO.wisFormulier(evaluatieFormulierID);
        }
        evaluatieFormulierDAO.bewaarFormulier(formulier);
        evaluatieFormulierID = evaluatieFormulierDAO.laadformulierID(formulier);
        return gson.toJson(evaluatieFormulierID);
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
        String volSchooljaar = maakSchooljaarString(request);
        int schooljaarID = schooljaarDAO.geefSchooljaarID(volSchooljaar);
        int semesterID = semesterDAO.laadSemesterID(request.getParameter("semester"));
        ArrayList<Gebruiker> cursisten = gebruikerDAO.gebruikersLaden(schooljaarID, semesterID, moduleID);
        return gson.toJson(cursisten);
    }

    //laad de taken die bij een module horen
    protected String laadTaken(int moduleID) {
        ArrayList<Taak> taken = taakDAO.takenLaden(moduleID);
        return gson.toJson(taken);
    }

   //bewaart de commentaar die bij een taak hoort en laadt de doelstellingen die bij een taak horen
    protected String bewaarCommentaarVoorTaak(int taakID, HttpServletRequest request) {
        String commentaar = request.getParameter("comment");
        int evalFormID = Integer.parseInt(request.getParameter("formID"));
        evaluatieFormulierDAO.saveComment(evalFormID, taakID, commentaar);
        return laadDoelstellingen(taakID);
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

    //destilleert schooljaar uit datum
    protected String maakSchooljaarString(HttpServletRequest request) {
        String datum = request.getParameter("datum");
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
        return volSchooljaar;
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

    