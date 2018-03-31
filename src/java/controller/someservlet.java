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
        
        ArrayList<Gebruiker> cursisten = gebruikerDAO.gebruikersLaden(1);
        String json = new Gson().toJson(cursisten);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json); 
    }
}