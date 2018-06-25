<%-- 
    Document   : Gebruiker
    Created on : 8-mrt-2018, 11:16:41
    Author     : Gil
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/jquery.js"></script>
           <script src="js/gebruikersOverzicht.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <title>Gebruikers overzicht</title>
        <link rel="stylesheet" href="css/gebruikersOverzicht.css">
    </head>

    <body>
        <%@include file="Bovenbalk.jsp" %>
        <!-- styling verplaatst naar theme.css "SUBNAV" -->
        <section id="pagename">
            <h2> GebruikersOverzicht </h2>
            <!-- <h1 align="center" class="kleur" style="display: inherit;"> GebruikersOverzicht </h1> -->
        </section>
        
                <table class="overzichtTable" id="overzichtTable">                   
                    <tr height="20px" />
                    <tr bgcolor="#ceccca" height="50px">
                        <th width="2%" />
                        <th width="12%" class="links"><b>Voornaam</b></th>   
                        <th width="2%" />
                        <th width="12%" class="links"><b>Achternaam</b></th>  
                        <th width="2%" />
                        <th width="12%" class="links"><b>Login</b></th>
                        <th width="2%" />
                        <th width="8%" class="links"><b>Rol</b></th>
                        <th width="2%" />
                        <th width="9%" class="links"><b>GebtDatum</b></th>
                        <th width="2%" />
                        <th width="24%" class="links"><b>E-mail</b></th>
                        <th width="2%" />
                        <th width="7%" class ="links"><b>Acties</b></th>
                         <th width="2%" />
                    </tr>
                    <tr height="20px" />
                </table>
        
          <table class="doelstelling1" >
                    <tr height="50px" />
                    <tr >
                        <td > <input type="button"  value=" Gebruiker toevoegen "
                                     class="evalButton" onclick="gebruikerToevoegen();"   id="buttonToevoeg" ></td>

                    </tr>
                </table>
     
    </body>
</html>
