
var formulierNaam = '';
var formulierID = 0;
var aantalTaken = 0;
//dynamisch gedeelte
var evalTable;
//bevat alle taken van een module
var taakSelectData;
//bevat de mogelijke scores waaruit kan worden gekozen
var scores = '';
//bevat de doelstellingen van een taak (json)
var doelstellingen = [];
var aantalScores = 0;


function bewaarFormulier() {

    if (aantalTaken === 0) {
        alert("Er zijn geen taken om te bewaren!");
        return;
    }
    //welke cursist?
    var cursist = document.getElementById("cursisten");
    var cursistID = cursist.options[cursist.selectedIndex].id;

    //datum?
    var lesdatum = document.getElementById("datum").value;

    // Semester? Module? Lesnr?
    var semester = document.getElementById("Semester").value;
    var module = document.getElementById("modules");
    var moduleID = module.options[module.selectedIndex].id;
    var lesnr = document.getElementById("lesnr");
    var lesnrID = lesnr.options[lesnr.selectedIndex].id;

    var arrXHTML = [];
    var arrXHTML2 = [];
    var comment = [];
    var taken = [];
    var taakID = [];

    var xhttp;
    xhttp = new XMLHttpRequest();
    xhttp.open("POST", "EvaluatieFormulierServlet?bewaarFormulier=" + cursistID + "&lesnr=" + lesnrID + "&module=" + moduleID + "&datum=" + lesdatum + "&semester=" + semester + "&formname=" + formulierNaam, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            formulierID = xhttp.responseText;
            for (let x = 0; x < aantalTaken; x++) {
                var taakDropDown = document.getElementById("select" + (x + 1));
                taken[x] = taakDropDown.options[taakDropDown.selectedIndex].id;
                taakID[x] = taken[x].replace("taakID", "");
                var textare = document.getElementById("comment" + (x + 1));
                comment[x] = textare.value;
                arrXHTML[x] = new XMLHttpRequest();
                arrXHTML[x].open("POST", "EvaluatieFormulierServlet?bewaarCommentaarVoorTaak=" + taakID[x] + "&comment=" + comment[x] + "&formID=" + formulierID, true);
                arrXHTML[x].send();
                //als het taken door de server worden afgeleverd, checken we de doelstellingen
                arrXHTML[x].onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        var doelstellingen = JSON.parse(arrXHTML[x].responseText);
                        var scoreBoxes = document.getElementsByName("score" + (x + 1));
                        for (let i = 0; i < doelstellingen.length; i++) {
                            var doelstellingID = doelstellingen[i].doelstellingID;
                            var score = scoreBoxes[i];
                            var scoreIDtekst = score.options[score.selectedIndex].id;
                            var scoreID = scoreIDtekst.replace("beoordelingssoortID", "");
                            arrXHTML2[i] = new XMLHttpRequest();
                            arrXHTML2[i].open("POST", "EvaluatieFormulierServlet?bewaarDoelstellingScore=" + taakID[x] + "&doelstellingID=" + doelstellingID + "&score=" + scoreID + "&evaluatieFormID=" + formulierID, true);
                            arrXHTML2[i].send();
                        }
                    }
                };
            }
            alert("Het formulier is opgeslagen onder de naam: " + formulierNaam);
            toonFormulierTitel("blue");
        }
    };
}

function toonFormulierTitel(kleur) {

    var label = document.getElementById('formulierNaam');
    label.style.color = kleur;
    label.innerHTML = "formulierNaam: " + formulierNaam;
    var label2 = document.getElementById('laadButton');
    label2.hidden = true;
}


function laadFormulier() {

    if (aantalTaken > 0)
        formulierLeegMaken();

    var label = document.getElementById('formulierNaam');
    label.style.color = "red";
    label.innerHTML = "formulierNaam: " + formulierNaam;
    var taakOverzicht = "";
    var scoreOverzicht = "";

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "EvaluatieFormulierServlet?laadTakenVanFormulier=" + formulierID, true);
    xhttp.send();
    //als het taken door de server worden afgeleverd
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            taakOverzicht = JSON.parse(xhttp.responseText);
            var xhttp2 = new XMLHttpRequest();
            xhttp2.open("POST", "EvaluatieFormulierServlet?laadScoresVanDoelstellingen=" + formulierID, true);
            xhttp2.send();
            //als het taken door de server worden afgeleverd
            xhttp2.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    scoreOverzicht = JSON.parse(xhttp2.responseText);
                    toonOverzicht(taakOverzicht, scoreOverzicht);
                }
            };
        }
    };
}

