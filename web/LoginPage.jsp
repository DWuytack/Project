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
        <link rel="stylesheet" href="css/login.css">
    </head>

    <body>  
        
            <h1 class="titel">Score On Web</h1><br>
           <form action="LoginServlet">
               <input type="text" name="un"><br>
                <input type="password" name="pw"><br>
                <br><br>
                <input type="submit" name="submit" value="Log in">	
            </form>
          
        
    </body>
</html>