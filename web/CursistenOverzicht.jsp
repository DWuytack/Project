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
        <script src="js/jquery.js"></script>
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/formulier.css">
        <link rel="stylesheet" href="css/CursistenOverzicht.css">
        <title>Gebruikers overzicht</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        
        <section id="pagename">
            <h2> Cursisten overzicht </h2>                      
        </section>
        
        <section>
            <form action="GebruikersServlet">
                <div class="table-container">                   
                    <div class="table-nav-header">
                        <div id="zoeken">
                            <a id="bt-zoeken">
                                <i class="material-icons">search</i>
                            </a>
                            <input type="text" name="zoekterm" value="${zoekterm}" onfocus="this.value=''" size="15">
                        </div>
                        <div>
                            <a id="bt-gebruiker_toevoegen" name="gebruiker toevoegen">
                                <i class="material-icons">person_add</i>
                            </a>
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div> 
                    <div role="wrapper" style="overflow-x: auto; pointer-events: all;">     
                        <table class="datatable">
                            <thead>
                                <tr>
                                    <th width="15%" onclick="sortTable(0)"><a>Achternaam</a</th>
                                    <th width="15%" onclick="sortTable(1)"><a>Voornaam</a</th>
                                    <th width="15%" onclick="sortTable(2)"><a>Login</a></th>
                                    <th width="15%" onclick="sortTable(4)"><a>GebtDatum</a></th>
                                    <th width="25%" onclick="sortTable(5)"><a>E-mail</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                                        <th class="actie">Acties</th>
                                    </c:if>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${lijstCursisten}" var="cursist">
                                    <tr  <c:if test="${cursist.gebruikerID == sessionScope.editID}" >style="background-color: rgba(255,255,0,.5);"</c:if>>
                                        <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                            <td> <input type="text" name="achternaam"  value="${cursist.achternaam}"> </td>
                                            <td> <input type="text" name="voornaam" value="${cursist.voorNaam}"> </td>
                                            <td> <input type="text" name="login"  value="${cursist.login}"> </td>
                                            <td> <input type="date" name="geboorteDatum" value="${cursist.geboorteDatum}"> </td>
                                            <td> <input type="text" name="email"  value="${cursist.email}"/> </td>
                                        </c:if>

                                <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                    <td> ${cursist.achternaam} </td>
                                    <td> ${cursist.voorNaam} </td>
                                    <td> ${cursist.login} </td>                                   
                                    <td> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yyyy" /> </td>
                                    <td> ${cursist.email} </td>
                                </c:if>

                                <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                                    <td class="actie">
                                        <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                            <div class="actie-images">
                                                <span> <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src='images/pencil.png'> </span>
                                                <span> <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png'> </span>
                                            </div>
                                        </c:if>
                                        <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                            <div class="actie-images">
                                                <span> <input type="image"  name="idSave" value="${cursist.gebruikerID}" src='images/green.png'> </span>
                                                <span> <input type="image"  name="idCancel" value="${cursist.gebruikerID}" src='images/cancel.png'> </span>
                                            </div>
                                        </c:if>
                                    </td>
                                </c:if>
                                </tr> 
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <br><br>
                    <div class="table-nav-footer">
                        <p>Totaal aantal cursisten: <span id="gebruikers">${sessionScope.getoondeCursisten}/${sessionScope.aantalRecords}</span></p>
                    </div>
                </div>
            </form>
        </section>
    </body>
</html>