function toonOverzicht(taakOverzicht, scoreOverzicht) {

    //toon alle doelstellingen met alle bijhorende vakken
    for (x = 0; x < taakOverzicht.length; x++) {
        laadLijn();
        var taakID = "taakID" + taakOverzicht[x].taakID;
        var select = document.getElementById("select" + (x + 1));
        var rowID = "row" + (x + 1);
        for (var i = 0; i < select.options.length; i++) {
            if (select.options[i].id === taakID) {
                select.options[i].selected = true;
                taakWissel(taakID, rowID, taakOverzicht[x].commentaar, scoreOverzicht);
            }
        }
    }

}

function formulierLeegMaken() {

    var label = document.getElementById('formulierNaam');
    if (aantalTaken === 0)
        return;

    toonOnderstersteButtons(true, true, true, true);

    var aantalRijen = evalTable.rows.length;
    for (let i = aantalRijen - 2; i > 2; i--) {
        var row = evalTable.rows[i];
        if (row.id !== "firstRow") {
            if (row.id !== "addLine") {
                evalTable.deleteRow(i);
            }
        }
    }
    aantalTaken = 0;
    if (label.style.color === "red") {
        var label6 = document.getElementById('laadButton');
        label6.style.color = "red";
        label6.hidden = false;
    }
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
    laadCursistenOpnieuw();
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
            xhttp.open("POST", "EvaluatieFormulierServlet?laadOpleidingen=" + studiegebiedID, true);
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
            xhttp.open("POST", "EvaluatieFormulierServlet?laadModules=" + opleidingID, true);
            break;
        case 'cursisten':
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            var module = document.getElementById('modules');
            var moduleID = module.options[module.selectedIndex].id;
            //datum?
            var lesdatum = document.getElementById("datum").value;

            var semester = document.getElementById("Semester").value;
            xhttp.open("POST", "EvaluatieFormulierServlet?laadCursisten=" + moduleID + "&datum=" + lesdatum + "&semester=" + semester, true);
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
            switch (soort) {
                case 'cursisten':
                    let  optionExtra = document.createElement('option');
                    optionExtra.text = "Blanco";
                    optionExtra.id = 10000000;
                    dropdown.add(optionExtra);
                    break;
            }
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
            let  optionNieuw = document.createElement('option');
            switch (soort) {

                case 'opleidingen':
                    optionNieuw.text = "Maak nieuwe opleiding...";
                    optionNieuw.id = 0;
                    break;
                case 'modules':
                    optionNieuw.text = "Maak nieuwe module...";
                    optionNieuw.id = 0;
                    break;
                case 'cursisten':
                    optionNieuw.text = "Maak nieuwe cursist...";
                    optionNieuw.id = 0;
                    laadTaakSelectData();
                    break;
            }
            dropdown.add(optionNieuw);
            genereerFormuliernaam();
        }
    };
}

function laadFormuliernaam() {
    var dropdown = document.querySelector("#lesnr");
    dropdown.style = "background: #f9f9f9";
    genereerFormuliernaam();
}


