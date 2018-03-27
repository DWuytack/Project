<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
    Author     : Gil
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>Gebruikers overzicht</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="GebruikersServlet">
                <div class="table-container">
                    
                    <div id="side-nav">
                        <div id="zoeken">
                            <a id="bt-zoeken">
                                <i class="material-icons">search</i>
                            </a>
                            <input type="text" name="zoekterm" value="" size="15"">
                        </div>
                        <div>
                            <input type="image" name="gebruiker toevoegen" value="gebruiker toevoegen" src='images/person_add.png'> 
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div>
                        
                    <table>
                        <thead>
                            <tr>
                                <th align="center" width="12%" onclick="sortTable(0)">Achternaam</a</th>
                                <th align="center" width="12%" onclick="sortTable(1)">Voornaam</a</th>
                                <th align="center" width="12%" onclick="sortTable(2)">Login</th>
                                <th align="center" width="12%" onclick="sortTable(3)">Rol</th>
                                <th align="center" width="14%" onclick="sortTable(4)">GebtDatum</th>
                                <th align="center" width="28%" onclick="sortTable(5)">E-mail</th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th >Acties</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstGebruikers}" var="cursist">
                                <tr>
                                    <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                        <td width="12%"> <input type="text" name="achternaam"  value="${cursist.achternaam}"> </td>
                                        <td width="12%"> <input type="text" name="voornaam" value="${cursist.voorNaam}"> </td>
                                        <td width="12%"> <input type="text" name="login"  value="${cursist.login}"> </td>
                                        <td> 
                                <select width="12%" name="rol" value="${cursist.rol}">
                                    <option value="admin">admin</option>
                                    <option value="leerkracht">leerkracht</option>
                                    <option value="cursist">cursist</option>
                                    <option value="secretariaat">secretariaat</option></td>
                                </select>
                                <td width="14%" align="center"> <input  style="text-align:right;" type="date" name="geboorteDatum" value="${cursist.geboorteDatum}" > </td>
                                <td width="28%"> <input type="text" name="email"  value="${cursist.email}"/> </td>
                                </c:if>

                            <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                <td> ${cursist.achternaam} </td>
                                <td> ${cursist.voorNaam} </td>
                                <td> ${cursist.login} </td>
                                <td > ${cursist.rol} </td>
                                <td align="center"> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yyyy" /> </td>
                                <td> ${cursist.email} </td>

                            </c:if>

                            <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                <td class="actie" >
                                    <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                        <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src='images/pencil.png'>
                                        <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png'>
                                    </c:if>
                                    <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                        <input type="image"  name="idSave" value="${cursist.gebruikerID}" src='images/green.png'>
                                        <input type="image"  name="idCancel" value="${cursist.gebruikerID}" src='images/cancel.png'>
                                    </c:if>
                                </td>
                            </c:if>
                            </tr> 
                        </c:forEach>
                        </tbody>
                    </table>
                    <br><br>
                    <div class="table-nav-footer">
                        <!-- WIP -->
                        <center>Totaal aantal gebruikers: <br><br> ${sessionScope.getoondeGebruikers}/${sessionScope.aantalRecords}</center>
                    </div>
                </div>
            </form>
        </section>
    </body>
</html>
