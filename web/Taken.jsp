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
        <script src="js/jquery.js"></script>
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/formulier.css">
        <title>Taken overzicht</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>

        <section id="pagename">
            <h1> TakenOverzicht </h1>
        </section>

        <section>
            <form action="TakenServlet">
                <div class="table-container">

                    <div class="table-nav-header">
                        <div id="zoeken">
                            <a id="bt-zoeken">
                                <i class="material-icons">search</i>
                            </a>
                            <input type="text" name="zoekterm" value="" size="15">
                        </div>
                        <div>
                            <a id="bt-taak_toevoegen" name="taak toevoegen">
                                <i class="material-icons">person_add</i>
                            </a>
                            <!-- <input type="image" name="taak toevoegen" value="taak toevoegen" src='images/person_add.png'> -->
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div>

                    <table class="datatable">
                        <thead>
                            <tr>
                                <th onclick="sortTable(0)"><a>naam</a</th>
                                <th onclick="sortTable(1)"><a>beschrijving</a</th>

                                <c:if test="${sessionScope.currentSessionUser.rol == 'admin' || sessionScope.currentSessionUser.rol == 'leerkracht' }" >
                                    <th class="actie">Acties</th>
                                    </c:if>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstTaken}" var="taak">
                                <tr  <c:if test="${taak.taakID == sessionScope.editID}" >style="background-color: rgba(255,255,0,.5);"</c:if>>
                                    <c:if test="${taak.taakID == sessionScope.editID}" >
                                        <td> <input type="text" name="naam"  value="${taak.naam}"> </td>
                                        <td> <input type="text" name="beschrijving" value="${taak.beschrijving}"> </td>
                                        </c:if>
                                        <c:if test="${taak.taakID != sessionScope.editID}" >
                                        <td> ${taak.naam} </td>
                                        <td> ${taak.beschrijving} </td>
                                    </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td class="actie">
                                            <c:if test="${taak.taakID != sessionScope.editID}" >
                                                <div class="actie-images">
                                                    <span> <input type="image"  name="idEdit" value="${taak.taakID}" src='images/pencil.png'> </span>
                                                    <span> <input type="image"  name="idDelete" value="${taak.taakID}" src='images/vuilbak.png'> </span>
                                                </div>
                                            </c:if>
                                            <c:if test="${taak.taakID == sessionScope.editID}" >
                                                <div class="actie-images">
                                                    <span> <input type="image"  name="idSave" value="${taak.taakID}" src='images/green.png'> </span>
                                                    <span> <input type="image"  name="idCancel" value="${taak.taakID}" src='images/cancel.png'> </span>
                                                </div>
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

                <div id="taak_toevoegen">
                    <fieldset>
                        <legend>Taak Toevoegen: </legend><br>
                        <label>Taak naam: </label>
                        <br>
                        <input type="text" name="naam" size="16" maxlength="30">
                        <br><br>
                        <label>Beschrijving: </label>
                        <br>
                        <input type="text" name="beschrijving" size="16" maxlength="30">
                        <br><br>
                    </fieldset>
                    <input type="submit" name="actie" value="toevoegen">
                </div>

            </form>
        </section>
    </body>
</html>
