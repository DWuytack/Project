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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>

<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Score Menu</title>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    <body>
       <%@include file="Bovenbalk.jsp" %>
        <h2>Score On Web</h2><br>
        <form action="ScoreServlet">
            
         
    
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
