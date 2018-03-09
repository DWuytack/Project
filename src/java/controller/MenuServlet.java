/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gebruiker;
import model.GebruikerDAO;
import model.Score;
import model.ScoreDAO;

/**
 *
 * @author Dirk
 */
public class MenuServlet extends HttpServlet {



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
        response.setContentType("text/html;charset=UTF-8");

        try {

            String actie = request.getParameter("actie");
            HttpSession session = request.getSession(true);
            switch (actie) {

                case "Overzicht cursisten":

                    //laden van cursisten uit database
                    GebruikerDAO gebruikerDAO = new GebruikerDAO();
                    ArrayList<Gebruiker> cursisten = gebruikerDAO.cursistenLaden();

                    session.setAttribute("lijstCursisten", cursisten);

                    response.sendRedirect("CursistenOverzicht.jsp");
                    break;
                case "Overzicht gebruikers":
                    GebruikerDAO gebruikerDAO2 = new GebruikerDAO();
                    ArrayList<Gebruiker> gebruikers = gebruikerDAO2.gebruikersLaden();

                    session.setAttribute("lijstGebruikers", gebruikers);
                    response.sendRedirect("GebruikersOverzicht.jsp");
                    break;
                case "Overzicht doelstellingen":

                    //laden van doelstellingen uit database
                    response.sendRedirect("Doelstelling.jsp");
                    break;
                case "Overzicht taken":
                    response.sendRedirect("Taken.jsp");
                    break;
                case "Overzicht opleidingen":
                    //laden van modules
                    response.sendRedirect("Opleiding.jsp");
                    break;
                case "Overzicht modules":
                    //laden van modules uit database

                    response.sendRedirect("Module.jsp");
                    break;
                case "Overzicht scores":
                    //laden van de scores uit de database
                    
                    response.sendRedirect("Score.jsp");

                    ScoreDAO scoreDAO = new ScoreDAO();
                    ArrayList<Score> score = ScoreDAO.scoreLaden();
                    session.setAttribute("lijstScores", score);

                    break;
                case "Evaluatieformulieren":
                    response.sendRedirect("EvaluatieFormulier.jsp");
                    break;
                case "Rapport":
                    response.sendRedirect("Rapport.jsp");
                    break;
                case "Type score aanpassen":
                    //laden van de types scores
                    response.sendRedirect("TypeScore.jsp");
                    break;
            }

        } catch (Throwable theException) {
            System.out.println(theException);
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
        processRequest(request, response);
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
        return "Short description";
    }// </editor-fold>

}
