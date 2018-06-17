<%-- 
    Document   : EvaluatieFormulier
    Created on : 8-mrt-2018, 20:19:21
    Author     : Dirk
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/evaluatie.js"></script>
        <script src="js/nav.js"></script>
        <script src="js/jquery.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/evaluatie.css">
        <title>EvaluatieFormulier</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <!-- Titel van de pagina -->
        <section id="pagename">
            <h2> EvaluatieFormulier </h2>
        </section>
        <section id="evaluatie">
            <form method="post" action="EvaluatieFormulierServlet" >
                <hr>

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
                    <td> <select name="Semester" id="Semester" onchange="laadCursistenOpnieuw()"
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

                    <!-- Cursist kiezer -->
                    <td>  
                        <select id="cursisten" onchange="laadLesnr()" hidden 
                                style="background: #f9f9f9"  class="drop"></select>
                    </td>
                    <td width="10px"></td>  

                    <!-- Lesnummer kiezer -->
                    <td>  
                        <select id="lesnr" onchange="laadFormuliernaam()" 
                                hidden style="background: #f9f9f9"  class="drop">
                            <option selected disabled> Lesnr... </option>
                            <c:forEach items="${lesnrs}" var="lesnr">                  
                                <option> ${lesnr.lesnr} </option>                     
                            </c:forEach>
                        </select> </td>  
                    <td width="20px"></td>

                    <!-- Formuliernaam -->
                    <td>  
                        <label id="formulierNaam"  hidden></label> 
                    </td>
                    <td width="10px"></td>
                    </tr>

                </table>

                <!-- Evaluatie venster -->
                <table id="evaluatieTable" class="evaltable">
                    <!-- Titelbalk-->
                    <tr height="20px" />
                    <tr bgcolor="#ceccca" height="50px" id="header" hidden>
                        <th width="3%" />
                        <th width="20%" class="links"><b>Taken</b></th>   
                        <th width="3%" />
                        <th width="30%" class="links"><b>Doelstellingen</b></th>  
                        <th width="3%" />
                        <th width="6%" class="center"><b>Kern</b></th>
                        <th width="3%" />
                        <th width="6%" class="center"><b>Scores</b></th>
                        <th width="3%" />
                        <th width="20%" class="links"><b>Commentaren</b></th>
                        <th width="3%" />
                    </tr>

                    <tr height="20px" >
                        <td> <hr/>  </td>
                        <td> <hr/> </td>
                        <td> <hr/> </td>
                        <td> <hr/> </td>
                        <td> <hr/>  </td>
                        <td> <hr/> </td>
                        <td> <hr/> </td>
                        <td> <hr/> </td>
                        <td> <hr/>  </td>
                        <td> <hr/>  </td>
                        <td> <hr/> </td>
                    </tr>



                    <!-- Taak toevoegen -->
                    <tr class="addLine" id="addLine" hidden>
                        <td />
                        <td><input type="button" value="+" 
                                   onclick="laadLijn();"  class="lijnButton" /></td>
                    </tr> 

                </table>

                <table class="doelstelling1" >
                    <tr height="20px" />
                    <tr >
                        <td > <input type="button"  value=" Formulier leeg maken "
                                     class="evalButton" onclick="formulierLeegMaken();" hidden  style="color:grey" id="leegButton" ></td>

                        <td > <input type="button"  value=" Print formulier  "  onclick="printFormulier();"  hidden  style="color:grey" id="printButton"
                                     class="evalButton"></td>
                        <td > <input type="button"  value=" Bewaar formulier " onclick="bewaarFormulier();"  id="bewaarButton" hidden  style="color:grey"
                                     class="evalButton"></td>
                        <td > <input type='button'  value= ' Laad formulier '  id="laadButton" hidden  style="color:grey"
                                     class='evalButton' onclick="laadFormulier();"></td>

                    </tr>
                </table>

                </p>
            </form>
        </section>

    </body>
</html>

