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
            String json = new Gson().toJson(gebruikers);
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
            
            ArrayList<Gebruiker> gebruikers = (ArrayList<Gebruiker>) session.getAttribute("lijstGebruikers");
            ArrayList<Gebruiker> gebruikersJson = (ArrayList<Gebruiker>) session.getAttribute("json");
            
            ArrayList<Gebruiker> result = gson.fromJson(session.getAttribute("json"), ArrayList<Gebruiker>.class);
            
            for(Gebruiker user : lijst) {
                for(Gebruiker user2 : gebruikers) {
                    if(user.voorNaam.equals(""))
                           
                }
                if(user.voorNaam.equals(""))
                           
            }
            //int id = cursisten.get(0).getGebruikerID();
            //session.setAttribute("editID", id);
            
            //String json = new Gson().toJson(session.getAttribute("editID"));
            String json = new Gson().toJson(lijst);

            response.setContentType("application/json");
            //response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        if (saveID != null) {
            /* try {} catch (ClassCastException | NullPointerException e) {
                
            }*/
            
            int id = Integer.valueOf(session.getAttribute("editID").toString());
            
            
            gebruiker.setVoorNaam(request.getParameter("voornaam"));
            gebruiker.setAchternaam(request.getParameter("achternaam"));
            gebruiker.setRol(request.getParameter("rol"));
            java.sql.Date datum = java.sql.Date.valueOf(request.getParameter("geboorteDatum"));
            gebruiker.setGeboorteDatum(datum);
            gebruiker.setEmail(request.getParameter("email"));
            gebruiker.setLogin(request.getParameter("login"));
            gebruikerDAO.gebruikerAanpassen(id, gebruiker);
            
            String json = new Gson().toJson(gebruiker);
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
