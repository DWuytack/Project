<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
    Author     : Gil
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="nl">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=0.8">
        <script src="js/jquery.js"></script>
        <script src="js/nav.js"></script>
        <script src="js/utilities2.js"></script>
        <!-- <script src="js/table.js"></script> -->
        <script src="js/gebruikersOverzicht2.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <!-- <link rel="stylesheet" href="css/formulier.css"> -->
        <link rel="stylesheet" href="css/gebruikersOverzicht.css">
        <title>Gebruikers overzicht</title>
        <link rel="stylesheet" href="css/overgang.css">
        <style>
            input[name="idEdit"].active, input[name="idOpslaan"].active {
                -webkit-filter: grayscale(100%);
                filter: grayscale(100%);
                pointer-events: none;
            }
            /* [name="Eerste"].inactive, [name="Vorige"].inactive, [name="Volgende"].inactive, [name="Laatste"].inactive */
        </style>
    </head>
    
    <body>
        <%@include file="Bovenbalk.jsp" %>
        
        <section id="pagename">
            <h2> GebruikersOverzicht </h2>        
        </section>
        
        <script>
            console.log("data");
            console.log('${sessionScope.lijstGebruikers[0].voorNaam}');
            console.log('${sessionScope.lijstGebruikers[0].gebruikerID}');
            console.log("json");
            console.log('${sessionScope.json}');
        </script>
        
        <section id="gebruikersOverzicht"></section>
        <section id="popup"></section>          
    </body>
</html>