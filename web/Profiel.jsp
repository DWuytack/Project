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
        <section>
            <h2>Mijn profiel</h2>
            <hr>
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
