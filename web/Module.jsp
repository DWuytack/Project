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
        <title>JSP Page</title>
    </head>
    <body>
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

