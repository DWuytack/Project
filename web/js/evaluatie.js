
var formulierNaam = '';
var aantalTaken = 0;
var aantalDoelstellingen = 0;
var dropdownKeuze;
var formTaken;
var taakDropdown;



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

            let option;
            option = document.createElement('option');
            switch (soort) {
                case 'cursisten':
                    option.text = "Blanco";
                    dropdown.add(option);
                    break;
            }

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
                    option.text = "Blanco";
                    dropdown.add(option);
                    option.text = "Voeg cursist toe...";
                    break;
            }
            dropdown.add(option);

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
    } else {
        verbergTaakToevoegen();
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
    dropdown.style = "background: #efc4c4";
    dropdown.hidden = false;
    if (formulierNaam !== '')
        genereerFormuliernaam();
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
            row.id = "row" + aantalTaken;
            row.style.height="40px";
            row.insertCell(0);

            var taakVak = row.insertCell(1);
            taakVak.style.verticalAlign = "center";

            //maak een dropdown aan voor de taken te laden
            var select = document.createElement('select');
            select.style = "background: #f9f9f9";

            //wat gebeurt er als er een taak wordt gekozen?
            select.onchange = function () {

                //welke taak is gekozen?
                var select_id = document.getElementById("formTaken" + aantalTaken);
                var selectedTaak = select_id.options[select_id.selectedIndex].value;
                var xhttp2 = new XMLHttpRequest();

                //laad de doelstellingen die overeenkomen met de taak
                xhttp2.open("POST", "EvaluatieFormulierServlet?formTaak=" + selectedTaak, true);
                xhttp2.send();

                //als de doelstellingen arriveren...
                xhttp2.onreadystatechange = function () {

                    if (this.readyState === 4 && this.status === 200) {

                        //lege cel
                        row.insertCell(2);
                        //we voorzien een vak voor onze doelstellingen
                        var doelstellingenVak = row.insertCell(3);

                        doelstellingenVak.style.whiteSpace = "nowrap";
                        const doelstellingen = JSON.parse(xhttp2.responseText);
                        aantalDoelstellingen = doelstellingen.length;
                        var strDoelstellingen = "";
                        for (let i = 0; i < doelstellingen.length; i++) {
                            //we maken een string aan
                            strDoelstellingen = strDoelstellingen + doelstellingen[i].naam + "<br/>";
                        }
                        strDoelstellingen = strDoelstellingen + "<br/> <b> TotaalScore: <b/>";
                        doelstellingenVak.style.verticalAlign = "top";
                        doelstellingenVak.innerHTML = strDoelstellingen;

                        //lege cel
                        row.insertCell(4);
                        //we voorzien een vak voor onze kernvakjes
                        var kernVak = row.insertCell(5);
                        var strKern = "";
                        for (let i = 0; i < doelstellingen.length; i++) {
                            //we maken een string aan
                            if (doelstellingen[i].kerndoelstelling === true)
                                strKern = strKern + '\u2611' + "<br>";
                            if (doelstellingen[i].kerndoelstelling === false)
                                strKern = strKern + '\u2610' + "<br>";
                        }
                        kernVak.style.verticalAlign = "top";
                        kernVak.innerHTML = strKern;

                        var xhttp3 = new XMLHttpRequest();
                        //we vragen de scores op
                        xhttp3.open("POST", "EvaluatieFormulierServlet?scores", true);
                        xhttp3.send();

                        //als de scores toekomen...
                        xhttp3.onreadystatechange = function () {

                            if (this.readyState === 4 && this.status === 200) {

                                const scores = JSON.parse(xhttp3.responseText);

                                //lege cel
                                row.insertCell(6);
                                //we voorzien een vak voor onze kernvakjes
                                var scoreVak = row.insertCell(7);
                                scoreVak.style.verticalAlign = "top";

                                for (let i = 0; i < aantalDoelstellingen; i++) {

                                    var scoreSelect = document.createElement('select');
                                    scoreSelect.style = "background: #f9f9f9";
                                    scoreSelect.id = "formScore" + i;
                                    scoreSelect.style.fontSize = "10pt";


                                    let defaultOption = document.createElement('option');
                                    defaultOption.text = 'Score...';
                                    defaultOption.disabled = true;
                                    scoreSelect.add(defaultOption);
                                    scoreSelect.selectedIndex = 0;
                                    let option;

                                    for (let x = 0; x < scores.length; x++) {
                                        option = document.createElement('option');
                                        option.text = scores[x].naam;
                                        scoreSelect.add(option);

                                    }
                                    scoreVak.appendChild(scoreSelect);
                                }
                            }
                            dropdown = document.querySelector("#lesnr");
                            dropdown.style = "background: #f9f9f9";
                            dropdown.hidden = false;

                            var comment = document.createElement('textarea');
                            comment.rows = aantalDoelstellingen + 1;
                            comment.cols = 35;
                            comment.style = "background: #f9f9f9";
                            //lege cel
                            row.insertCell(8);
                            //we voorzien een vak voor commentaar;
                            var commentVak = row.insertCell(9);
                            commentVak.style.verticalAlign = "top";
                            commentVak.appendChild(comment);

                        };

                    }
                };
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


