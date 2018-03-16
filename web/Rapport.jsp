<%-- 
    Document   : Rapport.jsp
    Created on : 8-mrt-2018, 20:19:56
    Author     : Dirk
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSP Page</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <header></header>
        <section>
            <h1>Hier komt het rapport van een individuele cursist!</h1>
        </section>
    </body>
</html>
