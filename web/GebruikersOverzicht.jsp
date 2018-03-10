<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
    Author     : CURSIST
--%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/theme.css">
        <title>Gebruikers overzicht</title>
    </head>
    <body>
        <form action="GebruikersServlet">
            <% ArrayList<Gebruiker> lijstGebruikers = (ArrayList<Gebruiker>) (session.getAttribute("lijstGebruikers"));%>

            <table>
                <c:forEach items="${lijstGebruikers}" var="gebruiker">
                    <tr>
                        <td> ${gebruiker.achternaam} <td>
                        <td> ${gebruiker.voorNaam} <td>
                        <td> ${gebruiker.geboorteDatum} <td>
                        <td> ${gebruiker.email} <td>
                    </tr>
                </c:forEach>
            </table>
        
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
        
       
        <% if (gebruiker.getRol().equals("admin")) { %>
        
        <input type="button" value="Gebruiker toevoegen" name="toevoegen"/><br>
        <input type="button" value="Gebruiker aanpassen" name="aanpassen"/><br>
        <input type="button" value="Gebruiker verwijderen"  name="verwijderen"/><br>
        
         <% } %>
        
        
        <% if (gebruiker.getRol().equals("secretariaat")) { %>
        <input type="button" value="Gebruiker toevoegen" name="toevoegen"/><br>
        <input type="button" value="Gebruiker aanpassen" name="aanpassen"/><br>
         <% } %>
        
         </form>
    </body>
</html>
