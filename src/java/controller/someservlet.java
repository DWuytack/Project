/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gebruiker;
import model.GebruikerDAO;

/**
 *
 * @author Jens & D
 */
public class someservlet extends HttpServlet {
    
    GebruikerDAO gebruikerDAO = new GebruikerDAO();

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

        String actie = "";
        String json = "";
        String page = request.getParameter("page");
        String editID = request.getParameter("idEdit");
        String saveID = request.getParameter("idSave");
        int sessionID = 0;

        if (page != null) {
            actie = "Vraag pagina aan";
            int p = Integer.parseInt(page);
            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            ArrayList<Gebruiker> gebruikers = gebruikerDAO.gebruikersLaden(p);
            session.removeAttribute("lijstGebruikers");
            session.setAttribute("lijstGebruikers", gebruikers);
            
            //omzetten naar json
            json = new Gson().toJson(gebruikers);
            session.removeAttribute("json");
            session.setAttribute("json", json);
            
            //session.setAttribute("json",  json);
            
            response.setContentType("application/json");
            //response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        
        Gebruiker gebruiker = new Gebruiker();
        
        if (editID != null) {
            actie = "Edit gebruiker";
            session.removeAttribute("idEdit");
            session.setAttribute("idEdit", editID);
            
            //ArrayList<Gebruiker> lijst = (ArrayList<Gebruiker>) session.getAttribute("lijstGebruikers");
            
            int callID = Integer.valueOf(editID) - 3;
            
            json = new Gson().toJson(callID);

            response.setContentType("application/json");
            //response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        
        if (saveID != null) { //onclick attack protection
            actie = "Gebruiker opslaan";
            editID = session.getAttribute("idEdit").toString();
            int requestID = Integer.valueOf(editID);
            int callID = Integer.valueOf(saveID);
             
            if( session.getAttribute("idEdit") != null && requestID == callID + 3 ) {
                int id = (int) (Integer.valueOf(editID) * 0.25);
                
                //session aanpassen
                session.removeAttribute("idEdit");
                session.removeAttribute("idSave");
                session.setAttribute("idSave", saveID);
                
                //object vullen
                gebruiker.setVoorNaam(request.getParameter("voornaam"));
                gebruiker.setAchternaam(request.getParameter("achternaam"));
                gebruiker.setRol(request.getParameter("rol"));
                java.sql.Date datum = java.sql.Date.valueOf(request.getParameter("geboorteDatum"));
                gebruiker.setGeboorteDatum(datum);
                gebruiker.setEmail(request.getParameter("email"));
                gebruiker.setLogin(request.getParameter("login"));
                //object doorsturen naar database
                gebruikerDAO.gebruikerAanpassen(id, gebruiker);

                json = new Gson().toJson(gebruiker);
            } else {
                json = new Gson().toJson("Error: idSave & idEdit komen niet overeen");
            }
            
            response.setContentType("application/json");
            //response.setCharacterEncoding("UTF-8");
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
        return "Dit is de someServlet!";
    }

}
