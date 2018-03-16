<%-- 
    Document   : Profiel
    Created on : 15-mrt-2018, 19:01:24
    Author     : Dirk
--%>

<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         %>


<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mijn Profiel</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <header></header>
        <section>
            <h1>Mijn profiel</h1>
            <hr>
            <table>
                <tr><td><label>Voornaam: </label></td><td><input type="text" name="username" size="4" maxlength="40" value="<%= gebruiker.getVoorNaam()%>"/><td></tr><br>
                <tr><td><label>Achternaam: </label></td><td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getAchternaam()%>"/><td></tr><br>
                <tr><td><label>Rol: </label></td><td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getRol()%>"/><td></tr><br>
                <tr><td><label>GeboorteDatum: </label></td><td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getGeboorteDatum()%>"/><td></tr><br>
                <tr><td> <label>Email: </label></td><td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getEmail()%>"/><td></tr><br>
                <tr><td><label>Login: </label></td><td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getLogin()%>"/><td></tr><br><br><br>
            </table>
            <br>
            <form action="GebruikersServlet">
                <input type="submit" name="submit" value=" Wijzig paswoord " /><br><br>
                <input type="submit" name="submit" value=" Wijzig profiel " />	
            </form>
        </section>
    </body>
</html>
