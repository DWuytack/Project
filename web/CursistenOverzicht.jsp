<%-- 
    Document   : CursistenOverzicht
    Created on : 8-mrt-2018, 11:27:41
    Author     : CURSIST
--%>
<%@ page language="java" 
         contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         import="model.Gebruiker"
         %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cursisten overzicht</title>
    </head>
    <body>

        <form action="GebruikersServlet">
            <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>

            <% if (gebruiker.getRol().equals("admin")) { %>

            <input type="submit" value="Cursist toevoegen" name="actie"/><br>
            <input type="submit" value="Cursist aanpassen" name="actie"/><br>
            <input type="submit" value="Cursist verwijderen"  name="actie"/><br>

            <% } %>

            <% if (gebruiker.getRol().equals("leerkracht")) { %>
            <input type="submit" value="Cursist toevoegen" name="actie"/><br>
            <input type="submit" value="Cursist aanpassen" name="actie"/><br>

            <% } %>

            <% if (gebruiker.getRol().equals("secretariaat")) { %>
            <input type="submit" value="Cursist toevoegen" name="actie"/><br>
            <input type="submit" value="Cursist aanpassen" name="actie"/><br>
            <% }%>

        </form>
    </body>
</html>
