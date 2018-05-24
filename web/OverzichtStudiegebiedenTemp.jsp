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
            } 
            .btn:hover {
                background: #e7e7e7;
            }
            .action {
                font-size: 16px;
            }
            .plus {
                font-size: 16px;
            }
            .opleiding td:first-child {
                padding-left: 25px;
            }
            .module td:first-child {
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
                        <tr class="studiegebied">
                            <td><button class="btn plus" value="closed" onclick="toggle('stu1', 'opleiding', this)"><b>+</b></button><button class="btn">Kokschool</button></td>
                            <td>"Beschrijving nog toe te voegen."</td>
                            <td><button class="btn action">✎</button><button class="btn action">✖</button></td>                                                             
                        </tr>
                        <tr class="opleiding stu1" hidden>
                            <td><button class="btn plus" value="closed" onclick="toggle('opl1', 'module', this)"><b>+</b></button><button class="btn">Banketbakker</button></td>
                            <td>"Beschrijving nog toe te voegen."</td>
                            <td><button class="btn action">✎</button><button class="btn action">✖</button></td>                                              
                        </tr>
                        <tr class="module stu1 opl1" value="closed" hidden>
                            <td>Banket AA</td>
                            <td>"Beschrijving nog toe te voegen."</td>
                            <td><button class="btn action">✎</button><button class="btn action">✖</button></td>
                        </tr>
                        <tr class="studiegebied" value="stu1">
                            <td>Mechanicaschool</td>
                            <td>"Beschrijving nog toe te voegen."</td>
                            <td><button class="btn action">✎</button><button class="btn action">✖</button></td>                                                             
                        </tr>
                    </tbody>
                </table> 
            </div>
        </section>

        <script>
            function get(name) {
                return document.getElementsByClassName(name);
            }

            function toggle(elementsName, type, element) {
                if (element.value === "closed") { //Show Elements
                    var elements = get(elementsName + " " + type);
                    var i = 0, l = elements.length;
                    for (; i < l; i++) {
                        elements[i].style.display = "table-row";
                        elements[i].parentElement.value = "open"; 
                    }
                    element.value = "open";

                } else if (element.value === "open") { //Hide Elements
                    var elements = get(elementsName);
                    var i = 0, l = elements.length;
                    for (; i < l; i++) {
                        elements[i].style.display = "none";
                        elements[i].value = "closed";
                    }
                        element.value = "closed";
                }
            }

        </script>
    </body>
</html>
