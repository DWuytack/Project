/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var aantalLijnen = 1;
var exdoelstelling = "";
var totaalScore = 0;
var aantalScores = 0;
var evalTable = "";
var doelstellingen = "";

function formulierLeegMaken() {
    if (aantalLijnen === 1)
        return;
    toonOnderstersteButtons(true, true);
    var aantalRijen = evalTable.rows.length;
    for (let i = aantalRijen; i > 1; i--) {
        evalTable.deleteRow(i);
    }
    aantalLijnen = 0;
}

function laadSemester() {

    var dropdown = document.querySelector("#schooljaar");
    if (dropdown.selectedIndex !== 0) {
        dropdown.style = "background: #f9f9f9";
    }
    dropdown = document.querySelector("#Semester");
    dropdown.hidden = false;
    dropdown = document.querySelector("#studiegebied");
    dropdown.hidden = false;
}

//laad de dropdown met de gevraagde soort
function laadDropdown(soort) {

    var xhttp = new XMLHttpRequest();
    //vraag informatie aan servlet
    switch (soort) {
        case 'opleidingen':
            //highlight opleiding in het rood
            var dropdown = document.querySelector("#studiegebied");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #efc4c4";
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #efc4c4";
            resetDropdowns("modules");
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            resetDropdowns("cursisten");
            var select = document.getElementById('studiegebied');
            var studiegebiedID = select.options[select.selectedIndex].id;
            xhttp.open("POST", "CursistOverzichtServlet?laadOpleidingen=" + studiegebiedID, true);
            break;
        case 'modules':
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #efc4c4";
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            resetDropdowns("cursisten");
            var select2 = document.getElementById('opleidingen');
            var opleidingID = select2.options[select2.selectedIndex].id;
            xhttp.open("POST", "CursistOverzichtServlet?laadModules=" + opleidingID, true);
            break;
        case 'cursisten':
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            var module = document.getElementById('modules');
            var moduleID = module.options[module.selectedIndex].id;
            var schooljaarDD = document.getElementById("schooljaar");
            var schooljaar = schooljaarDD.options[schooljaarDD.selectedIndex].id;
            var semester = document.getElementById("Semester").value;
            xhttp.open("POST", "CursistOverzichtServlet?laadCursisten=" + moduleID + "&datum=" + schooljaar + "&semester=" + semester, true);
            break;
    }
    xhttp.send();
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
                    defaultOption.id = 0;
                    break;
                case 'modules':
                    defaultOption.text = 'Module...';
                    defaultOption.id = 0;
                    break;
                case 'cursisten':
                    defaultOption.text = 'Cursist...';
                    defaultOption.id = 0;
                    break;
            }
            defaultOption.disabled = true;
            dropdown.add(defaultOption);
            dropdown.selectedIndex = 0;

            for (let i = 0; i < data.length; i++) {
                let  optiondata = document.createElement('option');
                optiondata.text = data[i].naam;
                if (soort === 'opleidingen')
                    optiondata.id = data[i].opleidingID;
                if (soort === 'modules')
                    optiondata.id = data[i].moduleID;
                if (soort === 'cursisten')
                    optiondata.id = data[i].gebruikerID;
                dropdown.add(optiondata);
            }
        }
    };
}
function laadCursistenOpnieuw() {

    var cursist = document.getElementById("cursisten");
    if (cursist.hidden === false)
        laadDropdown("cursisten");
}


//als een keuze wordt gewijzigd, ledig dan de daaropvolgende dropdowns
function resetDropdowns(naam) {

    if (aantalLijnen > 0)
        formulierLeegMaken();
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

function toonOnderstersteButtons(print, leeg) {

    var label4 = document.getElementById('printButton');
    label4.style.color = "blue";
    label4.hidden = print;
    var label5 = document.getElementById('leegButton');
    label5.style.color = "blue";
    label5.hidden = leeg;

}

function toonScores(row, rijID) {

    row.cells[7].innerHTML = "";
    for (let i = 0; i < doelstellingen.length; i++) {
        var scoreSelect = document.createElement('select');
        scoreSelect.style = "background: #f9f9f9";
        scoreSelect.name = "score" + rijID;
        scoreSelect.id = "doelstellingID" + doelstellingen[i].doelstellingID;
        scoreSelect.style.fontSize = "10pt";
        let defaultOption = document.createElement('option');
        defaultOption.text = 'Score...';
        defaultOption.id = "beoordelingssoortID" + 0;
        defaultOption.disabled = true;
        scoreSelect.add(defaultOption);
        scoreSelect.selectedIndex = 0;
        scoreSelect.onchange = function () {
            berekenGemiddelde(rijID);
        };
        let option;
        for (let x = 0; x < scores.length; x++) {
            option = document.createElement('option');
            option.text = scores[x].naam;
            option.id = "beoordelingssoortID" + scores[x].beoordelingssoortID;
            option.name = doelstellingen[i].doelstellingID;
            scoreSelect.add(option);
        }
        row.cells[7].appendChild(scoreSelect);
    }
}


function laadCursistInfo() {

    var cursistDD = document.querySelector("#cursisten");
    var cursistNaam = cursistDD.name;
    var cursistID = cursistDD.options[cursistDD.selectedIndex].id;
    var moduleNaam = document.querySelector("#modules");
    var moduleID = moduleNaam.options[moduleNaam.selectedIndex].id;
    var label = document.getElementById('cursistTitel');
    label.innerHTML = "<h3 >" + cursistNaam + ": " + "</h3>";
    var xhttp = new XMLHttpRequest();
    var dropdown3 = document.querySelector("#titelTable");
    dropdown3.hidden = false;
    var dropdown4 = document.querySelector("#evaluatieTable");
    dropdown4.hidden = false;
    var schooljaarDD = document.querySelector("#schooljaar");
    var schooljaarID = schooljaarDD.options[schooljaarDD.selectedIndex].id;
    var semesterDD = document.querySelector("#Semester");
    var semesterID = semesterDD.options[semesterDD.selectedIndex].id;
    formulierLeegMaken();

    xhttp.open("POST", "CursistOverzichtServlet?laadOverzicht=" + moduleID + "&cursist=" + cursistID + "&schooljaar=" + schooljaarID + "&semester=" + semesterID, true);
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
        laadLijn(scoreOverzicht[i].doelstellingnaam, scoreOverzicht[i].kerndoelstelling, scoreOverzicht[i].taaknaam, scoreOverzicht[i].score, scoreOverzicht[i].gemiddeldeScore);
    }
    if (aantalScores !== 0) {
        totaalScore = totaalScore / aantalScores;
    }
    var label = document.getElementById('cursistTitel');
    var cursistNaam = document.querySelector("#cursisten").value;
    label.innerHTML = "<h3 >" + cursistNaam + ": " + (Math.round((totaalScore * 10) * 100) / 100) + "%</h3>";
    if ((Math.round((totaalScore * 10) * 100) / 100) < 50) {
        label.style.color="red";
    }else {
        label.style.color="black";
    }
    var lijnen = document.getElementsByName("horizontal");
    lijnen[0].hidden = false;
    lijnen[1].hidden = false;
    lijnen[2].hidden = false;
    
}

