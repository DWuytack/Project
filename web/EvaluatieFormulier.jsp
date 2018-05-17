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

                    <td>  <select id="modules" hidden onchange="laadCursisten()" style="max-width:170px;">

                        </select> </td>

                    <td width="10px"></td>


                    <td>  <select id="cursisten" onchange="laadLesnr()" hidden  style="max-width:170px;">

                        </select> </td>

                    <td width="10px"></td>  

                    <td>  <select id="lesnr" onchange="genereerFormuliernaam()" hidden  style="max-width:170px;">

                            <option selected disabled> Lesnr... </option>
                            <c:forEach items="${lesnrs}" var="lesnr">                  
                                <option> ${lesnr.lesnr} </option>                     
                            </c:forEach>
                            <option> Voeg lesnr toe... </option>

                        </select> </td> 



                    <td width="20px"></td>

                    <td>  <label id="formulierNaam"  hidden  style="max-width:120px;">
                        </label> </td>

                    <td width="10px"></td>
                </tr>
            </table>

            <table>

                <tr bgcolor="#ceccca" height="50px" >
                    <td width="3%" />
                    <td width="30%"><b>Taak</td>   
                    <td width="3%" />
                    <td width="15%"><b>Doelstelling</b></td>  
                    <td width="3%" />
                    <td width="3%"><b>Kern</b></td>
                    <td width="3%" />
                    <td width="7%"><b>Score</b></td>
                    <td width="3%" />
                    <td width="30%"><b>Commentaar</b></td>
                    <td width="3%" />
                </tr>

                <br/>
                <%--lijn met invoer --%>
                
                <tr height="20px" />

                <tr>
                    <td />
                    <td><select name="taak" id="formTaken1" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling"  id="formDoelstellingen1" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern" id="formKern1" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score" id="formScore1" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea" hidden id="formCommentaar1"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>

                <tr>
                    <td />
                    <td><select name="taak2" id="formTaken2" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling2"  id="formDoelstellingen2" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern2" id="formKern2" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score2" id="formScore2" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea2" hidden id="formCommentaar2"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>

                <tr>
                    <td />
                    <td><select name="taak3" id="formTaken3" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling3"  id="formDoelstellingen3" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern3" id="formKern3" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score3" id="formScore3" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea3" hidden id="formCommentaar3"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>

                <tr>
                    <td><br/></td><td><br/></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="button" width="25" value="+" id="addLine" hidden  onclick="laadLijn();" height="25" class="lijnButton" /></td>
                </tr>



            </table>
            <br>
            <br>
            <br>
            <br>

            <table class="doelstelling1" >

                <tr >
                    <td > <input type="submit"  value=" Formulier leeg maken " class="button"></td>
                    <td > <input type="submit"  value=" Print formulier  "  class="button"></td>
                    <td > <input type="submit"  value=" Bewaar formulier " class="button"></td>
                    <td > <input type="submit"  value=" Laad formulier " class="button"></td>
                </tr>

            </table>


        </p>
    </form>

</body>
</html>

