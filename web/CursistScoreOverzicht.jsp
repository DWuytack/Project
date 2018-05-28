<%-- 
    Document   : CursistScoreOverzicht
    Created on : 28-mei-2018, 21:38:42
    Author     : Gil en Keanu
--%>

<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>    
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <script src="js/score.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        
        <title>Score overzicht per klas</title>
        <style>
            #nav-topke {
                width: auto;
            }     
        </style>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <form method ="POST" action="ScoreServlet?keuze=reset">
            <section id="pagename">
                <h2> Score overzicht per klas </h2> 
            </section>       
            <section>            
                <br>
                <div class="table-container">
                    <div class="table-nav-header">

                        <div>                           
                            <input type="image" name="Eerste" value="skip_previous" src='images/skip_previous.png'> 
                            <input type="image" name="Vorige" value="fast_rewind" src='images/fast_rewind.png'>  
                            <input type="image" name="Volgende" value="fast_forward" src='images/fast_forward.png'> 
                            <input type="image" name="Laatste" value="skip_next" src='images/skip_next.png'>
                        </div>
                    </div>
                    <br><br>
                    <table id="nav-topke">
                        <tr>
                            <td>
                                <select name="schooljaar" id="schooljaar" style="background: #f9f9f9">
                                    <option  selected disabled> Kies schooljaar.. </option>
                                    <c:forEach items="${schooljaren}" var="schooljaar">
                                        <option> ${schooljaar.schooljaar} </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td width="10px"></td>
                            <td>
                                <select name="semester" id="semester" onchange="checkJaar()" style="background: #f9f9f9" class="drop" >" >
                                    <option selected disabled> Semester... </option>
                                    <c:forEach items="${semesters}" var="semester">                  
                                        <option> ${semester.semester} </option>                     
                                    </c:forEach>
                                </select>
                            </td>
                            <td width="10px"></td>
                            <!-- Studiegebied kiezer -->
                            <td>
                                <select name="studiegebied" id="studiegebied"  
                                        onchange="laadDropdown('opleidingen')" 
                                        style="background: #efc4c4"  class="drop" hidden>
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
                            
                             <td>  
                                <select id="cursisten" hidden onchange="laadDoelstellingen()"
                                        style="background: #f9f9f9"  class="drop"></select> 
                            </td>
                            <td width="10px"></td>
                        </tr>
                    </table>
                    <br><br>
                    <table>
                    <!-- Titelbalk-->
                    <tr height="20px" />
                    <tr bgcolor="#ceccca" height="50px" >
                        <td width="3%" />
                        <td width="40%"><b>Doelstelling</td>   
                        <td width="3%" />
                        <td><b>Kern</b></td>
                        <td width="3%" />
                        <td width="6%"><b>Score</b></td>
                        <td width="8%" />
                    </tr>
                    <tr height="20px" />
                </table>

                <table id="evaluatieTable">

                    <tr id="firstRow">
                        <td width="3%" />
                        <td width="40%"/>
                        <td width="3%" />
                        <td/>
                        <td width="3%" />
                        <td width="6%"/>
                        <td width="8%" />
                    </tr>
                </div>
            </section>
        </form>
    </body>
</html>
