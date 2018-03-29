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
        
        <section id="pagename">
            <h1> Opleidingen </h1> 
        </section>
        
        <section>
            <form action="OpleidingServlet">
                <div class="table-container">
                    <% ArrayList<Opleiding> lijstOpleidingen = (ArrayList<Opleiding>) (session.getAttribute("lijstOpleidingen"));%>
                    <br>

                    <div class="table-nav-header">
                        <div id="zoeken">
                            <a id="bt-zoeken">
                                <i class="material-icons">search</i>
                            </a>
                            <input type="text" name="zoekterm" value="" size="15">
                        </div>
                        <div>
                            <a id="bt-opleidingen_toevoegen" name="Opleiding Toevoegen" >
                                <i class="material-icons">add</i>
                            </a>
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div>
                
                    <table class="datatable">
                        <thead>
                            <tr>
                                <th onclick="sortTable(0)"><a>naam</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>Actions</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstOpleidingen}" var="opleiding">
                                <tr>
                                    <c:if test="${opleiding.opleidingID == sessionScope.editID}" >
                                        <td> <input type="text" name="modulenaam" size="15" maxlength="25" value="${opleiding.naam}"> </td>
                                    </c:if>
                                        
                                    <c:if test="${opleiding.opleidingID != sessionScope.editID}" >
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
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="table-nav-footer"></div>
                </div>
            </form>   
        </section>    
    </body>
</html>

