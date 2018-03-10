<%-- 
    Document   : opleiding
    Created on : 8-mrt-2018, 11:44:51
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
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    <body>
        <h2>Opleidingen</h2><br>
        
         
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
         
          <% if (gebruiker.getRol().equals("admin")) { %>
          <input type="button" value="Opleiding Toevoegen" name="Toevoegen"/><br>
         <input type="button" value="Opleiding Aanpassen" name="Aanpassen"/><br>
         <input type="button" value="Opleiding Verwijderen"name="Verwijderen"/><br>
            <%} %>
            <% if (gebruiker.getRol().equals("leerkracht")) { %>
          <input type="button" value="Opleiding Toevoegen" name="Toevoegen"/><br>
         <input type="button" value="Opleiding Aanpassen" name="Aanpassen"/><br>
         <input type="button" value="Opleiding Verwijderen"name="Verwijderen"/><br>
            <%} %>
            
        
    </body>
</html>

