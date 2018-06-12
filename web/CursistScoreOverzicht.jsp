<%-- 
    Document   : CursistScoreOverzicht
    Created on : 28-mei-2018, 21:38:42
    Author     : Gil en Keanu
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/cursistOverzicht.js"></script>
        <script src="js/nav.js"></script>
        <script src="js/jquery.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/cursistenOverzicht.css">
        <title>Overzicht per cursist</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <!-- Titel van de pagina -->
        <section id="pagename">
            <h2> Overzicht Score Cursist </h2>
        </section>
        <section id="overzicht">
            <form method="post" action="CursistOverzichtServlet" >
                <hr>

                <!-- SelectieRij -->
                <table>
                    <tr height="10px">
                    <tr>
                        <td width="20px"></td>

                        <!-- Schooljaar kiezer -->
                        <td> <select name="schooljaar"  id="schooljaar" style="background: #f9f9f9" onchange="laadDropdown('semesters')" class="drop" > 
                                <option selected > Schooljaar... </option>
                                <c:forEach items="${schooljaren}" var="schooljaar">                  
                                    <option> ${schooljaar.schooljaar} </option>                     
                                </c:forEach>
                            </select> </td>

                        <td width="10px"></td>

                        <!-- Semester kiezer-->
                        <td> <select name="Semester" id="Semester" style="background: #f9f9f9" onchange="laadDropdown('studiegebieden')" class="drop" hidden>
                                <option selected > Semester... </option>
                                <c:forEach items="${semesters}" var="semester">                  
                                    <option> ${semester.semester} </option>                     
                                </c:forEach>
                            </select> </td>

                        <td width="10px"></td>


                        <!-- Studiegebied kiezer -->
                        <td>
                            <select name="studiegebied" id="studiegebied"  
                                    onchange="laadDropdown('opleidingen')" hidden
                                    style="background: #efc4c4"  class="drop">
                                <option selected disabled> Studiegebied... 
                                </option>
                                <c:forEach items="${studiegebieden}" 
                                           var="studiegebied">                  
                                    <option> ${studiegebied.naam} </option>                     
                                </c:forEach>
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

                        <!-- Cursist kiezer -->
                        <td>  
                            <select id="cursisten"  hidden onchange="laadCursistInfo()" style="background: #f9f9f9"  class="drop"></select>
                        </td>
                        <td width="10px"></td>  

                    </tr>

                </table>
                <br/><br/>
                <table class="evaltable" id="titelTable" hidden>
                    <tr bgcolor="#b9d1f7" height="50px">
                        <td class="center" id="cursistTitel" ></td>
                    <tr>
                </table<
                <!-- Evaluatie venster -->
                <table id="evaluatieTable" class="evaltable" hidden>
                    <!-- Titelbalk-->
                    <tr height="20px" />
                    <tr bgcolor="#ceccca" height="50px">
                        <th width="3%" />
                        <th width="30%" class="links"><b>Doelstellingen</b></th>  
                        <th width="3%" />
                        <th width="6%" class="center"><b>Kern</b></th>
                        <th width="3%" />
                        <th width="30%" class="links"><b>Taken</b></th>  
                        <th width="3%" />
                        <th width="6%" class="center"><b>Score</b></th>
                        <th width="3%" />
                        <th width="6%" class="center"><b>Gemiddelde Score</b></th>
                        <th width="3%" />
                    </tr>


                </table>







                </div>
        </section>  
    </body>
</html>
