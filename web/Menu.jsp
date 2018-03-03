<%-- 
    Document   : userLogged
    Created on : 16-feb-2018, 14:06:33
    Author     : Dirk
--%>

<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="model.Gebruiker"
         %>

<!DOCTYPE html>

<html>

    <head>
    
        <title>   User Logged Successfully   </title>
         <link rel="stylesheet" href="css/menu.css">
    </head>

    <body>

    <center>
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
   
        <h1> Welkom  <%= gebruiker.getVoorNaam() + "!"%> </h1>
        <p>Rol: <%=gebruiker.getRol() %> <br>
            Naam: <%=gebruiker.getVoorNaam() + " " + gebruiker.getAchternaam() %> <br>
            GeboorteDatum: <%=gebruiker.getGeboorteDatum() %> <br>
         Email: <%=gebruiker.getEmail() %> </p>
        
    </center>

</body>

</html>
