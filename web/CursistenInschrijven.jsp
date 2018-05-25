<%-- 
    Document   : CursistenInschrijven
    Created on : 22-mei-2018, 11:07:08
    Author     : Aaron
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/evaluatie.js"></script>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>CursistenInschrijven</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <form method="post" action="CursistenInschrijvenServlet?keuze=reset" >

            <section id="pagename">
                <h2> CursistenInschrijven </h2>
            </section>

            <section>
                <form action="CursistenInschrijvenServlet">
                    <div class="table-container">

                        <div class="table-nav-header">
                            <div id="zoeken">
                                <a id="bt-zoeken">
                                    <i class="material-icons">search</i>
                                </a>
                                <input type="text" name="zoekterm" size="15">
                            </div>
                        </div> 
                        <div role="wrapper" style="overflow-x: auto; pointer-events: all;">     
                            <table class="datatable">
                                <thead>
                                    <tr>
                                        <th width="15%" onclick="sortTable(0)"><a>Voornaam</a</th>
                                        <th width="15%" onclick="sortTable(1)"><a>Achternaam</a</th>
                                        <th width="25%" onclick="sortTable(2)"><a>E-mail</a></th>
                                        <th class="actie">Inschrijven in</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${lijstCursisten}" var = "cursist">
                                        <tr>
                                            <td> ${cursist.voorNaam} </td>
                                            <td> ${cursist.achternaam} </td>
                                            <td> ${cursist.email} </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>>
                            </table>
                        </div>
                    </div>
                </form>
            </section>

        </form>

    </body>
</html>

