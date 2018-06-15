
var formulierNaam = '';
var aantalTaken = 0;
var evalTable;
var taakSelectData;
var scoreVastGedeelte=[];
var scores;


function bewaarFormulier() {

    if (aantalTaken === 0) {
        alert("Er zijn geen taken om te bewaren!");
        return;
    }
    var cursist = document.getElementById("cursisten").value;
    var xhttp;
    xhttp = new XMLHttpRequest();
    var lesdatum = document.getElementById("datum").value;
    var jaar = lesdatum.substr(0, 4);
    var maand = lesdatum.substr(5, 2);
    var dag = lesdatum.substr(8, 2);
    var titelDatum = dag + '-' + maand + "-" + jaar;
    var semester = document.getElementById("Semester").value;
    var module = document.getElementById("modules").value;
    var cursist = document.getElementById("cursisten").value;
    var lesnr = document.getElementById("lesnr").value;
    var arrXHTML = [];
    var arrXHTML2 = [];
    var taak = [];
    var comment = [];

    xhttp.open("POST", "EvaluatieFormulierServlet?bewaarCursist=" + cursist + "&lesnr=" + lesnr + "&module=" + module + "&datum=" + titelDatum + "&semester=" + semester + "&formname=" + formulierNaam, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const evaluatieFormID = xhttp.responseText;
            for (let x = 0; x < aantalTaken; x++) {
                var dropdown = document.getElementById("formTaken" + (x + 1));
                var textare = document.getElementById("formCommentrow" + (x + 1));
                comment[x] = textare.value;
                taak[x] = dropdown.value;
                arrXHTML[x] = new XMLHttpRequest();
                arrXHTML[x].open("POST", "EvaluatieFormulierServlet?formTaak=" + taak[x] + "&comment=" + comment[x] + "&formID=" + evaluatieFormID, true);
                arrXHTML[x].send();
                //als het taken door de server worden afgeleverd, checken we de doelstellingen
                arrXHTML[x].onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        const doelstellingen = JSON.parse(arrXHTML[x].responseText);
                        for (let i = 0; i < doelstellingen.length; i++) {
                            var doelstellingID = doelstellingen[i].doelstellingID;
                            var scoreBoxes = document.getElementsByName("formScorerow" + (x + 1));
                            var score = scoreBoxes[i].value;
                            var res = score.replace("+", "$");
                            arrXHTML2[i] = new XMLHttpRequest();
                            //alert("Taak: " + taak[x] + ' DoelstellingID: ' + doelstellingID + " Score: " + score + " evalID: " + evaluatieFormID);
                            arrXHTML2[i].open("POST", "EvaluatieFormulierServlet?saveScores=" + taak[x] + "&doelstellingID=" + doelstellingID + "&score=" + res + "&evaluatieFormID=" + evaluatieFormID, true);
                            arrXHTML2[i].send();
                        }
                    }
                };
            }
            alert("Het formulier is opgeslagen onder de naam: " + formulierNaam);
        }
    };
}

function laadFormulier() {

    if (aantalTaken > 0)
        formulierLeegMaken();
    alert('Formulier geladen');
}

function formulierLeegMaken() {
    if (aantalTaken === 0)
        return;
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
            var dropdownKeuze = document.getElementById('studiegebied').value;
            xhttp.open("POST", "EvaluatieFormulierServlet?studiegebied=" + dropdownKeuze, true);
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
                    break;
                case 'modules':
                    optionNieuw.text = "Maak nieuwe module...";
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
   var dropdown = document.querySelector("#lesnr");
    dropdown.style = "background: #f9f9f9";
    genereerFormuliernaam();
}


//nadat alle keuzes zijn gemaakt, wordt de formuliernaam gegenereerd...
function genereerFormuliernaam() {

    var lesnummer = document.getElementById('lesnr').value;
    var label = document.getElementById('formulierNaam');
    label.hidden = false;
    var lesdatum = document.getElementById("datum").value;
    var jaar = lesdatum.substr(0, 4);
    var maand = lesdatum.substr(5, 2);
    var dag = lesdatum.substr(8, 2);
    var titelDatum = dag + '-' + maand + "-" + jaar;
    var leskeuze = document.getElementById('modules').value;
    var module = document.getElementById("modules").value;
    var semester = document.getElementById("Semester").value;
    var module = document.getElementById("modules").value;
    var cursist = document.getElementById("cursisten").value;
    var lesnr = document.getElementById("lesnr").value;
    formulierNaam = cursist + "_" + leskeuze + "_" + titelDatum + "_" + lesnummer;
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
        var xhttp = new XMLHttpRequest();

        xhttp.open("POST", "EvaluatieFormulierServlet?checkFormulier=" + cursist + "&module=" + module + "&lesnr=" + lesnr + "&semester=" + semester + "&datum=" + titelDatum, true);
        xhttp.send();
        //als het taken door de server worden afgeleverd
        xhttp.onreadystatechange = function () {

            if (this.readyState === 4 && this.status === 200) {
                const formulierID = JSON.parse(xhttp.responseText);
                if (formulierID !== 0) {
                    alert("Let op! Je hebt al een evaluatieformulier aangemaakt voor deze cursist voor deze les. Als je verder gaat, zal het oude formulier overschreven worden. Je kan met 'Formulier laden' het bestaande formulier inladen. ");
                }
            }
        };
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
        dropdown.options.length=1;
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
    kernVak.style.textAlign = "center";
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
        option.tagName = taakSelectData[i].taakID;
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


function taakWissel(rowid) {

//welke taak is gekozen?
    var rijID=rowid;
    var taakid = rijID.replace("row", "formTaken");
    var selectTask = document.getElementById(taakid);
    var row = document.getElementById(rowid);
    var selectedTaak = selectTask.value;
    var xhttp7 = new XMLHttpRequest();
    //laad de doelstellingen die overeenkomen met de taak
    xhttp7.open("POST", "EvaluatieFormulierServlet?formTaak2=" + selectedTaak, true);
    xhttp7.send();
    //als de doelstellingen arriveren...
    xhttp7.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            const doelstellingen = JSON.parse(xhttp7.responseText);
            var  aantalDoelstellingen = doelstellingen.length;
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
                        scoreVastGedeelte[rowid] = row.cells[3].innerHTML;
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
            comment.id = "formComment" + rowid;
            comment.rows = aantalDoelstellingen + 1;
            comment.cols = 35;
            comment.style = "background: #f9f9f9";
            row.cells[9].appendChild(comment);
        }
    };
}
;

function berekenGemiddelde(rij) {


    var scoreBoxes = document.getElementsByName("formScore" + rij);
    //we overlopen alle score dropdowns
    var score;
    var totaalScore = 0;
    var aantalScores=0;
    for (let i = 0; i < scoreBoxes.length; i++) {
        var score = scoreBoxes[i].value;
        for (let x = 0; x < scores.length; x++) {
            if (score === scores[x].naam) {
                totaalScore = totaalScore + scores[x].waarde;
                if (score !== "Geen" && score !== "Score...") aantalScores=aantalScores + 1;
            }
        }
    }
   
    var selectedRij = document.getElementById(rij);
    var totaal = Math.round((totaalScore / aantalScores) * 10) / 10;
    if (totaal > 4.9) {
        selectedRij.cells[3].innerHTML = scoreVastGedeelte[rij] + "<b> <font COLOR='#1e5abc' >" + totaal + "</font></b>";
    } else {
        selectedRij.cells[3].innerHTML = scoreVastGedeelte[rij] + "<b> <font COLOR='#f21515' >" + totaal + "</font></b>";
    }



}