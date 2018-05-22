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
                border: none;
                background-color: inherit;
                padding: 5px 5px;
                cursor: pointer;
                display: inline-block;
                color: black;
            } 
            .btn:hover {
                background: #e7e7e7;
            }
            .default {
                font-size: 16px;
            }
            .plus {
                font-size: 20px;
            }
           .datatable tbody tr:nth-child(2) td:first-child {
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
                        <!-- FOR EACH DING -->
                        <tr>
                            <td>Kookschool <button class="btn plus"><strong>+</strong></button></td>
                            <td>School der koks</td>
                            <td><button class="btn default">✎</button><button class="btn default">✖</button></td>                                                             
                        </tr>
                        <tr>
                            <td class="modules">Opleiding</td>
                            <td>School der koks</td>
                            <td><button class="btn default">✎</button><button class="btn default">✖</button></td>                                              
                        </tr>
                        <tr>
                            <td>Mechanicaschool</td>
                            <td>School der robots</td>
                            <td><button class="btn default">✎</button><button class="btn default">✖</button></td>                                                             
                        </tr>
                        <!-- END FOR EACH -->
                    </tbody>
                </table> 
            </div>
        </section>
    </body>
</html>
