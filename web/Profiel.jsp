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
        <title>Mijn Profiel</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <!--
        <style>
            section {
                display: flex;
                justify-content: space-around;
                flex-direction: column;
            }
            #testa {
                display: flex;
                width: 400px;
                flex-direction: column;
                margin: auto;
                justify-content: space-around;
            }
            #testa div {
                display: inherit;
                margin: 5px;
            }
            #testa label {
                display: inherit;
                width: 50%;

            }
            #testa input {
                display: inherit;
                width: 50%;
                /* justify-content: space-around; */
            }
            #testa input {
                margin: 0;
            }
        </style>
        -->
        <section>
            <h2>Mijn profiel</h2>
            <hr>
            
            <!--
            <div id="testa">
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
            -->

            <table>
                <tr>
                    <td><label>Voornaam: </label></td>
                    <td><input type="text" id="voornaam" value="${sessionScope.currentSessionUser.voorNaam}"/><td>
                </tr>
                <tr>
                    <td><label>Achternaam: </label></td>
                    <td><input type="text" id="achternaam" value="${sessionScope.currentSessionUser.achternaam}"/><td>
                </tr>
                <tr>
                    <td><label>Rol: </label></td>
                    <td><input type="text" id="rol" value="${sessionScope.currentSessionUser.rol}"/><td>
                </tr>
                <tr>
                    <td><label>GeboorteDatum: </label></td>
                    <td><input type="date" id="geboorteDatum" value="${sessionScope.currentSessionUser.geboorteDatum}"/><td>
                </tr>
                <tr>
                    <td> <label>Email: </label></td>
                    <td><input type="text" id="email" value="${sessionScope.currentSessionUser.email}"/><td>
                </tr>
                <tr>
                    <td><label>Login: </label></td>
                    <td><input type="text" id="login" value="${sessionScope.currentSessionUser.login}"><td>
                </tr>
            </table>
            <br>
            <form action="ProfielServlet">
                <input type="submit" name="submit" value="paswoordWijzigen"><br><br>
                <input type="submit" name="submit" value="profielWijzigen">	
            </form>
        </section>
    </body>
</html>
