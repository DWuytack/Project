<%-- 
    Document   : opleiding
    Created on : 8-mrt-2018, 11:44:51
    Author     : Davino
--%>


<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         import="model.Opleiding"
         import="java.util.ArrayList"
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
        <form action="MenuServlet">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                <h2>Opleidingen</h2><br>
                <% ArrayList<Opleiding> lijstOpleidingen = (ArrayList<Opleiding>) (session.getAttribute("lijstOpleidingen"));%>

                <table>
                    <c:forEach items="${lijstOpleidingen}" var="opleiding">
                        <tr>
                             <td> ${opleiding.opleidingID} <td>
                             <td> ${opleiding.naam} <td>



                        </tr>
                    </c:forEach>
                </table>
                <% if (gebruiker.getRol().equals("admin"))
                {%>
                    <input type="submit" value="Opleiding Toevoegen" name="actie"/><br>
                    <input type="submit" value="Opleiding Aanpassen" name="actie"/><br>
                    <input type="submit" value="Opleiding Verwijderen"name="actie"/><br>
                <%}
                %>
            </section>
     
        </form>    
        
    </body>
</html>

