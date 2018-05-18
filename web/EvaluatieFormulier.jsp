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
        <form method="post" action="EvaluatieFormulierServlet?keuze=reset" >
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
                    <td width="20%"><b>Taak</td>   
                    <td width="3%" />
                    <td width="40%"><b>Doelstelling</b></td>  
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
          
                    <tr class="taakScore1" hidden><td><br/></tr>
                    <tr class="taakScore1" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>
                
                <tr  id="lijn1" hidden><td><br/></td><td><br/></td></tr>
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
                <tr><td><br/></tr>
                <tr class="taakScore2" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr class="taakScore2" hidden><td><br/></td><td><br/></td></tr>
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
                <tr><td><br/></tr>
                <tr class="taakScore3" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore3" hidden><td><br/></td><td><br/></td></tr>

                <tr>
                    <td />
                    <td><select name="taak4" id="formTaken4" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling4"  id="formDoelstellingen4" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern4" id="formKern4" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score4" id="formScore4" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea4" hidden id="formCommentaar4"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore4" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore4" hidden><td><br/></td><td><br/></td></tr>

                <tr>
                    <td />
                    <td><select name="taak5" id="formTaken5" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling5"  id="formDoelstellingen5" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern5" id="formKern5" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score5" id="formScore5" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea5" hidden id="formCommentaar5"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore5" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore5" hidden><td><br/></td><td><br/></td></tr>


                <tr>
                    <td />
                    <td><select name="taak6" id="formTaken6" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling6"  id="formDoelstellingen6" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern6" id="formKern6" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score6" id="formScore6" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea6" hidden id="formCommentaar6">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                
                <tr class="taakScore6" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore6" hidden><td><br/></td><td><br/></td></tr>

                <tr>
                    <td />
                    <td><select name="taak7" id="formTaken7" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling7"  id="formDoelstellingen7" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern7" id="formKern7" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score7" id="formScore7" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea7" hidden id="formCommentaar7"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore7" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>


                <tr  class="taakScore7" hidden><td><br/></td><td><br/></td></tr>

                <tr>
                    <td />
                    <td><select name="taak8" id="formTaken8" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling8"  id="formDoelstellingen8" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern8" id="formKern8" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score8" id="formScore8" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea8" hidden id="formCommentaar8"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore8" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore8" hidden><td><br/></td><td><br/></td></tr>

                <tr>
                    <td />
                    <td><select name="taak9" id="formTaken9" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling9"  id="formDoelstellingen9" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern9" id="formKern9" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score9" id="formScore9" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea9" hidden id="formCommentaar9"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                 <tr><td><br/></tr>
                <tr class="taakScore9" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>
                <tr  class="taakScore9" hidden><td><br/></td><td><br/></td></tr>

                <tr>
                    <td />
                    <td><select name="taak10" id="formTaken10" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling10"  id="formDoelstellingen10" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern10" id="formKern10" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score10" id="formScore10" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea10" hidden id="formCommentaar10"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore10" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore10" hidden><td><br/></td><td><br/></td></tr>

                <tr>
                    <td />
                    <td><select name="taak11" id="formTaken11" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling11"  id="formDoelstellingen11" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern11" id="formKern11" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score11" id="formScore11" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea11" hidden id="formCommentaar11"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore11" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore11" hidden><td><br/></td><td><br/></td></tr>

                <tr>
                    <td />
                    <td><select name="taak12" id="formTaken12" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling12"  id="formDoelstellingen12" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern12" id="formKern12" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score12" id="formScore12" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea12" hidden id="formCommentaar12"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore12" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore12" hidden><td><br/></td><td><br/></td></tr>


                <tr>
                    <td />
                    <td><select name="taak13" id="formTaken13" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling13"  id="formDoelstellingen13" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern13" id="formKern13" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score13" id="formScore13" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea13" hidden id="formCommentaar13"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore13" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr class="taakScore13" hidden><td><br/></td><td><br/></td></tr>


                <tr>
                    <td />
                    <td><select name="taak14" id="formTaken14" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling14"  id="formDoelstellingen14" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern14" id="formKern14" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score14" id="formScore14" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea14" hidden id="formCommentaar14"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>
                <tr><td><br/></tr>
                <tr class="taakScore14" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore14" hidden><td><br/></td><td><br/></td></tr>


                <tr>
                    <td />
                    <td><select name="taak15" id="formTaken15" hidden  onchange="laadFormDoelstellingen()" ></select></td>
                    <td />

                    <td><label name="doelstelling15"  id="formDoelstellingen15" hidden disabled >
                        </label></td>
                    <td />

                    <td style="text-align: center"><label name="kern15" id="formKern15" hidden disabled ></label> </td>
                    <td />

                    <td><select name="score15" id="formScore15" hidden ></select></td>
                    <td />

                    <td><textarea name="textarea15" hidden id="formCommentaar15"
                                  rows="3" cols="35">Vul hier je commentaar in...</textarea></td>
                    <td />

                </tr>

                <tr><td><br/></tr>
                <tr class="taakScore15" hidden> <td></td> <td></td> <td></td><td> <b>TaakScore: </b></td> </tr>

                <tr  class="taakScore15" hidden><td><br/></td><td><br/></td></tr>



                <tr>
                    <td></td>
                    <td><input type="button" width="25" value="+" id="addLine" hidden  onclick="laadLijn();" height="25" class="lijnButton" /></td>
                </tr>



            </table>

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

