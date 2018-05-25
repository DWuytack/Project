utilities.params = 'page=' + utilities.pageCounter;
const   titels = ["Achternaam", "Voornaam", "Login", "Rol", "GebtDatum", "Email", "Acties"],
        parameters = ["achternaam", "voornaam", "login", "rol", "geboorteDatum", "email"];
        //parameters = ["achternaam", "voornaam", "login", "rol", "geboorteDatumValue", "email"];
let meta;
let rij = '';

//Render HTML achter Ajax request
const renderHTML = function(type, data) {
    if (type === "tabel") {
        meta = {
            rol : ["admin", "leerkracht", "cursist", "secretariaat"]
        };
        let lijst = data.lijst;
        let tabel = utilities.tabelAanmaken(lijst, parameters, titels);
        let inhoud = document.getElementById("gebruikersOverzicht");
        if(!inhoud.contains(tabel)) {
            while(inhoud.firstChild) inhoud.removeChild(inhoud.firstChild);
            inhoud.appendChild(tabel);
        } else {
            inhoud = document.querySelector("#gebruikersOverzicht tbody");
            lijst.forEach(function(e){
                let tr = utilities.rijAanmaken(e, parameters, titels, inhoud);
            });
        }
    } else if (type === "aanpassen")
        utilities.rijAanpassen(rij, meta, parameters, lijst); 
    else if (type === "opslaan")
        utilities.rijOpslaan(rij, parameters, titels);  
    else
        console.log(data);
    document.getElementById("gebruikers").innerHTML = data.toonGebruikers + '/' + data.aantalGebruikers;
};

//Klik events
document.addEventListener("click", function(e){
    let target = e.target;
    
    if( e.target.name === "Volgende") {
        utilities.pageCounter++;
        utilities.params = 'page=' + utilities.pageCounter;
        utilities.requestData("tabel",);
    }
    
    if( e.target.name === "Vorige") {
        utilities.pageCounter--;
        utilities.params = 'page=' + utilities.pageCounter;
        utilities.requestData("tabel",);
    }
    
    if( e.target.id === "somebutton" ) {
        utilities.pageCounter++;
        utilities.params = 'page=' + utilities.pageCounter;
        utilities.requestData("tabel",);
    }

    if(target.hasAttribute("role")) {
        let role = target.getAttribute("role");
        if(role === "aanpassen") {
            rij = utilities.vindRij(target);
            rij.classList.add("edit");
            let editID = target.value;
            utilities.backupID = editID;
            utilities.params = 'idEdit=' + editID;
            utilities.requestData("aanpassen", target);
        }
        if(role === "opslaan") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            console.log(rij.cells);
            utilities.tabelID = 'gebruikersOverzicht';
            utilities.params = 'idSave=' + utilities.saveID;
            for(let i=0; i < utilities.editRow.length; i++) {
                let val = utilities.editRow[i].value;
                let parameter = parameters[i];
                //if(parameter === "geboorteDatumValue") parameter = "geboorteDatum";
                utilities.params += '&' + parameter + '=' + val;
            }
            utilities.requestData("opslaan", target);
        }
        if(role === "annuleren") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            utilities.rijHerstellen(rij, parameters, titels); 
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
    utilities.requestData("tabel",);
})();