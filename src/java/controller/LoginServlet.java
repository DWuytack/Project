/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

     public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {
            
            Gebruiker gebruiker = new Gebruiker();
            gebruiker.setLogin(request.getParameter("un"));
            gebruiker.setPaswoord(request.getParameter("pw"));
            
            gebruiker = GebruikerDAO.login(gebruiker);

            if (gebruiker.isGeldig()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", gebruiker);
                response.sendRedirect("Menu.jsp"); //logged-in page      		
            } else {
                response.sendRedirect("InvalidLogin.jsp"); //error page 
            }
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
}
