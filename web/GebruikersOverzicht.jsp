<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
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
        <title>Gebruikers overzicht</title>
    </head>
    <body>
         <%@include file="Bovenbalk.jsp" %>
       <form action="GebruikersServlet">
            <% ArrayList<Gebruiker> lijstGebruikers = (ArrayList<Gebruiker>) (session.getAttribute("lijstGebruikers"));%>
            
            <table>
                <c:forEach items="${lijstGebruikers}" var="gebruiker">
                    <tr>
                        <td><input type="checkbox" name="checkbox" value="gebruiker" ></td>
                        <td> ${gebruiker.achternaam} <td>
                        <td> ${gebruiker.voorNaam} <td>
                        <td> ${gebruiker.geboorteDatum} <td>
                        <td> ${gebruiker.email} <td>
                    </tr>
                </c:forEach>
            </table>
        
        
       
        <% if (gebruiker.getRol().equals("admin")) { %>
        
        <input type="submit" value="Gebruiker toevoegen" name="actie"/><br>
        <input type="submit" value="Gebruiker aanpassen" name="actie"/><br>
        <input type="submit" value="Gebruiker verwijderen"  name="actie"/><br>
        
         <% } %>
        
        
        <% if (gebruiker.getRol().equals("secretariaat")) { %>
        <input type="submit" value="Gebruiker toevoegen" name="actie"/><br>
        <input type="submit" value="Gebruiker aanpassen" name="actie"/><br>
         <% } %>
        
         </form>
    </body>
</html>
