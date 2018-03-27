<%-- 
    Document   : Taken
    Created on : 8-mrt-2018, 20:17:05
    Author     : Dirk & Aaron
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
        <title>Taken</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="TakenServlet">
                <div class="table-container">

                    <div id="side-nav">
                        <div id="zoeken">
                            <a id="bt-zoeken">
                                <i class="material-icons">search</i>
                            </a>
                            <input type="text" name="zoekterm" value="" size="15">
                        </div>
                        <div>
                            <input type="image" name="taak toevoegen" value="taak toevoegen" src='images/person_add.png'> 
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <th align="center" width="12%" onclick="sortTable(0)">naam</a</th>
                                <th align="center" width="70%" onclick="sortTable(1)">beschrjiving</a</th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'|| sessionScope.currentSessionUser.rol == 'leerkracht' }" >
                                    <th width="17%">Acties</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstTaken}" var="taak">
                                <tr>
                                    <c:if test="${taak.taakID == sessionScope.editID}" >
                                        <td width="12%"> <input type="text" name="naam"  value="${taak.naam}"> </td>
                                        <td width="12%"> <input type="text" name="beschrijving" value="${taak.beschrijving}"> </td>
                                        </c:if>

                                    <c:if test="${taak.taakID != sessionScope.editID}" >
                                        <td> ${taak.naam} </td>
                                        <td> ${taak.beschrijving} </td>
                                    </c:if>

                                   <c:if test="${sessionScope.currentSessionUser.rol == 'admin'|| sessionScope.currentSessionUser.rol == 'leerkracht' }" >
                                        <td class="actie" >
                                            <c:if test="${taak.taakID != sessionScope.editID}" >
                                                <input type="image"  name="idEdit" value="${taak.taakID}" src='images/pencil.png'>
                                                <input type="image"  name="idDelete" value="${taak.taakID}" src='images/vuilbak.png'>
                                            </c:if>
                                            <c:if test="${taak.taakID == sessionScope.editID}" >
                                                <input type="image"  name="idSave" value="${taak.taakID}" src='images/green.png'>
                                                <input type="image"  name="idCancel" value="${taak.taakID}" src='images/cancel.png'>
                                            </c:if>
                                        </td>
                                    </c:if>
                                </tr> 
                            </c:forEach>
                        </tbody>
                    </table>
                    <br><br>
                    <div class="table-nav-footer">
                        <!-- WIP -->
                        <center>Totaal aantal taken: <br><br> ${sessionScope.getoondeTaken}/${sessionScope.aantalRecords}</center>
                    </div>
                </div>
            </form>
        </section>
    </body>
</html>
