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
        <link rel="stylesheet" href="css/formulier.css">
        <title>Type score</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp"%>
        <section>
            <h1 align="center" class="kleur"> Type scores </h1>
            <hr>
            <form action="ScoreServlet">
                <div class="table-container">
                    <table class="datatable">
                        <thead>
                            <tr>
                                <th align="center" onclick="sortTable(0)">naam</a</th>
                                <th align="center" onclick="sortTable(1)">beschrijving</a</th>
                                <th align="center" onclick="sortTable(2)">waarde</th>
                                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>Acties</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstBeoordelingssoorten}" var="typeScore">
                                <tr>
                                    <c:if test="${typeScore.beoordelingssoortID == sessionScope.editID}" >
                                        <td> <input type="text" name="naam"  value="${typeScore.naam}"> </td>
                                        <td> <input type="text" name="beschrijving" value="${typeScore.beschrijving}"> </td>
                                        <td> <input type="text" name="waarde"  value="${typeScore.waarde}"> </td>
                                    </c:if>
                                        
                                    <c:if test="${typeScore.beoordelingssoortID != sessionScope.editID}" >
                                        <td> ${typeScore.naam} </td>
                                        <td> ${typeScore.beschrijving} </td>
                                        <td> ${typeScore.waarde} </td>
                                    </c:if>

                            <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                <td class="actie">
                                    <c:if test="${typeScore.beoordelingssoortID != sessionScope.editID}" >
                                        <div class="actie-images">
                                            <span> <input type="image"  name="idEdit" value="${typeScore.beoordelingssoortID}" src='images/pencil.png'> </span>
                                            <span> <input type="image"  name="idDelete" value="${typeScore.beoordelingssoortID}" src='images/vuilbak.png'> </span>
                                        </div>
                                    </c:if>
                                    <c:if test="${typeScore.beoordelingssoortID == sessionScope.editID}" >
                                        <div class="actie-images">
                                            <span> <input type="image"  name="idSave" value="${typeScore.beoordelingssoortID}" src='images/green.png'> </span>
                                            <span> <input type="image"  name="idCancel" value="${typeScore.beoordelingssoortID}" src='images/cancel.png'> </span>
                                        </div>
                                    </c:if>
                                </td>
                            </c:if>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </section>
    </body>
</html>