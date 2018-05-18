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
import model.Doelstelling;
import model.DoelstellingDAO;
import model.Module;
import model.ModuleDAO;
import model.Opleiding;
import model.OpleidingDAO;
import model.Studiegebied;
import model.StudiegebiedDAO;
import model.Taak;
import model.TaakDAO;


/**
 *
 * @author CURSIST
 */
public class StudiegebiedenServlet extends HttpServlet {
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
        
        if (keuze != null) {response.sendRedirect("OverzichtStudiegebieden.jsp");}

        Gson gson = new Gson();
        String studiegebied = request.getParameter("studiegebied");

        if (studiegebied != null) {

            //laad opleidingen per studiegebied
            OpleidingDAO opleidingDAO = new OpleidingDAO();
            StudiegebiedDAO studieGebiedDAO = new StudiegebiedDAO();
            ArrayList<Opleiding> opleidingen = opleidingDAO.opleidingenLaden(studieGebiedDAO.geefStudiegebiedID(studiegebied));

            String json = gson.toJson(opleidingen);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        String opleiding = request.getParameter("opleiding");

        if (opleiding != null) {

            //laad modules per opleiding
            ModuleDAO moduleDAO = new ModuleDAO();
            OpleidingDAO opleidingDAO = new OpleidingDAO();
            ArrayList<Module> modules = moduleDAO.modulesLaden(opleidingDAO.geefOpleidingID(opleiding));

            String json = gson.toJson(modules);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        String module = request.getParameter("module");

        if (module != null) {

            //laad doelstellingen voor module
            DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
            ModuleDAO moduleDAO = new ModuleDAO();
            ArrayList<Doelstelling> doelstellingen = doelstellingDAO.doelstellingenLaden(moduleDAO.geefModuleID(module));
            String json = gson.toJson(doelstellingen);

            response.setContentType("application/json");
            response.getWriter().write(json);
        }


         String doelstelling = request.getParameter("doelstelling");

        if (doelstelling != null) {

            //laad taken per doelstelling
            TaakDAO taakDAO = new TaakDAO();
            DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
            ArrayList<Taak> taken = taakDAO.takenLaden(doelstellingDAO.geefDoelstellingID(doelstelling));
            String json = gson.toJson(taken);

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
        return "Dit is de StudiegebiedenServlet!";
    }
}
