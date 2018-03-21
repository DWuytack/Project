<%-- 
    Document   : module
    Created on : 8-mrt-2018, 11:37:32
    Author     : Davino
--%>

<%@page import="model.Module"%>
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
        <title>JSP Page</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="MenuServlet">
                <h2>Modules</h2><br>
                <% ArrayList<Module> lijstModules = (ArrayList<Module>) (session.getAttribute("lijstModules"));%>

                <table>
                    <c:forEach items="${lijstModules}" var="module">
                    <tr>
                        <td> ${module.moduleID} </td>
                        <td> ${module.naam} </td>
                        <% if (gebruiker.getRol().equals("admin")) { %>
                        <td> <input type="image"  name="idEdit" value="${module.moduleID}" src='images/pencil.png'> </td>
                        <td> <input type="image"  name="idDelete" value="${module.naam}" src='images/vuilbak.png'> </td>
                        <% } %>
                    </tr>
                    </c:forEach>
                </table>
            </form>
        </section>
    </body>
</html>

