<%-- 
    Document   : opleiding
    Created on : 8-mrt-2018, 11:44:51
    Author     : Davino
--%>

<%@page import="model.Opleiding"%>
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
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    <body>
        <nav class="top-navigatie">
            <li id="menu">
                <a id="bt-menu" href="BeginMenu.jsp" title="Menu"></a>
            </li>
        </nav>
        <br><br>
        <h2>Opleidingen</h2><br>
        
         <form action="MenuServlet">

            <% ArrayList<Opleiding> lijstOpleidingen = (ArrayList<Opleiding>) (session.getAttribute("lijstOpleidingen"));%>

            <table>
                <c:forEach items="${lijstModules}" var="opleiding">
                    <tr>
                        <td> ${opleiding.opleidingID} <td>
                        <td> ${opleiding.naam} <td>
                       
                       
                    </tr>
                </c:forEach>
            </table>

        
         
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
         
          <% if (gebruiker.getRol().equals("admin")) { %>
          <input type="button" value="Opleiding Toevoegen" name="Toevoegen"/><br>
         <input type="button" value="Opleiding Aanpassen" name="Aanpassen"/><br>
         <input type="button" value="Opleiding Verwijderen"name="Verwijderen"/><br>
            <%} %>
     
            
        
    </body>
</html>

