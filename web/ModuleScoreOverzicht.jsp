<%-- 
    Document   : ModuleScoreOverzicht
    Created on : 8-mrt-2018, 11:04:02
    Author     : Keanu & Gil
--%>

<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>    
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>        
        <script src="js/score.js"></script>
        <link rel="stylesheet" href="css/theme.css">

        <title>Score overzicht per klas</title>
        <style>
            #nav-topke {
                width: auto;
            }     
        </style>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <form method ="POST" action="ScoreServlet?keuze=reset">
            <section id="pagename">
                <h2> Score overzicht per klas </h2> 
            </section>       
            <section>            
                <br>
                <div class="table-container">
                    <div class="table-nav-header">

                        <div>                           
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div>
                    <br><br>
                    <table id="nav-topke">
                        <tr>
                            <td>
                                <select name="schooljaar" id="schooljaar" style="background: #f9f9f9">
                                    <option  selected disabled> Kies schooljaar.. </option>
                                    <c:forEach items="${schooljaren}" var="schooljaar">
                                        <option> ${schooljaar.schooljaar} </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td width="10px"></td>
                            <td>
                                <select name="semester" id="semester" onchange="checkJaar()" style="background: #f9f9f9" class="drop" >" >
                                    <option selected disabled> Semester... </option>
                                    <c:forEach items="${semesters}" var="semester">                  
                                        <option> ${semester.semester} </option>                     
                                    </c:forEach>
                                </select>
                            </td>
                            <td width="10px"></td>
                            <!-- Studiegebied kiezer -->
                            <td>
                                <select name="studiegebied" id="studiegebied"  
                                        onchange="laadDropdown('opleidingen')" 
                                        style="background: #efc4c4"  class="drop" hidden>
                                    <option selected disabled> Studiegebied... 
                                    </option>
                                    <c:forEach items="${studiegebieden}" 
                                               var="studiegebied">                  
                                        <option> ${studiegebied.naam} </option>                     
                                    </c:forEach>
                                    <option> Voeg studiegebied toe... </option>
                                </select> 
                            </td>
                            <td width="10px"></td>

                            <!-- Opleiding kiezer -->
                            <td>
                                <select id="opleidingen" onchange="laadDropdown('modules')"
                                        hidden style="background: #f9f9f9"  class="drop"></select>
                            </td>
                            <td width="10px"></td>

                            <!-- Module kiezer -->
                            <td>  
                                <select id="modules" hidden onchange="laadCursistenScores()"
                                        style="background: #f9f9f9"  class="drop"></select> 
                            </td>
                            <td width="10px"></td>
                        </tr>
                    </table>
                    <br><br>
                    <table class="datatable">
                        <thead> 
                            <tr>
                                <th onclick="sortTable(0)"><a>Achternaam</a></th>
                                <th onclick="sortTable(1)"><a>Voornaam</a></th>
                                <th onclick="sortTable(2)"><a>Score</a></th>                                                             
                            </tr>
                        </thead>
                        <tbody>

                            <tr id="rij0" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij1" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij2" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij3" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij4" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij5" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij6" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij7" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij8" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij9" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij10" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij11" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                            <tr id="rij12" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij13" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij14" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij15" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij16" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij17" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij18" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij19" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij20" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij21" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij22" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij23" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij24" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij25" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij26" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij27" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij28" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij29" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>
                            <tr id="rij30" hidden>
                                <td><label id="achternaam" disabled >
                                    </label></td>
                                <td><label id="voornaam" disabled >
                                    </label></td>                              
                                <td><label id="score" disabled >
                                    </label></td>
                                <td />                         
                            </tr>

                        </tbody>
                    </table>
                </div>
            </section>
        </form>
    </body>
</html>
