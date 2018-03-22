<%-- 
    Document   : DoelstellingenOverzicht
    Created on : 8-mrt-2018, 11:05:14
    Author     : Ewout phlips
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>Doelstellingen</title>
    </head>
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="DoelstellingenServlet">
                <table>
                    <tr>
                        <th>Naam</th>
                        <th>Beschrijving</th>
                        <th>Kenrdoelstelling</th>
                        <th>Acties</th>
                    </tr>
                    <c:forEach items="${lijstDoelstellingen}" var="doelstelling">
                        <tr>
                            <c:if test="${doelstelling.doelstellingID == sessionScope.editID}" >
                                <td> <input type="text" name="naam" size="25" maxlength="25" value="${doelstelling.naam}"> </td>
                                <td> <input type="text" name="beschrijving" size="50" maxlength="50" value="${doelstelling.beschrijving}"> </td>
                                <td> <input type="text" name="kerndoelstelling" size="25" maxlength="25" value="${doelstelling.kerndoelstelling}"> </td>
                                </c:if>

                            <c:if test="${doelstelling.doelstellingID != sessionScope.editID}" >
                                <td> ${doelstelling.naam} </td>
                                <td> ${doelstelling.beschrijving} </td>
                                <td> ${doelstelling.kerndoelstelling} </td>
                            </c:if>

                            <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                <td> <input type="image"  name="idEdit" value="${doelstelling.doelstellingID}" src='images/pencil.png'> </td>
                                <td> <input type="image"  name="idDelete" value="${doelstelling.doelstellingID}" src='images/vuilbak.png'> </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <br>

                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                    <input type="submit" value="Doelstelling toevoegen" name="actie"/><br>
                </c:if>

                <c:if test="${sessionScope.currentSessionUser.rol == 'secretariaat'}" >
                    <input type="submit" value="Doelstelling toevoegen" name="actie"/><br>
                </c:if>
            </form>
        </section> 
    </body>
</html>


