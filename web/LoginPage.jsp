<%-- 
    Document   : LoginPage
    Created on : 22-feb-2018, 19:46:39
    Author     : Dirk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Pagina</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/login.css">
    </head>

    <body>  
        <header>
            <h1 class="titel">Score On Web</h1><br>
        </header>
        <main>
            <form action="LoginServlet">
                <input type="text" name="un">
                <input type="password" name="pw">
                <br><br>
                <input type="submit" name="submit" value="Log in">	
            </form>
        </main>
    </body>
</html>