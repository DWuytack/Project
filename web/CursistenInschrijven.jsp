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

        <section id="pagename">
            <h2> Cursisten Inschrijven </h2>
        </section>

        <!-- SelectieRij -->
        <table>
            <tr height="10px">
            <tr>
                <td width="20px"></td>

                <!-- Datum kiezer -->
                <td> <input type="date" id="datum"  value="today" onchange="pasSemesterAan()"
                            required style="font-size: 0.9rem"  
                            style="background: #f9f9f9"  > </td>
            <script>
                let today = new Date().toISOString().substr(0, 10);
                document.querySelector("#datum").value = today;
            </script>

            <!-- Semester kiezer-->
            <td> <select name="Semester" id="Semester"
                         style="background: #f9f9f9" class="drop" >
                    <option selected > Semester... </option>
                    <c:forEach items="${semesters}" var="semester">                  
                        <option> ${semester.semester} </option>                     
                    </c:forEach>
                </select> </td>

            <td width="10px"></td>

            <script>
                let tijd = new Date();
                switch (tijd.getMonth() + 1) {
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        document.querySelector("#Semester").selectedIndex = 2;
                        break;
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 1:
                        document.querySelector("#Semester").selectedIndex = 1;
                        break;
                }

            </script>



            <!-- Studiegebied kiezer -->
            <td>
                <select name="studiegebied" id="studiegebied"  
                        onchange="laadDropdown('opleidingen')" 
                        style="background: #efc4c4"  class="drop">
                    <option selected disabled> Studiegebied... 
                    </option>
                    <c:forEach items="${studiegebieden}" 
                               var="studiegebied">                  
                        <option> ${studiegebied.naam} </option>                     
                    </c:forEach>
                    <option> Voeg studiegebied toe... </option>
                </select> 
            </td>
            <td width="10px"></td>

            <!-- Opleiding kiezer -->
            <td>
                <select id="opleidingen" onchange="laadDropdown('modules')"
                        hidden style="background: #f9f9f9"  class="drop"></select>
            </td>
            <td width="10px"></td>

            <!-- Module kiezer -->
            <td>  
                <select id="modules" hidden onchange="laadDropdown('cursisten')"
                        style="background: #f9f9f9"  class="drop"></select> 
            </td>
            <td width="10px"></td>
        </table>

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
                                    <th width="15%" class="actie">Cursist uit deze lijst verwijderen</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${lijstCursisten}" var = "cursist">
                                    <tr>
                                        <td> ${cursist.voorNaam} </td>
                                        <td> ${cursist.achternaam} </td>
                                        <td> ${cursist.email} </td>
                                        <td> <span> <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png'> </span> </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </section>
    </body>
</html>

