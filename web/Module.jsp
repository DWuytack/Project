<%-- 
    Document   : module
    Created on : 8-mrt-2018, 11:37:32
    Author     : Davino
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
        <link rel="stylesheet" href="css/theme.css">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="top-navigatie">
            <li id="menu">
                <a id="bt-menu" title="Menu"></a>
            </li>
        </nav>
        <br><br>
        <h2>Modules</h2><br>
         
         
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
         
          <% if (gebruiker.getRol().equals("admin")) { %>
          <input type="button" value="Module Toevoegen" name="Toevoegen"/><br>
         <input type="button" value="Module Aanpassen" name="Aanpassen"/><br>
         <input type="button" value="Module Verwijderen"name="Verwijderen"/><br>
            <%} %>
            <% if (gebruiker.getRol().equals("leerkracht")) { %>
          <input type="button" value="Module Toevoegen" name="Toevoegen"/><br>
         <input type="button" value="Module Aanpassen" name="Aanpassen"/><br>
         <input type="button" value="Module Verwijderen"name="Verwijderen"/><br>
            <%} %>
            
        
    </body>
</html>

