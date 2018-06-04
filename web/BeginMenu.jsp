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
        <style>
            input[type="submit"] { 
                font-family: Raleway; /*Force Elem Bug */

                position: relative;
                padding: 10px;

                background-color: #ec6e24;

                font-size: 30px;
                text-align: center;
                color: #fff;
                font-weight: 600;
                text-shadow: 0 0 150px #000;

                background-image: url(../images/bg-chef3.png);
                background-blend-mode: soft-light;
                background-size: 250px;

                text-align: center;
            }
            
            input[type="button"]:focus, input[type="submit"]:focus {
                filter: brightness(75%);
            }
        </style>
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
                        <input type="submit" value="Cursisten inschrijven" name="actie"><br>
                        <input type="submit" value="Overzicht studiegebieden" name="actie"><br>
                        <input type="submit" value="Overzicht scores klas" name="actie"><br>
                        <input type="submit" value="Overzicht scores cursist" name="actie"><br>
                        <input type="submit" value="Type score aanpassen" name="actie"><br>
                        <input type="submit" value="Taken beheren" name="actie"><br>
                        <input type="submit" value="Doelstellingen beheren" name="actie"><br>
                        <input type="submit" value="Studiegebieden beheren" name="actie"><br>
                        <input type="submit" value="Opleidingen beheren" name="actie"><br>
                        <input type="submit" value="Modules beheren" name="actie"><br>
                       
                    </c:if>

                    <c:if test="${sessionScope.currentSessionUser.rol == 'leerkracht'}" >
                        <input type="submit" value="Evaluatieformulieren" name="actie"><br> 
                        <input type="submit" value="Cursisten inschrijven" name="actie"><br>
                        <input type="submit" value="Overzicht studiegebieden" name="actie"><br>
                        <input type="submit" value="Overzicht scores klas" name="actie"><br>
                        <input type="submit" value="Overzicht scores cursist" name="actie"><br>
                        <input type="submit" value="Taken beheren" name="actie"><br>
                        <input type="submit" value="Doelstellingen beheren" name="actie"><br>
                    </c:if>

                    <c:if test="${sessionScope.currentSessionUser.rol == 'cursist'}" >
                        <input type="submit" value="Rapport" name="actie"><br>
                    </c:if>

                    <c:if test="${sessionScope.currentSessionUser.rol == 'secretariaat'}" >
                        <input type="submit" value="Overzicht scores" name="actie"><br>
                        <input type="submit" value="Cursisten inschrijven" name="actie"><br>
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
