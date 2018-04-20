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
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/formulier.css">

        <title>EvaluatieFormulier</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <form method="post" action="EvaluatieFormulierServlet" >
            <h2 align="center" class="kleur"> EvaluatieFormulier </h2>
            <hr>

            <table class="doelstelling1" >
                <tr>
                    <th> </th>
                    <th><a>Datum</a></th>
                    <th></th>
                    <th><a>Jaar</a></th>
                    <th></th>
                    <th ><a>Semester</a</th>
                    <th></th>
                    <th ><a>Studiegebied</a</th>
                    <th></th>
                    <th><a>Opleiding</a></th>
                    <th></th>
                    <th><a>Module</a></th>
                    <th></th>
                    <th><a>Cursist</a></th>
                    <th></th>
                    <th><a>Lesnr</a></th>
                    <th></th>

                </tr>
                <tr>
                    <td width="10px"></td>

                    <td> <input type="date" name="datum" style="font-size: 0.9rem; max-width:120px;"  > </td>

                    <td width="10px"></td>

                    <td> <select name="Schooljaar" >
                            <c:forEach items="${schooljaren}" var="schooljaar">                  
                                <option value="schooljaar"> ${schooljaar.schooljaar} </option>                     
                            </c:forEach>
                        </select> </td>
                    <td width="10px"></td>

                    <td> <select name="Semester">
                            <c:forEach items="${semesters}" var="semester">                  
                                <option value="semester"> ${semester.semester} </option>                     
                            </c:forEach>
                        </select> </td>

                    <td width="10px"></td>

                     <td><select name="Studiegebied" style="max-width:130px;">
                            <c:forEach items="${studiegebieden}" var="studiegebied">                  
                                <option value="studiegebied"> ${studiegebied.naam} </option>                     
                            </c:forEach>
                        </select> </td>

                    <td width="10px"></td>
                    
                    <td><select name="Opleiding" style="max-width:130px;">
                            <c:forEach items="${opleidingen}" var="opleiding">                  
                                <option value="opleiding"> ${opleiding.naam} </option>                     
                            </c:forEach>
                        </select> </TD>

                    <td width="10px"></td>

                    <td>  <select name="Module" style="max-width:100px;">
                            <c:forEach items="${modules}" var="module">                  
                                <option value="module"> ${module.naam} </option>                     
                            </c:forEach>
                        </select> </td>

                    <td width="10px"></td>

                    <td>  <select name="cursist" style="max-width:100px;">
                            <option value="Banket AA">Piet Rademakers</option>
                            <option value="Beslag en cakes AA">Leo Vertongen</option>
                            <option value="Stukgebak AA">Jeroen Meus</option>
                            <option value="Beslagen A">Peter Goossens</option>
                            <option value="Beslagen">Maak een cursist aan...</option>
                        </select> </td>

                    <td width="10px"></td>

                    <td>  <select name="les">
                            <option value="Banket AA">1</option>
                            <option value="Beslag en cakes AA">2</option>
                            <option value="Stukgebak AA">3</option>
                            <option value="Beslagen A">4</option>
                            <option value="Beslagen">5</option>
                        </select> </td>

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
                        <td class="lijn">Zelfstandig werken</td>
                        <td class="center"></td>
                        <td>Samenstellen grondstoffen</td>
                        <td class="center">A+</td>
                        <td class="center">10</td>
                        <td>Fantastisch gedaan!</td>
                    </tr>
                    <tr class="formrow">
                        <td></td>
                        <td></td>
                        <td>Instructies geven</td>
                        <td class="center">B+</td>
                        <td class="center">8</td>
                        <td>ne goeien baas</td>
                    </tr>

                    <tr class="formrow">
                        <td></td>
                        <td></td>
                        <td>Afwassen</td>
                        <td class="center">B+</td>
                        <td class="center">8</td>
                        <td>nog vuile taloren</td>
                    </tr>

                    <tr class="formrow">
                        <td class="lijn" >Hygienisch werken</td>
                        <td class="center">x</td>
                        <td>Bestellen schoonmaakprodukten</td>
                        <td class="center">C</td>
                        <td class="center">6</td>
                        <td>Hier en daar wat vergeten!</td>
                    </tr>
                    <tr class="formrow">
                        <td></td>
                        <td></td>
                        <td>Opstelling plan</td>
                        <td class="center">A</td>
                        <td class="center">9</td>
                        <td>Redelijk goed gestructureerd</td>
                    </tr>
                </tbody>
                <tfoot></tfoot>

                <tr class="formrow">
                    <td><select name="cursist" style="max-width:100px;">
                            <c:forEach items="${doelstellingen}" var="doelstelling">
                                <option value="Banket AA">${doelstelling.naam}</option>
                            </c:forEach>
                            <option value="Banket AA">Maak een nieuwe doelstelling aan...</option>

                        </select></td>

                    <td></td>
                    <td><select name="cursist" style="max-width:100px;">
                            <option value="Banket AA">Taak 1</option>
                            <option value="Banket AA">Taak 2</option>
                            <option value="Banket AA">Taak 3</option>
                            <option value="Banket AA">Taak 4</option>
                            <option value="Banket AA">Maak een nieuwe taak aan...</option>
                        </select></td></td>
                    <td class="center"><select name="cursist" value="" style="max-width:100px;">

                            <option value="Banket AA">A+</option>
                            <option value="Banket AA">A</option>
                            <option value="Banket AA">B+</option>
                            <option value="Banket AA">B</option>
                            <option value="Banket AA">C+</option>
                            <option value="Banket AA">C</option>
                            <option value="Banket AA">D+</option>
                            <option value="Banket AA">D</option>

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
                    <td > <input type="submit"  value=" Bewaar Formuier " class="button"></td>
                    <td > <input type="submit"  value=" Laad Formulier " class="button"></td>
                </tr>

            </table>
        </form>

    </body>
</html>

