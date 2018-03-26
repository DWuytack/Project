<%-- 
    Document   : Taken
    Created on : 8-mrt-2018, 20:17:05
    Author     : Dirk & Aaron
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %> 

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
                <br>
                <div class="table-container">
                    <div class="table-nav-header">

                        <table >
                            <tr >
                                <td style="background-color: rgba(0,0,0,0);" width="5%"> <img src='images/searchUser.png' > </td>

                                <td> <input type="text" name="zoekterm" value="" size="15"> </td>
                                <td> </td>
                                <td> </td>
                                <td> </td>
                                <td> </td>
                                <td  align="right"> <center> Totaal aantal taken: <br><br> ${sessionScope.getoondeTaken}/${sessionScope.aantalRecords} </center></td>

                            <tr>
                        </table>
                    </div>
                    <br>
                    <table >
                        <thead>
                            <tr>
                                <th align="center" width="12%" onclick="sortTable(0)">naam</a</th>
                                <th align="center" width="8%" onclick="sortTable(1)">beschrjiving</a</th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th width="17%">Acties</th>
                                    </c:if>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                                    <th width="17%">Acties</th>
                                    </c:if>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <c:forEach items="${lijstTaken}" var="taak">
                                <tr>
                                    <c:if test="${taak.taakID == sessionScope.editID}" >
                                        <td> <input type="text" size="25" name="naam"  value="${taak.naam}"> </td>
                                        <td> <input type="text" size="50" name="beschrijving" value="${taak.beschrijving}"> </td>
                                        </c:if>

                                    <c:if test="${taak.taakID != sessionScope.editID}" >
                                        <td> ${taak.naam} </td>
                                        <td> ${taak.beschrijving} </td>
                                    </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td>
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

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                                        <td>
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
                        <div>
                            <input type="image"  name="Eerste" value="Eerste" src='images/eerste.png'> 
                            <input type="image"  name="Vorige" value="Eerste" src='images/terug.png'>  
                            <input type="image"  name="Volgende" value="Volgende" src='images/volgende.png'> 
                            <input type="image"  name="Laatste" value="Laatste" src='images/laatste.png'>
                        </div>
                        <br>
                        <input type="image"  name="taak toevoegen" value="taak toevoegen" src='images/adduser.png'> 
                        <br><br>

                    </div>
                </div>
            </form>
        </section>
    </body>
</html>