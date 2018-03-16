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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Score Menu</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    
    <body>
        <form action="ScoreServlet">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                <h2>Score On Web</h2><br>

                <% if (gebruiker.getRol().equals("admin")) { %>
                
                <select name="Schooljaar">

                    <option value="ipod">iPod</option>
                    <option value="radio">Radio</option>
                    <option value="computer">Computer</option>
                </select>

                //admin mag alle scores van alle modules zien

                    <% if (gebruiker.getRol().equals("cursist")) { %>
                        <input type="submit" value="Score Overzicht" name="actie"/><br>
                    <% } %>

                <% if (gebruiker.getRol().equals("leerkracht")) { %>

                //leerkracht mag scores zien van zijn eigen modules


                <%}%>

                <% if (gebruiker.getRol().equals("cursist")) { %>

                //cursist mag enkel zijn eigen scores zien

                <% } %>

                <% if (gebruiker.getRol().equals("secretariaat")) { %>

                //secretariaat mag alle scores van alle modules zien

                <%}%>
            </section>
        </form>
    </body>
</html>