//nadat alle keuzes zijn gemaakt, wordt de formuliernaam gegenereerd...
function genereerFormuliernaam() {

    var ready = true;
    let dropdowns = document.getElementsByClassName('drop');
    for (let i = 0; i < dropdowns.length; i++) {
        if (dropdowns[i].selectedIndex === 0) {
            ready = false;
        }
        if (dropdowns[i].hidden === true) {
            ready = false;
        }
    }

    if (ready) {
        formulierLeegMaken();
        var label = document.getElementById('formulierNaam');
        label.style.color = "black";
        label.hidden = false;
        var lesdatum = document.getElementById("datum").value;
        var jaar = lesdatum.substr(0, 4);
        var maand = lesdatum.substr(5, 2);
        var dag = lesdatum.substr(8, 2);
        var titelDatum = dag + '-' + maand + "-" + jaar;
        var leskeuze = document.getElementById('lesnr');
        var lesnummer = document.getElementById('lesnr').value;
        var lesnrID = leskeuze.options[leskeuze.selectedIndex].id;
        var module = document.getElementById("modules");
        var semester = document.getElementById("Semester").value;
        var module = document.getElementById("modules");
        var moduleNaam = document.getElementById("modules").value;
        var moduleID = module.options[module.selectedIndex].id;
        var cursist = document.getElementById("cursisten");
        var cursistNaam = document.getElementById("cursisten").value;
        var cursistID = cursist.options[cursist.selectedIndex].id;
        formulierNaam = cursistNaam + "_" + moduleNaam + "_" + titelDatum + "_" + lesnummer;
        label.innerHTML = "formulierNaam: " + formulierNaam;

        if (ready === true) {
            toonTaakToevoegen();
            label.hidden = false;
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "EvaluatieFormulierServlet?bestaatFormulierAl=" + cursistID + "&module=" + moduleID + "&lesnr=" + lesnrID + "&semester=" + semester + "&datum=" + lesdatum, true);
            xhttp.send();
            //als het taken door de server worden afgeleverd
            xhttp.onreadystatechange = function () {

                if (this.readyState === 4 && this.status === 200) {
                    formulierID = JSON.parse(xhttp.responseText);
                    if (formulierID !== 0) {
                        alert("Let op! Je hebt al een evaluatieformulier aangemaakt voor deze cursist voor deze les. Als je verder gaat, zal het oude formulier overschreven worden. Je kan met 'Laad formulier' het bestaande formulier inladen. ");
                        var label = document.getElementById('formulierNaam');
                        label.style.color = "red";
                        label.innerHTML = "formulierNaam: " + formulierNaam;
                        var label2 = document.getElementById('laadButton');
                        label2.style.color = "red";
                        label2.hidden = false;
                    } else {
                        toonFormulierTitel("black");
                    }
                }
            };
        } else {
            verbergTaakToevoegen();
            label.hidden = true;
        }
    }
}

function verbergTaakToevoegen() {

    var extraLijn = document.getElementById("addLine");
    extraLijn.hidden = true;
}

function toonTaakToevoegen() {
    var lijn = document.getElementById("header");
    lijn.hidden = false;
    var extraLijn = document.getElementById("addLine");
    extraLijn.hidden = false;
}

function laadCursistenOpnieuw() {

    var cursist = document.getElementById("cursisten");
    if (cursist.hidden === false)
        laadDropdown("cursisten");
}


