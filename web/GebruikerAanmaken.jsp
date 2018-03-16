<%-- 
    Document   : Gebruiker
    Created on : 14-mrt-2018, 23:16:41
    Author     : Gil
--%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gebruiker toevoegen</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">  
    </head>
    
    <body>
        <form action="GebruikersServlet" method="post">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                <table>
                    <tr><td><label>Voornaam: </label></td><td><input type="text" name="voornaam" size="40" maxlength="40"/><td></tr><br>
                    <tr><td><label>Achternaam: </label></td><td><input type="text" name="achternaam" size="40" maxlength="40"/><td></tr><br>
                    <tr><td><label>Rol: </label></td><td><input type="date" name="rol" size="40" maxlength="40"/><td></tr><br>
                    <tr><td><label>GeboorteDatum: </label></td><td><input type="text" name="geboortedatum" size="40" maxlength="40"/><td></tr><br>
                    <tr><td> <label>Email: </label></td><td><input type="text" name="email" size="40" maxlength="40"/><td></tr><br>
                    <tr><td><label>Login: </label></td><td><input type="text" name="login" size="40" maxlength="40"/><td></tr><br><br><br>
                    <tr><td><label>Wachtwoord: </label></td><td><input type="text" name="wachtwoord" size="40" maxlength="40"/><td></tr>
                </table>
            </section>
        </form>
    </body>
</html>
