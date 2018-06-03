
var formulierNaam = '';
var aantalTaken = 0;
var aantalDoelstellingen = 0;
var dropdownKeuze;
var formTaken;
var taakDropdown;
var evalTable;
var taakSelectData;
var scores;
var scoreVastGedeelte = "";


function laadFormulier() {

    if (aantalTaken > 0)
        formulierLeegMaken();
    alert('Formulier geladen');


}

function formulierLeegMaken() {
    if (aantalTaken === 0)
        return;
    aantalRijen = evalTable.rows.length;
    for (let i = aantalRijen - 2; i > 2; i--) {
        var row = evalTable.rows[i];
        if (row.id !== "firstRow") {
            if (row.id !== "addLine") {
                evalTable.deleteRow(i);
            }
        }
    }
    aantalTaken = 0;
}


//kiest juiste semester aan de hand van datum
function pasSemesterAan() {

    let nu = new Date(document.querySelector("#datum").value);
    switch (nu.getMonth() + 1) {
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
            document.querySelector("#Semester").selectedIndex = 2;
            break;
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
        case 12:
        case 1:
            document.querySelector("#Semester").selectedIndex = 1;
            break;
    }
    if (formulierNaam !== '')
        genereerFormuliernaam();
}

//laad de dropdown met de gevraagde soort
function laadDropdown(soort) {

    var xhttp = new XMLHttpRequest();
    //vraag informatie aan servlet
    switch (soort) {
        case 'opleidingen':
            //highlight opleiding in het rood
            dropdown = document.querySelector("#studiegebied");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #efc4c4";
            dropdownKeuze = document.getElementById('studiegebied').value;
            xhttp.open("POST", "EvaluatieFormulierServlet?studiegebied=" + dropdownKeuze, true);
            break;
        case 'modules':
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #efc4c4";
            dropdownKeuze = document.getElementById('opleidingen').value;
            xhttp.open("POST", "EvaluatieFormulierServlet?opleiding=" + dropdownKeuze, true);
            break;
        case 'cursisten':
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            dropdownKeuze = document.getElementById('modules').value;
            var schooljaar = document.getElementById("datum").value;
            var semester = document.getElementById("Semester").value;
            xhttp.open("POST", "EvaluatieFormulierServlet?module=" + dropdownKeuze + "&schooljaar=" + schooljaar + "&semester=" + semester, true);
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
            switch (soort) {
                case 'cursisten':
                    let  optionExtra = document.createElement('option');
                    optionExtra.text = "Blanco";
                    dropdown.add(optionExtra);
                    break;
            }

            for (let i = 0; i < data.length; i++) {
                let  optiondata = document.createElement('option');
                optiondata.text = data[i].naam;
                dropdown.add(optiondata);
            }

            let  optionNieuw = document.createElement('option');
            switch (soort) {

                case 'opleidingen':
                    optionNieuw.text = "Maak nieuwe opleiding...";
                    resetDropdowns('studiegebieden');
                    break;
                case 'modules':
                    optionNieuw.text = "Maak nieuwe module...";
                    resetDropdowns('opleidingen');
                    break;
                case 'cursisten':
                    optionNieuw.text = "Maak nieuwe cursist...";
                    laadTaakSelectData();
                    break;
            }
            dropdown.add(optionNieuw);
            genereerFormuliernaam();
        }
    };
}

function laadFormuliernaam() {
    dropdown = document.querySelector("#lesnr");
    dropdown.style = "background: #f9f9f9";
    genereerFormuliernaam();
}


//nadat alle keuzes zijn gemaakt, wordt de formuliernaam gegenereerd...
function genereerFormuliernaam() {

    var lesnummer = document.getElementById('lesnr').value;
    var label = document.getElementById('formulierNaam');
    label.hidden = false;
    var lesdatum = document.getElementById("datum").value;
    var leskeuze = document.getElementById('modules').value;
    var lescursist = document.getElementById("cursisten").value;
    formulierNaam = lescursist + "_" + leskeuze + "_" + lesdatum + "_" + lesnummer;
    label.innerHTML = "formulierNaam: " + formulierNaam;
    //ready to take off?
    let dropdowns = document.getElementsByClassName('drop');
    var ready = true;
    for (let i = 0; i < dropdowns.length; i++) {
        if (dropdowns[i].selectedIndex === 0) {
            ready = false;
        }
        if (dropdowns[i].hidden === true) {
            ready = false;
        }

    }
    if (ready === true) {
        toonTaakToevoegen();
        label.hidden = false;
    } else {
        verbergTaakToevoegen();
        label.hidden = true;
    }

}