//als een keuze wordt gewijzigd, ledig dan de daaropvolgende dropdowns
function resetDropdowns(naam) {
    toonFormulierTitel("black");
    if (aantalTaken > 0)
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

function laadLesnr() {

    var dropdown = document.querySelector("#cursisten");
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
    var module = document.getElementById('modules');
    var moduleID = module.options[module.selectedIndex].id;
    xhttp3.open("POST", "EvaluatieFormulierServlet?laadTaken=" + moduleID, true);
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
    evalTable = document.getElementById("evaluatieTable");
    //maak een extra rij
    var row = evalTable.insertRow((aantalTaken * 2) + 1);
    row.id = "row" + aantalTaken;
    row.name = "rij" + aantalTaken;
    //lege cel
    row.insertCell(0);
    //vak voor taakdropdown
    var taakVak = row.insertCell(1);
    taakVak.style.verticalAlign = "center";
    //maak een dropdown aan voor de taken te laden
    var select = document.createElement('select');
    select.style = "background: #f9f9f9";
    select.name = "taak" + aantalTaken;
    //geef de taken dropdown een id
    select.id = "select" + aantalTaken;
    //en vul de dropdown met de gewenste taken
    let defaultOption = document.createElement('option');
    defaultOption.text = 'Kies een taak...';
    defaultOption.id = "taakID" + 0;
    defaultOption.disabled = true;
    select.add(defaultOption);
    select.selectedIndex = 0;
    let option;
    for (let i = 0; i < taakSelectData.length; i++) {
        option = document.createElement('option');
        option.text = taakSelectData[i].naam;
        option.id = "taakID" + taakSelectData[i].taakID;
        option.name = "optie" + (i + 1);
        select.add(option);
    }
    option = document.createElement('option');
    option.text = "Voeg taak toe...";
    option.id = "taakID" + 0;
    select.add(option);
    //plaats de dropdown in de rij op de evaluatie.jsp
    select.onchange = function () {
        taakWissel(select.options[select.selectedIndex].id, row.id, "", "");
    };
    taakVak.appendChild(select);
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
    kernVak.style.textAlign = "center";
    //lege cel
    row.insertCell(6);
    //we voorzien een vak voor de scores
    var scoreVak = row.insertCell(7);
    scoreVak.style.verticalAlign = "top";
    //lege cel
    row.insertCell(8);
    //we voorzien een vak voor commentaar;
    var commentVak = row.insertCell(9);
    commentVak.style.verticalAlign = "top";
    //lege cel
    row.insertCell(10);
    //we trekken er een streep onder
    var legeLijn = evalTable.insertRow((aantalTaken * 2) + 2);
    legeLijn.id = "leeg";
    for (let i = 0; i < 11; i++) {
        var vak = legeLijn.insertCell(i);
        vak.innerHTML = "<hr/>";
    }
}


function taakWissel(taakID, rowID, commentaar, scoreOverzicht) {
    var row = document.getElementById(rowID);
    var rijnr = rowID.replace("row", "");
    var xhttp7 = new XMLHttpRequest();
    //laad de doelstellingen die overeenkomen met de taak
    var taak = taakID.replace("taakID", "");
    xhttp7.open("POST", "EvaluatieFormulierServlet?laadDoelstellingen=" + taak, true);
    xhttp7.send();
    //als de doelstellingen arriveren...
    xhttp7.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            doelstellingen[rijnr] = JSON.parse(xhttp7.responseText);
            var aantalDoelstellingen = doelstellingen[rijnr].length;
            var labelDoelstelling = [];
            row.cells[3].innerHTML = "";
            for (let i = 0; i < aantalDoelstellingen; i++) {
                labelDoelstelling[i] = document.createElement('LABEL');
                labelDoelstelling[i].innerHTML = doelstellingen[rijnr][i].naam + "<BR/>";
                labelDoelstelling[i].id = "doelstellingID" + doelstellingen[rijnr][i].doelstellingID;
                labelDoelstelling[i].name = "doelstelling" + (i + 1);
                row.cells[3].appendChild(labelDoelstelling[i]);
            }
            var scoreLabel = document.createElement('LABEL');
            scoreLabel.innerHTML = "<br/> <b>TotaalScore: </b>";
            scoreLabel.id = "TotaalScore" + rijnr;
            scoreLabel.name = "totaalScore" + rijnr;
            row.cells[3].appendChild(scoreLabel);
            var strKern = [];
            row.cells[5].innerHTML = "";
            for (let i = 0; i < doelstellingen[rijnr].length; i++) {
                //we maken een string aan
                strKern[i] = document.createElement('LABEL');
                strKern[i].id = "kern" + doelstellingen[rijnr][i].doelstellingID;
                strKern[i].name = "kern" + (i + 1);
                if (doelstellingen[rijnr][i].kerndoelstelling === true)
                    strKern[i].innerHTML = '\u2611 <br/>';
                if (doelstellingen[rijnr][i].kerndoelstelling === false)
                    strKern[i].innerHTML = '\u2610 <br/>';
                row.cells[5].appendChild(strKern[i]);
            }

            if (scores === '') {
                var xhttp9 = new XMLHttpRequest();
                xhttp9.open("POST", "EvaluatieFormulierServlet?laadScores", true);
                xhttp9.send();
                //als de scores toekomen...
                xhttp9.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        scores = JSON.parse(xhttp9.responseText);
                        toonScores(row, rijnr, scoreOverzicht);
                    }
                };
            } else {
                toonScores(row, rijnr, scoreOverzicht);
            }
            ;

            var comment = document.createElement('textarea');

            comment.id = "comment" + rijnr;
            comment.rows = aantalDoelstellingen + 1;
            comment.cols = 35;
            comment.style = "background: #f9f9f9";
            comment.wrap = "hard";
            if (commentaar !== "") {
                comment.value = commentaar;
            }
            row.cells[9].innerHTML = "";
            row.cells[9].appendChild(comment);
        }
        ;
        var label = document.getElementById('formulierNaam');
        if (label.style.color === "red") {
            toonOnderstersteButtons(false, false, false, false);
            var label6 = document.getElementById('laadButton');
            label6.style.color = "red";

        } else {
            toonOnderstersteButtons(false, false, false, true);
        }
    };
}


