<%-- 
    Document   : CursistenInschrijven
    Created on : 22-mei-2018, 11:07:08
    Author     : CURSIST
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
        <form method="post" action="CursistenInschrijvenServlet" >

            <section id="pagename">
                <h2> CursistenInschrijven </h2>
            </section>
            <hr>

            <table>
                <tr height="10px">
                <tr>
                    <td width="20px"></td>

                    <td>  
                        <select id="modules"
                                style="background: #f9f9f9"  class="drop"></select> 
                    </td>
                    
            </table>

        </form>

    </body>
</html>

