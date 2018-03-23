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
        <title>Score Menu</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    
            <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="ScoreServlet">
                
                                <select name="Schooljaar">
                    <option value="schooljaar">schooljaar</option>
                    <option value="semester">semester</option>
                    <option value="module">module</option>
                    <option value="opleiding">opleiding</option>
                </select>

                
                
    <table>
                        <tr style="background-color:rgba(0, 0, 0, 0);"> 
                            <td  style="text-align:center;">   
                                <input type="image"  name="score zoeken" value="score zoeken" src='images/searchUser.png'> 
                                <input  type="text" style="height:30px;font-size:14pt;" name="zoekterm" size="25" maxlength="25" value=""> 
                            </td> 
                        </tr>
                    </table>
    <body> 
        
        <table class="datatable">
                        <thead> 
                            <tr>
                                <th onclick="sortTable(0)"><a>Taak</a></th>
                                <th onclick="sortTable(1)"><a>Beschrijving</a></th>
                                <th onclick="sortTable(2)"><a>Score</a></th>
                                <th onclick="sortTable(3)"><a>Module</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>Actions</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstGebruikers}" var="cursist">
                                <tr>
                                    <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                        <td> <input type="text" name="achternaam" size="15" maxlength="25" value="${cursist.achternaam}"> </td>
                                        <td> <input type="text" name="voornaam" size="15" maxlength="25" value="${cursist.voorNaam}"> </td>
                                        <td> <input type="text" name="login" size="15" maxlength="25" value="${cursist.login}"> </td>
                                        <td> <input type="text" name="rol" size="15" maxlength="25" value="${cursist.rol}"> </td>
                                        <td> <input type="text" name="geboorteDatum" size="15" maxlength="25" value="<fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" />"> </td>
                                        <td> <input type="text" name="email" size="15" maxlength="25" value="${cursist.email}"/> </td>
                                        </c:if>

                                    <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                        <td> ${cursist.achternaam} </td>
                                        <td> ${cursist.voorNaam} </td>
                                        <td> ${cursist.login} </td>
                                        <td> ${cursist.rol} </td>
                                        <td> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" /> </td>
                                        <td> ${cursist.email} </td>

                                    </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td>
                                            <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                                <input type="image"  name="scoreEdit" value="${cursist.gebruikerID}" src='images/pencil.png'>
                                                <input type="image"  name="scoreDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png'>
                                            </c:if>
                                            <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                                <input type="image"  name="scoreSave" value="${cursist.gebruikerID}" src='images/green.png'>
                                                <input type="image"  name="scoreCancel" value="${cursist.gebruikerID}" src='images/cancel.png'>
                                            </c:if>
                                        </td>
                                    </c:if>


                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                    <table>
                        <tr style="background-color:rgba(0, 0, 0, 0);"> 
                            <td style="text-align:center;">   
                                <input type="image"  name="Eerste 10" value="Eerste 10" src='images/eerste.png'> 
                                <input type="image"  name="Vorige" value="Eerste 10" src='images/terug.png'>  
                                <input type="image"  name="Volgende" value="Volgende 10" src='images/volgende.png'> 
                                <input type="image"  name="Laatste 10" value="Laatste 10" src='images/laatste.png'> 
                            </td> 
                        </tr>
                        <tr> 
                            <td colspan="3" style="text-align:center;">   
                                <input type="image"  name="score toevoegen" value="score toevoegen" src='images/adduser.png'> 
                            </td> 
        

                <select name="Schooljaar">
                    <option value="schooljaar">schooljaar</option>
                    <option value="semester">semester</option>
                    <option value="module">module</option>
                </select>
            </form>    
        </section>
    </body>
</html>
