<%-- 
    Document   : DoelstellingenOverzicht
    Created on : 8-mrt-2018, 11:05:14
    Author     : Ewout phlips
--%>

<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         import="model.Doelstelling"
         import="java.util.ArrayList"
         %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>

    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/theme.css">
        <title>Doelstellingen</title>
    
    <body>
        <form action="MenuServlet">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                <fieldset>
                    <legend>Doelstellingen</legend>

                <% if (!gebruiker.getRol().equals("cursist")) { %>

                <% ArrayList<Doelstelling> lijstDoelstellingen = (ArrayList<Doelstelling>) (session.getAttribute("lijstDoelstellingen"));%>

                <table>
                    <tr>
                        <th>Naam</th>
                        <th>Beschrijving</th>
                        <th>Kerndoelstelling</th>
                    </tr> 

                    <c:forEach items="${lijstDoelstellingen}" var="doelstelling">
                        <tr>
                            <td> ${doelstelling.naam} </td>
                            <td> ${doelstelling.beschrijving} </td>
                            <td> ${doelstelling.kerndoelstelling} </td>
                            <% if (gebruiker.getRol().equals("admin")) { %>

                            <td> <input type="image"  name="idEdit" value="${doelstelling.doelstellingID}" formmethod="post" src='images/pencil.png'  /> </td>
                            <td> <input type="image"  name="idDelete" value="${doelstelling.doelstellingID}" formmethod="post" src='images/vuilbak.png' /> </td>

                            <% } %>
                        </tr>
                    </c:forEach>
                </table>

                <% } else { %>
                <h1>Cursisten hebben geen toegang tot deze informatie!</h1>
                <% }%>
                
                </fieldset>
            </section>

        </form>
    </body>

