<%-- 
    Document   : typeScore
    Created on : 26-mrt-2018, 22:16:41
    Author     : Gil
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>Type score</title>

        -->
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="ScoreServlet">
                <br>
                <div class="table-container">
                    <br>
                    <table>
                        <thead>
                            <tr>
                                <th align="center" width="12%" onclick="sortTable(0)">naam</a</th>
                                <th align="center" width="12%" onclick="sortTable(1)">beschrijving</a</th>
                                <th align="center" width="12%" onclick="sortTable(2)">waarde</th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th width="15%">Acties</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${beoordelingssoorten}" var="typeScore">
                                <tr>
                                    <c:if test="${typeScore.beoordelingssoortID == sessionScope.editID}" >
                                        <td width="12%"> <input type="text" name="naam"  value="${typeScore.naam}"> </td>
                                        <td width="12%"> <input type="text" name="beschrijving" value="${typeScore.beschrijving}"> </td>
                                        <td width="12%"> <input type="text" name="waarde"  value="${typeScore.waarde}"> </td>
                                    </c:if>

                            <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                <td class="actie" width="10%">
                                    <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                        <input type="image"  name="idEdit" value="${score.beoordelinssoortID}" src='images/pencil.png'>
                                        
                                    </c:if>
                                    <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                        <input type="image"  name="idSave" value="${score.beoordelinssoortID}" src='images/green.png'>
                                        <input type="image"  name="idCancel" value="${score.beoordelinssoortID}" src='images/cancel.png'>
                                    </c:if>
                                </td>
                            </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
            </form>
        </section>
    </body>
</html>
