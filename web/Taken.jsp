<%-- 
    Document   : Taken
    Created on : 8-mrt-2018, 20:17:05
    Author     : Dirk & Aaron
--%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         import="model.Taak"
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
    
      
        <h2>Taken</h2><br>
        
         <form action="MenuServlet">

            <% ArrayList<Taak> lijstTaken = (ArrayList<Taak>) (session.getAttribute("lijstTaken"));%>

            <table>
                <tr>
                    <th>Select</th>
                    <th>naam</th>
                    <th>beschrijving</th>
                </tr>
                <c:forEach items="${lijstTaken}" var="taken">
                    <tr>
                        <td> <input type="radio" name="taak" value="${taak.taakID}" > </td>
                        <td> ${taken.naam} <td>
                        <td> ${taken.beschrijving} <td>
                       
                    </tr>
                </c:forEach>
            </table>

        
           <% if (gebruiker.getRol().equals("admin")) { %>
           <input type="submit" value="Taak Toevoegen" name="actie"/><br>
           <input type="submit" value="Taak Aanpassen" name="actie"/><br>
           <input type="submit" value="Taak Verwijderen"name="actie"/><br>
           <%} %>
           
           <% if (gebruiker.getRol().equals("leerkracht")) { %>
           <input type="submit" value="Taak Toevoegen" name="actie"/><br>
           <input type="submit" value="Taak Aanpassen" name="actie"/><br>
           <input type="submit" value="Taak Verwijderen"name="actie"/><br>
           <%} %>
           
     
            
        
        <%@include file="Bovenbalk.jsp" %>
        <header></header>
        <section>
            <h1>Hier komt het overzicht van de taken!</h1>
        </section>
    </body>
</html>
