<%-- 
    Document   : fetch
    Created on : 29-mrt-2018, 12:58:26
    Author     : Jens
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery.js"></script>
    </head>
    <body>
        <!-- Dit is een test pagina -->
        
        
        <div id="tabel-gebruikers"></div>
        <script>
            var test = "<%= session %>";
            var test1 = '@Request.RequestContext.HttpContext.Session["lijstGebruikers"]';
            var test2 = '@Session["gebruikers"]';
            var myMap = {  
                <c:forEach items="${lijstGebruikers}" var="cursist" varStatus="loop">  
                  ${cursist.login}: '${cursist.achternaam}' ${not loop.last ? ',' : ''}  
                </c:forEach>  
            };  
            
            alert("${lijstGebruikers}");
            
            var gebruikers = {
                <c:forEach items="${lijstGebruikers}" var="cursist">
                "${cursist.login}": {
                  name:"${cursist.achternaam}",
                  voornaam:"${cursist.voorNaam}",
                },
                </c:forEach>
            };
            
            
            $(document).ready(function() {
                $.get('GebruikersServlet', {
                        userName : name
                }, function(responseText) {
                        $('#ajaxGetUserServletResponse').text(responseText);
                });
            });

            
            $("#tabel-gebruikers tbody").append( function(){
                
            });
            
            function arrayToTable(tableData) {
                var table = $('<table></table>');
                $(tableData).each(function (i, rowData) {
                    var row = $('<tr></tr>');
                    $(rowData).each(function (j, cellData) {
                        row.append($('<td>'+cellData+'</td>'));
                    });
                    table.append(row);
                });
                return table;
            }

            $('body').append(arrayToTable([
                ["John","Slegers",34],
                ["Tom","Stevens",25],
                ["An","Davies",28],
                ["Miet","Hansen",42],
                ["Eli","Morris",18]
            ]));
            
            <c:if test="${cursist.gebruikerID == sessionScope.editID}">
                $('body').append(arrayToTable([
                    "${cursist.achternaam}",
                    "${cursist.voorNaam}",
                    "${cursist.login}",
                    "${cursist.rol}",
                    "${cursist.geboorteDatum}",
                    "${cursist.email}"
                ]));
            </c:if>
            
            /*
            $("#tabel-gebruikers thead").add(function(){
                this.
            });
            $("#tabel-gebruikers thead tr").each(function( index ) {
                console.log( index + ": " + $( this ).text() );
            });
            $("#tabel-gebruikers tbody").add(function(){
                
            });
            */
        </script>
        
        
        
        
        
        
        
        
        
        <div id="lol"></div>
        <form action="GebruikersServlet">
            <c:forEach items="${lijstGebruikers}" var="cursist">
                <script>
                var cursist = {
                    achternaam: "${cursist.achternaam}",
                    voorNaam:  "${cursist.voorNaam}",
                    login: "${cursist.login}"
                };
                document.getElementById("lol").innerHTML = JSON.stringify(cursist, null, 4);
                </script>
            </c:forEach>
                
        </form>
        
        <style>
            #testje {
                font-family: 'Raleway2';
                font-size: 50px;
                position: relative;
            }
            #testje > p {
                position: absolute;
                top: 0;
                left: 0;
            }
            #testje p:nth-child(1) {
                font-weight: 800;
                top: 100px;
            }
            #testje p:nth-child(2) {
                font-family: 'Raleway3';
                top: 100px;
            }
        </style>
        
        <div id="testje">
        <p>railways</p>
        <p>railways</p>
        <p>railways</p>
        <p>railways</p>
        </div>
        
    </body>
</html>