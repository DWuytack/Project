<%-- 
    Document   : OverzichtStudiegebieden
    Created on : 30-mrt-2018, 11:10:04
    Author     : CURSIST
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/jquery.js"></script>
        <script src="js/nav.js"></script>
        <script src="js/table.js"></script>
        <!-- START Test -->
        <script src="js/dataStudiegebiedenTemp.js"></script>
        <!-- END Test -->
        <script src="js/studiegebieden.js"></script>
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/studiegebieden.css">
        <title>StudieGebieden</title>
    </head>
    
    <body>  
        <script>
            var params = 'studiegebied=' + '';
            var requestData = function(params) {
                var xhttp = new XMLHttpRequest();
                var data = "";
                //params = 'page=1';

                xhttp.open("POST", "StudiegebiedenServlet", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.onreadystatechange = function() {
                    if (this.readyState === 4 && this.status === 200) {
                       // Typical action to be performed when the document is ready:
                       dataTest = JSON.parse(xhttp.responseText);
                       console.log("pulled data");
                       console.log(dataTest);
                    }
                };
                xhttp.send(params);
            };
            
            requestData(params);
            
        </script>
        
        <%@include file="Bovenbalk.jsp" %>
        
        <section id="pagename">
            <h2> OverzichtStudiegebieden </h2>
        </section>

        <section>
            <form action="StudiegebiedenServlet">
                <div id="menu">
                    <div role="menu-header">
                        <select name="studiegebieden">
                            <option value="0">Kies Uw StudieGebied</option>
                            <option value="gebied1">Gebied1</option>
                            <option value="gebied2">Gebied2</option>
                        </select>
                    </div>
                    <div role="menu-content"></div>          
                </div>
            </form>
        </section>
        
    </body>
</html>
