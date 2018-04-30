/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function laadOpleidingen() {

    var keuze = document.getElementById('studiegebied').value;
    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    //open(method,url,async)
    xhttp.open("POST", "ScoreServlet?studiegebied="+keuze, true);
    xhttp.send();
    
    xhttp.onreadystatechange = function () {

        //200: "OK"
        //403: "Forbidden"
        //404: "Not Found"
               
        //0: request not initialized 
        //1: server connection established
        //2: request received 
        //3: processing request 
        //4: request finished and response is ready
        if (this.readyState === 4 && this.status === 200) {
            
            document.getElementById("demo").innerHTML= xhttp.responseText;
        }
        
    };
}
