/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var formulierNaam;
var aantalLijnen = 0;
var taken = "";


function laadLijn() {

    aantalLijnen=aantalLijnen +1;
    
    if (aantalLijnen > 1) {
        laadExtraLijn();
        return;
    }

    if (document.getElementById("modules").selectedIndex === 0) {
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
    xhttp.open("POST", "EvaluatieFormulierServlet?taak=" + keuze, true);
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

            let dropdown = document.getElementById('formTaken1');
            dropdown.hidden = false;
            dropdown.length = 0;

            let defaultOption = document.createElement('option');
            defaultOption.text = 'Kies een taak...';
            defaultOption.disabled = true;
            dropdown.add(defaultOption);
            dropdown.selectedIndex = 0;

            const data = JSON.parse(xhttp.responseText);
            taken = data;
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].naam;
                dropdown.add(option);
            }
            option = document.createElement('option');
            option.text = "Voeg taak toe...";
            dropdown.add(option);
        }

    };
    let lijn1 = document.getElementById('lijn1');
    lijn1.hidden = false;
}

function laadExtraLijn() {


    let dropdown = document.getElementById('formTaken' + aantalLijnen);
    dropdown.hidden = false;
    dropdown.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Kies een taak...';
    defaultOption.disabled = true;
    dropdown.add(defaultOption);
    dropdown.selectedIndex = 0;

    let option;
    for (let i = 0; i < taken.length; i++) {
        option = document.createElement('option');
        option.text = taken[i].naam;
        dropdown.add(option);
    }
    option = document.createElement('option');
    option.text = "Voeg taak toe...";
    dropdown.add(option);

    let lijn = document.getElementById('lijn' + aantalLijnen);
    lijn.hidden = false;
   
}




function laadFormDoelstellingen() {

    if (document.getElementById("formTaken" + aantalLijnen).selectedIndex === 0) {
        return;
    }
    var keuze = document.getElementById('formTaken' + aantalLijnen).value;
    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //open(method,url,async)
    xhttp.open("POST", "EvaluatieFormulierServlet?formTaak=" + keuze, true);
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

            let label = document.getElementById('formDoelstellingen' + aantalLijnen);
            let label2 = document.getElementById('formKern' + aantalLijnen);
            label.hidden = false;
            label2.hidden = false;

            const data = JSON.parse(xhttp.responseText);
            var doelstellingen = "";
            for (let i = 0; i < data.length; i++) {
                doelstellingen = doelstellingen + data[i].naam + "<br>";

            }
            label.innerHTML = doelstellingen;

            var kern = "";
            for (let i = 0; i < data.length; i++) {
                if (data[i].kerndoelstelling) {
                    kern = kern + '\u2611' + "<br>";
                } else {
                    kern = kern + '\u2610' + "<br>";
                }
            }
            label2.innerHTML = kern;
            laadScores();
        }
    }
    ;
     let taakScore = document.getElementById('taakScore' + aantalLijnen);
    taakScore.hidden = false;
}

function laadScores() {

    var score = document.getElementById('formCommentaar' + aantalLijnen);
    var plus = document.getElementById('addLine');
    plus.hidden = false;
    score.hidden = false;

    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //open(method,url,async)
    xhttp.open("POST", "EvaluatieFormulierServlet?scores", true);
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

            let dropdown = document.getElementById('formScore' + aantalLijnen);
            dropdown.hidden = false;
            dropdown.length = 0;

            let defaultOption = document.createElement('option');
            defaultOption.text = 'Score...';
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
        }
    };

}

function genereerFormuliernaam() {

    var lesnummer = document.getElementById('lesnr').value;
    var label = document.getElementById('formulierNaam');
    label.hidden = false;
    var lesdatum = document.getElementById("datum").value;
    var leskeuze = document.getElementById('modules').value;
    var lescursist = document.getElementById("cursisten").value;
    formulierNaam = lescursist + "_" + leskeuze + "_" + lesdatum + "_" + lesnummer;
    label.innerHTML = "formulierNaam: " + formulierNaam;
    laadLijn();


}

function laadLesnr() {
    var lesnr = document.getElementById('lesnr');
    lesnr.hidden = false;
}

function laadCursisten() {

    if (document.getElementById("modules").selectedIndex === 0) {
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

    var schooljaar = document.getElementById("datum").value;
    var semester = document.getElementById("Semester").value;


    //open(method,url,async)
    xhttp.open("POST", "EvaluatieFormulierServlet?module=" + keuze + "&schooljaar=" + schooljaar + "&semester=" + semester, true);
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
            option = document.createElement('option');
            option.text = "Voeg cursist toe...";
            dropdown.add(option);
        }
    };

}

function laadModules() {

    if (document.getElementById("opleidingen").selectedIndex === 0) {
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
    xhttp.open("POST", "EvaluatieFormulierServlet?opleiding=" + keuze, true);
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
            option.text = "Voeg Module toe...";
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

    if (document.getElementById("studiegebied").selectedIndex === 0) {
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
            option.text = "Voeg Opleiding toe...";
            dropdown.add(option);
        }
    };

}
