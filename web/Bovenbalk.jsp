<%-- 
    Document   : Bovenbalk
    Created on : 15-mrt-2018, 20:37:51
    Author     : Dirk
--%>

<%@ page language="java"
         import="model.Gebruiker"
         %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
         <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <nav class="top-navigatie">
            <li id="afmelden">
                <a id="bt-afmelden" title="Afmelden">
                    <i class="material-icons">exit_to_app</i>
                </a>
            </li>
            <img id="logo" src="images/LogoPCVO.png" width="165" height="50">
            <li id="profiel" tabindex="1">
                <a id="bt-profiel">
                    <i class="material-icons">person</i>
                </a>
                <ul class="dropdown">
                    <li>
                        <a id="bt-naam">
                            <i class="material-icons">account_box</i><span><%= gebruiker.getVoorNaam() + " " + gebruiker.getAchternaam()%></span>
                        </a>
                    </li>
                    <li>
                        <a href="Profiel.jsp" title="profiel">
                            <i class="material-icons">settings</i><span>Profiel</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="material-icons">exit_to_app</i><span>Afmelden</span>
                        </a>
                    </li>
                </ul>

            </li>
        </nav>
        <br><br><br>
    </body>
</html>
