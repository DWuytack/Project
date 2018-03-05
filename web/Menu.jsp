<%-- 
    Document   : index
    Created on : 5-mrt-2018, 9:45:49
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
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <header></header>
        <nav class="active">
            <div id="menu">
                <ul id="main-menu" class="flex-container wrap">
                    <li><a>Evaluaties</a></li>
                    <li><a>Cursisten</a></li>
                    <li><a>Doelstellingen</a></li>
                    <li><a>Taken</a></li>
                    <li><a>Opleidingen</a></li>
                    <li><a>Gebruikers</a></li>
                </ul>
            </div>
            <% Gebruiker gebruiker = (Gebruiker) (session.getAttribute("currentSessionUser"));%>
            <div id="bg-menu">
                <div id="menu-buttons">
                    <a id="bt-logo">
                        <!-- <img src="logo%20scheldeland.png" alt="logo"> -->
                    </a>
                    <a id="bt-profile">
                        <i class="material-icons">person</i>
                        <span>
                            <%=gebruiker.getVoorNaam() + " " + gebruiker.getAchternaam() %>
                        </span>
                    </a>
                </div>
                <a id="bt-menu"></a>
            </div>

        </nav>
        <main>
            <h2>Welkom</h2>
            <p>
                Welkom bij het evaluatie programma.

                Klik op ☰ om verder te gaan.
            </p>
        </main>
        <footer></footer>
        <script>
            document.addEventListener("click", function(e){
                if(e.target.id === "bt-menu" || e.target.alt === "logo") {
                    var loc = document.querySelector("nav");
                    if(!loc.className.includes("active"))
                        loc.className += " active";
                    else
                        loc.classList.remove("active");
                }
            });
        </script>
    </body>
</html>