<%-- 
    Document   : opleiding
    Created on : 8-mrt-2018, 11:44:51
    Author     : Davino
--%>


<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         import="model.Opleiding"
         import="java.util.ArrayList"
         %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSP Page</title>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
    </head>
    
    <body> 
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="OpleidingServlet">
                <h2>Opleidingen</h2>
                <br>
                <table class="datatable">
                        <thead>
                            <tr>
                                <th onclick="sortTable(0)"><a>opleidingID</a></th>
                                <th onclick="sortTable(1)"><a>naam</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>Actions</th>
                                    </c:if>

                            </tr>
                        </thead>
                        <tbody>
                             <c:forEach items="${lijstOpleidingen}" var="taak">
                                <tr>
                                    <c:if test="${opleiding.opleidingID == sessionScope.editID}" >
                                        <td> <input type="text" name="moduleID" size="15" maxlength="25" value="${opleiding.opleidingID}"> </td>
                                        <td> <input type="text" name="modulenaam" size="15" maxlength="25" value="${opleiding.naam}"> </td>
                                        </c:if>

                                    <c:if test="${opleiding.opleidingID != sessionScope.editID}" >
                                        <td> ${opleiding.opleidingID} </td>
                                        <td> ${opleiding.naam} </td>
                                    </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td>
                                            <c:if test="${opleiding.opleidingID != sessionScope.editID}" >
                                                <input type="image"  name="idEdit" value="${opleiding.opleidingID}" src='images/pencil.png'>
                                                <input type="image"  name="idDelete" value="${opleiding.opleidingID}" src='images/vuilbak.png'>
                                            </c:if>
                                            <c:if test="${opleiding.opleidingID == sessionScope.editID}" >
                                                <input type="image"  name="idSave" value="${opleiding.opleidingID}" src='images/green.png'>
                                                <input type="image"  name="idCancel" value="${opleiding.opleidingID}" src='images/cancel.png'>
                                            </c:if>
                                        </td>
                                    </c:if>
                         </c:forEach>
                        </tbody>
                    </table>
                 <table>
                        <tr style="background-color:rgba(0, 0, 0, 0);"> 
                            <td style="text-align:center;">   
                                <input type="image"  name="Eerste" value="Eerste 10" src='images/eerste.png'> 
                                <input type="image"  name="Vorige" value="Eerste 10" src='images/terug.png'>  
                                <input type="image"  name="Volgende" value="Volgende 10" src='images/volgende.png'> 
                                <input type="image"  name="Laatste" value="Laatste 10" src='images/laatste.png'> 
                            </td> 
                        </tr>
                        <tr> 
                            <td colspan="3" style="text-align:center;">   
                                <input type="image"  name="Opleiding Toevoegen" value="Opleiding Toevoegen" src='images/adduser.png'> 
                            </td> 
                        </tr>
                    </table>
                </div> 
            </form>   
        </section>    
    </body>
</html>

