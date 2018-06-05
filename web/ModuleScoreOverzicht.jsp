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
        <link rel="stylesheet" href="css/score.css">
        <link rel="stylesheet" href="css/theme.css">

        <title>Score overzicht per klas</title>
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
                    <br><br>
                    <table id="nav-topke">
                        <tr>
                            <!-- Datum kiezer -->
                            <td> <input type="date" id="datum"  value="today" onchange="pasSemesterAan()"
                                        required style="font-size: 0.9rem"  
                                        style="background: #f9f9f9"  > </td>
                        <script>
                            let today = new Date().toISOString().substr(0, 10);
                            document.querySelector("#datum").value = today;
                        </script>

                        <!-- Semester kiezer-->
                        <td> <select name="Semester" id="Semester" onchange="laadCursistenOpnieuw()"
                                     style="background: #f9f9f9" class="drop" >
                                <option selected > Semester... </option>
                                <c:forEach items="${semesters}" var="semester">                  
                                    <option> ${semester.semester} </option>                     
                                </c:forEach>
                            </select> </td>

                        <td width="10px"></td>

                        <script>
                            let tijd = new Date();
                            switch (tijd.getMonth() + 1) {
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                    document.querySelector("#Semester").selectedIndex = 2;
                                    break;
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 1:
                                    document.querySelector("#Semester").selectedIndex = 1;
                                    break;
                            }

                        </script>

                        <!-- Studiegebied kiezer -->
                        <td>
                            <select name="studiegebied" id="studiegebied"  
                                    onchange="laadDropdown('opleidingen')" 
                                    style="background: #efc4c4"  class="drop">
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
                    <table width="65%">
                        <!-- Titelbalk-->
                        <tr height="20px" />
                        <tr bgcolor="#ceccca" height="50px" >                            
                            <th width="60%"><b>Achternaam</th>                             
                            <th width="30%"><b>Voornaam</b></th>                             
                            <th width="10%"><b>Score</b></th>                            
                        </tr>
                        
                        <tr height="20px" />
                    </table>
                    <table id="scoretable" width="80%">
                    </table>
                </div>
            </section>
        </form>
    </body>
</html>
