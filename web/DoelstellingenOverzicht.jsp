<%-- 
    Document   : DoelstellingenOverzicht.jsp
    Created on : 8-mrt-2018, 11:05:14
    Author     : Ewout phlips
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>


<!DOCTYPE html>

<%-- Is de gebruiker admin: --%>
<c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >

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
                    <div class="table-container">
                        <div id="side-nav">
                            <div id="zoeken">
                                <a id="zoeken">
                                    <i class="material-icons">search</i>
                                </a>

                                <input type="text" name="zoekterm" value="" size="25">
                            </div>

                            <div>
                                <input type="image" name="gebruiker toevoegen" value="gebruiker toevoegen" src='images/person_add.png'> 
                                <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                                <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                                <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                                <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                            </div>
                        </div>

                        <table>
                            <thead>
                                <tr>
                                    <th onclick="sortTable(0)"><a>Naam</a></th>
                                    <th onclick="sortTable(1)"><a>Beschrijving</a></th>
                                    <th onclick="sortTable(2)"><a>Kerndoelstelling</a></th>
                                    <th>Acties</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach items="${lijstDoelstellingen}" var="doelstelling">
                                    <tr>
                                        <c:if test="${doelstelling.doelstellingID == sessionScope.editID}" >
                                             <td width="20%"> <input type="text" name="naam" value="${doelstelling.naam}"> </td>
                                             <td width="60%"> <input type="text" name="beschrijving" value="${doelstelling.beschrijving}"> </td>
                                             <c:if test="${doelstelling.kerndoelstelling == 'true'}" >
                                             <td width="10%"><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" checked> </td>
                                             </c:if>
                                             <c:if test="${doelstelling.kerndoelstelling == 'false'}" >
                                             <td width="10%"><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" > </td>
                                             </c:if>
                                        </c:if>
                                             
                                        <c:if test="${doelstelling.doelstellingID != sessionScope.editID}" >
                                            <td width="20%"> ${doelstelling.naam} </td> 
                                            <td width="60%"> ${doelstelling.beschrijving} </td>
                                            <c:if test="${doelstelling.kerndoelstelling == 'true'}" >
                                            <td width="10%"><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" checked onclick="return false;"> </td>
                                            </c:if>
                                            <c:if test="${doelstelling.kerndoelstelling == 'false'}" >
                                            <td width="10%"><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" onclick="return false;" > </td>
                                            </c:if>
                                        </c:if>
                                            
                                        <td width="10%">
                                            <c:if test="${doelstelling.doelstellingID != sessionScope.editID}" >
                                            <input type="image"  name="idEdit" value="${doelstelling.doelstellingID}" src='images/pencil.png'>
                                            <input type="image"  name="idDelete" value="${doelstelling.doelstellingID}" src='images/vuilbak.png'>
                                            </c:if>
                                            <c:if test="${doelstelling.doelstellingID == sessionScope.editID}" >
                                            <input type="image"  name="idSave" value="${doelstelling.doelstellingID}" src='images/green.png'>
                                            <input type="image"  name="idCancel" value="${doelstelling.doelstellingID}" src='images/cancel.png'>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                        <br><br>
                        
                        <div class="table-nav-footer">
                            <center>Totaal aantal doelstellingen: <br><br> ${sessionScope.getoondeDoelstellingen}/${sessionScope.aantalRecords}</center>
                        </div>
                    </div>
                </form>
            </section>
        </body>
    </html>

</c:if>

<%-- Is de gebruiker leerkracht of secretariaat: --%>
<c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht' || 'secretariaat'}" >

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
                    <div class="table-container">
                        <div id="side-nav">
                            <div id="zoeken">
                                <a id="zoeken">
                                    <i class="material-icons">search</i>
                                </a>

                                <input type="text" name="zoekterm" value="" size="25">
                            </div>

                            <div>
                                <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                                <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                                <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                                <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                            </div>
                        </div>

                        <table>
                            <thead>
                                <tr>
                                    <th onclick="sortTable(0)"><a>Naam</a></th>
                                    <th onclick="sortTable(1)"><a>Beschrijving</a></th>
                                    <th onclick="sortTable(2)"><a>Kerndoelstelling</a></th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach items="${lijstDoelstellingen}" var="doelstelling">
                                    <tr>                                      
                                        <td width="20%"> ${doelstelling.naam} </td> 
                                        <td width="70%"> ${doelstelling.beschrijving} </td>
                                        <c:if test="${doelstelling.kerndoelstelling == 'true'}" >
                                        <td width="10%"><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" checked onclick="return false;"> </td>
                                        </c:if>
                                        <c:if test="${doelstelling.kerndoelstelling == 'false'}" >
                                        <td width="10%"><input type="checkbox" name="kerndoelstelling" value="${doelstelling.kerndoelstelling}" onclick="return false;" > </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                        <br><br>
                        
                        <div class="table-nav-footer">
                            <center>Totaal aantal doelstellingen: <br><br> ${sessionScope.getoondeDoelstellingen}/${sessionScope.aantalRecords}</center>
                        </div>
                    </div>
                </form>
            </section>
        </body>
    </html>

</c:if>

<%-- Is de gebruiker cursist: --%>
<c:if test="${sessionScope.currentSessionUser.rol == 'cursist'}" >
    
    <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <script src="js/nav.js"></script>
            <link rel="stylesheet" href="css/theme.css">
            <title>Doelstellingen</title>
        </head>
        
        <body>
            <%@include file="Bovenbalk.jsp" %>
            
            <br><br><br>
            
            <h1 align="center"> U hebt geen toegang tot deze pagina!</h1>
        </body>
    </html>
    
</c:if>
