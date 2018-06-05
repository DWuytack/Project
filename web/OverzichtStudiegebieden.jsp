<%-- 
    Document   : OverzichtStudiegebieden
    Created on : 30-mrt-2018, 11:10:04
    Author     : Davino Tizarine


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <script src="js/studiegebieden.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/studiegebieden.css">
        <title>StudieGebieden</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <form method="post" action="StudiegebiedenServlet?keuze=reset" >
            <section id="pagename">
                <h2> OverzichtStudiegebieden </h2>
            </section>
            <hr>

            <tr>
                <td width="70px"></td>



                <td width="10px"></td>

                <td>
                    <select name="studiegebied" id="studiegebied"  onchange="laadOpleidingen()"  style="max-width:170px;">
                        <option selected disabled> Studiegebied... </option>
                        <c:forEach items="${studiegebieden}" var="studiegebied">                  
                            <option> ${studiegebied.naam} </option>                     
                        </c:forEach>
                        <option> Voeg studiegebied toe... </option>

                    </select> 
                </td>


                <td width="10px"></td>

                <td><select id="opleidingen" onchange="laadModules()"  hidden style="max-width:170px;">

                    </select></td>

                <td width="10px"></td>

                <td>  <select id="modules"  onchange="laadDoelstellingen()" hidden style="max-width:170px;">

                    </select> </td>

                <td width="10px"></td>
                <td>  <select id="doelstellingen"  onchange="laadTaken()" hidden style="max-width:170px;">

                    </select> </td>

                <td>  <select id="taken" hidden style="max-width:170px;">

                    </select> </td>

                <td width="10px"></td>  


                <td width="20px"></td>

                <td>  <label id="studiegebied"  hidden  style="max-width:120px;">
                    </label> </td>
        </form>
    </body>
</html>
--%>

<%-- HUIDIG WERKEND STUK -----------------------------------------------------------
    Document   : OverzichtStudiegebieden
    Created on : 22-mei-2018, 9:50:00
    Author     : Ewout
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="keywords" content="ScoreOnWeb, Studiegebieden"
              name="description" content="Overzicht van Studiegebieden"
              name="author" content="Ewout Phlips"
              name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/nav.js"></script>
        <script src="js/studiegebieden.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/studiegebiedenOverzicht.css">
        <title>ScoreOnWeb - Overzicht Studiegebieden</title>    
    </head>

    <body>
        <!-- BOVENBALK -->
        <%@include file="Bovenbalk.jsp" %>

        <!-- PAGINA TITEL -->
        <section id="pagename">
            <h2>Overzicht Studiegebieden</h2> 
        </section>

        <!-- TABEL OVERZICHT -->
        <br>
        <section>
            <div class="table-container" id="overzicht">
                <table class="studiegebieden">
                    <thead>
                        <tr>
                            <th>Studiegebieden</th>
                        </tr>
                    </thead>
                    <c:forEach items="${studiegebieden}" var="studiegebied">
                        <tr class="studiegebied" data-ID="${studiegebied.studiegebiedID}" >
                            <td><button onclick="toggle(this)">+</button>${studiegebied.naam}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </section>

    </body>
</html>
