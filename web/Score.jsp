<%-- 
    Document   : Score
    Created on : 8-mrt-2018, 11:04:02
    Author     : Keanu Pestka
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
        <title>Score Menu</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    
    <body>
        <form action="ScoreServlet">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                
                <select name="Schooljaar">
                    <option value="schooljaar">schooljaar</option>
                    <option value="semester">semester</option>
                    <option value="module">module</option>
                </select>
                
            </section>
        </form>
    </body>
</html>
