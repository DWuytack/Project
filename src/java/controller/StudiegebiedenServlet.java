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
import model.ScoreOverzicht;
import model.Taak;
import model.TaakDAO;

/**
 *
 * @author Ewout Phlips & Davino Tizarine
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

        int studiegebied, opleiding, module, doelstelling;

        Gson gson = new Gson();

        try {
            studiegebied = Integer.parseInt(request.getParameter("studiegebied"));

            if (studiegebied != 0) {

                //laad opleidingen per studiegebied
                OpleidingDAO opleidingDAO = new OpleidingDAO();
                ArrayList<Opleiding> opleidingen = opleidingDAO.opleidingenLaden(studiegebied);

                String json = gson.toJson(opleidingen);

                response.setContentType("application/json");
                response.getWriter().write(json);
            }
        } catch (NumberFormatException e) {
        }

        try {
            opleiding = Integer.parseInt(request.getParameter("opleiding"));

            if (opleiding != 0) {

                //laad opleidingen per opleiding
                ModuleDAO moduleDAO = new ModuleDAO();
                ArrayList<Module> modules = moduleDAO.modulesLaden(opleiding);

                String json = gson.toJson(modules);

                response.setContentType("application/json");
                response.getWriter().write(json);
            }
        } catch (NumberFormatException e) {
        }

        try {
            module = Integer.parseInt(request.getParameter("module"));

            if (module != 0) {

                //laad opleidingen per module
                DoelstellingDAO doelstellingDAO = new DoelstellingDAO();
                ArrayList<ScoreOverzicht> scoreOverzicht = doelstellingDAO.doelstellingenLadenModule(module);

                String json = gson.toJson(scoreOverzicht);

                response.setContentType("application/json");
                response.getWriter().write(json);
            }
        } catch (NumberFormatException e) {
        }

        try {
            doelstelling = Integer.parseInt(request.getParameter("doelstelling"));

            if (doelstelling != 0) {

                //laad opleidingen per doelstelling
                TaakDAO taakDAO = new TaakDAO();
                ArrayList<Taak> taken = taakDAO.takenLaden(doelstelling);

                String json = gson.toJson(taken);

                response.setContentType("application/json");
                response.getWriter().write(json);
            }
        } catch (NumberFormatException e) {
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
