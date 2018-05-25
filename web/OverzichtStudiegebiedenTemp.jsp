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
            .btn {
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
            .btn:hover {
                background: #e7e7e7;
            }
            .OPL td:first-child {
                padding-left: 25px;
            }
            .MOD td:first-child {
                padding-left: 50px; 
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
                            <th width="70%"><a>Beschrijving</a></th>
                            <th width="5%"></th>                                                             
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${lijstOverzicht}" var="Object">
                            
                        </c:forEach>
                        <tr class="STU" data-status="CLOSED" ID='STU_1'>
                            <td>
                                <button class="btn" onclick="toggle('STU_1', 'OPL');"><b>+</b></button>
                                Studiegebied 1
                            </td>
                            <td>Beschrijving</td>
                            <td><button class="btn">✎</button><button class="btn">✖</button></td>
                        </tr>
                        <tr class="OPL STU_1" value="CLOSED" ID="OPL_1" hidden>
                            <td>
                                <button class="btn" onclick="toggle('OPL_1', 'MOD');"><b>+</b></button>
                                Opleiding 1
                            </td>
                            <td>Beschrijving</td>
                            <td><button class="btn">✎</button><button class="btn">✖</button></td>
                        </tr>
                        <tr class="MOD OPL_1" value="CLOSED" ID="MOD_1" hidden>
                            <td>
                                <button class="btn" onclick="//toggle();"><b>+</b></button>
                                Module 1
                            </td>
                            <td>Beschrijving</td>
                            <td><button class="btn">✎</button><button class="btn">✖</button></td>
                        </tr>
                        <tr class="OPL STU_1" value="CLOSED" ID="OPL_2" hidden>
                            <td>
                                <button class="btn" onclick="//toggle();"><b>+</b></button>
                                Opleiding 2
                            </td>
                            <td>Beschrijving</td>
                            <td><button class="btn">✎</button><button class="btn">✖</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>

        <script>
            function get(name) {
                return document.getElementsByClassName(name);
            }

            function toggle(elementName, typeToToggle) {
                var toOpenElements = get(typeToToggle.concat(" ", elementName));
                var toCloseElements = get(elementName);

                // OPEN ELEMENTS
                if (document.getElementById(elementName).dataset.status === "CLOSED") {
                    var i;
                    for (i = 0; i < toOpenElements.length; i++) {
                        toOpenElements[i].style.display = "table-row";
                    }
                    document.getElementById(elementName).dataset.status = "OPENED";
                    document.getElementById(elementName).firstElementChild.firstElementChild.innerHTML = "<b>-</b>";
                }

                // CLOSE ELEMENTS
                else if (document.getElementById(elementName).dataset.status === "OPENED") {
                    var i;
                    for (i = 0; i < toCloseElements.length; i++) {
                        toCloseElements[i].style.display = "none";
                    }
                    document.getElementById(elementName).dataset.status = "CLOSED";
                    document.getElementById(elementName).firstElementChild.firstElementChild.innerHTML = "<b>+</b>";
                }
            }
        </script>
    </body>
</html>
