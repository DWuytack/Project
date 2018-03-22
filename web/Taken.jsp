<%-- 
    Document   : Taken
    Created on : 8-mrt-2018, 20:17:05
    Author     : Dirk & Aaron
--%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         import="model.Taak"
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
                <h2>Taken</h2><br>

                <% ArrayList<Taak> lijstTaken = (ArrayList<Taak>) (session.getAttribute("lijstTaken"));%>

                <table>
                    <tr>
                        <th>Select</th>
                        <th>naam</th>
                        <th>beschrijving</th>
                    </tr>
                    <c:forEach items="${lijstTaken}" var="taken">
                        <tr>
                            <td> <input type="radio" name="taak" value="${taak.taakID}" > </td>
                            <td> ${taken.naam} <td>
                            <td> ${taken.beschrijving} <td>
                                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                <td> <input type="image"  name="idEdit" value="${cursist.taakID}" src='images/pencil.png'  /> </td>
                                <td> <input type="image"  name="idDelete" value="${cursist.taakID}" src='images/vuilbak.png' /> </td>
                                </c:if>

                            <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                                <td> <input type="image"  name="idEdit" value="${cursist.taakID}" src='images/pencil.png'  /> </td>
                                <td> <input type="image"  name="idDelete" value="${cursist.taakID}" src='images/vuilbak.png' /> </td>
                                </c:if>

                            <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                <td> <input type="image"  name="idSave" value="${cursist.taakID}" src='images/green.gif'  /> </td>
                                <td> <input type="image"  name="idCancel" value="${cursist.taakID}" src='images/cancel.jpg'  /> </td>
                            </c:if>

                        </tr>
                    </c:forEach>
                </table>

                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                    <input type="submit" value="taak toevoegen" name="actie"/><br>
                </c:if>

                <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                    <input type="submit" value="taak toevoegen" name="actie"/><br>
                </c:if>

            </form>
        </section> 
    </body>
</html>
