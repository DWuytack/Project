<%-- 
    Document   : CursistenOverzicht
    Created on : 8-mrt-2018, 11:27:41
    Author     : CURSIST
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
        <link rel="stylesheet" href="css/theme.css">
        <title>Cursisten overzicht</title>
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

            <input type="submit" value="Cursist toevoegen" name="actie"/><br>
            <input type="submit" value="Cursist aanpassen" name="actie"/><br>
            <input type="submit" value="Cursist verwijderen"  name="actie"/><br>

            <% } %>

            <% if (gebruiker.getRol().equals("leerkracht")) { %>
            <input type="submit" value="Cursist toevoegen" name="actie"/><br>
            <input type="submit" value="Cursist aanpassen" name="actie"/><br>

            <% } %>

            <% if (gebruiker.getRol().equals("secretariaat")) { %>
            <input type="submit" value="Cursist toevoegen" name="actie"/><br>
            <input type="submit" value="Cursist aanpassen" name="actie"/><br>
            <% }%>

        </form>
    </body>
</html>
