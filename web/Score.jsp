<%-- 
    Document   : Score
    Created on : 8-mrt-2018, 11:04:02
    Author     : Keanu Pestka
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
         <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Score Menu</title>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    <body>
        <nav class="top-navigatie">
            <li id="menu">
                <a id="bt-menu" href="BeginMenu.jsp" title="Menu"></a>
            </li>
        </nav>
        <br><br>
        <h2>Score On Web</h2><br>
        <form action="ScoreServlet">
            
         
            <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>

            <% if (gebruiker.getRol().equals("admin")) { %>
                <input type="submit" value="Score Overzicht" name="actie"/><br>

            <%} %>

            <% if (gebruiker.getRol().equals("leerkracht")) { %>
            <input type="submit" value="Score Aanmaken" name="actie"/><br>
            <input type="submit" value="Score Bewerken" name="actie"/><br>
            <input type="submit" value="Score Overzicht" name="actie"/><br>
            <%}%>

            <% if (gebruiker.getRol().equals("cursist")) { %>
                <input type="submit" value="Score Overzicht" name="actie"/><br>
            <% } %>

            <% if (gebruiker.getRol().equals("secretariaat")) { %>
                <input type="submit" value="Score Overzicht" name="actie"/><br>
            <%}%>
        </form>
        
            
        
        
        
    </body>
</html>