function verbergTaakToevoegen() {

    var extraLijn = document.getElementById("addLine");
    extraLijn.hidden = true;
}

function toonTaakToevoegen() {

    var extraLijn = document.getElementById("addLine");
    extraLijn.hidden = false;
}

function laadCursistenOpnieuw() {

    if (formulierNaam !== '')
        laadDropdown("cursisten");
}


//als een keuze wordt gewijzigd, ledig dan de daaropvolgende dropdowns
function resetDropdowns(naam) {

    if (aantalTaken > 0)
        formulierLeegMaken();
    let dropdowns = document.getElementsByClassName('drop');
    var idDropDown;
    for (let i = 0; i < dropdowns.length; i++) {
        idDropDown = dropdowns[i].id;
        //reset dropdowns na studiegebied
        switch (naam) {
            case 'studiegebieden':

                if (idDropDown === 'modules') {
                    dropdowns[i].selectedIndex = 0;
                    dropdowns[i].style = "background: #efc4c4";
                    ledigDropDown(dropdowns[i]);
                }
                if (idDropDown === 'cursisten') {
                    dropdowns[i].selectedIndex = 0;
                    dropdowns[i].style = "background: #efc4c4";
                    ledigDropDown(dropdowns[i]);
                }
                break;
            case 'opleidingen':
                if (idDropDown === 'cursisten') {
                    dropdowns[i].selectedIndex = 0;
                    dropdowns[i].style = "background: #efc4c4";
                    ledigDropDown(dropdowns[i]);
                }
                break;
        }
    }
}

//maak een gevulde dropdown terug leeg
function ledigDropDown(dropdown) {

    var length = dropdown.options.length;
    for (i = 1; i < length; i++) {
        dropdown.options[i] = null;
    }
}

function laadLesnr() {

    dropdown = document.querySelector("#cursisten");
    dropdown.style = "background: #f9f9f9";
    dropdown = document.querySelector("#lesnr");
    if (dropdown.selectedIndex === 0)
        dropdown.style = "background: #efc4c4";
    dropdown.hidden = false;
    if (formulierNaam !== '')
        genereerFormuliernaam();
}

function laadTaakSelectData() {

    var xhttp3 = new XMLHttpRequest();
    var module = document.getElementById('modules').value;
    xhttp3.open("POST", "EvaluatieFormulierServlet?taak=" + module, true);
    xhttp3.send();
    //als het taken door de server worden afgeleverd
    xhttp3.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            taakSelectData = JSON.parse(xhttp3.responseText);
        }
    };
}

//laad de taken
function laadLijn() {


    aantalTaken = aantalTaken + 1;
    //maak een rij in ons evaluatie.jsp
    evalTable = document.getElementById("evaluatieTable");

    var row = evalTable.insertRow((aantalTaken * 2));
    row.id = "row" + aantalTaken;
    //lege cel
    row.insertCell(0);
    var taakVak = row.insertCell(1);
    //lege cel
    row.insertCell(2);
    //we voorzien een vak voor onze doelstellingen
    var doelstellingenVak = row.insertCell(3);
    doelstellingenVak.style.whiteSpace = "nowrap";
    doelstellingenVak.style.verticalAlign = "top";
    //lege cel
    row.insertCell(4);
    //we voorzien een vak voor onze kernvakjes
    var kernVak = row.insertCell(5);
    kernVak.style.verticalAlign = "top";
    kernVak.style.textAlign="center";
    taakVak.style.verticalAlign = "center";
    row.insertCell(6);
    //we voorzien een vak voor onze kernvakjes
    var scoreVak = row.insertCell(7);
    scoreVak.style.verticalAlign = "top";
    //lege cel
    row.insertCell(8);
    //we voorzien een vak voor commentaar;
    var commentVak = row.insertCell(9);
    commentVak.style.verticalAlign = "top";
    //maak een dropdown aan voor de taken te laden
    var select = document.createElement('select');
    select.style = "background: #f9f9f9";
    //geef de taken dropdown een id
    select.id = "formTaken" + aantalTaken;
    select.onchange = function () {
        taakWissel(row.id);
    };
    //en vul de dropdown met de gewenste taken
    let defaultOption = document.createElement('option');
    defaultOption.text = 'Kies een taak...';
    defaultOption.disabled = true;
    select.add(defaultOption);
    select.selectedIndex = 0;
    let option;
    for (let i = 0; i < taakSelectData.length; i++) {
        option = document.createElement('option');
        option.text = taakSelectData[i].naam;
        select.add(option);
    }
    option = document.createElement('option');
    option.text = "Voeg taak toe...";
    select.add(option);
    //plaats de dropdown in de rij op de evaluatie.jsp
    taakVak.appendChild(select);
    var legeLijn = evalTable.insertRow((aantalTaken * 2));
    legeLijn.id = "leeg";
    for (let i = 0; i < 10; i++) {
        var vak = legeLijn.insertCell(i);
        vak.innerHTML = "<hr/>";
    }
     row.insertCell(10);

}
function berekenGemiddelde(rij) {


    var scoreBoxes = document.getElementsByName("formScore" + rij);
    //we overlopen alle score dropdowns
    var score;
    var totaalScore = 0;
    for (let i = 0; i < scoreBoxes.length; i++) {
        score = scoreBoxes[i].value;
        for (let x = 0; x < scores.length; x++) {
            if (score === scores[x].naam) {
                totaalScore = totaalScore + scores[x].waarde;
            }
        }
    }
    var aantalScores = scoreBoxes.length;
    var selectedRij = document.getElementById(rij);
    var totaal = Math.round((totaalScore / aantalScores) * 10) / 10;
    if (totaal > 4.9) {
        selectedRij.cells[3].innerHTML = scoreVastGedeelte + "<b> <font COLOR='#1e5abc' >" + totaal + "</font></b>";
    } else {
        selectedRij.cells[3].innerHTML = scoreVastGedeelte + "<b> <font COLOR='#f21515' >" + totaal + "</font></b>";
    }



}