function toonOnderstersteButtons(bewaar, print, leeg, laad) {
    //de buttons van onder tonen
    var label3 = document.getElementById('bewaarButton');
    label3.style.color = "blue";
    label3.hidden = bewaar;
    var label4 = document.getElementById('printButton');
    label4.style.color = "blue";
    label4.hidden = print;
    var label5 = document.getElementById('leegButton');
    label5.style.color = "blue";
    label5.hidden = leeg;
    var label6 = document.getElementById('laadButton');
    label6.style.color = "blue";
    label6.hidden = laad;
}



function toonScores(row, rijID, scoreOverzicht) {

    row.cells[7].innerHTML = "";
    for (let i = 0; i < doelstellingen[rijID].length; i++) {
        var scoreSelect = document.createElement('select');
        scoreSelect.style = "background: #f9f9f9";
        scoreSelect.name = "score" + rijID;
        scoreSelect.id = "doelstellingID" + doelstellingen[rijID][i].doelstellingID;
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
            option.name = doelstellingen[rijID][i].doelstellingID;

            if (scoreOverzicht !== "") {
                var huidigSelect = document.getElementById("select" + rijID);
                var taakID = huidigSelect.options[huidigSelect.selectedIndex].id;
                var taskID=taakID.replace("taakID", "");

                for (s = 0; s < scoreOverzicht.length; s++) {
                    if (taskID == scoreOverzicht[s].taakID) {
                        if (scoreOverzicht[s].doelstellingID == doelstellingen[rijID][i].doelstellingID) {
                            if (scores[x].beoordelingssoortID == scoreOverzicht[s].beoordelingssoortID){
                                option.selected=true;
                            }
                        }
                    }
                }
                berekenGemiddelde(rijID);
            }
            scoreSelect.add(option);
        }
        row.cells[7].appendChild(scoreSelect);
    }
}



function berekenGemiddelde(rij) {
    var scoreBoxes = document.getElementsByName("score" + rij);
    //we overlopen alle score dropdowns
    var score;
    var totaalScore = 0;
    var aantalScores = 0;
    for (let i = 0; i < scoreBoxes.length; i++) {
        var score = scoreBoxes[i];
        var scoreText = scoreBoxes[i].value;
        var scoreTextID = score.options[score.selectedIndex].id;
        var scoreID = scoreTextID.replace("beoordelingssoortID", "");
        for (let x = 0; x < scores.length; x++) {
            if (scoreID == scores[x].beoordelingssoortID) {
                totaalScore = totaalScore + scores[x].waarde;
                if ((scoreText !== "Geen" && scoreText !== "Score...") || scoreID == 0)
                    aantalScores = aantalScores + 1;
            }
        }
    }

    var selectedRij = document.getElementById("row" + rij);
    var totaal = Math.round((totaalScore / aantalScores) * 100) / 100;
    var totaalScoreLabel = document.getElementById("TotaalScore" + rij);
    if (totaal > 4.9) {
        totaalScoreLabel.innerHTML = "<br/> <b> TaakScore:  <font COLOR='#1e5abc' >" + totaal + "</font></b>";
    } else {
        totaalScoreLabel.innerHTML = "<br/> <b> TaakScore:  <font COLOR='#f21515' >" + totaal + "</font></b>";
    }
    selectedRij.cells[3].appendChild(totaalScoreLabel);
}