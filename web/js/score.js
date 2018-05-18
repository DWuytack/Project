

function laadOpleidingen() {

    var keuze = document.getElementById('Opleidingen').value;
    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //open(method,url,async)
    xhttp.open("POST", "ScoreServlet?opleidingen=" + keuze, true);
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
            dropdown.length = 0;

            let defaultOption = document.createElement('option');
            defaultOption.text = 'Kies opleiding...';

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
function laadStudiegebieden() {

    var keuze = document.getElementById('studiegebieden').value;
    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //open(method,url,async)
    xhttp.open("POST", "ScoreServlet?studiegebieden=" + keuze, true);
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

            let dropdown = document.getElementById('studiegebieden');
            dropdown.length = 0;

            let defaultOption = document.createElement('option');
            defaultOption.text = 'Kies studiegebieden...';

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
function laadModule() {

    var keuze = document.getElementById('module').value;
    var xhttp = new XMLHttpRequest();

    if (window.XMLHttpRequest) {
        // code voor moderne browsers
        xhttp = new XMLHttpRequest();
    } else {
        // code voor oude IE browsers
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //open(method,url,async)
    xhttp.open("POST", "ScoreServlet?module=" + keuze, true);
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

            let dropdown = document.getElementById('module');
            dropdown.length = 0;

            let defaultOption = document.createElement('option');
            defaultOption.text = 'Kies module...';

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
}

