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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gebruikers overzicht</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    <body>
        <form action="GebruikersServlet">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                <% ArrayList<Gebruiker> lijstGebruikers = (ArrayList<Gebruiker>) (session.getAttribute("lijstGebruikers"));%>
                <% String id = (String) session.getAttribute("gebruikerID");%>

                <table>
                    <c:forEach items="${lijstGebruikers}" var="cursist">
                        <tr>

                            <td> ${cursist.achternaam} </td>
                            <td> ${cursist.voorNaam} </td>
                            <td> ${cursist.geboorteDatum} </td>
                            <td> ${cursist.email} </td>

                            <% if (gebruiker.getRol().equals("admin")) { %>

                            <td> <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src='images/pencil.png'  /> </td>
                            <td> <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png' /> </td>

                            <% } %>
                        </tr>
                    </c:forEach>
                </table>
                <br>

                <% if (gebruiker.getRol().equals("admin") || gebruiker.getRol().equals("secretariaat")) { %>

                <input type="submit" value="Gebruiker toevoegen" name="actie"/><br>

                <% }%>

            </section>
        </form>
    </body>
</html>
