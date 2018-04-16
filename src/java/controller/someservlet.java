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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Gebruiker;
import model.GebruikerDAO;

/**
 *
 * @author Jens
 */


@WebServlet(name = "someservlet", urlPatterns = {"/someservlet"})
public class someservlet extends HttpServlet {
    
    GebruikerDAO gebruikerDAO = new GebruikerDAO();

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        HttpSession session = request.getSession(true);  
        
        ArrayList<Gebruiker> gebruikers = null;
        
        ArrayList<Gebruiker> cursisten = gebruikerDAO.gebruikersLaden(1);
        String json = new Gson().toJson(cursisten);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json); 
        */
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String page = request.getParameter("page");
        int p = Integer.parseInt(page);
        
        ArrayList<Gebruiker> cursisten = gebruikerDAO.gebruikersLaden(p);
        String json = new Gson().toJson(cursisten);

        session.setAttribute("json",  json);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }	
}