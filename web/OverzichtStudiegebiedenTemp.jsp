<%-- 
    Document   : OverzichtStudiegebieden
    Created on : 22-mei-2018, 9:50:00
    Author     : Ewout
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <style>
            button {
                outline:none;
                min-width: 25px;
                height: 30px;
                border-radius: 5px;
                border: 0;
                background-color: inherit;
                padding: 5px 5px;
                cursor: pointer;
                display: inline-block;
                color: black;
                font-size: 16px;
                font-weight: 700;
            } 
            button:hover {
                background: #e7e7e7;
            }
            studiegebied {

            }
            opleiding {

            }
        </style>
        <meta name="keywords" content="ScoreOnWeb, Studiegebieden"
              name="description" content="Overzicht van Studiegebieden"
              name="author" content="Ewout Phlips"
              name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/nav.js"></script>
        <script src="js/studiegebieden.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>ScoreOnWeb - Overzicht Studiegebieden</title>
    </head>

    <body>
        <!-- BOVENBALK -->
        <%@include file="Bovenbalk.jsp" %>

        <!-- PAGINA TITEL -->
        <section id="pagename">
            <h2>Overzicht Studiegebieden - TEMP</h2> 
        </section>

        <!-- TABEL OVERZICHT -->
        <br>
        <section>
            <div class="table-container" id="overzicht">
                <table id="studiegebieden">
                    <thead>
                        <tr>
                            <th>Naam</th>
                        </tr>
                    </thead>
                    <c:forEach items="${studiegebieden}" var="studiegebied">
                        <tr class="studiegebied" data-ID="${studiegebied.studiegebiedID}" >
                            <td><button onclick="toggleOpleidingen(this)">+</button>${studiegebied.naam}
                                <table data-status="CLOSED">
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </section>

    </body>
</html>
