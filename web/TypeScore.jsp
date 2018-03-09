<%-- 
    Document   : TypeScore
    Created on : 9-mrt-2018, 9:24:43
    Author     : Keanu
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
        <title>Score on Web</title>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    <body>
        <h1>Hier worden de type scores aangepast</h1>
         <form action="TypeScoreServlet">
        
             
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
        <% if (gebruiker.getRol().equals("admin")) { %>
          <input type="submit" value="Type Score Aanpassen" name="actie"/><br>
           <%} %>
    </body>
</html>
