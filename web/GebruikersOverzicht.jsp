<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
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
        <title>Gebruikers overzicht</title>
    </head>
    <body>
        
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
        
       
        <% if (gebruiker.getRol().equals("admin")) { %>
        
        <input type="button" value="Cursist toevoegen" name="toevoegen"/><br>
        <input type="button" value="Cursist aanpassen" name="aanpassen"/><br>
        <input type="button" value="Cursist verwijderen"  name="verwijderen"/><br>
        
        
        <% if (gebruiker.getRol().equals("secretariaat")) { %>
        <input type="button" value="Cursist toevoegen" name="toevoegen"/><br>
        <input type="button" value="Cursist aanpassen" name="aanpassen"/><br>
    </body>
</html>
