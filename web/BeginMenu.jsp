<%-- 
    Document   : BeginMenu
    Created on : 8-mrt-2018, 9:34:57
    Author     : CURSIST
--%>
<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Startmenu</title>
        <link rel="stylesheet" href="css/menu2.css">
    </head>
    <body>
        <h2>Score On Web</h2><br>
        
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>

        <h2><p> Welkom, <%= gebruiker.getVoorNaam() %> ! </p> </h2>
        <nav>    
            <form action="MenuServlet" id="main-menu">

                <% if (gebruiker.getRol().equals("admin")) { %>
                    <input type="submit" value="Overzicht gebruikers" name="actie"/><br>
                    <input type="submit" value="Overzicht doelstellingen" name="actie"/><br>
                    <input type="submit" value="Overzicht taken"  name="actie"/><br>
                    <input type="submit" value="Overzicht opleidingen" name="actie"/><br>
                    <input type="submit" value="Overzicht modules" name="actie"/><br>
                    <input type="submit" value="Overzicht scores" name="actie"/><br>
                    <input type="submit" value="Type score aanpassen" name="actie"/><br>
                <%} %>

                <% if (gebruiker.getRol().equals("leerkracht")) { %>
                    <input type="submit" value="Evaluatieformulieren" name="actie"/><br> 
                    <input type="submit" value="Overzicht cursisten" name="actie"/><br>
                    <input type="submit" value="Overzicht doelstellingen" name="actie"/><br>
                    <input type="submit" value="Overzicht taken"  name="actie"/><br>
                    <input type="submit" value="Overzicht opleidingen" name="actie"/><br>
                    <input type="submit" value="Overzicht modules" name="actie"/><br>
                <%}%>

                <% if (gebruiker.getRol().equals("cursist")) { %>
                    <input type="submit" value="Rapport" name="actie"/><br>
                <% } %>

                <% if (gebruiker.getRol().equals("secretariaat")) { %>
                    <input type="submit" value="Overzicht scores" name="actie"/><br>
                <%}%>
            </form>
        </nav>
    </body>
</html>