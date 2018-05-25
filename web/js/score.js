var dropdownKeuze;

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

    var schooljaar = document.getElementById("schooljaar").value;
    var semester = document.getElementById("semester").value;


    //open(method,url,async)
    xhttp.open("POST", "ScoreServlet?module=" + keuze + "&schooljaar=" + schooljaar + "&semester=" + semester, true);
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

            let achternaam = document.getElementById('achternaam');
            let voornaam = document.getElementById('voornaam');
            let score = document.getElementById('score');

            const data = JSON.parse(xhttp.responseText);

            for (let teller = 0; teller < data.length; teller++) {
                var rij = document.getElementById("rij" + teller);
                rij.cells[0].innerHTML = data[teller].achternaam;
                rij.cells[1].innerHTML = data[teller].voornaam;
                var voorlopigeScore = data[teller].score * 10;
                rij.cells[2].innerHTML = voorlopigeScore + "%";
                document.getElementById("rij" + teller).hidden = false;
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

 //laad de dropdowns van de gegeven soort.
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
            xhttp.open("POST", "ScoreServlet?studiegebied=" + dropdownKeuze, true);
            break;
        case 'modules':
            dropdown = document.querySelector("#opleidingen");
            dropdown.style = "background: #f9f9f9";
            dropdown = document.querySelector("#modules");
            dropdown.style = "background: #efc4c4";
            dropdownKeuze = document.getElementById('opleidingen').value;
            xhttp.open("POST", "ScoreServlet?opleiding=" + dropdownKeuze, true);
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
            }
            dropdown.add(optionNieuw);
        }
    };
}
    
function ledigDropDown(dropdown) {

    var length = dropdown.options.length;
    for (i = 1; i < length; i++) {
        dropdown.options[i] = null;
    }
}
