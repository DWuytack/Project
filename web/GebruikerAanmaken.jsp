<%-- 
    Document   : Gebruiker
    Created on : 14-mrt-2018, 23:16:41
    Author     : Gil
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
        <title>Gebruiker toevoegen</title>
    </head>
    <body>
        
         <%@include file="Bovenbalk.jsp" %>
         
        <form action="GebruikersServlet" method="post">
 
                <ol><input type="text" name="voornaam"></ol>
                <ol><input type="text" name="achternaam" ></ol>
                <ol><input type="date" name="geboortedatum" ></ol>
                <ol><input type="text" name="email" ></ol> 
                <ol><input type="text" name="login" ></ol>
                <ol><input type="text" name="wachtwoord" ></ol>
                <input type="submit" name="submit">
            

            
        </form>
    </body>
</html>
