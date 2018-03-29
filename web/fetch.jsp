<%-- 
    Document   : fetch
    Created on : 29-mrt-2018, 12:58:26
    Author     : Jens
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery.js"></script>
    </head>
    <body>
        <!-- Dit is een test pagina -->
        <div id="lol"></div>
        <form action="GebruikersServlet">
            <c:forEach items="${lijstGebruikers}" var="cursist">
                <script>
                var cursist = {
                    achternaam: "${cursist.achternaam}",
                    voorNaam:  "${cursist.voorNaam}",
                    login: "${cursist.login}"
                };
                document.getElementById("lol").innerHTML = JSON.stringify(cursist, null, 4);
                </script>
            </c:forEach>
                
        </form>
        
        <style>
            #testje {
                font-family: 'Raleway2';
                font-size: 50px;
                position: relative;
            }
            #testje > p {
                position: absolute;
                top: 0;
                left: 0;
            }
            #testje p:nth-child(1) {
                font-weight: 800;
                top: 100px;
            }
            #testje p:nth-child(2) {
                font-family: 'Raleway3';
                top: 100px;
            }
        </style>
        
        <div id="testje">
        <p>railways</p>
        <p>railways</p>
        <p>railways</p>
        <p>railways</p>
        </div>
        
    </body>
</html>