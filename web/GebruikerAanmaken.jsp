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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gebruiker toevoegen</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">  
    </head>
    
    <body>
        <form action="GebruikersServlet" method="post">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                <ol><input type="text" name="voornaam"></ol>
                <ol><input type="text" name="achternaam" ></ol>
                <ol><input type="date" name="geboortedatum" ></ol>
                <ol><input type="text" name="email" ></ol> 
                <ol><input type="text" name="login" ></ol>
                <ol><input type="text" name="wachtwoord" ></ol>
                <input type="submit" name="submit">
            </section>
        </form>
    </body>
</html>
