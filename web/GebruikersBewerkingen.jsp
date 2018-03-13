<%-- 
    Document   : GebruikerBewerkingen
    Created on : 13-mrt-2018, 22:01:45
    Author     : Gil-_
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/theme.css">
        <title>Gebruikers bewerkingen</title>
    </head>
    <body>
        <nav class="top-navigatie">
            <li id="menu">
                <a id="bt-menu" href="BeginMenu.jsp" title="Menu"></a>
            </li>
        </nav>
        <br><br>
        <form action="Servlet">
 
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
        
       
        <% if (gebruiker.getRol().equals("admin")) { %>
        
        <input type="submit" value="definitief verwijderen"  name="actie"/><br>
        
        
         <% } %>
        
        
        <% if (gebruiker.getRol().equals("secretariaat")) { %>
        
        <input type="submit" value="Gebruiker aanpassen" name="actie"/><br>
         <% } %>
        
         </form>
    </body>
</html>
