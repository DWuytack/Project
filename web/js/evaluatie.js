/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function laadCursisten(){
    
      if (document.getElementById("modules").selectedIndex === 0){
        return;
    }
    var keuze = document.getElementById('modules').value;
    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //open(method,url,async)
    xhttp.open("POST", "EvaluatieFormulierServlet?module=" + keuze , true);
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

            let dropdown = document.getElementById('cursisten');
            dropdown.hidden = false;
            dropdown.length = 0;

            let defaultOption = document.createElement('option');
            defaultOption.text = 'Cursist...';
            defaultOption.disabled = true;
            dropdown.add(defaultOption);
            dropdown.selectedIndex = 0;

            const data = JSON.parse(xhttp.responseText);
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].naam;
                dropdown.add(option);
            }
            option.text = "Voeg cursist toe..."
            dropdown.add(option);
        }
    };
    
    
    
    
}

function laadModules(){
    
      if (document.getElementById("opleidingen").selectedIndex === 0){
        return;
    }
    var keuze = document.getElementById('opleidingen').value;
    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //open(method,url,async)
    xhttp.open("POST", "EvaluatieFormulierServlet?opleiding=" + keuze , true);
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

            let dropdown = document.getElementById('modules');
            dropdown.hidden = false;
            dropdown.length = 0;

            let defaultOption = document.createElement('option');
            defaultOption.text = 'Module...';
            defaultOption.disabled = true;
            dropdown.add(defaultOption);
            dropdown.selectedIndex = 0;

            const data = JSON.parse(xhttp.responseText);
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].naam;
                dropdown.add(option);
            }
            option.text = "Voeg Module toe..."
            dropdown.add(option);
        }
    };
    
    
    
    
}

function checkDate() {

    var datum = document.getElementById("datum").value;

    if (datum === '') {
        alert("Selecteer eerst een datum!");
        document.getElementById("Semester").selectedIndex = 0;
    } else {
        if (document.getElementById("Semester").selectedIndex === 0) {
             document.getElementById("studiegebied").hidden = true;
        } else {
            document.getElementById("studiegebied").hidden = false;
        }
    }

}

function laadOpleidingen() {

    if (document.getElementById("studiegebied").selectedIndex == 0){
        return;
    }
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
    xhttp.open("POST", "EvaluatieFormulierServlet?studiegebied=" + keuze, true);
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

            let dropdown = document.getElementById('opleidingen');
            dropdown.hidden = false;
            dropdown.length = 0;

            let defaultOption = document.createElement('option');
            defaultOption.text = 'Opleiding...';
            defaultOption.disabled = true;
            dropdown.add(defaultOption);
            dropdown.selectedIndex = 0;

            const data = JSON.parse(xhttp.responseText);
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].naam;
                dropdown.add(option);
            }
            option.text = "Voeg Opleiding toe..."
            dropdown.add(option);
        }
    };
}

