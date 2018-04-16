<%-- 
    Document   : some
    Created on : 30-mrt-2018, 18:55:36
    Author     : Jens
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SO question 4112686</title>
        <!--
        <script src="js/jquery.js"></script>
        <script>
            $(document).on("click", "#somebutton", function() {
                $.get("someservlet", function(responseJson) {
                    console.log(responseJson);
                    /*
                    var $ul = $("<ul>").appendTo($("#somediv"));
                    $.each(responseJson, function(index, item) {
                        $("<li>").text(item).appendTo($ul);
                    });
                    */
                });
            });
        </script>
        -->
        <script>sessionStorage.setItem('json', '${sessionScope.json}');</script>
        <script>
            /*
             * Test met success, servlet moet enkel nog worden geupdate zodat hij json doorstuurt bij het opvragen van een andere pagina.
             * 
             * Bugs
             * Date format is incorrect
             *  
             *    */
            var pageCounter = 1;

            /*
            var getData = function(page) {
                var xhttp = new XMLHttpRequest();
                var data = "";
                params = 'page=' + page;
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                       // Typical action to be performed when the document is ready:
                       data = JSON.parse(xhttp.responseText);
                       renderHTML(data);
                    }
                };
                xhttp.open("GET", "someservlet", true);
                xhttp.send();
            };
    */
            
            var requestData = function(page) {
                var xhttp = new XMLHttpRequest();
                var data = "";
                params = 'page=' + page;
                /*xhttp.setRequestHeader('Content-type', 'application/x-www.form.urlencoded');
                xhttp.setRequestHeader('Content-length', params.length );*/
        
                xhttp.open("POST", "someservlet", true);
                
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                
                xhttp.onreadystatechange = function() {
                    if (this.readyState === 4 && this.status === 200) {
                       // Typical action to be performed when the document is ready:
                       data = JSON.parse(xhttp.responseText);
                       renderHTML(data);
                    }
                };
                xhttp.send(params);
            };

            var renderHTML = function(data) {
                htmlString = "";
                var temp = "";
                var keuzes = ["admin", "leerkracht", "cursist", "secretariaat"];

                data.forEach(function(e) {
                    var options = function(e, keuzes){
                        da = "";
                        for(option of keuzes) {
                            da += '<option value="' + option + '"' + selected(e, option) + '>' + option + '</option>';
                        }
                        return da;
                            
                    }
                    var selected = function(rol, d){
                        if(rol === d)
                            return " selected";
                        else
                            return "";
                    };
                    console.log(e.geboorteDatum);
                    temp += '<td><input type="text" name="achternaam" value="' + e.achternaam + '"></td>';
                    temp += '<td><input type="text" name="voornaam" value="' + e.voorNaam + '"></td>'; 
                    temp += '<td> <input type="text" name="login"  value="' + e.login + '"></td>';
                    temp += '<td> <select name="rol" value="' + e.rol + '">' + options(e.rol, keuzes) + '</select></td>';
                    //temp += '<td> <input type="date" name="geboorteDatum" value="' + e.geboorteDatum + '"> </td>';
                    temp += '<td> <input type="text" name="email" value="' + e.email + '"> </td>';
                    htmlString += '<tr>' + temp + '</tr>';
                    temp = "";
                });
                htmlString = '<table>' + htmlString + '</table>';
                document.getElementById("somediv").innerHTML = htmlString;
            };

            document.addEventListener("click", function(e){
                if( e.target.id === "somebutton" ) {
                    pageCounter++;
                    requestData(pageCounter);
                }
            });
            
            //Load first page (error doesn't work when the page is refreshed!!!!
            document.addEventListener("DOMContentLoaded", function(event) {
                renderHTML(JSON.parse(sessionStorage.getItem('json')));
            });
    </script>
    </head>
    <body>
        <button id="somebutton">press here</button>
        <div id="somediv"></div>
    </body>
</html>