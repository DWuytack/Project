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
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Score On Web</h2><br>
        <form action="LoginServlet">

            <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>

            <% if (gebruiker.getRol().equals("admin")) { %>
                <input type="button" value="Overzicht cursisten" name="cursisten"/><br>
                <input type="button" value="Overzicht doelstellingen" name="doelstellingen"/><br>
                <input type="button" value="Overzicht taken"  name="taken"/><br>
                <input type="button" value="Overzicht opleidingen" name="opleidingen"/><br>
                <input type="button" value="Overzicht modules" name="modules"/><br>
                <input type="button" value="Overzicht scores" name="modules"/><br>
            <%} %>

            <% if (gebruiker.getRol().equals("leerkracht")) { %>
                <input type="button" value="Evaluatieformulieren" name="evaluatie"/><br> 
                <input type="button" value="Overzicht cursisten" name="cursisten"/><br>
                <input type="button" value="Overzicht doelstellingen" name="doelstellingen"/><br>
                <input type="button" value="Overzicht taken"  name="taken"/><br>
                <input type="button" value="Overzicht opleidingen" name="opleidingen"/><br>
                <input type="button" value="Overzicht modules" name="modules"/><br>
            <%}%>

            <% if (gebruiker.getRol().equals("cursist")) { %>
                <input type="button" value="Rapport" name="rapport"/><br>
            <% } %>

            <% if (gebruiker.getRol().equals("secretariaat")) { %>
                <input type="button" value="Overzicht scores" name="modules"/><br>
            <%}%>
        </form>
    </body>
</html>