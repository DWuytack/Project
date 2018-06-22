/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gebruiker;
import model.GebruikerDAO;

/**
 *
 * @author Dirk
 */
public class LoginServlet extends HttpServlet {

     
    Gson gson = new Gson();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {
               
            Gebruiker gebruiker = new Gebruiker();
            gebruiker.setLogin(request.getParameter("checkLogin"));
            gebruiker.setPaswoord(request.getParameter("paswoord"));
            
            GebruikerDAO gebruikerDAO=new GebruikerDAO();
            gebruiker = gebruikerDAO.login(gebruiker);
            
             response.setContentType("application/json");

            if (gebruiker.isGeldig()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", gebruiker);
               
                response.getWriter().write(gson.toJson(true));
                		
            } else {
               response.getWriter().write(gson.toJson(false));
            }
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
   
}