function taakWissel(rowid) {

    //welke taak is gekozen?
    taakid = rowid.replace("row", "formTaken");
    var selectTask = document.getElementById(taakid);
    var row = document.getElementById(rowid);
    var selectedTaak = selectTask.value;

    var xhttp7 = new XMLHttpRequest();
    //laad de doelstellingen die overeenkomen met de taak
    xhttp7.open("POST", "EvaluatieFormulierServlet?formTaak=" + selectedTaak, true);
    xhttp7.send();
    //als de doelstellingen arriveren...
    xhttp7.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            const doelstellingen = JSON.parse(xhttp7.responseText);
            aantalDoelstellingen = doelstellingen.length;
            var strDoelstellingen = "";
            for (let i = 0; i < doelstellingen.length; i++) {
                //we maken een string aan
                strDoelstellingen = strDoelstellingen + doelstellingen[i].naam + "<br/>";
            }
            strDoelstellingen = strDoelstellingen + "<br/> <b> TaakScore: <b/>";
            var doelstellingvak = row.cells[3];
            doelstellingvak.innerHTML = strDoelstellingen;
            var strKern = "";
            for (let i = 0; i < doelstellingen.length; i++) {
                //we maken een string aan
                if (doelstellingen[i].kerndoelstelling === true)
                    strKern = strKern + '\u2611' + "<br>";
                if (doelstellingen[i].kerndoelstelling === false)
                    strKern = strKern + '\u2610' + "<br>";
            }
            row.cells[5].innerHTML = strKern;

            var xhttp9 = new XMLHttpRequest();
            //we vragen de scores op
            xhttp9.open("POST", "EvaluatieFormulierServlet?scores", true);
            xhttp9.send();
            //als de scores toekomen...
            xhttp9.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {

                    scores = JSON.parse(xhttp9.responseText);
                    row.cells[7].innerHTML = "";
                    for (let i = 0; i < aantalDoelstellingen; i++) {
                        var scoreSelect = document.createElement('select');
                        scoreSelect.style = "background: #f9f9f9";
                        scoreSelect.name = "formScore" + rowid;
                        scoreSelect.style.fontSize = "10pt";
                        let defaultOption = document.createElement('option');
                        defaultOption.text = 'Score...';
                        defaultOption.disabled = true;
                        scoreSelect.add(defaultOption);
                        scoreSelect.selectedIndex = 0;
                        scoreVastGedeelte = row.cells[3].innerHTML;
                        scoreSelect.onchange = function () {
                            berekenGemiddelde(row.id);
                        };
                        let option;
                        for (let x = 0; x < scores.length; x++) {
                            option = document.createElement('option');
                            option.text = scores[x].naam;
                            scoreSelect.add(option);
                        }
                        row.cells[7].appendChild(scoreSelect);
                    }
                }
            };
            row.cells[9].innerHTML = "";
            var comment = document.createElement('textarea');
            comment.rows = aantalDoelstellingen + 1;
            comment.cols = 35;
            comment.style = "background: #f9f9f9";
            row.cells[9].appendChild(comment);
        }
    };
}
;
