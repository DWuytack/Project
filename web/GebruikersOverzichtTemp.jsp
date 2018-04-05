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
        <title>Gebruikers overzicht</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <!-- styling verplaatst naar theme.css "SUBNAV" -->
        <section id="pagename">
            <h2> GebruikersOverzicht </h2>
            <!-- <h1 align="center" class="kleur" style="display: inherit;"> GebruikersOverzicht </h1> -->
            
        </section>
        
        <section>
            <form action="GebruikersServlet">
                <div class="table-container">
                    
                    <div class="table-nav-header">
                        <div id="zoeken">
                            <a id="bt-zoeken">
                                <i class="material-icons">search</i>
                            </a>
                            <input type="text" name="zoekterm" value="${zoekterm}" size="15">
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
                    <style>
                        [role="wrapper"] {
                            overflow-x: auto;
                            pointer-events: all;
                            
                        }
                        #somediv table td,  #somediv table th {
                            padding: 10px;
                            line-height: 1.15;
                        }
                        #somediv table td:last-child {
                            padding: 0px;
                            vertical-align: baseline;
                        }
                        #somediv input[type="date"] {
                            width: 120px;
                        }
                    </style>
                    <div id="somediv" role="wrapper"></div>
                    <div style="overflow-x: auto; pointer-events: all;">   
                        <table class="datatable">
                            <thead>
                                <tr>
                                    <th width="15%" onclick="sortTable(0)"><a>Achternaam</a</th>
                                    <th width="15%" onclick="sortTable(1)"><a>Voornaam</a</th>
                                    <th width="15%" onclick="sortTable(2)"><a>Login</a></th>
                                    <th width="15%" onclick="sortTable(3)"><a>Rol</a></th>
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
                    </div>
                    <br><br>
                    <div class="table-nav-footer">
                        <p>Totaal aantal gebruikers: <span id="gebruikers">${sessionScope.getoondeGebruikers}/${sessionScope.aantalRecords}</span></p>
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
            <script>
                var pageCounter = 1;

                var requestData = function(page) {
                    var xhttp = new XMLHttpRequest();
                    var data = "";
                    params = 'page=' + page;

                    xhttp.open("POST", "someservlet", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhttp.onreadystatechange = function() {
                        if (this.readyState === 4 && this.status === 200) {
                           // Typical action to be performed when the document is ready:
                           data = JSON.parse(xhttp.responseText);
                           renderHTML(data);
                        }
                    };
                    xhttp.send(params);
                };

                var renderHTML = function(data) {
                    htmlString = "";
                    var temp = "";
                    var keuzes = ["admin", "leerkracht", "cursist", "secretariaat"];
                    var titels = ["Achternaam", "Voornaam", "Login", "Rol", "GebtDatum", "Email", "Acties"];
                    var h = "";
                    
                    for(titel of titels) {
                        var style;
                        if (titel === "Email") style = 'style="min-width: 180px;"'; else style = '';
                        h += '<th '+ style +'><a>' + titel + '</a</th>';
                    }
                    var thead = '<thead><tr>' + h + '</tr></thead>';

                    data.forEach(function(e) {
                        var options = function(e, keuzes) {
                            da = "";
                            for(option of keuzes) {
                                da += '<option value="' + option + '"' + selected(e, option) + '>' + option + '</option>';
                            }
                            return da;
                        }
                        var selected = function(rol, d){
                            if(rol === d)
                                return " selected";
                            else
                                return "";
                        };
                        console.log(e.geboorteDatum);
                        temp += '<td><input type="text" name="achternaam" value="' + e.achternaam + '"></td>';
                        temp += '<td><input type="text" name="voornaam" value="' + e.voorNaam + '"></td>'; 
                        temp += '<td> <input type="text" name="login"  value="' + e.login + '"></td>';
                        temp += '<td> <select name="rol" value="' + e.rol + '">' + options(e.rol, keuzes) + '</select></td>';
                        var date = new Date(e.geboorteDatum).toJSON();
                        console.log("Date = ");
                        console.log(date);
                        temp += '<td> <input type="date" name="geboorteDatum" value="' + date + '"> </td>';
                        temp += '<td> <input type="text" name="email" value="' + e.email + '"> </td>';
                        temp += '<td><div class="actie-images">';
                        temp += '<span> <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src="images/pencil.png"> </span>';
                        temp += '<span> <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src="images/vuilbak.png"> </span>';
                        temp += '</div></td>';
                        htmlString += '<tr>' + temp + '</tr>';
                        temp = "";
                    });
                    htmlString = thead + '<tbody>' + htmlString + '</tbody>';
                    htmlString = '<table>' + htmlString + '</table>';
                    document.getElementById("somediv").innerHTML = htmlString;
                };

                document.addEventListener("click", function(e){
                    if( e.target.id === "somebutton" ) {
                        pageCounter++;
                        requestData(pageCounter);
                    }
                });
                requestData(pageCounter);
        </script>
        </section>
    </body>
</html>