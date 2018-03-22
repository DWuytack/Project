<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
    Author     : Gil
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>Gebruikers overzicht</title>
    </head>
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="GebruikersServlet">
                <table>
                    <c:forEach items="${lijstGebruikers}" var="cursist">
                        <tr>
                            <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                <td> <input type="text" name="achternaam" size="25" maxlength="25" value="${cursist.achternaam}"/> </td>
                                <td>  <input type="text" name="voornaam" size="25" maxlength="25" value="${cursist.voorNaam}"/> </td>
                                <td>  <input type="text" name="geboorteDatum" size="25" maxlength="25" value="<fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" />"/> </td>
                                <td>  <input type="text" name="email" size="25" maxlength="25" value="${cursist.email}"/> </td>
                            </c:if>

                            <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                <td> ${cursist.achternaam} </td>
                                <td> ${cursist.voorNaam} </td>
                                <td> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" /> </td>
                                <td> ${cursist.email} </td>
                            </c:if>

                            <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                <td> <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src='images/pencil.png'  /> </td>
                                <td> <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png' /> </td>
                                </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <br>

                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                    <input type="submit" value="Gebruiker toevoegen" name="actie"/><br>
                </c:if>

                <c:if test="${sessionScope.currentSessionUser.rol == 'secretariaat'}" >
                    <input type="submit" value="Gebruiker toevoegen" name="actie"/><br>
                </c:if>
            </form>
        </section>
    </body>
</html>
