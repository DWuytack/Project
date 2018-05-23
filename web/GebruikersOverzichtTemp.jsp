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
        <script src="js/utilities.js"></script>
        <script src="js/table.js"></script>
        <script src="js/gebruikersOverzicht.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/formulier.css">
        <link rel="stylesheet" href="css/gebruikersOverzicht.css">
        <title>Gebruikers overzicht</title>
        <style>
            input[name="idEdit"].active, input[name="idOpslaan"].active {
                -webkit-filter: grayscale(100%);
                filter: grayscale(100%);
                pointer-events: none;
            }
        </style>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        
        <section id="pagename">
            <h2> GebruikersOverzicht </h2>        
        </section>
        
        <script>
            console.log("data");
            console.log('${sessionScope.lijstGebruikers[0].voorNaam}');
            console.log('${sessionScope.lijstGebruikers[0].gebruikerID}');
            console.log("json");
            console.log('${sessionScope.json}');
        </script>
        
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
        </section>
                
        <section id="popup">
            <div id="gebruiker_verwijderen" style="width: 100%; display: flex;">
                <a id="bt-gebruiker_verwijderen" style="font-size: 20px; background-color: red; color: white; margin: auto;">Ok, placeholder</a>
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
        </section>          
    </body>
</html>