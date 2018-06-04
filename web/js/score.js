var dropdownKeuze;
var aantalCursisten = 0;
var scoretable;

function checkJaar() {

    var schooljaar = document.getElementById("schooljaar").value;

    if (schooljaar === 'Kies schooljaar..') {
        alert("Selecteer eerst een schooljaar!");
        document.getElementById("semester").selectedIndex = 0;
    } else {
        if (document.getElementById("semester").selectedIndex === 0) {
            document.getElementById("studiegebied").hidden = true;
        } else {
            document.getElementById("studiegebied").hidden = false;
        }
    }
}

function laadCursistenScores() {

    dropdown = document.querySelector("#modules");
    dropdown.style = "background: #f9f9f9";

    if (document.getElementById("modules").selectedIndex === 0) {
        return;
    }

    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    dropdownKeuze = document.getElementById('modules').value;
    var schooljaar = document.getElementById("datum").value;
    var semester = document.getElementById("Semester").value;
    xhttp.open("POST", "ScoreServlet?module=" + dropdownKeuze + "&schooljaar=" + schooljaar + "&semester=" + semester, true);

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

            var jsonData = JSON.parse(xhttp.responseText);

            var scoreTable = document.getElementById("scoretable");
            for (let i = 0; i < jsonData.length; i++) {
                aantalCursisten = aantalCursisten + 1;
                var row = scoreTable.insertRow(i);
                row.id = "row" + aantalCursisten;

                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);

                var naam = jsonData[i].achternaam;
                var voornaam = jsonData[i].voornaam;
                var score = jsonData[i].score;
                cell1.innerHTML = naam;
                cell2.innerHTML = voornaam;
                cell3.innerHTML = score;

            }

        }
    };
}

//als een keuze wordt gewijzigd, ledig dan de daaropvolgende dropdowns
function resetDropdowns(naam) {

    formulierLeegMaken();
    let dropdowns = document.getElementsByClassName('drop');
    var idDropDown;
    for (let i = 0; i < dropdowns.length; i++) {
        idDropDown = dropdowns[i].id;

        //reset dropdowns na studiegebied
        switch (naam) {
            case 'studiegebieden':

                if (idDropDown === 'opleidingen') {
                    dropdowns[i].selectedIndex = 0;
                    dropdowns[i].style = "background: #efc4c4";
                    ledigDropDown(dropdowns[i]);
                }
                if (idDropDown === 'modules') {
                    dropdowns[i].selectedIndex = 0;
                    dropdowns[i].style = "background: #efc4c4";
                    ledigDropDown(dropdowns[i]);
                }
                break;
            case 'opleidingen':
                if (idDropDown === 'modules') {
                    dropdowns[i].selectedIndex = 0;
                    dropdowns[i].style = "background: #efc4c4";
                    ledigDropDown(dropdowns[i]);
                }
                break;

        }
    }
}

//laad de dropdown met de gevraagde soort
function laadDropdown(soort) {

    var xhttp2 = new XMLHttpRequest();
    //vraag informatie aan servlet
    switch (soort) {
        case 'opleidingen':
            //highlight opleiding in het rood
            dropdown = document.querySelector("#studiegebied");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #efc4c4";
            dropdownKeuze = document.getElementById('studiegebied').value;
            xhttp2.open("POST", "ScoreServlet?studiegebied=" + dropdownKeuze, true);
            break;
        case 'modules':
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #efc4c4";
            dropdownKeuze = document.getElementById('opleidingen').value;
            xhttp2.open("POST", "ScoreServlet?opleiding=" + dropdownKeuze, true);
            break;
        case 'cursisten':
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#cursisten");
            dropdown.style = "background: #efc4c4";
            dropdownKeuze = document.getElementById('modules').value;
            var schooljaar = document.getElementById("datum").value;
            var semester = document.getElementById("Semester").value;
            xhttp2.open("POST", "ScoreServlet?module=" + dropdownKeuze + "&schooljaar=" + schooljaar + "&semester=" + semester, true);
            break;

    }

    xhttp2.send();
    //als het antwoord wordt ontvangen...
    xhttp2.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {

            //plaats het antwoord in een object...
            const data = JSON.parse(xhttp2.responseText);
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
        }
    };
}

function ledigDropDown(dropdown) {

    var length = dropdown.options.length;
    for (i = 1; i < length; i++) {
        dropdown.options[i] = null;
    }
}

function formulierLeegMaken() {

    var datatable = document.getElementsByClassName("datatable");
    for (let i = datatable - 2; i > 0; i--) {
        var row = datatable.rows[i];
        if (row.id !== "firstRow") {
            if (row.id !== "addLine") {
                datatable.deleteRow(i);
            }
        }
    }
}

function laadTabel(data) {

    scoreTable = document.getElementById("scoretable");
    for (i = 0; i < data.length; i++) {
        aantalCursisten = aantalCursisten + 1;
        var row = scoreTable.insertRow(aantalCursisten);
        row.id = "row" + aantalCursisten;

        var achternaam = data.keys(achternaam);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        //cell1.innerhtml = valueOf(achternaam);
        cell1.innerhtml = data.valueOf("achternaam");
        cell2.innerhtml = data.valueOf("voornaam");
        cell3.innerhtml = data.valueOf(achternaam);
    }
}

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
}

function formulierLeegMaken() {

    aantalRijen = scoretable.rows.length;
    for (let i = aantalRijen - 2; i > 0; i--) {
        var row = scoretable.rows[i];
        if (row.id !== "firstRow") {
            if (row.id !== "addLine") {
                scoretable.deleteRow(i);
            }
        }
    }
    aantalCursisten = 0;
}

function laadDoelstellingenScores() {
    var xhttp3 = new XMLHttpRequest();

    dropdown = document.querySelector("#cursisten");
    dropdown.style = "background: #f9f9f9";

    dropdownKeuze = document.getElementById('cursisten').value;
    var schooljaar = document.getElementById("datum").value;
    var semester = document.getElementById("Semester").value;
    xhttp3.open("POST", "ScoreServlet?cursisten=" + dropdownKeuze + "&schooljaar=" + schooljaar + "&semester=" + semester, true);

}
