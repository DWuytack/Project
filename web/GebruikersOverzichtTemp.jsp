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
        <link rel="stylesheet" href="css/gebruikersOverzicht">
        <title>Gebruikers overzicht</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <!-- styling verplaatst naar theme.css "SUBNAV" -->
        <section id="pagename">
            <h2> GebruikersOverzicht </h2>
            <!-- <h1 align="center" class="kleur" style="display: inherit;"> GebruikersOverzicht </h1> -->
            
        </section>
        <!--<pre>${sessionScope.test}</pre>-->
        <section>
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
                <div id="gebruikersOverzicht" role="wrapper"></div>
                <br><br>
                <div class="table-nav-footer">
                    <p>Totaal aantal gebruikers: <span id="gebruikers">${sessionScope.getoondeGebruikers}/${sessionScope.aantalRecords}</span></p>
                </div>
            </div>    
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
                        temp += '<td data-label="' + titels[0] + '"><input type="text" name="achternaam" value="' + e.achternaam + '"><span style="display: none">' + e.achternaam + '</span></td>';
                        temp += '<td data-label="' + titels[1] + '"><input type="text" name="voornaam" value="' + e.voorNaam + '"><span style="display: none">' + e.voorNaam + '</span></td>'; 
                        temp += '<td data-label="' + titels[2] + '"> <input type="text" name="login"  value="' + e.login + '"><span style="display: none">' + e.login + '</span></td>';
                        temp += '<td data-label="' + titels[3] + '"> <select name="rol" value="' + e.rol + '">' + options(e.rol, keuzes) + '</select><span style="display: none">' + e.rol + '</span></td>';
                        var date = new Date();  
                        var dateFormat = function(d) { 
                            var t = new Date(d);
                            return {
                                date: t,
                                local: t.toLocaleDateString('sgn-NL', {day: '2-digit', month: '2-digit', year: 'numeric'}).replace(new RegExp('-', 'g'), '\/')
                            }
                        };
                        //Opmerking: Automatische aanvulling vind waarde niet!!!! "e.geboorteDatumValue"
                        temp += '<td data-label="' + titels[4] + '"> <input type="date" name="geboorteDatum" value="' + e.geboorteDatumValue + '"><span style="display: none">' + dateFormat(e.geboorteDatumValue).local + '</span></td>';
                        temp += '<td data-label="' + titels[5] + '"> <input type="text" name="email" value="' + e.email + '"><span style="display: none">' + e.email + '</span></td>';
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
        <section id="popup">
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
        </section>
    </body>
</html>