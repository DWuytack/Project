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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Score on Web</title>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    <body>
         <%@include file="Bovenbalk.jsp" %>
        
       
        <h1>Hier worden de type scores aangepast</h1>
         <form action="TypeScoreServlet">
        
             
        <% if (gebruiker.getRol().equals("admin")) { %>
          <input type="submit" value="Type Score Aanpassen" name="actie"/><br>
           <%} %>
    </body>
</html>
