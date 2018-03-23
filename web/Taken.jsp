<%-- 
    Document   : Taken
    Created on : 8-mrt-2018, 20:17:05
    Author     : Dirk & Aaron
--%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         import="model.Taak"
         %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>JSP Page</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="TakenServlet">
                <div class="table-container">

                    <table>
                        <tr style="background-color:rgba(0, 0, 0, 0);"> 
                            <td  style="text-align:center;">   
                                <input type="image"  name="taak zoeken" value="taak zoeken" src='images/searchUser.png'> 
                                <input  type="text" style="height:30px;font-size:14pt;" name="zoekterm" size="25" maxlength="25" value=""> 
                            </td> 
                        </tr>
                    </table>


                    <table class="datatable">
                        <thead>
                            <tr>
                                <th onclick="sortTable(0)"><a>Taak naam</a></th>
                                <th onclick="sortTable(1)"><a>Beschrijving</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>Actions</th>
                                    </c:if>

                                <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                                    <th>Actions</th>
                                    </c:if>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstTaken}" var="taak">
                                <tr>
                                    <c:if test="${taak.taakID == sessionScope.editID}" >
                                        <td> <input type="text" name="taak_naam" size="15" maxlength="25" value="${taak.naam}"> </td>
                                        <td> <input type="text" name="taak_beschrijving" size="15" maxlength="25" value="${taak.beschrijving}"> </td>
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
                                <input type="image"  name="taak toevoegen" value="taak toevoegen" src='images/adduser.png'> 
                            </td> 
                        </tr>
                    </table>
                </div>  
            </form>
        </section>
    </body>
</html>