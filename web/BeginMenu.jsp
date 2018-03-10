<%-- 
    Document   : BeginMenu
    Created on : 8-mrt-2018, 9:34:57
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Startmenu</title>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/menu.css">
    </head>
    <body>
        <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
        <nav class="top-navigatie">
            <li id="afmelden">
                <a id="bt-afmelden" title="Afmelden">
                    <i class="material-icons">exit_to_app</i>
                </a>
            </li>
            <li id="profiel" tabindex="1">
                <a id="bt-profiel">
                    <i class="material-icons">person</i>
                    <span><%= gebruiker.getVoorNaam() + " " + gebruiker.getAchternaam() %></span>
                </a>
                <ul class="dropdown">
                    <li>
                        <a href="#">
                            <i class="material-icons">account_box</i><span>Profiel</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="material-icons">settings</i><span>Instellingen</span>
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
        <header>
            <!-- <h2>Score On Web</h2><br> -->
            <img src="images/LogoPCVO.png">
        </header>
        <section id="welkom">
            <p> Welkom, <%= gebruiker.getVoorNaam() %> ! </p>
        </section>
        <section id="nav-menu">    
            <form action="MenuServlet" id="main-menu">

                <% if (gebruiker.getRol().equals("admin")) { %>
                    <input type="submit" value="Overzicht gebruikers" name="actie"/><br>
                    <input type="submit" value="Overzicht doelstellingen" name="actie"/><br>
                    <input type="submit" value="Overzicht taken"  name="actie"/><br>
                    <input type="submit" value="Overzicht opleidingen" name="actie"/><br>
                    <input type="submit" value="Overzicht modules" name="actie"/><br>
                    <input type="submit" value="Overzicht scores" name="actie"/><br>
                    <input type="submit" value="Type score aanpassen" name="actie"/><br>
                <%} %>

                <% if (gebruiker.getRol().equals("leerkracht")) { %>
                    <input type="submit" value="Evaluatieformulieren" name="actie"/><br> 
                    <input type="submit" value="Overzicht cursisten" name="actie"/><br>
                    <input type="submit" value="Overzicht doelstellingen" name="actie"/><br>
                    <input type="submit" value="Overzicht taken"  name="actie"/><br>
                    <input type="submit" value="Overzicht opleidingen" name="actie"/><br>
                    <input type="submit" value="Overzicht modules" name="actie"/><br>
                <%}%>

                <% if (gebruiker.getRol().equals("cursist")) { %>
                    <input type="submit" value="Rapport" name="actie"/><br>
                <% } %>

                <% if (gebruiker.getRol().equals("secretariaat")) { %>
                    <input type="submit" value="Overzicht scores" name="actie"/><br>
                <%}%>
            </form>
        </section>
        <script>
            document.addEventListener("click", function(e){
                console.log(e.target.id);
                if(e.target.id === "bt-profiel") {
                    e.target.blur();
                    var loc = document.querySelector("#profiel");
                    if(!loc.className.includes("active"))
                        loc.className += " active";
                    else
                        loc.classList.remove("active");
                }
            });
        </script>
    </body>
</html>
