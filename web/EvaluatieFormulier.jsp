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
            <h1 align="center" class="kleur"> EvaluatieFormulier </h1>
            <hr>

            <table class="doelstelling1">
                <tr>
                    <th> </th>
                    <th><a>Datum</a></th>
                    <th></th>
                    <th><a>Jaar</a></th>
                    <th></th>
                    <th ><a>Semester</a</th>
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

                    <td> <input type="date" name="datum" style="font-size: 0.9rem"   > </td>

                    <td width="10px"></td>

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
                    <td width="10px"></td>

                    <td> <select name="Semester">
                            <option value="Eerste Semester">Eerste Semester</option>
                            <option value="Tweede Semester">Tweede Semester</option> 
                        </select> </td>

                    <td width="10px"></td>

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

                    <td width="10px"></td>

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

                    <td width="10px"></td>

                    <td>  <select name="cursist">
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
                    <td><select name="cursist">
                            <c:forEach items="${doelstellingen}" var="doelstelling">
                                <option value="Banket AA">${doelstelling.naam}</option>
                            </c:forEach>
                            <option value="Banket AA">Maak een nieuwe doelstelling aan...</option>

                        </select></td>

                    <td></td>
                    <td><select name="cursist">
                            <option value="Banket AA">Taak 1</option>
                            <option value="Banket AA">Taak 2</option>
                            <option value="Banket AA">Taak 3</option>
                            <option value="Banket AA">Taak 4</option>
                            <option value="Banket AA">Maak een nieuwe taak aan...</option>
                        </select></td></td>
                    <td class="center"><select name="cursist" value="">

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

