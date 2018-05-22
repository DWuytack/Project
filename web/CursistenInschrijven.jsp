<%-- 
    Document   : CursistenInschrijven
    Created on : 22-mei-2018, 11:07:08
    Author     : Aaron
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/evaluatie.js"></script>
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>CursistenInschrijven</title>
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <form method="post" action="CursistenInschrijvenServlet?keuze=reset" >

            <section id="pagename">
                <h2> CursistenInschrijven </h2>
            </section>

            <td width="70px"></td>

            <td width="10px"></td>

            <table>

                <td> <select name="module" id="module"
                             style="background: #f9f9f9" class="drop" >
                        <option selected disabled > Module... </option>
                        <c:forEach items="${modules}" var="module">                  
                            <option> ${module.naam} </option>                     
                        </c:forEach>
                            <option> Voeg Module toe... </option>
                    </select> </td>

            </table>

        </form>

    </body>
</html>

