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
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/login.css">
        <title>Login Pagina</title>
    </head>

    <body>  
        <main>
            <h1>Score On Web</h1>
            <form action="LoginServlet" method="post">
                
                <input type="text" name="un">
                <input type="password"  name="pw">
                <input id="bt-login" type="submit" name="submit" value="Log in">	
            </form>
        </main>
    </body>
</html>