function laadLijn(doelstelling, kerndoelstelling, taaknaam, score, gemiddeldeScore) {
    aantalLijnen = aantalLijnen + 1;
    //maak een rij in ons cursistoverzicht.jsp
    evalTable = document.getElementById("evaluatieTable");
    var row = evalTable.insertRow((aantalLijnen));
    row.id = "row" + aantalLijnen;
    row.style.height = "30px";

    //lege cel
    row.insertCell(0);
    if (aantalLijnen % 2 !== 0)
        row.cells[0].style.backgroundColor = "#ceccca";
    row.insertCell(1);
    if (exdoelstelling !== doelstelling)
        row.cells[1].innerHTML = doelstelling;
    //lege cel
    if (aantalLijnen % 2 !== 0)
        row.cells[1].style.backgroundColor = "#ceccca";
    if (gemiddeldeScore < 5)
        row.cells[1].style.color = "red";


    row.insertCell(2);
    if (aantalLijnen % 2 !== 0)
        row.cells[2].style.backgroundColor = "#ceccca";
    //we voorzien een vak voor onze kern
    var kernVak = row.insertCell(3);
    if (aantalLijnen % 2 !== 0)
        row.cells[3].style.backgroundColor = "#ceccca";
    kernVak.style.textAlign = "center";
    var strKern = "";
    //we maken een string aan
    if (kerndoelstelling === true)
        strKern = strKern + '\u2611' + "<br>";
    if (kerndoelstelling === false)
        strKern = strKern + '\u2610' + "<br>";
    if (exdoelstelling !== doelstelling)
        row.cells[3].innerHTML = strKern;

    //lege cel
    row.insertCell(4);
    if (aantalLijnen % 2 !== 0)
        row.cells[4].style.backgroundColor = "#ceccca";
    //we voorzien een vak voor onze kernvakjes
    var taakVak = row.insertCell(5);
    taakVak.style.verticalAlign = "center";
    row.insertCell(5).innerHTML = taaknaam;
    if (score !== '')
        row.cells[5].style.color = "blue";
    if (aantalLijnen % 2 !== 0)
        row.cells[5].style.backgroundColor = "#ceccca";
    row.insertCell(6);
    if (aantalLijnen % 2 !== 0)
        row.cells[6].style.backgroundColor = "#ceccca";
    //we voorzien een vak voor onze kernvakjes
    var scoreVak = row.insertCell(7);
    row.insertCell(7).innerHTML = "&nbsp&nbsp&nbsp" + score;
    if (score !== '')
        row.cells[7].style.color = "blue";
    if (aantalLijnen % 2 !== 0)
        row.cells[7].style.backgroundColor = "#ceccca";
    scoreVak.style.textAlign = "right";

    //lege cel
    row.insertCell(8);
    if (aantalLijnen % 2 !== 0)
        row.cells[8].style.backgroundColor = "#ceccca";
    //we voorzien een vak voor commentaar;
    var gemiddeldeScoreVak = row.insertCell(9);
    if (aantalLijnen % 2 !== 0)
        row.cells[9].style.backgroundColor = "#ceccca";
    gemiddeldeScoreVak.style.verticalAlign = "center";
    gemiddeldeScoreVak.style.textAlign = "center";
    gemiddeldeScoreVak.style.color="black";
    if (exdoelstelling !== doelstelling && gemiddeldeScore !== 0) {

        gemiddeldeScoreVak.innerHTML = Math.round((gemiddeldeScore) * 100) / 100;
        if ((Math.round((gemiddeldeScore) * 100) / 100) < 5) gemiddeldeScoreVak.style.color="red";
        totaalScore = totaalScore + gemiddeldeScore;
        aantalScores = aantalScores + 1;
    }
    row.insertCell(10);
    if (aantalLijnen % 2 !== 0)
        row.cells[10].style.backgroundColor = "#ceccca";

    exdoelstelling = doelstelling;


}

