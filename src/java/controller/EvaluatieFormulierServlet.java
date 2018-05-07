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
import model.Gebruiker;
import model.GebruikerDAO;
import model.Module;
import model.ModuleDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.SchooljarenDAO;
import model.SemesterDAO;
import model.StudiegebiedDAO;

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

            String schooljaar = request.getParameter("schooljaar");
            String semester = request.getParameter("semester");

            String semesterNr = semester.substring(0, 1);

            int schooljaarID = Integer.valueOf(schooljaar.substring(0,4));
            int semesterNummer = Integer.valueOf(semesterNr);
            String volSchooljaar="";
            String volgendSchooljaar=String.valueOf(schooljaarID +1);
            String vorigSchooljaar=String.valueOf(schooljaarID -1);

            switch (semesterNummer) {
                case 1:
                    volSchooljaar=schooljaar.substring(0,4)+" - "+ volgendSchooljaar;
                    break;
                    
                case 2:
                      volSchooljaar=vorigSchooljaar + " - " + schooljaar.substring(0,4);
                    break;  
            }

            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            SchooljarenDAO schooljarenDAO=new SchooljarenDAO();
            SemesterDAO semesterDAO=new SemesterDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            int param1=schooljarenDAO.laadSchooljaarID(volSchooljaar);
            int param2=semesterDAO.laadSemesterID(semester);
            int param3= moduleDAO.laadModuleID(module);
            ArrayList<Gebruiker> gebruikers = gebruikerDAO.gebruikersLaden(param1,param2 ,param3);

            String json = gson.toJson(gebruikers);

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
