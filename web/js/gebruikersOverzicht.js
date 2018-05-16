let pageCounter = 1;
let params = 'page=' + pageCounter;
let titels, parameters;
let meta;
let backup = [];
let rij = '';

let requestData = function(params) {
    let xhttp = new XMLHttpRequest();
    let data = "";
    //params = 'page=1';

    xhttp.open("POST", "someservlet", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
           // Typical action to be performed when the document is ready:
           data = JSON.parse(xhttp.responseText);
           renderHTML(data);
        }
    };
    xhttp.send(params);
};

var renderHTML = function(data) {
    titels = ["Achternaam", "Voornaam", "Login", "Rol", "GebtDatum", "Email", "Acties"];
    //titels = ["Achternaam", "Voornaam", "Login", "Rol", "GebtDatum", "Email"];
    meta = {
        rol : ["admin", "leerkracht", "cursist", "secretariaat"]
    };
    parameters = ["achternaam", "voorNaam", "login", "rol", "geboorteDatumValue", "email"];
    let tabel = utilities.tabelAanmaken(data, parameters, titels);
    let inhoud = document.getElementById("gebruikersOverzicht");
    console.log(inhoud);
    if(!inhoud.contains(tabel)) {
    while(inhoud.firstChild)
        inhoud.removeChild(inhoud.firstChild);
    inhoud.appendChild(tabel);
    } else {
        inhoud = document.querySelector("#gebruikersOverzicht tbody");
        data.forEach(function(e){
            let tr = utilities.rijAanmaken(e, parameters, titels, inhoud);
        });
    }
};

document.addEventListener("click", function(e){
    let target = e.target;
    
    if( e.target.name === "Volgende") {
        pageCounter++;
        params = 'page=' + pageCounter;
        requestData(params);
    }
    
    if( e.target.name === "Vorige") {
        pageCounter--;
        params = 'page=' + pageCounter;
        requestData(params);
    }
    
    if( e.target.id === "somebutton" ) {
        pageCounter++;
        params = 'page=' + pageCounter;
        requestData(params);
    }

    if(target.hasAttribute("role")) {
        let role = target.getAttribute("role");
        if(role === "aanpassen") {
            rij = utilities.vindRij(target);
            rij.classList.add("edit");
            utilities.rijAanpassen(rij, meta, parameters);
        }
        if(role === "opslaan") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            let tabelID = 'gebruikersOverzicht';
            utilities.rijOpslaan(rij, tabelID, parameters, titels);  
        }
        if(role === "annuleren") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            utilities.rijHerstellen(rij, parameters, titels, backup); 
        }
        if(role === "verwijderen") {
            rij = utilities.vindRij(target);
            rij.classList.add("delete");

            var popup = document.querySelector("#popup");
            var loc = document.querySelector("#gebruiker_verwijderen");
            if(!loc.className.includes("active")) {
                popup.classList.add("active");
                loc.classList.add("active");
            } else {
                popup.classList.remove("active");
                loc.classList.remove("active");
            }
        }
    }
    
    if(e.target.id === "popup") {
        var popup = document.querySelector("#popup");
        var loc = document.querySelector("#gebruiker_verwijderen");
        popup.classList.remove("active");
        loc.classList.remove("active");
        rij.classList.remove("delete");
    }
    
    if(e.target.id === "bt-gebruiker_verwijderen") {
        document.querySelector("#gebruikersOverzicht table").deleteRow(rij.rowIndex);
        var popup = document.querySelector("#popup");
        var loc = document.querySelector("#gebruiker_verwijderen");
        popup.classList.remove("active");
        loc.classList.remove("active");
    }
    
});
//Load
(function() {
    requestData(params);
})();