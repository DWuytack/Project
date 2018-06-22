/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function logIn() {
    
    var xhttp = new XMLHttpRequest();
    
    var login= document.getElementById("login").value;
    var paswoord=document.getElementById("paswoord").value;
    xhttp.open("POST", "LoginServlet?checkLogin=" + login  + "&paswoord=" + paswoord, true);
    xhttp.send();
    //als het taken door de server worden afgeleverd
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const login = JSON.parse(xhttp.responseText);
            if (login){
                window.location='BeginMenu.jsp';
            } else {
                var foutboodschap= document.getElementById("foutBoodschap");
                foutboodschap.hidden=false;
            }       
        }
    };
    
    
    
    
}
