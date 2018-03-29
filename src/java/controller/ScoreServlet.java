package controller;

import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Score;
import model.ScoreDAO; 

/**
 *  Deze klasse bevat de aanpassing van het scoretype.
 * @author gil-_
 */
@WebServlet(name = "ScoreServlet", urlPatterns = {"/ScoreServlet"})
public class ScoreServlet extends HttpServlet {
    
    ScoreDAO scoreDAO = new ScoreDAO();

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
        ArrayList<Score> beoordelingssoorten = null;

        try {
            String actie = "";
            String editID = request.getParameter("idEdit");
            String cancelID = request.getParameter("idCancel");
            String saveID = request.getParameter("idSave");
            String deleteID = request.getParameter("idDelete");
            
             if (editID != null) {
                actie = "Edit typeScore";
            }
            if (cancelID != null) {
                actie = "Cancel typeScore";
            }
            if (saveID != null) {
                actie = "Save typeScore";
            }
            if (deleteID != null) {
                actie = "Delete typeScore";
            }
            
            
            Score typeScore = new Score();

            switch (actie) {

                case "Edit typeScore":
                    session.setAttribute("editID", editID);
                    session.removeAttribute("saveID");                  
                    beoordelingssoorten = scoreDAO.typeScoreLaden();
                    session.setAttribute("lijstBeoordelingssoorten", beoordelingssoorten);
                    response.sendRedirect("TypeScoreOverzicht.jsp");
                    break;
                    
                case "Cancel typeScore":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");
                    response.sendRedirect("TypeScoreOverzicht.jsp");
                    break;

                case "Save typeScore":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    int id = Integer.parseInt(saveID);
                    typeScore.setNaam(request.getParameter("naam"));
                    typeScore.setBeschrijving(request.getParameter("beschrijving"));
                    typeScore.setWaarde(Integer.parseInt(request.getParameter("waarde")));
                    scoreDAO.typeScoreAanpassen(id, typeScore);
                    beoordelingssoorten = scoreDAO.typeScoreLaden();
                    session.setAttribute("lijstBeoordelingssoorten", beoordelingssoorten);
                    response.sendRedirect("TypeScoreOverzicht.jsp");
                    session.removeAttribute("saveID");
                    break;
                    
                case "Delete typeScore":
                    session = request.getSession(true);
                    session.removeAttribute("editID");
                    session.removeAttribute("saveID");
                    scoreDAO.typeScoreVerwijderen(Integer.parseInt(deleteID));
                    beoordelingssoorten = scoreDAO.typeScoreLaden();
                    session.setAttribute("lijstBeoordelingssoorten", beoordelingssoorten);
                    response.sendRedirect("TypeScoreOverzicht.jsp");
                    break;
     
            }

        } catch (Throwable theException) {}
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
