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
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>Mijn Profiel</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <h1>Mijn profiel</h1>
            <hr>
            <table>
                <tr>
                    <td><label>Voornaam: </label></td>
                    <td><input type="text" name="username" size="4" maxlength="40" value="<%= gebruiker.getVoorNaam()%>"/><td>
                </tr>
                <tr>
                    <td><label>Achternaam: </label></td>
                    <td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getAchternaam()%>"/><td>
                </tr>
                <tr>
                    <td><label>Rol: </label></td>
                    <td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getRol()%>"/><td>
                </tr>
                <tr>
                    <td><label>GeboorteDatum: </label></td>
                    <td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getGeboorteDatum()%>"/><td>
                </tr>
                <tr>
                    <td> <label>Email: </label></td>
                    <td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getEmail()%>"/><td>
                </tr>
                <tr>
                    <td><label>Login: </label></td>
                    <td><input type="text" name="username" size="40" maxlength="40" value="<%= gebruiker.getLogin()%>"><td>
                </tr>
            </table>
            <br>
            <form action="GebruikersServlet">
                <input type="submit" name="submit" value=" Wijzig paswoord "><br><br>
                <input type="submit" name="submit" value=" Wijzig profiel ">	
            </form>
        </section>
    </body>
</html>
