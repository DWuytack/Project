<%-- 
    Document   : CursistenOverzicht
    Created on : 8-mrt-2018, 11:27:41
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
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>Cursisten overzicht</title>
    </head>
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="GebruikersServlet">
                <% ArrayList<Gebruiker> lijstCursisten = (ArrayList<Gebruiker>) (session.getAttribute("lijstCursisten"));%>

                <table>
                    <c:forEach items="${lijstCursisten}" var="gebruiker">
                    <tr>
                        <td><input type="checkbox" name="checkbox" value="gebruiker" ></td>
                        <td> ${gebruiker.achternaam} </td>
                        <td> ${gebruiker.voorNaam} </td>
                        <td> ${gebruiker.geboorteDatum} </td>
                        <td> ${gebruiker.email} </td>
                    </tr>
                    </c:forEach>
                 </table>

                 <% if (gebruiker.getRol().equals("admin")) { %>
                 <input type="submit" value="Cursist toevoegen" name="actie"><br>
                 <input type="submit" value="Cursist aanpassen" name="actie"><br>
                 <input type="submit" value="Cursist verwijderen"  name="actie"><br>
                 <% } %>

                 <% if (gebruiker.getRol().equals("secretariaat")) { %>
                 <input type="submit" value="Cursist toevoegen" name="actie"><br>
                 <input type="submit" value="Cursist aanpassen" name="actie"><br>
                 <% }%>
             </form>
        </section>
    </body>
</html>
