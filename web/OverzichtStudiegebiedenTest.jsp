<%-- 
    Document   : OverzichtStudiegebiedenTest
    Created on : Apr 16, 2018, 11:08:18 PM
    Author     : Ewout Phlips
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/studiegebieden.css">
        <link rel="stylesheet" href="css/theme.css">
        <title>JSP Page</title>
    </head>
    <body>

        <%@include file="Bovenbalk.jsp" %>

        <section id="pagename">
            <h2> Studiegebieden </h2>
        </section>

        <div class="flex-container">
            
            <div style="flex-grow: 1">
                <table id="Table">
                    <tr><th>Studiegebieden</th></tr>
                    <tr><td>Testgebied 1</td></tr>
                    <tr><td>Testgebied 1</td></tr>
                    <tr><td>Testgebied 1</td></tr>
                    <tr><td>Testgebied 1</td></tr>
                    <tr><td>Testgebied 1</td></tr>
                    <tr><td>Testgebied 1</td></tr>
                </table>
            </div>
            
            <div style="flex-grow: 1">
                <table id="Table">
                    <tr><th>Opleidingen</th></tr>
                    <tr><td>Opleiding 1</td></tr>
                    <tr><td>Opleiding 1</td></tr>
                    <tr><td>Opleiding 1</td></tr>
                    <tr><td>Opleiding 1</td></tr>
                    <tr><td>Opleiding 1</td></tr>
                    <tr><td>Opleiding 1</td></tr>
                </table>
            </div>
            
            <div style="flex-grow: 1">
                <table id="Table">
                    <tr><th>Modules</th></tr>
                    <tr><td>Module 1</td></tr>
                    <tr><td>Module 1</td></tr>
                    <tr><td>Module 1</td></tr>
                    <tr><td>Module 1</td></tr>
                    <tr><td>Module 1</td></tr>
                    <tr><td>Module 1</td></tr>
                </table>
            </div>
            
            <div style="flex-grow: 1">
                <table id="Table">
                    <tr><th>Doelstellingen</th></tr>
                    <tr><td>Doelstelling 1</td></tr>
                    <tr><td>Doelstelling 1</td></tr>
                    <tr><td>Doelstelling 1</td></tr>
                    <tr><td>Doelstelling 1</td></tr>
                    <tr><td>Doelstelling 1</td></tr>
                    <tr><td>Doelstelling 1</td></tr>
                </table>
            </div>
            
            <div style="flex-grow: 1">
                <table id="Table">
                    <tr><th>Taken</th></tr>
                    <tr><td>Taak 1</td></tr>
                    <tr><td>Taak 1</td></tr>
                    <tr><td>Taak 1</td></tr>
                    <tr><td>Taak 1</td></tr>
                    <tr><td>Taak 1</td></tr>
                    <tr><td>Taak 1</td></tr>
                </table>    
            </div>
            
        </div>
    </body>
</html>
