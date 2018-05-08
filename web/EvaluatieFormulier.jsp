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
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <script src="js/evaluatie.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/formulier.css">

        <title>EvaluatieFormulier</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <form method="post" action="EvaluatieFormulierServlet" >
            <section id="pagename">
                <h2> EvaluatieFormulier </h2>
            </section>
            <hr>

            <table>

                <tr>
                    <td width="70px"></td>

                    <td> <input type="date" id="datum" 
                       required style="font-size: 0.9rem"  > </td>
                    
                    <td> <select name="Semester" id="Semester" onchange="checkDate()" style="max-width:140px;" >
                            <option selected > Semester... </option>
                            <c:forEach items="${semesters}" var="semester">                  
                                <option> ${semester.semester} </option>                     
                            </c:forEach>

                        </select> </td>

                    <td width="10px"></td>

                    <td>
                        <select name="studiegebied" id="studiegebied" hidden  onchange="laadOpleidingen()"  style="max-width:170px;">
                            <option selected disabled> Studiegebied... </option>
                            <c:forEach items="${studiegebieden}" var="studiegebied">                  
                                <option> ${studiegebied.naam} </option>                     
                            </c:forEach>
                            <option> Voeg studiegebied toe... </option>

                        </select> 
                    </td>


                    <td width="10px"></td>

                    <td><select id="opleidingen" onchange="laadModules()" hidden  style="max-width:170px;">

                        </select></TD>

                    <td width="10px"></td>

                    <td>  <select id="modules" hidden onchange="laadCursistenEnDoelstellingen()" style="max-width:170px;">

                        </select> </td>

                    <td width="10px"></td>

                    <td>  <select id="cursisten" onchange="genereerFormulierNaam()" hidden  style="max-width:170px;">

                        </select> </td>


                    <td width="10px"></td>

                    <td>  <label id="formulierNaam" hidden  style="max-width:70px;">
                       </label> </td>

                    <td width="10px"></td>
                </tr>
            </table>
            <br>
            <table class="doelstelling2">
                <thead>

                    <tr class="lijn" bgcolor="#AAAA00" height="50" >
                        <th  class="links" width="25%"><b>Doelstellingen</th>                      
                        <th><b>Kern</b></th>               
                        <th  class="links" ><b>Taken</b></th>
                        <th  class="links" width="5%"><b>Score</b></th>
                        <th  class="center" width="10%"><b>Waarde</b></th>
                        <th  class="links" width="25%"><b>Commentaar</b></th>
                    </tr>
                </thead>


                <tbody>

                    <tr class="formrow">
                        <td><select name="cursist" style="max-width:300px;">
                                <option value="doelstelling" selected disabled> Kies doelstelling... </option>
                                <c:forEach items="${doelstellingen}" var="doelstelling">
                                    <option value="doelstelling">${doelstelling.naam}</option>
                                </c:forEach>
                                <option value="doelstelling">Maak een nieuwe doelstelling aan...</option>

                            </select></td>

                        <td></td>
                        <td><select name="taak" style="max-width:300px;">
                                <option value="taak" selected disabled> Kies taak... </option>
                                <c:forEach items="${taken}" var="taak">
                                    <option value="taak">${taak.naam}</option>
                                </c:forEach>
                                <option value="taak">Maak een nieuwe taak aan...</option>

                            </select></td></td>
                        <td class="center">
                            <select name="score" value="">
                                <option value="score" selected disabled> Kies score... </option>
                                <c:forEach items="${scores}" var="score">
                                    <option value="score">${score.naam}</option>
                                </c:forEach>


                            </select></td>
                        <td></td>
                        <td><textarea name="textarea"
                                      rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    </tr>
            </table>
            <br>

            <table class="doelstelling1">

                <tr >

                    <td align="center" >Totale Score: 80% </td>
                </tr>

            </table>
            <br>

            <table class="doelstelling1" >

                <tr >

                    <td > <input type="submit"  value=" Print Formulier "  class="button"></td>
                    <td > <input type="submit"  value=" Bewaar Formulier " class="button"></td>
                    <td > <input type="submit"  value=" Laad Formulier " class="button"></td>
                </tr>

            </table>

            <h2 id="demo"> </h2>
        </p>
    </form>

</body>
</html>

