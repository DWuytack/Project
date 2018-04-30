<%-- 
    Document   : Score
    Created on : 8-mrt-2018, 11:04:02
    Author     : Keanu Pestka
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
        <script src="js/table.js"></script>
        <script src="js/score.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/gebruikers.css">
        <title>Score overzicht</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <form method ="POST" action="ScoreServlet">
        <section id="pagename">
            <h2> Score overzicht </h2> 
        </section>       
        <section>            
                <br>
                <div class="table-container">
                    <div class="table-nav-header">

                        <div id="zoeken">
                            <a id="bt-zoeken">
                                <i class="material-icons">search</i>
                            </a>
                            <input type="text" name="zoekterm" value="${zoekterm}" onfocus="this.value = ''" size="15">
                        </div>
                        <div>                           
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div>
                    <br><br>
                    <table>
                        <tr>
                            <td>
                                <select name="Schooljaar">
                                    <option value="schooljaar" selected disabled> Kies schooljaar.. </option>
                                    <c:forEach items="${schooljaren}" var="schooljaar">
                                        <option value="schooljaar"> ${schooljaar.schooljaar} </option>   
                                    </c:forEach>                                 
                                </select>
                            </td>
                            <td width="10px"></td>
                            <td>
                                <select name="Semester" >
                                    <option value="semester" selected disabled> Kies semester.. </option>
                                    <c:forEach items="${semesters}" var="semester">
                                        <option value="semester"> ${semester.semester} </option> 
                                    </c:forEach> 
                                </select>
                            </td>                           
                            <td width="10px"></td>
                            <td>
                                <select name="Studiegebied" id ="studiegebied" onchange="laadOpleidingen()" style="max-width:150px;">
                                    <option value="studiegebied" selected disabled> Kies studiegebied.. </option>
                                    <c:forEach items="${studiegebieden}" var="studiegebied">                  
                                        <option value="studiegebied"> ${studiegebied.naam} </option>                     
                                    </c:forEach>                                    
                                </select>
                            </td>
                            <td width="10px"></td>
                            <td>
                                <select name="Opleiding" style="max-width:122px;">
                                    <option   value="opleiding" selected disabled> Kies opleiding.. </option>
                                    <c:forEach items="${opleidingen}" var="opleiding">
                                        <option value="opleiding"> ${opleiding.naam} </option>  
                                    </c:forEach>                                      
                                </select>
                            </td>
                            <td width="10px"></td>
                            <td>
                                <select name="Module" style="max-width:112px;">
                                    <option   value="module" selected disabled> Kies module.. </option>
                                    <c:forEach items="${modules}" var="module">
                                        <option value="module"> ${module.naam} </option> 
                                    </c:forEach> 
                                </select>
                            </td>                            
                        </tr>
                    </table>
                    <br> <br>
                    <table class="datatable">
                        <thead> 
                            <tr>
                                <th onclick="sortTable(0)"><a>Achternaam</a></th>
                                <th onclick="sortTable(1)"><a>Voornaam</a></th>
                                <th onclick="sortTable(2)"><a>Typescore</a></th>
                                <th onclick="sortTable(3)"><a>Score</a></th>
                                <th onclick="sortTable(4)"><a>Geslaagd</a></th>                                
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:forEach items="${lijstScore}" var="score">
                                <tr>
                                    <c:if test="${score.scoreID == sessionScope.editID}" >
                                        <td> <input type="text" name="score" size="15" maxlength="25" value="${score}"> </td>
                                        <td> <input type="text" name="score_beschrijving" size="15" maxlength="25" value="${score.beschrijving}"> </td>
                                        </c:if>

                                    <c:if test="${score.scoreID != sessionScope.editID}" >
                                        <td> ${score} </td>
                                        <td> ${score_beschrijving} </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h2 id="demo"> </h2>
                </div>
            </form>    
        </section>
    </body>
</html>
