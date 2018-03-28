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
          <link rel="stylesheet" href="css/formulier.css">
        <title>Gebruikers overzicht</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
               <h1 align="center" class="kleur"> GebruikersOverzicht </h1>
                <hr>

            <form action="GebruikersServlet">
                <div class="table-container">
                    
                    <div id="side-nav">
                        <div id="zoeken">
                            <a id="bt-zoeken">
                                <i class="material-icons">search</i>
                            </a>
                            <input type="text" name="zoekterm" value="" size="15">
                        </div>
                        <div>
                            <a id="bt-gebruiker_toevoegen" name="gebruiker toevoegen">
                                <i class="material-icons">person_add</i>
                            </a>
                            <!-- <input type="image" name="gebruiker toevoegen" value="gebruiker toevoegen" src='images/person_add.png'> -->
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div>
                        
                    <table class="datatable">
                        <thead>
                            <tr>
                                <th width="15%" onclick="sortTable(0)"><a>Achternaam</a</th>
                                <th width="15%" onclick="sortTable(1)"><a>Voornaam</a</th>
                                <th width="15%" onclick="sortTable(2)"><a>Login</a></th>
                                <th width="15%" onclick="sortTable(3)" style="min-width: 100px;"><a>Rol</a></th>
                                <th width="15%" onclick="sortTable(4)"><a>GebtDatum</a></th>
                                <th width="25%" onclick="sortTable(5)"><a>E-mail</a></th>
                                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th class="actie">Acties</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstGebruikers}" var="cursist">
                                <tr  <c:if test="${cursist.gebruikerID == sessionScope.editID}" >style="background-color: rgba(255,255,0,.5);"</c:if>>
                                    <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                        <td> <input type="text" name="achternaam"  value="${cursist.achternaam}"> </td>
                                        <td> <input type="text" name="voornaam" value="${cursist.voorNaam}"> </td>
                                        <td> <input type="text" name="login"  value="${cursist.login}"> </td>
                                        <td> 
                                <select name="rol" value="${cursist.rol}">
                                    <option value="admin">admin</option>
                                    <option value="leerkracht">leerkracht</option>
                                    <option value="cursist">cursist</option>
                                    <option value="secretariaat">secretariaat</option></td>
                                </select>
                                <td> <input type="date" name="geboorteDatum" value="${cursist.geboorteDatum}"> </td>
                                <td> <input type="text" name="email"  value="${cursist.email}"/> </td>
                                </c:if>

                            <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                <td> ${cursist.achternaam} </td>
                                <td> ${cursist.voorNaam} </td>
                                <td> ${cursist.login} </td>
                                <td> ${cursist.rol} </td>
                                <td> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yyyy" /> </td>
                                <td> ${cursist.email} </td>

                            </c:if>

                            <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
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
                    <br><br>
                    <div class="table-nav-footer">
                        <!-- WIP -->
                        <center>Totaal aantal gebruikers: <b>${sessionScope.getoondeGebruikers}/${sessionScope.aantalRecords}</b></center>
                    </div>
                </div>
                <div id="gebruiker_toevoegen">
                    <fieldset>
                        <legend>Gebruiker Toevoegen: </legend><br>
                        <label>Voornaam: </label>
                        <br>
                        <input type="text" name="voornaam" size="16" maxlength="30">
                        <br><br>
                        <label>Achternaam: </label>
                        <br>
                        <input type="text" name="achternaam" size="16" maxlength="30">
                        <br><br>
                        <label>Rol: </label>
                        <br>
                        <input type="radio" name="rol" value="3"> <span>Cursist</span>
                        <br>
                        <input type="radio" name="rol" value="2"> <span>Leerkracht</span>
                        <br><br>
                        <label>GeboorteDatum: </label>
                        <br>
                        <input type="date" name="geboortedatum" size="16" maxlength="20">
                        <br><br>
                        <label>Email: </label>
                        <br>
                        <input type="text" name="email" size="16" maxlength="30">
                        <br><br>
                        <label>Login: </label>
                        <br>
                        <input type="text" name="login" size="16" maxlength="30">
                        <br><br>
                        <label>Wachtwoord: </label>
                        <br>
                        <input type="password" name="wachtwoord" size="16" maxlength="20">
                        <br>
                    </fieldset>
                    <input type="submit" name="actie" value="toevoegen">
                </div>
            </form>
        </section>
    </body>
</html>
