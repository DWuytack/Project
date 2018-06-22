<%-- 
    Document   : LoginPage
    Created on : 22-feb-2018, 19:46:39
    Author     : Dirk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
       <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/login.js"></script>
        <script src="js/nav.js"></script>
        <script src="js/jquery.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/login.css">
        <title>Login Pagina</title>
    </head>

    <body>  
        <main>
            <h1>Score On Web</h1>
            <form action="LoginServlet" method="post" >
                
                <input type="text" name="un" id="login"/>
                <input type="password"  name="pw"  id="paswoord"/>
                
                <input type='button'  value= "Log in"  id="bt-login" onclick="logIn()" />
            </form>
             <p id="foutBoodschap" class="foutBoodschap" hidden ><br/> Login en/of paswoord onbekend. Probeer opnieuw!</p>
        </main>
    </body>
</html>