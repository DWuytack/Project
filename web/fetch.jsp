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
        
    </body>
</html>