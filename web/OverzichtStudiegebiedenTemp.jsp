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
            } 
            button:hover {
                background: #e7e7e7;
            }
        </style>
        <meta name="keywords" content="ScoreOnWeb, Studiegebieden"
              name="description" content="Overzicht van Studiegebieden"
              name="author" content="Ewout Phlips"
              name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/nav.js"></script>
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
                <table class="datatable">
                    <thead> 
                        <tr>
                            <th width="25%"><a>Naam</a></th>
                            <th width="65%"><a>Beschrijving</a></th>
                            <th width="10%"></th>                                                             
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${studiegebieden}" var="studiegebied">
                            <tr data-stu-ID="${studiegebied.studiegebiedID}">
                                <td><button onclick="laadOpleidingen()"><b>+</b></button>${studiegebied.naam}</td>
                                <td>${studiegbied.beschrijving}</td>
                                <td><button>✎</button><button>✖</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>

    </body>
</html>
