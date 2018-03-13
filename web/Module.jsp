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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/theme.css">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="top-navigatie">
            <li id="menu">
                <a id="bt-menu" href="BeginMenu.jsp" title="Menu"></a>
            </li>
        </nav>
        <br><br>
        <h2>Modules</h2><br>
          <form action="MenuServlet">

            <% ArrayList<Module> lijstModules = (ArrayList<Module>) (session.getAttribute("lijstModules"));%>

            <table>
                <c:forEach items="${lijstModules}" var="module">
                    <tr>
                        <td> ${module.moduleID} <td>
                        <td> ${module.opleidingID} <td>
                        <td> ${module.naam} <td>
                       
                    </tr>
                </c:forEach>
            </table>
         
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
         
          <% if (gebruiker.getRol().equals("admin")) { %>
          <input type="button" value="Module Toevoegen" name="Toevoegen"/><br>
         <input type="button" value="Module Aanpassen" name="Aanpassen"/><br>
         <input type="button" value="Module Verwijderen"name="Verwijderen"/><br>
            <%} %>
      
            
        
    </body>
</html>

