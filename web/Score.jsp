<%-- 
    Document   : Score
    Created on : 8-mrt-2018, 11:04:02
    Author     : Keanu Pestka
--%>

<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>    
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/gebruikers.css">
        <title>Score overzicht</title>
    </head>
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="ScoreServlet">
                <br>
                <div class="table-container">
                    <div class="table-nav-header">

                        <table >
                            <tr >
                                <td style="background-color: rgba(0,0,0,0);" width="5%"> <img src='images/searchUser.png' > </td>

                                <!-- béta -->
                                <!-- <div style="position: relative;">
                                    <i name="gebruiker zoeken" class="material-icons" style="font-size: 50px;">person</i>
                                    <i name="gebruiker zoeken" class="material-icons" style="
                                       font-size: 25px; position: absolute; bottom: 11px; right: 0; color: #ec6e24; transform: scale(-1, 1);
                                       
                                       ">search</i>
                                </div>
                                -->
                                <td> <input type="text" name="zoekterm" value="" size="15"> </td>
                                <td> </td>
                                <td> </td>
                                <td> </td>
                                <td> </td>
                                <td  align="right"> <center> Totaal aantal gebruikers: <br><br> ${sessionScope.getoondeGebruikers}/${sessionScope.aantalRecords} </center></td>

                            <tr>
                        </table>
                    </div>
                    <br><br>
                    <table>
                        <tr>
                            <td> <select name="Schooljaar">
                                    <option value="2018-2019">2018-2019</option>
                                    <option value="2019-2020">2019-2020</option>
                                    <option value="2020-2021">2020-2021</option>
                                    <option value="2021-2022">2021-2022</option>
                                    <option value="2022-2023">2022-2023</option>
                                    <option value="2023-2024">2023-2024</option>
                                    <option value="2024-2025">2024-2025</option>
                                    <option value="2025-2026">2025-2026</option>
                                    <option value="2026-2027">2026-2027</option>
                                    <option value="2027-2028">2027-2028</option>
                                    <option value="2028-2029">2028-2029</option>
                                    <option value="2029-2030">2029-2030</option>
                                </select> </td>

                            <td><select name="Opleiding">
                                    <option value="Banketbakker">Banketbakker</option>
                                    <option value="Hotelbedrijf">Hotelbedrijf</option>
                                    <option value="Hulpkok">Hulpkok</option>
                                    <option value="Keukenverantwoordelijke">Keukenverantwoordelijke</option>
                                    <option value="Kok">Kok</option>
                                    <option value="Medewerker brasserie-taverne-bistro">Medewerker brasserie-taverne-bistro</option>
                                    <option value="Traiteur-banketaannemer">Traiteur-banketaannemer</option>
                                    <option value="Traiteurkok">Traiteurkok</option>
                                    <option value="Chocoladebewerker">Chocoladebewerker</option>
                                </select> </TD>

                            <td>  <select name="Module">
                                    <option value="Banket AA">Banket AA</option>
                                    <option value="Beslag en cakes AA">Beslag en cakes AA</option>
                                    <option value="Stukgebak AA">Stukgebak AA</option>
                                    <option value="Beslagen A">Beslagen A</option>
                                    <option value="Best of nagerechten AA">Best of nagerechten AA</option>
                                    <option value="Buitelandse keuken AA">Buitelandse keuken AA</option>
                                    <option value="Dagschotels met vis AA">Dagschotels met vis AA</option>
                                    <option value="Dessert op bord AA">Dessert op bord AA</option>
                                    <option value="Dienst aan de kaart AA">Dienst aan de kaart AA</option>
                                    <option value="Gastronomisch koken">Gastronomisch koken</option>
                                    <option value="Initatie snacks">Initatie snacks</option>
                                    <option value="Koude Keuken AA">Koude Keuken AA</option>
                                    <option value="Nagerechten AA">Nagerechten AA</option>
                                    <option value="Onthaal en interactie AA">Onthaal en interactie AA</option>
                                    <option value="Steekgerechten AA">Steekgerechten AA</option>
                                    <option value="Vegetarisch koken BB">Vegetarisch koken BB</option>
                                    <option value="Visgerechten AA">Visgerechten AA</option>
                                    <option value="Bewust gezond koken herfst/winter om de 14 dagen">Bewust gezond koken herfst/winter om de 14 dagen</option>
                                    <option value="Initatie buitelandse keuken A - om de 14 dagen">Initatie buitelandse keuken A - om de 14 dagen</option>
                                    <option value="Vegetarisch koken A om de 14 dagen">Vegetarisch koken A om de 14 dagen</option>
                                    <option value="Best of nagerechten A">Best of nagerechten A</option>
                                    <option value="Buitelandse keuken A">Buitelandse keuken A</option>
                                    <option value="Dagschotels met vlees en gevogelte A">Dagschotels met vlees en gevogelte A</option>
                                    <option value="Dienst aan de kaart A">Dienst aan de kaart A</option>
                                    <option value="Gastronomisch Snacks">Gastronomisch Snacks</option>
                                    <option value="Initatie snacks">Initatie snacks</option>
                                    <option value="Koken voor cursisten met een beperking">Koken voor cursisten met een beperking</option>
                                    <option value="Koude keuken A">Koude keuken A</option>
                                    <option value="Nagerechten A">Nagerechten A</option>
                                    <option value="Onthaal en interactie A">Onthaal en interactie A</option>
                                    <option value="Start to cook A(initatie warme keuken)">Start to cook A(initatie warme keuken)</option>
                                    <option value="Vleesgerechten A">Vleesgerechten A</option>
                                    <option value="Koude Keuken AA">Koude Keuken AA</option>
                                    <option value="Koude Keuken A">Koude Keuken A</option>
                                    <option value="Start to cook A (initiatie warme keuken)">Start to cook A (initiatie warme keuken)</option>
                                    <option value="Initiatie buitenlandse keuken AA">Initiatie buitenlandse keuken AA</option>
                                    <option value="Initiatie buitenlandse keuken AA">Initiatie buitenlandse keuken AA</option>
                                    <option value="Koude Keuken AA">Koude Keuken AA</option>
                                    <option value="Onthaal en interactie AA">Onthaal en interactie AA</option>
                                    <option value="Initiatie buitenlandse keuken A - om de 14 dagen">Initiatie buitenlandse keuken A - om de 14 dagen</option>
                                    <option value="Koude Keuken A">Koude Keuken A</option>
                                    <option value="Onthaal en interactie A">Onthaal en interactie A</option>
                                    <option value="Start to cook A (initiatie warme keuken)">Start to cook A (initiatie warme keuken)</option>
                                    <option value="Snack tearoom 1">Snack tearoom 1</option>
                                    <option value="Snack tearoom 2">Snack tearoom 2</option>
                                    <option value="Snack tearoom ">Snack tearoom </option>
                                    <option value="Recepties en ontvangsten">Recepties en ontvangsten</option>
                                    <option value="Initiatie buitenlandse keuken A - om de 14 dagen">Initiatie buitenlandse keuken A - om de 14 dagen</option>
                                    <option value="Koude en warme buffetten A">Koude en warme buffetten A</option>
                                    <option value="Onthaal en interactie A">Onthaal en interactie A</option>
                                    <option value="Traiteur">Traiteur</option>
                                    <option value="Recepties en ontvangsten">Recepties en ontvangsten</option>
                                    <option value="Koude en warme buffetten A">Koude en warme buffetten A</option>
                                    <option value="Traiteur">Traiteur</option>
                                    <option value="Eenvoudige chocoladeproducten">Eenvoudige chocoladeproducten</option>
                                </select> </td>

                            <td> <select name="Semester">
                                    <option value="Eerste Semester">Eerste Semester</option>
                                    <option value="Tweede Semester">Tweede Semester</option> 
                                </select> </td>
                        </tr>
                    </table>
                    <br> <br>
                    <table class="datatable">
                        <thead> 
                            <tr>
                                <th onclick="sortTable(0)"><a>Taak</a></th>
                                <th onclick="sortTable(1)"><a>Beschrijving</a></th>
                                <th onclick="sortTable(2)"><a>Score</a></th>
                                <th onclick="sortTable(3)"><a>Module</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <th>Actions</th>
                                    </c:if>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                                    <th>Actions</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:forEach items="${lijstScore}" var="score">
                                <tr>
                                    <c:if test="${score.scoreID == sessionScope.editID}" >
                                        <td> <input type="text" name="score" size="15" maxlength="25" value="${score}"> </td>
                                        <td> <input type="text" name="score_beschrijving" size="15" maxlength="25" value="${score.beschrijving}"> </td>
                                        </c:if>

                                    <c:if test="${score.scoreID != sessionScope.editID}" >
                                        <td> ${score} </td>
                                        <td> ${score_beschrijving} </td>
                                    </c:if>


                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td>
                                            <c:if test="${score.scoreID != sessionScope.editID}" >
                                                <input type="image"  name="scoreEdit" value="${score.scoreID}" src='images/pencil.png'>
                                                <input type="image"  name="scoreDelete" value="${score.scoreID}" src='images/vuilbak.png'>
                                            </c:if>
                                            <c:if test="${score.scoreID == sessionScope.editID}" >
                                                <input type="image"  name="scoreSave" value="${score.scoreID}" src='images/green.png'>
                                                <input type="image"  name="scoreCancel" value="${score.scoreID}" src='images/cancel.png'>
                                            </c:if>
                                        </td>
                                    </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                                        <td>
                                            <c:if test="${score.scoreID != sessionScope.editID}" >
                                                <input type="image"  name="scoreEdit" value="${score.scoreID}" src='images/pencil.png'>
                                                <input type="image"  name="scoreDelete" value="${score.scoreID}" src='images/vuilbak.png'>
                                            </c:if>
                                            <c:if test="${score.scoreID == sessionScope.editID}" >
                                                <input type="image"  name="scoreSave" value="${score.scoreID}" src='images/green.png'>
                                                <input type="image"  name="scoreCancel" value="${score.scoreID}" src='images/cancel.png'>
                                            </c:if>
                                        </td>
                                    </c:if>

                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>
                    <br><br>
                    <table>
                        <tr style="background-color:rgba(0, 0, 0, 0);"> 
                            <td style="text-align:center;">   
                                <div>
                                    <input type="image"  name="Eerste" value="Eerste" src='images/eerste.png'> 
                                    <input type="image"  name="Vorige" value="Eerste" src='images/terug.png'>  
                                    <input type="image"  name="Volgende" value="Volgende" src='images/volgende.png'> 
                                    <input type="image"  name="Laatste" value="Laatste" src='images/laatste.png'>
                                </div>
                            </td> 
                        </tr>
                        <tr> 
                            <td colspan="3" style="text-align:center;">   
                                <input type="image"  name="score toevoegen" value="score toevoegen" src='images/adduser.png'> 
                            </td> 
                        </tr>
                    </table>
            </form>    
        </section>
    </body>
</html>
