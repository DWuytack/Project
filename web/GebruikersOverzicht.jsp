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
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="GebruikersServlet">
                <div class="table-container">

                    <table>
                        <tr style="background-color:rgba(0, 0, 0, 0);"> 
                            <td  style="text-align:center;">   
                                <input type="image"  name="gebruiker zoeken" value="gebruiker zoeken" src='images/searchUser.png'> 
                                <input  type="text" style="height:30px;font-size:14pt;" name="zoekterm" size="25" maxlength="25" value=""> 
                            </td> 
                        </tr>
                    </table>


                    <table class="datatable">
                        <thead>
                            <tr>
                                <th onclick="sortTable(0)"><a>Achternaam</a></th>
                                <th onclick="sortTable(1)"><a>Voornaam</a></th>
                                <th onclick="sortTable(2)"><a>Login</a></th>
                                <th onclick="sortTable(3)"><a>Rol</a></th>
                                <th onclick="sortTable(4)"><a>GeboorteDatum</a></th>
                                <th onclick="sortTable(5)"><a>E-mail</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>Actions</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstGebruikers}" var="cursist">
                                <tr>
                                    <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                        <td> <input type="text" name="achternaam" size="15" maxlength="25" value="${cursist.achternaam}"> </td>
                                        <td> <input type="text" name="voornaam" size="15" maxlength="25" value="${cursist.voorNaam}"> </td>
                                        <td> <input type="text" name="login" size="15" maxlength="25" value="${cursist.login}"> </td>
                                        <td> <input type="text" name="rol" size="15" maxlength="25" value="${cursist.rol}"> </td>
                                        <td> <input type="text" name="geboorteDatum" size="15" maxlength="25" value="<fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" />"> </td>
                                        <td> <input type="text" name="email" size="15" maxlength="25" value="${cursist.email}"/> </td>
                                        </c:if>

                                    <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                        <td> ${cursist.achternaam} </td>
                                        <td> ${cursist.voorNaam} </td>
                                        <td> ${cursist.login} </td>
                                        <td> ${cursist.rol} </td>
                                        <td> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" /> </td>
                                        <td> ${cursist.email} </td>

                                    </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td>
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
                    <table>
                        <tr style="background-color:rgba(0, 0, 0, 0);"> 
                            <td style="text-align:center;">   
                                <input type="image"  name="Eerste 10" value="Eerste 10" src='images/eerste.png'> 
                                <input type="image"  name="Vorige" value="Eerste 10" src='images/terug.png'>  
                                <input type="image"  name="Volgende" value="Volgende 10" src='images/volgende.png'> 
                                <input type="image"  name="Laatste 10" value="Laatste 10" src='images/laatste.png'> 
                            </td> 
                        </tr>
                        <tr> 
                            <td colspan="3" style="text-align:center;">   
                                <input type="image"  name="gebruiker toevoegen" value="gebruiker toevoegen" src='images/adduser.png'> 
                            </td> 
                        </tr>
                    </table>
                </div>  
            </form>
        </section>
    </body>
</html>
