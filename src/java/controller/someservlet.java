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
import model.Instellingen;

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
        String deleteID = request.getParameter("idDelete");
        //int bladz = (int) session.getAttribute("bladzijde");
        int aantalGebruikers = (int) session.getAttribute("aantalRecords");

        if (page != null) {
            int getoondeGebruikers = Instellingen.AANTAL_RECORDS_PER_PAGE;
            if (getoondeGebruikers > aantalGebruikers) {
                getoondeGebruikers = aantalGebruikers;
            }
            session.setAttribute("aantalRecords", aantalGebruikers);
            session.setAttribute("getoondeGebruikers", getoondeGebruikers);
            session.setAttribute("bladzijde", page);

            int p = Integer.parseInt(page);
            GebruikerDAO gebruikerDAO = new GebruikerDAO();
            ArrayList<Gebruiker> gebruikers = gebruikerDAO.gebruikersLaden(p);
            session.removeAttribute("lijstGebruikers");
            session.setAttribute("lijstGebruikers", gebruikers);
            
            //omzetten naar json
            json = new Gson().toJson(gebruikers);
            session.removeAttribute("json");
            session.setAttribute("json", json);
            
            //json doorsturen
            response.setContentType("application/json");
            //response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        
        //set action
        if (editID != null)
            actie = "Edit gebruiker";
        else if (saveID != null)
            actie = "Save gebruiker";
        else if (deleteID != null)
            actie = "Delete gebruiker";
        
        Gebruiker gebruiker = new Gebruiker();
        int requestID, callID;
        
        switch (actie) {
            case "Edit gebruiker":
                callID = Integer.valueOf(editID) - 3;
                
                //session aanpassen
                session.removeAttribute("idEdit");
                session.setAttribute("idEdit", editID);
                //ArrayList<Gebruiker> lijst = (ArrayList<Gebruiker>) session.getAttribute("lijstGebruikers");
 
                json = new Gson().toJson(callID);
                
                //json doorsturen
                response.setContentType("application/json");
                //response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                
                break;
            case "Save gebruiker":
                editID = session.getAttribute("idEdit").toString();
                requestID = Integer.valueOf(editID);
                callID = Integer.valueOf(saveID);

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
                    gebruiker.setGeboorteDatum(request.getParameter("geboorteDatum"));
                    gebruiker.setEmail(request.getParameter("email"));
                    gebruiker.setLogin(request.getParameter("login"));
                    //object doorsturen naar database
                    gebruikerDAO.gebruikerAanpassen(id, gebruiker);

                    json = new Gson().toJson(gebruiker);
                } else {
                    json = new Gson().toJson("Error: idSave & idEdit komen niet overeen");
                }
                
                //json doorsturen
                response.setContentType("application/json");
                //response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            case "Delete gebruiker":
                int id = (int) (Integer.valueOf(deleteID) * 0.25);
                
                //gebruiker verwijderen
                gebruikerDAO.gebruikerVerwijderen(id);

                json = new Gson().toJson("verwijderd: " + id);
                
                //json doorsturen
                response.setContentType("application/json");
                //response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                
                
                //page request wordt opnieuw opgevraagd om pagina te verwijderen
                break;
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
