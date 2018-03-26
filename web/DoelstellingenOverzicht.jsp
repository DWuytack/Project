<%-- 
    Document   : DoelstellingenOverzicht.jsp
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
        <script src="js/table.js"></script>
        <link rel="stylesheet" href="css/theme.css">

        <title>Doelstellingen</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="DoelstellingenServlet">
                <br>
                <div class="table-container">
                    <div class="table-nav-header">

                        <table>
                            <tr >
                                <td style="background-color:rgba(0, 0, 0, 0);"> <img src='images/searchUser.png' > </td>
                                <td> <input type="text" name="zoekterm" value="" size="15"> </td>
                                <td> </td>
                                <td> </td>
                                <td> </td>
                                <td> </td>
                                <td  align="right"> <center> Totaal aantal doelstellingen: <br><br> ${sessionScope.getoondeDoelstellingen}/${sessionScope.aantalRecords} </center></td>

                            </tr>
                        </table>
                    </div>
                    <table >
                        <thead>
                            <tr>
                                <th onclick="sortTable(0)"><a>Naam</a></th>
                                <th onclick="sortTable(1)"><a>Beschrijving</a></th>
                                <th onclick="sortTable(2)"><a>Kerndoelstelling</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>Acties</th>
                                    </c:if>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${lijstDoelstellingen}" var="doelstelling">
                                <tr>
                                    <c:if test="${doelstelling.doelstellingID == sessionScope.editID}" >
                                        <td> <input type="text" name="naam" size="25" maxlength="25" value="${doelstelling.naam}"> </td>
                                        <td> <input type="text" name="beschrijving" size="50" maxlength="50" value="${doelstelling.beschrijving}"> </td>
                                            <c:if test="${doelstelling.kerndoelstelling == 'true'}" >
                                            <td><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" checked> </td>
                                            </c:if>
                                            <c:if test="${doelstelling.kerndoelstelling == 'false'}" >
                                            <td><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" > </td>
                                            </c:if>
                                        </c:if>


                                    <c:if test="${doelstelling.doelstellingID != sessionScope.editID}" >
                                        <td> ${doelstelling.naam} </td>
                                        <td> ${doelstelling.beschrijving} </td>
                                        <c:if test="${doelstelling.kerndoelstelling == 'true'}" >
                                            <td><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" checked onclick="return false;"> </td>
                                            </c:if>
                                            <c:if test="${doelstelling.kerndoelstelling == 'false'}" >
                                            <td><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" onclick="return false;" > </td>
                                            </c:if>
                                        </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td>
                                            <c:if test="${doelstelling.doelstellingID != sessionScope.editID}" >
                                                <input type="image"  name="idEdit" value="${doelstelling.doelstellingID}" src='images/pencil.png'>
                                                <input type="image"  name="idDelete" value="${doelstelling.doelstellingID}" src='images/vuilbak.png'>
                                            </c:if>
                                            <c:if test="${doelstelling.doelstellingID == sessionScope.editID}" >
                                                <input type="image"  name="idSave" value="${doelstelling.doelstellingID}" src='images/green.png'>
                                                <input type="image"  name="idCancel" value="${doelstelling.doelstellingID}" src='images/cancel.png'>
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
                        <input type="image"  name="doelstelling toevoegen" value="doelstelling toevoegen" src='images/adduser.png'> 
                        <br><br>

                    </div>
                </div>
            </form>
        </section> 
    </body>
</html>


