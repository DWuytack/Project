<%-- 
    Document   : BeginMenu
    Created on : 8-mrt-2018, 9:34:57
    Author     : CURSIST
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/menu.css">
        <title>Startmenu</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section id="welkom">
            <p> Welkom, <c:out value = "${sessionScope.currentSessionUser.voorNaam}" />! </p>
        </section>
        <section id="nav-menu">  
            <form action="MenuServlet">
                <div id="main-menu">

                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                        <input type="submit" value="Overzicht gebruikers" name="actie"><br>
                        <input type="submit" value="Overzicht studiegebieden" name="actie"><br>
                        <input type="submit" value="Overzicht scores klas" name="actie"><br>
                        <input type="submit" value="Overzicht scores cursist" name="actie"><br>
                        <input type="submit" value="Type score aanpassen" name="actie"><br>
                    </c:if>

                    <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                        <input type="submit" value="Evaluatieformulieren" name="actie"><br> 
                        <input type="submit" value="Cursisten inschrijven" name="actie"><br>
                        <input type="submit" value="Overzicht studiegebieden" name="actie"><br>
                        <input type="submit" value="Overzicht scores klas" name="actie"><br>
                        <input type="submit" value="Overzicht scores cursist" name="actie"><br>
                    </c:if>

                    <c:if test="${sessionScope.currentSessionUser.rol == 'cursist'}" >
                        <input type="submit" value="Rapport" name="actie"><br>
                        <input type="submit" value="Overzicht scores klas" name="actie"><br>
                        <input type="submit" value="Overzicht scores cursist" name="actie"><br>
                    </c:if>

                    <c:if test="${sessionScope.currentSessionUser.rol == 'secretariaat'}" >
                        <input type="submit" value="Overzicht scores" name="actie"><br>
                        <input type="submit" value="Overzicht gebruikers" name="actie"><br>
                        <input type="submit" value="Overzicht studiegebieden" name="actie"><br>
                        <input type="submit" value="Overzicht scores klas" name="actie"><br>
                        <input type="submit" value="Overzicht scores cursist" name="actie"><br>
                    </c:if>

                </div>
            </form>
        </section>
    </body>
</html>
