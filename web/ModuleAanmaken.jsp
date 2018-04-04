<%-- 
    Document   : ModuleAanmaken
    Created on : 16-mrt-2018, 11:07:35
    Author     : Davino
--%>

<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Module"
         import="model.Opleiding"
         %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/nav.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>ModuleAanmaken</title>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        <section>
            <h2>Module Aanmaken</h2>
            
              <form action="ModuleServlet" method="post">
                <table>
                    <tr>
                        <td><label>naam: </label></td>
                        <td><input type="text" name="naam" size="16" maxlength="30"></td>
                    </tr>
                </table>
                <br>
                <input type="submit" name="actie" value="toevoegen">
            </form>
        </section>
    </body>
</html>
