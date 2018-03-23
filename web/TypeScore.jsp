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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>Score on Web</title>
    </head>
        
    <body>  
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="TypeScoreServlet">
                <h1>Hier worden de type scores aangepast</h1>

                <<c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                  <input type="submit" value="Type Score Aanpassen" name="actie"/><br>
                  </c:if>
                
                <%} %>
            </form>
        </section>
    </body>
</html>
