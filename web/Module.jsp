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
        <title>JSP Page</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    
    <body>
        <form action="MenuServlet">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                <h2>Modules</h2><br>
                    <% ArrayList<Module> lijstModules = (ArrayList<Module>) (session.getAttribute("lijstModules"));%>

                    <table>
                        <c:forEach items="${lijstModules}" var="module">
                            <tr>
                                <td> ${module.moduleID} <td>

                                <td> ${module.naam} <td>

                            </tr>
                        </c:forEach>
                    </table>

                    <% if (gebruiker.getRol().equals("admin"))
                        { %>
                        <input type="submit" value="Module Toevoegen" name="actie"/><br>
                        <input type="submit" value="Module Aanpassen" name="actie"/><br>
                        <input type="submit" value="Module Verwijderen"name="actie"/><br>
                        <%}
                    %>
                    
            </section>
        </form>
    </body>
</html>

