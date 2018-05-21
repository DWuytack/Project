
var formulierNaam;
var aantalTaken = 0;
var aantalDoelstellingen = 0;
var dropdownKeuze;
var formTaken;
var taakDropdown;



//toont studiegebieden nadat de semester is gekozen
function toonStudiegebieden() {

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

//laad de dropdown met de gevraagde soort
function laadDropdown(soort) {


    var xhttp = new XMLHttpRequest();
    //vraag informatie aan servlet
    switch (soort) {
        case 'opleidingen':
            dropdownKeuze = document.getElementById('studiegebied').value;
            xhttp.open("POST", "EvaluatieFormulierServlet?studiegebied=" + dropdownKeuze, true);
            break;
        case 'modules':
            dropdownKeuze = document.getElementById('opleidingen').value;
            xhttp.open("POST", "EvaluatieFormulierServlet?opleiding=" + dropdownKeuze, true);
            break;
        case 'cursisten':
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

            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].naam;
                dropdown.add(option);
            }
            switch (soort) {
                case 'opleidingen':
                    option.text = "Voeg opleiding toe...";
                    resetDropdowns('studiegebieden');
                    break;
                case 'modules':
                    option.text = "Voeg module toe...";
                    resetDropdowns('opleidingen');
                    break;
                case 'cursisten':
                    option.text = "Voeg cursist toe...";
                    //toon lesnr dropdown

                    break;
            }
            dropdown.add(option);
        }
    };
}

//nadat alle keuzes zijn gemaakt, wordt de formuliernaam gegenereerd...
function genereerFormuliernaam() {

    //zijn alle keuzes gemaakt?
    if (checkKeuzesOk() === true) {
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
}
//Zijn alle keuzes gemaakt?
function  checkKeuzesOk() {
    let dropdowns = document.getElementsByClassName('drop');
    var i;
    for (i = 0; i < dropdowns.length; i++) {
        if (dropdowns[i].selectedIndex === 0) {
            return false;
        }
        ;
    }
    return true;
}

//als een keuze wordt gewijzigd, ledig dan de daaropvolgende dropdowns
function resetDropdowns(naam) {

    let dropdowns = document.getElementsByClassName('drop');
    var i;
    var idDropDown;

    for (i = 0; i < dropdowns.length; i++) {
        idDropDown = dropdowns[i].id;
        //reset dropdowns na studiegebied
        switch (naam) {
            case 'studiegebieden':

                if (idDropDown === 'modules') {
                    dropdowns[i].selectedIndex = 0;
                    ledigDropDown(dropdowns[i]);
                }
                if (idDropDown === 'cursisten') {
                    dropdowns[i].selectedIndex = 0;
                    ledigDropDown(dropdowns[i]);
                }
                break;

            case 'opleidingen':
                if (idDropDown === 'cursisten') {
                    dropdowns[i].selectedIndex = 0;
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
    var lesnr = document.getElementById('lesnr');
    lesnr.hidden = false;
}


//laad de taken
function laadLijn() {

    aantalTaken = aantalTaken + 1;
    var xhttp = new XMLHttpRequest();
    var module = document.getElementById('modules').value;

    //open(method,url,async)
    xhttp.open("POST", "EvaluatieFormulierServlet?taak=" + module, true);
    xhttp.send();

    //als het taken door de server worden afgeleverd
    xhttp.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {

            const taken = JSON.parse(xhttp.responseText);

            //maak een rij in ons evaluatie.jsp
            var evalTable = document.getElementById("evaluatieTable");
            var row = evalTable.insertRow(0);
            row.insertCell(0);
            var taakVak = row.insertCell(1);

            //maak een dropdown aan voor de taken te laden
            var select = document.createElement('select');

            //wat gebeurt er als er een taak wordt gekozen?
            select.onchange = function () {

                //welke taak is gekozen?
                var select_id = document.getElementById("formTaken" + aantalTaken);
                var selectedTaak=select_id.options[select_id.selectedIndex].value;
                var xhttp2 = new XMLHttpRequest();

                //laad de doelstellingen die overeenkomen met de taak
                xhttp2.open("POST", "EvaluatieFormulierServlet?formTaak=" + selectedTaak, true);
                xhttp2.send();

                xhttp2.onreadystatechange = function () {

                    //als de doelstellingen arriveren...
                    if (this.readyState === 4 && this.status === 200) {

                        row.insertCell(2);
                        //we voorzien een vak voor onze doelstellingen
                        var doelstellingenVak = row.insertCell(3);

                        const doelstellingen = JSON.parse(xhttp2.responseText);
                        aantalDoelstellingen = doelstellingen.length;
                        var strDoelstellingen = "";

                        for (let i = 0; i < doelstellingen.length; i++) {
                            //we maken een string aan
                            strDoelstellingen = strDoelstellingen + doelstellingen[i].naam + "<br/>";
                        }

                        doelstellingenVak.innerHTML = strDoelstellingen;

                        var kernen = document.getElementsByClassName('formkern' + aantalTaken);
                        for (let i = 0; i < data.length; i++) {
                            kernen[i].hidden = false;
                            if (data[i].kerndoelstelling) {
                                kernen[i].innerHTML = '\u2611' + "<br>";
                            } else {
                                kernen[i].innerHTML = '\u2610' + "<br>";
                            }
                        }

                        var comment = document.getElementById('formCommentaar' + aantalTaken);
                        comment.hidden = false;
                        comment.rows = aantalDoelstellingen + 1;
                        var xhttp = new XMLHttpRequest();
                        //open(method,url,async)
                        xhttp.open("POST", "EvaluatieFormulierServlet?scores", true);
                        xhttp.send();

                        xhttp.onreadystatechange = function () {

                            if (this.readyState === 4 && this.status === 200) {

                                const data = JSON.parse(xhttp.responseText);
                                let scores = document.getElementsByClassName('formScore' + aantalTaken);
                                var i;
                                for (i = 0; i < aantalDoelstellingen; i++) {
                                    scores[i].hidden = false;
                                    scores[i].length = 0;
                                    let defaultOption = document.createElement('option');
                                    defaultOption.text = 'Score...';
                                    defaultOption.disabled = true;
                                    scores[i].add(defaultOption);
                                    scores[i].selectedIndex = 0;
                                    let option;
                                    for (let x = 0; x < data.length; x++) {
                                        option = document.createElement('option');
                                        option.text = data[x].naam;
                                        scores[i].add(option);
                                    }
                                }
                            }
                        };

                        let verborgen = document.getElementsByClassName('addLine');
                        var i;
                        for (i = 0; i < verborgen.length; i++) {
                            verborgen[i].hidden = false;
                        }
                    }
                }
                ;
                let taakScore = document.getElementsByClassName('taakScore' + aantalTaken);
                var i;
                for (i = 0; i < taakScore.length; i++) {
                    taakScore[i].hidden = false;
                }

            };

            //geef de taken dropdown een id
            select.id = "formTaken" + aantalTaken;

            //en vul de dropdown met de gewenste taken
            let defaultOption = document.createElement('option');
            defaultOption.text = 'Kies een taak...';
            defaultOption.disabled = true;
            select.add(defaultOption);
            select.selectedIndex = 0;

            let option;
            for (let i = 0; i < taken.length; i++) {
                option = document.createElement('option');
                option.text = taken[i].naam;
                select.add(option);
            }
            option = document.createElement('option');
            option.text = "Voeg taak toe...";
            select.add(option);

            //plaats de dropdown in de rij op de evaluatie.jsp
            taakVak.appendChild(select);
        }
    };
}


