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
import model.*;



/**
*
* @author Dirk
*/
public class MenuServlet extends HttpServlet {

/**
*
* Processes requests for both HTTP GET and POST * methods.
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
session.setAttribute("zoekterm", null);

switch (actie) {

case "Overzicht cursisten":

//laden van cursisten uit database
GebruikerDAO gebruikerDAO = new GebruikerDAO();
ArrayList cursisten = gebruikerDAO.cursistenLaden(1);

int aantalCursisten = gebruikerDAO.geefAantalGebruikers();
session.setAttribute("aantalRecords", aantalCursisten);
session.setAttribute("bladzijde", 1);
int getoondeCursisten = Instellingen.AANTAL_RECORDS_PER_PAGE;
if (getoondeCursisten > aantalCursisten) {
getoondeCursisten = aantalCursisten;
}
session.setAttribute("getoondeGebruikers", getoondeCursisten);
session.setAttribute("lijstCursisten", cursisten);
response.sendRedirect("CursistenOverzicht.jsp");
break;

case "Overzicht gebruikers":

//laden van gebruikers uit database
gebruikerDAO = new GebruikerDAO();
ArrayList gebruikers = gebruikerDAO.gebruikersLaden(1);

int aantalGebruikers = gebruikerDAO.geefAantalGebruikers();
session.setAttribute("aantalRecords", aantalGebruikers);
session.setAttribute("bladzijde", 1);
int getoondeGebruikers = Instellingen.AANTAL_RECORDS_PER_PAGE;
if (getoondeGebruikers > aantalGebruikers) {
getoondeGebruikers = aantalGebruikers;
}
session.setAttribute("getoondeGebruikers", getoondeGebruikers);
session.setAttribute("lijstGebruikers", gebruikers);
response.sendRedirect("GebruikersOverzicht.jsp");
break;

case "Overzicht scores klas":

// laden van schooljaren uit database en in het geheugen plaatsen
SchooljaarDAO schooljaarDAO = new SchooljaarDAO();
ArrayList schooljaren = schooljaarDAO.schooljarenLaden();
session.setAttribute("schooljaren", schooljaren);

// laden semester uit database en in het geheugen plaatsen
SemesterDAO semesterDAO = new SemesterDAO();
ArrayList semesters = semesterDAO.semestersLaden();
session.setAttribute("semesters", semesters);

// laden studiegebieden
StudiegebiedDAO studiegebiedDAO = new StudiegebiedDAO();
ArrayList studiegebieden = studiegebiedDAO.studiegebiedenLaden();
session.setAttribute("studiegebieden", studiegebieden);
response.sendRedirect("ModuleScoreOverzicht.jsp");
break;

case "Overzicht scores cursist":

// laden van schooljaren uit database en in het geheugen plaatsen
schooljaarDAO = new SchooljaarDAO();
schooljaren = schooljaarDAO.schooljarenLaden();
session.setAttribute("schooljaren", schooljaren);

// laden semester uit database en in het geheugen plaatsen
semesterDAO = new SemesterDAO();
semesters = semesterDAO.semestersLaden();
session.setAttribute("semesters", semesters);

// laden studiegebieden
studiegebiedDAO = new StudiegebiedDAO();
studiegebieden = studiegebiedDAO.studiegebiedenLaden();
session.setAttribute("studiegebieden", studiegebieden);
response.sendRedirect("CursistScoreOverzicht.jsp");
break;

case "Overzicht studiegebieden":
studiegebiedDAO = new StudiegebiedDAO();
studiegebieden = studiegebiedDAO.studiegebiedenLaden();
session.setAttribute("studiegebieden", studiegebieden); 
response.sendRedirect("OverzichtStudiegebieden.jsp"); 
break;

case "Evaluatieformulieren":

//laden schooljaren
    
schooljaarDAO = new SchooljaarDAO();
schooljaren = schooljaarDAO.schooljarenLaden();
session.setAttribute("schooljaren", schooljaren);

//laden semesters
semesterDAO = new SemesterDAO();
semesters = semesterDAO.semestersLaden();
session.setAttribute("semesters", semesters);

//laden studiegebieden
studiegebiedDAO = new StudiegebiedDAO();
studiegebieden = studiegebiedDAO.studiegebiedenLaden();
session.setAttribute("studiegebieden", studiegebieden);

// laden lesnr
LesnrDAO  lesnrDAO = new LesnrDAO();
ArrayList<Lesnr> lesnrs = lesnrDAO.lesnrsLaden();
session.setAttribute("lesnrs", lesnrs);

response.sendRedirect("EvaluatieFormulier.jsp");
break;

case "Rapport":
response.sendRedirect("Rapport.jsp");
break;

case "Type score aanpassen":
//laden van de types score
BeoordelingssoortDAO beoordelingssoortDAO = new BeoordelingssoortDAO();
ArrayList beoordelingssoort = beoordelingssoortDAO.beoordelingssoortenLaden();
session.setAttribute("beoordelingssoorten", beoordelingssoort);
response.sendRedirect("TypeScoreOverzicht.jsp");
break;

case "Cursisten inschrijven":
ModuleDAO moduleDAO = new ModuleDAO();
gebruikerDAO = new GebruikerDAO();
ArrayList module = moduleDAO.modulesLaden();
ArrayList gebruiker = gebruikerDAO.cursistenLaden1();
session.setAttribute("modules", module);
session.setAttribute("gebruikers", gebruiker);
response.sendRedirect("CursistenInschrijven.jsp");
break;

}

} catch (Throwable theException) {

}

}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}

/**
* Handles the HTTP POST method.
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
}// 
}