utilities.pageCounter = 1;
utilities.params = 'page=' + utilities.pageCounter;
let titels, parameters;
let meta;
let rij = '';

const requestData = function(type, target) {
    let xhttp = new XMLHttpRequest();
    let data = "";

    xhttp.open("POST", "someservlet", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            // Typical action to be performed when the document is ready:
            data = JSON.parse(xhttp.responseText);
            console.log(data);
            renderHTML(type, data);
        }
    };
    xhttp.send(utilities.params);
};

let renderHTML = function(type, data) {
    if (type === "tabel") {
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
    } else if (type === "aanpassen")
        utilities.rijAanpassen(rij, meta, parameters, data); 
    else if (type === "opslaan")
        utilities.rijOpslaan(rij, parameters, titels);  
    else
        console.log(data);
};

document.addEventListener("click", function(e){
    let target = e.target;
    
    if( e.target.name === "Volgende") {
        utilities.pageCounter++;
        utilities.params = 'page=' + utilities.pageCounter;
        requestData("tabel",);
    }
    
    if( e.target.name === "Vorige") {
        utilities.pageCounter--;
        utilities.params = 'page=' + utilities.pageCounter;
        requestData("tabel",);
    }
    
    if( e.target.id === "somebutton" ) {
        utilities.pageCounter++;
        utilities.params = 'page=' + utilities.pageCounter;
        requestData("tabel",);
    }

    if(target.hasAttribute("role")) {
        let role = target.getAttribute("role");
        if(role === "aanpassen") {
            rij = utilities.vindRij(target);
            rij.classList.add("edit");
            let editID = target.value;
            utilities.backupID = editID;
            utilities.params = 'idEdit=' + editID;
            requestData("aanpassen");
        }
        if(role === "opslaan") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            console.log(rij.cells);
            utilities.tabelID = 'gebruikersOverzicht';
            utilities.params = 'idSave=' + utilities.saveID;
            let i3 = -1;
            //beter SYSTEEM zoeken !!!!
            let requestParameter = ["voornaam", "achternaam", "rol", "geboorteDatum", "email"];
            parameters.forEach(function(parameter){
                i3++;
                let cel = rij.cells[i3];
                console.log(cel);
                let getVal = function(cel){
                    let ie = 0;
                    let val = cel.childNodes;
                    ie++;
                    for(let node of val) {
                        if(node.name === parameter) {
                            return node;
                        } else {
                            node = node.childNodes;
                            for(let node2 of node) {
                                if(node2.name === parameter) {
                                    return node;
                                } else {
                                    for(let node3 of node2) {
                                        if(node3.name === parameter) {
                                            return node;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return '';
                }
                let val = getVal(cel);
                console.log(val);
                //let val = document.querySelector('#' + utilities.tabelID + ' tbody tr:nth-child(' + rij.rowIndex + ') [name="' + parameter + '"]').value;
                
                utilities.params += parameter + '=' + val;
            });
            console.log(utilities.params);
            requestData("opslaan");
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
    requestData("tabel",);
})();