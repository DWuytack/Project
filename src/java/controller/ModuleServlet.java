/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Instellingen;
import model.Module;
import model.ModuleDAO;


/**
 *
 * @author Davino
 */
@WebServlet(name = "ModuleServlet", urlPatterns = {"/ModuleServlet"})
public class ModuleServlet extends HttpServlet {

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
    
            
      
        HttpSession session = request.getSession(true);
        ModuleDAO moduleDAO = new ModuleDAO();
        
         ArrayList<Module> modules = null;

        try {
            String actie = "";
            String editID = request.getParameter("idEdit");
            String cancelID = request.getParameter("idCancel");
            String saveID = request.getParameter("idSave");
            String deleteID = request.getParameter("idDelete");
            String eerste = request.getParameter("Eerste");
            String vorige = request.getParameter("Vorige");
            String volgende = request.getParameter("Volgende");
            String laatste = request.getParameter("Laatste");
            int bladz = (int) session.getAttribute("bladzijde");
            int aantalModules = (int) session.getAttribute("aantalRecords");

            if (eerste != null) {
                bladz = 1;
                session.setAttribute("bladzijde", bladz);
                int getoondeModules = Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeModules > aantalModules) {
                    getoondeModules = aantalModules;
                }
                session.setAttribute("getoondeModules", getoondeModules);
                modules = moduleDAO.modulesLaden(bladz);
                session.setAttribute("lijstModules", modules);
                response.sendRedirect("Module.jsp");
            }

            if (vorige != null) {
                bladz--;
                if (bladz < 1) {
                    bladz = 1;
                }
                int getoondeModules = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeModules > aantalModules) {
                    getoondeModules = aantalModules;
                }
                session.setAttribute("getoondeModules", getoondeModules);
                session.setAttribute("bladzijde", bladz);
                modules = moduleDAO.modulesLaden(bladz);
                session.setAttribute("lijstModules", modules);
                response.sendRedirect("Module.jsp");
            }

            if (volgende != null) {
                bladz++;
                if (bladz > ((aantalModules / 5) + 1)) {
                    bladz--;
                }
                int getoondeModules = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeModules > aantalModules) {
                    getoondeModules = aantalModules;
                }
                session.setAttribute("getoondeModules", getoondeModules);
                session.setAttribute("bladzijde", bladz);
                modules = moduleDAO.modulesLaden(bladz);
                session.setAttribute("lijstModules", modules);
                response.sendRedirect("Module.jsp");
            }

            if (laatste != null) {
                bladz = (aantalModules / 5) + 1;
                int getoondeModules = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
                if (getoondeModules > aantalModules) {
                    getoondeModules = aantalModules;
                }
                session.setAttribute("getoondeModules", getoondeModules);
                System.out.println("bladz: " + bladz);
                session.setAttribute("bladzijde", bladz);
                modules = moduleDAO.modulesLaden(bladz);
                session.setAttribute("lijstModules", modules);
                response.sendRedirect("Module.jsp");
            }

            if (editID != null) {
                actie = "Edit module";
            }
            if (cancelID != null) {
                actie = "Cancel module";
            }
            if (saveID != null) {
                actie = "Save module";
            }
            if (deleteID != null) {
                actie = "Delete module";
            }

            Module module = new Module();

            switch (actie) {

                case "Edit module":
                    session.setAttribute("editID", editID);
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    
                    
                    moduleDAO.moduleAanpassen(module);
                    modules = moduleDAO.modulesLaden(1);
                    session.setAttribute("lijstModules", modules);
                    response.sendRedirect("Module.jsp"); //logged-in page 
                    break;

                case "Delete module":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");

                    moduleDAO.moduleVerwijderen(module);
                    modules = moduleDAO.modulesLaden(1);

                    session.setAttribute("lijstModules", modules);
                    response.sendRedirect("Module.jsp");
                    break;

                case "Cancel module":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("Module.jsp"); //logged-in page 
                    break;

                case "Save module":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("deleteID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("Module.jsp"); //logged-in page 
                    break;

    
}
            } catch (Throwable theException) {

        }
    }
}
    


