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
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="MenuServlet">
                <h2>Opleidingen</h2>
                <br>
                <% ArrayList<Opleiding> lijstOpleidingen = (ArrayList<Opleiding>) (session.getAttribute("lijstOpleidingen"));%>

                <table>
                    <c:forEach items="${lijstOpleidingen}" var="opleiding">
                    <tr>
                        <td> ${opleiding.opleidingID} <td>
                        <td> ${opleiding.naam} <td>
                        <% if (gebruiker.getRol().equals("admin")) { %>
                        <td> <input type="image"  name="idEdit" value="${opleiding.opleidingID}" src='images/pencil.png'> </td>
                        <td> <input type="image"  name="idDelete" value="${opleiding.naam}" src='images/vuilbak.png'> </td>
                        <% } %>
                    </tr>
                    </c:forEach>
                </table>
            </form>   
        </section>    
    </body>
</html>

