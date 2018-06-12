/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var aantalLijnen = 1;
var exdoelstelling="";

//laad de dropdown met de gevraagde soort
function laadDropdown(soort) {

    var dropdownKeuze;
    var dropdown;
    var xhttp = new XMLHttpRequest();
    //vraag informatie aan servlet
    switch (soort) {
        case 'studiegebieden':
            //highlight opleiding in het rood
            dropdown = document.querySelector("#Semester");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#studiegebied");
            dropdown.style = "background: #efc4c4";
            dropdown.hidden = false;
            break;
        case 'semesters':
            dropdown = document.querySelector("#Semester");
            dropdown.style = "background: #efc4c4";
            dropdown.hidden = false;
            resetDropdowns("opleidingen");
            resetDropdowns("modules");
            resetDropdowns("cursisten");
            break;
        case 'opleidingen':
            //highlight opleiding in het rood
            dropdown = document.querySelector("#studiegebied");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #efc4c4";
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #efc4c4";
            resetDropdowns("modules");
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            resetDropdowns("cursisten");
            dropdownKeuze = document.getElementById('studiegebied').value;
            xhttp.open("POST", "CursistOverzichtServlet?studiegebied=" + dropdownKeuze, true);
            xhttp.send();
            break;
        case 'modules':
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #efc4c4";
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            resetDropdowns("cursisten");
            dropdownKeuze = document.getElementById('opleidingen').value;
            xhttp.open("POST", "CursistOverzichtServlet?opleiding=" + dropdownKeuze, true);
            xhttp.send();
            break;
        case 'cursisten':
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            dropdownKeuze = document.getElementById('modules').value;
            var schooljaar = document.getElementById("schooljaar").value;
            var semester = document.getElementById("Semester").value;
            xhttp.open("POST", "CursistOverzichtServlet?module=" + dropdownKeuze + "&schooljaar=" + schooljaar + "&semester=" + semester, true);
            xhttp.send();
            break;
    }


    //als het antwoord wordt ontvangen...
    xhttp.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {

            //plaats het antwoord in een object...
            const data = JSON.parse(xhttp.responseText);
            //toon dropdown
            let dropdown = document.getElementById(soort);
            dropdown.hidden = false;
            dropdown.length = 0;
            //plaats naam in dropdown en zorg ervoor dat de gebruiker dat niet kan selecteren
            let defaultOption = document.createElement('option');
            //plaats titel in dropdown
            switch (soort) {
                case 'opleidingen':
                    defaultOption.text = 'Opleiding...';
                    break;
                case 'modules':
                    defaultOption.text = 'Module...';
                    break;
                case 'cursisten':
                    defaultOption.text = 'Cursist...';
                    break;
            }
            defaultOption.disabled = true;
            dropdown.add(defaultOption);
            dropdown.selectedIndex = 0;

            for (let i = 0; i < data.length; i++) {
                let  optiondata = document.createElement('option');
                optiondata.text = data[i].naam;
                dropdown.add(optiondata);
            }
        }
    };
}

//als een keuze wordt gewijzigd, ledig dan de daaropvolgende dropdowns
function resetDropdowns(naam) {
    let dropdowns = document.getElementsByClassName('drop');
    var idDropDown;
    for (let i = 0; i < dropdowns.length; i++) {
        if (dropdowns[i].hidden === false) {
            idDropDown = dropdowns[i].id;
            if (idDropDown === naam) {
                dropdowns[i].selectedIndex = 0;
                dropdowns[i].style = "background: #efc4c4";
                ledigDropDown(dropdowns[i]);
            }
        }
    }
}
//maak een gevulde dropdown terug leeg
function ledigDropDown(dropdown) {
    if (dropdown.hidden === false) {
        dropdown.options.length = 1;
    }
}

function laadCursistInfo() {

    var dropdown = document.querySelector("#cursisten");
    var cursistNaam = dropdown.value;
    var dropdown2 = document.querySelector("#modules");
    var module = dropdown2.value;

    var label = document.getElementById('cursistTitel');
    label.innerHTML = "<h3 >" + cursistNaam + "</h3>";
    var xhttp = new XMLHttpRequest();
    var dropdown3 = document.querySelector("#titelTable");
    dropdown3.hidden = false;
    var dropdown4 = document.querySelector("#evaluatieTable");
    dropdown4.hidden = false;

    xhttp.open("POST", "CursistOverzichtServlet?module2=" + module + "&cursist=" + cursistNaam, true);
    xhttp.send();
    //als het taken door de server worden afgeleverd
    xhttp.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            const scoreOverzicht = JSON.parse(xhttp.responseText);
            toonDoelstellingen(scoreOverzicht);
        }
    };
}

function toonDoelstellingen(scoreOverzicht) {

    //verzamel info voor één lijn en roep laadlijn op
    for (let i = 0; i < scoreOverzicht.length; i++) {
        laadLijn(scoreOverzicht[i].doelstellingnaam, scoreOverzicht[i].kerndoelstelling,scoreOverzicht[i].taaknaam);
    }
}

function laadLijn(doelstelling, kerndoelstelling, taaknaam) {

  
   
    aantalLijnen = aantalLijnen + 1;
    //maak een rij in ons cursistoverzicht.jsp
    evalTable = document.getElementById("evaluatieTable");
    var row = evalTable.insertRow((aantalLijnen));
    row.id = "row" + aantalLijnen;
    //lege cel
    row.insertCell(0);
    var doelstellingVak = row.insertCell(1);
    doelstellingVak.style.whiteSpace = "nowrap";
    doelstellingVak.style.verticalAlign = "top";
    if (exdoelstelling !== doelstelling) row.cells[1].innerHTML = doelstelling;
    //lege cel
    row.insertCell(2);
    //we voorzien een vak voor onze kern
    var kernVak = row.insertCell(3);
    kernVak.style.verticalAlign = "top";
    kernVak.style.textAlign = "center";
    var strKern = "";
    //we maken een string aan
    if (kerndoelstelling === true)
        strKern = strKern + '\u2611' + "<br>";
    if (kerndoelstelling === false)
        strKern = strKern + '\u2610' + "<br>";
     if (exdoelstelling !== doelstelling)  row.cells[3].innerHTML = strKern;

    //lege cel
    row.insertCell(4);
    //we voorzien een vak voor onze kernvakjes
    var taakVak = row.insertCell(5);
    taakVak.style.verticalAlign = "center";
    row.insertCell(5).innerHTML=taaknaam;
    row.insertCell(6);
    //we voorzien een vak voor onze kernvakjes
    var scoreVak = row.insertCell(7);
    scoreVak.style.verticalAlign = "top";
    //lege cel
    row.insertCell(8);
    //we voorzien een vak voor commentaar;
    var gemiddeldeScoreVak = row.insertCell(9);
    gemiddeldeScoreVak.style.verticalAlign = "top";
    row.insertCell(10);
    exdoelstelling=doelstelling;

}

