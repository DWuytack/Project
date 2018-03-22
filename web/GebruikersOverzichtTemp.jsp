<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
    Author     : Gil
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/gebruikers.css">
        <title>Gebruikers overzicht</title>
    </head>
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <form action="GebruikersServlet">
                <div class="table-container">
                    <table class="datatable">
                        <thead>
                            <tr>
                                <th onclick="sortTable(0)"><a>Achternaam</a></th>
                                <th onclick="sortTable(1)"><a>Voornaam</a></th>
                                <th onclick="sortTable(2)"><a>GeboorteDatum</a></th>
                                <th onclick="sortTable(3)"><a>E-mail</a></th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lijstGebruikers}" var="cursist">
                                <tr>
                                    <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                        <td> <input type="text" name="achternaam" size="25" maxlength="25" value="${cursist.achternaam}"> </td>
                                        <td> <input type="text" name="voornaam" size="25" maxlength="25" value="${cursist.voorNaam}"> </td>
                                        <td> <input type="text" name="geboorteDatum" size="25" maxlength="25" value="<fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" />"> </td>
                                        <td> <input type="text" name="email" size="25" maxlength="25" value="${cursist.email}"> </td>
                                    </c:if>

                                    <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                        <td> ${cursist.achternaam} </td>
                                        <td> ${cursist.voorNaam} </td>
                                        <td> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yy" /> </td>
                                        <td> ${cursist.email} </td>
                                    </c:if>

                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <td class="actie">
                                            <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src='images/pencil.png'>
                                            <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png'>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <br>

                <script>
                    function sortTable(n) {
                      var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                      table = document.querySelector(".datatable");
                      switching = true;
                      // Set the sorting direction to ascending:
                      dir = "asc"; 
                      /* Make a loop that will continue until
                      no switching has been done: */
                      while (switching) {
                        // Start by saying: no switching is done:
                        switching = false;
                        rows = table.getElementsByTagName("TR");
                        /* Loop through all table rows (except the
                        first, which contains table headers): */
                        for (i = 1; i < (rows.length - 1); i++) {
                          // Start by saying there should be no switching:
                          shouldSwitch = false;
                          /* Get the two elements you want to compare,
                          one from current row and one from the next: */
                          x = rows[i].getElementsByTagName("TD")[n];
                          y = rows[i + 1].getElementsByTagName("TD")[n];
                          /* Check if the two rows should switch place,
                          based on the direction, asc or desc: */
                          if (dir == "asc") {
                            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                              // If so, mark as a switch and break the loop:
                              shouldSwitch= true;
                              break;
                            }
                          } else if (dir == "desc") {
                            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                              // If so, mark as a switch and break the loop:
                              shouldSwitch= true;
                              break;
                            }
                          }
                        }
                        if (shouldSwitch) {
                          /* If a switch has been marked, make the switch
                          and mark that a switch has been done: */
                          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                          switching = true;
                          // Each time a switch is done, increase this count by 1:
                          switchcount ++; 
                        } else {
                          /* If no switching has been done AND the direction is "asc",
                          set the direction to "desc" and run the while loop again. */
                          if (switchcount == 0 && dir == "asc") {
                            dir = "desc";
                            switching = true;
                          }
                        }
                      }
                    }
                </script>

                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                    <input type="submit" value="Gebruiker toevoegen" name="actie"/><br>
                </c:if>

                <c:if test="${sessionScope.currentSessionUser.rol == 'secretariaat'}" >
                    <input type="submit" value="Gebruiker toevoegen" name="actie"/><br>
                </c:if>
            </form>
        </section>
    </body>
</html>