<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
    Author     : Gil
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="nl">
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
                        .table-container {
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                        }
                        #gebruikersOverzicht {
                            max-width: 1200px;
                            display: flex;
                            justify-content: center;
                            width: 100%;
                        }
                        #gebruikersOverzicht table {
                            width: 100%;
                            table-layout: auto;
                        }
                        #gebruikersOverzicht table td,  #somediv table th {
                            line-height: 1.15;
                        }
                        #gebruikersOverzicht table td:last-child {
                            padding: 0px;
                            vertical-align: baseline;
                        }
                        #gebruikersOverzicht table td > input, select {
                            display: flex;
                            width: 100%;
                        }
                        #gebruikersOverzicht table th > a {
                            display: flex;
                            width: 100%; /* Beter voor herberekening */
                        }
                        #gebruikersOverzicht table td > input[type="text"]:not([name="email"]) {
                            max-width: 100px;
                        }
                        #gebruikersOverzicht table td > input[type="date"], select {
                            max-width: 100px;
                        }         
                        #gebruikersOverzicht table th[title="Rol"] > a {
                            min-width: 100px;
                        }
                        #gebruikersOverzicht table th[title="Email"] > a {
                            min-width: 180px;
                        }
                        #gebruikersOverzicht table th[title="Acties"] > a {
                            width: 70px;
                            margin: auto;
                        }
                        
                        /*RESIZE SUPPORT */
                        @media screen and (max-width: 600px) {
                            .table-container table tbody tr {
                                background-color: #f8f6ff;
                            }
                            #gebruikersOverzicht table {
                                border: 0;
                                table-layout: fixed;
                            }

                            #gebruikersOverzicht table thead {
                                border: none;
                                clip: rect(0 0 0 0);
                                height: 1px;
                                margin: -1px;
                                overflow: hidden;
                                padding: 0;
                                position: absolute;
                                width: 1px;
                            }

                            #gebruikersOverzicht table tr {
                                border-bottom: 3px solid #ddd;
                                display: block;
                                margin-bottom: .625em;
                            }

                            #gebruikersOverzicht table td {
                                border-bottom: 1px solid #ddd;
                                display: block;
                                font-size: .8em;
                                text-align: right;
                                display: flex;
                                justify-content: flex-start;
                                align-items: center;
                            }

                            #gebruikersOverzicht table td::before {
                                content: attr(data-label);
                                font-weight: bold;
                                text-transform: uppercase;
                                display: flex;
                                min-width: 120px;
                                padding: 10px;
                            }

                            #gebruikersOverzicht table td:last-child {
                                border-bottom: 0;
                                padding-top: 5px;
                                padding-bottom: 5px;
                                padding-left: 12px;
                            }
                        }
                        
                    </style>
                    <div id="gebruikersOverzicht" role="wrapper"></div>
                    <!--
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
                        -->
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
                var params = 'page=' + pageCounter;

                var requestData = function(params) {
                    var xhttp = new XMLHttpRequest();
                    var data = "";
                    //params = 'page=1';

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
                        var style = '';
                        var style2 = '';
                        /*if (titel === "Rol")
                            style2 = 'style="min-width: 100px;"';
                        else if (titel === "Email")
                            style2 = 'style="min-width: 180px;"';
                        else if (titel === "Acties")
                            style2 = 'style="width: 70px; margin: auto;"';*/
                        
                        h += '<th scope="col" title="' + titel + '" '+ style +'><a '+ style2 +'>' + titel + '</a</th>';
                    }
                    var thead = '<thead><tr>' + h + '</tr></thead>';

                    data.forEach(function(e) {
                        console.log(e);
                        var options = function(e, keuzes) {
                            da = "";
                            for(option of keuzes) {
                                da += '<option value="' + option + '"' + selected(e, option) + '>' + option + '</option>';
                            }
                            return da;
                        };
                        var selected = function(rol, d){
                            if(rol === d)
                                return " selected";
                            else
                                return "";
                        };
                        temp += '<td data-label="' + titels[0] + '"><input type="text" name="achternaam" value="' + e.achternaam + '"></td>';
                        temp += '<td data-label="' + titels[1] + '"><input type="text" name="voornaam" value="' + e.voorNaam + '"></td>'; 
                        temp += '<td data-label="' + titels[2] + '"> <input type="text" name="login"  value="' + e.login + '"></td>';
                        temp += '<td data-label="' + titels[3] + '"> <select name="rol" value="' + e.rol + '">' + options(e.rol, keuzes) + '</select></td>';
                        var date = new Date();
                        var dateFormat = function(d) {
                            var t = d;
                            var list = {
                                jan : 1,
                                feb : 2,
                                mrt : 3,
                                apr : 4,
                                mei : 5,
                                jun : 6,
                                jul : 7,
                                aug : 8,
                                sep : 9,
                                okt : 10,
                                nov : 11,
                                dec : 12
                            };
                            var key = Object.keys(list);
                            t = t.replace(',','','g');
                            t = t.split(' ');
                            for(month of key) {
                                if(month === t[0]) t[0] = list[month];
                            }
                            if(t[0].length !== 2) t[0] = '0' + t[0];
                            if(t[1].length !== 2) t[1] = '0' + t[1];
                            t = t[2] + '-' + t[0] + '-' + t[1];
                            console.log(t);
                            return t;
                        };
                        console.log("Date = ");
                        console.log(date);
                        temp += '<td data-label="' + titels[4] + '"> <input type="date" name="geboorteDatum" value="' + dateFormat(e.geboorteDatum) + '"> </td>';
                        temp += '<td data-label="' + titels[5] + '"> <input type="text" name="email" value="' + e.email + '"> </td>';
                        temp += '<td data-label="' + titels[6] + '"><div class="actie-images">';
                        temp += '<span> <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src="images/pencil.png"> </span>';
                        temp += '<span> <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src="images/vuilbak.png"> </span>';
                        temp += '</div></td>';
                        htmlString += '<tr>' + temp + '</tr>';
                        temp = "";
                    });
                    htmlString = thead + '<tbody>' + htmlString + '</tbody>';
                    htmlString = '<table>' + htmlString + '</table>';
                    document.getElementById("gebruikersOverzicht").innerHTML = htmlString;
                };

                document.addEventListener("click", function(e){
                    if( e.target.id === "somebutton" ) {
                        pageCounter++;
                        params = 'page=' + pageCounter;
                        requestData(params);
                    }
                });
                requestData(params);
        </script>
        </section>
    </body>
</html>