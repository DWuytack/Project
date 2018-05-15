<%-- 
    Document   : typeScore
    Created on : 26-mrt-2018, 22:15:41
    Author     : Gil en Keanu
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
        <section id="pagename">
            <h2> Type scores </h2> 
        </section>
        
        <section>
            <form action="ScoreServlet">
                <div class="table-container">                   
                    <div class="table-nav-header" style="min-height: 20px;">                       
                    </div>                   
                    <table class="datatable">
                        <thead>
                            <tr>
                                <th align="center" onclick="sortTable(0)">naam</a</th>
                                <th align="center" onclick="sortTable(1)">beschrijving</a</th>
                                <th align="center" onclick="sortTable(2)">waarde</th>
                                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>acties</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${beoordelingssoorten}" var="beoordelingssoort">
                                <tr <c:if test="${beoordelingssoort.beoordelingssoortID == sessionScope.editID}" >style="background-color: rgba(255,255,0,.5);"</c:if>>
                                    <c:if test="${beoordelingssoort.beoordelingssoortID == sessionScope.editID}" >
                                        <td> <input type="text" name="naam"  value="${beoordelingssoort.naam}"> </td>
                                        <td> <input type="text" name="beschrijving" value="${beoordelingssoort.beschrijving}"> </td>
                                        <td> <input type="text" name="waarde"  value="${beoordelingssoort.waarde}"> </td>
                                    </c:if>
                                        
                                    <c:if test="${beoordelingssoort.beoordelingssoortID != sessionScope.editID}" >
                                        <td> ${beoordelingssoort.naam} </td>
                                        <td> ${beoordelingssoort.beschrijving} </td>
                                        <td> ${beoordelingssoort.waarde} </td>
                                    </c:if>

                            <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                <td class="actie">
                                    <c:if test="${beoordelingssoort.beoordelingssoortID != sessionScope.editID}" >
                                        <div class="actie-images">
                                            <span> <input type="image"  name="idEdit" value="${beoordelingssoort.beoordelingssoortID}" src='images/pencil.png'> </span>
                                            <span> <input type="image"  name="idDelete" value="${beoordelingssoort.beoordelingssoortID}" src='images/vuilbak.png'> </span>
                                        </div>
                                    </c:if>
                                    <c:if test="${beoordelingssoort.beoordelingssoortID == sessionScope.editID}" >
                                        <div class="actie-images">
                                            <span> <input type="image"  name="idSave" value="${beoordelingssoort.beoordelingssoortID}" src='images/green.png'> </span>
                                            <span> <input type="image"  name="idCancel" value="${beoordelingssoort.beoordelingssoortID}" src='images/cancel.png'> </span>
                                        </div>
                                    </c:if>
                                </td>
                            </c:if>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="typeScore_toevoegen">
                    <fieldset>
                        <legend>typeScore Toevoegen: </legend><br>
                        <label>Naam: </label>
                        <br>
                        <input type="text" name="naam" size="16" maxlength="30">
                        <br><br>
                        <label>Beschrijving: </label>
                        <br>
                        <input type="text" name="beschrijving" size="16" maxlength="30">
                        <br><br>
                        <label>Waarde: </label>                       
                        <br>
                    </fieldset>
                    <input type="submit" name="actie" value="toevoegen">
                </div>
                </div>
            </form>
        </section>
    </body>
</html>