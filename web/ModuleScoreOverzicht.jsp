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
        <%--<script src="js/table.js"></script>--%>
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
                                <select name="schooljaar" id="schooljaar" style="max-width:150px;">
                                    <option  selected disabled> Kies schooljaar.. </option>
                                    <c:forEach items="${schooljaren}" var="schooljaar">
                                        <option> ${schooljaar.schooljaar} </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td width="10px"></td>
                            <td>
                                <select name="semester" id="semester" onchange="checkJaar()" style="max-width:140px;" >
                                    <option selected disabled> Semester... </option>
                                    <c:forEach items="${semesters}" var="semester">                  
                                        <option> ${semester.semester} </option>                     
                                    </c:forEach>
                                </select>
                            </td>
                            <td width="10px"></td>
                            <td>
                                <select name="studiegebied" id="studiegebied" hidden onchange="laadOpleidingen()"  style="max-width:140px;">
                                    <option selected disabled> Studiegebied... </option>
                                    <c:forEach items="${studiegebieden}" var="studiegebied">                  
                                        <option> ${studiegebied.naam} </option>                     
                                    </c:forEach>
                                    <option> Voeg studiegebied toe... </option>
                                </select> 
                            </td>   
                            <td width="10px"></td>

                            <td><select id="studiegebied" hidden onchange="laadOpleidingen()" style="max-width:170px;">

                                </select></td>

                            <td width="10px"></td>

                            <td><select id="opleidingen" hidden onchange="laadModules()" style="max-width:130px;">

                                </select></td>

                            <td width="10px"></td>

                            <td><select id="modules" hidden onchange="laadCursistenScores()" style="max-width:130px;">

                                </select></td>
                        </tr>
                    </table>
                    <br><br>
                    <table class="datatable">
                        <thead> 
                            <tr>
                                <th onclick="sortTable(0)"><a>Achternaam</a></th>
                                <th onclick="sortTable(1)"><a>Voornaam</a></th>
                                <th onclick="sortTable(2)"><a>totaal score</a></th>                                                             
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><label name="curstinstenScores"  id="curstinstenScores" hidden disabled >
                                </label></td>
                                <td />
                                <c:forEach items="${cursistenScore}" var="cursistenScore">
                                
                                    <td> ${cursistenScore.voornaam} </td>
                                    <td> ${cursistenScore.achternaam} </td>
                                    <td> ${cursistenScore.totaal_score} </td>
                                  
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </section>
        </form>
    </body>
</html>
