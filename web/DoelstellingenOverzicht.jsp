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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/theme.css">
        <title>Doelstellingen</title>
    </head>
    <body>
        <form action="MenuServlet">
            <%@include file="Bovenbalk.jsp" %>
            <header></header>
            <section>
                <h2>Doelstellingen</h2>

                <% ArrayList<Doelstelling> lijstDoelstellingen = (ArrayList<Doelstelling>) (session.getAttribute("lijstDoelstellingen"));%>

                <table>
                    <tr>
                        <th>Select</th>
                        <th>Doelstelling</th>
                        <th>Beschrijving</th>
                        <th>Kerndoelstelling</th>
                    </tr> 

                    <c:forEach items="${lijstDoelstellingen}" var="doelstelling">
                        <tr>
                            <td> <input type="radio" name="doelstelling" value="${doelstelling.doelstellingID}" > </td>
                            <td> ${doelstelling.naam} </td>
                            <td> ${doelstelling.beschrijving} </td>
                            <td> ${doelstelling.kerndoelstelling} </td>
                        </tr>
                    </c:forEach>
                </table>

                <br>
                <input type="submit" value="Doelstelling Aanmaken" name="actie"/>
                <input type="submit" value="Doelstelling Aanpassen" name="actie"/>
                <input type="submit" value="Doelstelling Verwijderen" name="actie"/>
        </section>

        </form>
    </body>
</html>
