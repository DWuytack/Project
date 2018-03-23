<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
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
        <link rel="stylesheet" href="css/gebruikers.css">
        <title>Gebruikers overzicht</title>
        <!--
        <script>
            document.querySelector(".datatable tbody tr:first-child td:first-child input").removeAttribute("readonly");
            document.querySelector(".datatable tbody tr:first-child td:first-child input").setAttribute("readonly");
        </script>
        -->
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="GebruikersServlet">
                <br>
                <div class="table-container">
                    <div class="table-nav-header">
                        <img src='images/searchUser.png'>
                  
                        <!-- béta -->
                        <!-- <div style="position: relative;">
                            <i name="gebruiker zoeken" class="material-icons" style="font-size: 50px;">person</i>
                            <i name="gebruiker zoeken" class="material-icons" style="
                               font-size: 25px; position: absolute; bottom: 11px; right: 0; color: #ec6e24; transform: scale(-1, 1);
                               
                               ">search</i>
                        </div>
                         -->
                        <input type="text" name="zoekterm" size="25" maxlength="25" value=""> 
                        
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th width="15%" onclick="sortTable(0)"><a>Achternaam</a></th>
                                <th width="12%" onclick="sortTable(1)"><a>Voornaam</a></th>
                                <th width="10%" onclick="sortTable(2)"><a>Login</a></th>
                                <th width="10%" onclick="sortTable(3)"><a>Rol</a></th>
                                <th width="13%" onclick="sortTable(4)"><a>GeboorteDatum</a></th>
                                <th width="30%" onclick="sortTable(5)"><a>E-mail</a></th>
                                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th width="10%">Acties</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstGebruikers}" var="cursist">
                                <tr>
                                    <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                        <td> <input type="text" name="achternaam"  value="${cursist.achternaam}"> </td>
                                        <td> <input type="text" name="voornaam" value="${cursist.voorNaam}"> </td>
                                        <td> <input type="text" name="login"  value="${cursist.login}"> </td>
                                        <td> <input type="text" name="rol" value="${cursist.rol}"> </td>
                                        <td  align="center"> <input  type="text" name="geboorteDatum" value="<fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" />"> </td>
                                        <td> <input type="text" name="email"  value="${cursist.email}"/> </td>
                                    </c:if>

                                    <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                        <td> ${cursist.achternaam} </td>
                                        <td> ${cursist.voorNaam} </td>
                                        <td> ${cursist.login} </td>
                                        <td > ${cursist.rol} </td>
                                        <td align="center"> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" /> </td>
                                        <td> ${cursist.email} </td>

                                    </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td class="actie">
                                            <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                                <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src='images/pencil.png'>
                                                <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png'>
                                            </c:if>
                                            <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                                <input type="image"  name="idSave" value="${cursist.gebruikerID}" src='images/green.png'>
                                                <input type="image"  name="idCancel" value="${cursist.gebruikerID}" src='images/cancel.png'>
                                            </c:if>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                    <br>
                    <div class="table-nav-footer">
                        <!-- béta -->
                        <!--
                        <div class="table-nav-icons">
                            <a name="Eerste"><i class="material-icons">skip_previous</i></a>
                            <a name="Vorige"><i class="material-icons">fast_rewind</i></a>
                            <a name="Volgende"><i class="material-icons">fast_forward</i></a>
                            <a name="Laatste"><i class="material-icons">skip_next</i></a>
                        </div>
                        <a name="gebruiker toevoegen"><i class="material-icons">person_add</i></a>
                        -->
                        
                        <div>
                            <input type="image"  name="Eerste" value="Eerste" src='images/eerste.png'> 
                            <input type="image"  name="Vorige" value="Eerste" src='images/terug.png'>  
                            <input type="image"  name="Volgende" value="Volgende" src='images/volgende.png'> 
                            <input type="image"  name="Laatste" value="Laatste" src='images/laatste.png'>
                        </div>
                        <br>
                        <input type="image"  name="gebruiker toevoegen" value="gebruiker toevoegen" src='images/adduser.png'> 
                        
                    </div>
                </div>
            </form>
        </section>
    </body>
</html>
