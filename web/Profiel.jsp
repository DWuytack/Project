<%-- 
    Document   : Profiel
    Created on : 15-mrt-2018, 19:01:24
    Author     : Dirk
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/profielOverzicht.css">
        <title>Mijn Profiel</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        
        <section id="profielOverzicht">
            
            <div role="header">
                <h2>Mijn profiel</h2>
                <hr>
            </div>
            
            <div role="content" id="testa">
                <div>
                    <label>Voornaam: </label>
                    <input type="text" id="voornaam" value="${sessionScope.currentSessionUser.voorNaam}">
                </div>
                <div>
                    <label>Achternaam: </label>
                    <input type="text" id="achternaam" value="${sessionScope.currentSessionUser.achternaam}">
                </div>
                <div>
                    <label>Rol: </label>
                    <input type="text" id="rol" value="${sessionScope.currentSessionUser.rol}">
                </div>
                <div>
                    <label>GeboorteDatum: </label>
                    <input type="date" id="geboorteDatum" value="${sessionScope.currentSessionUser.geboorteDatum}">
                </div>
                <div>
                    <label>Email: </label>
                    <input type="text" id="email" value="${sessionScope.currentSessionUser.email}">
                </div>
                <div>
                    <label>Login: </label>
                    <input type="text" id="login" value="${sessionScope.currentSessionUser.login}">
                </div>
            </div>
                
            <div role="footer">    
                <form action="ProfielServlet">
                    <input type="submit" name="submit" value="paswoordWijzigen">
                    <input type="submit" name="submit" value="profielWijzigen">	
                </form>
            </div>
                
        </section>
    </body>
</html>
