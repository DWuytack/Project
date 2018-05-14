var pageCounter = 1;
var params = 'page=' + pageCounter;
var titels, parameters;
var meta;
var backup = [];

var requestData = function(params) {
    var xhttp = new XMLHttpRequest();
    var data = "";
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
    meta = {
        rol : ["admin", "leerkracht", "cursist", "secretariaat"]
    };
    parameters = ["achternaam", "voorNaam", "login", "rol", "geboorteDatumValue", "email"];
    var tabel = utilities.tabelAanmaken(data, parameters, titels);
    var inhoud = document.getElementById("gebruikersOverzicht");
    console.log(inhoud);
    if(!inhoud.contains(tabel)) {
    while(inhoud.firstChild)
        inhoud.removeChild(inhoud.firstChild);
    inhoud.appendChild(tabel);
    } else {
        inhoud = document.querySelector("#gebruikersOverzicht tbody");
        data.forEach(function(e){
            var tr = utilities.rijAanmaken(e, parameters, titels);
            inhoud.appendChild(tr);
        });
    }
};

document.addEventListener("click", function(e){
    var target = e.target;
    var rij = "";
    
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
        var role = target.getAttribute("role");
        if(role === "aanpassen") {
            rij = utilities.vindRij(target);
            console.log("onze rij");
            console.log(rij);
            rij.classList.add("edit");
            utilities.rijAanpassen(rij, meta, parameters);
        }
        if(role === "opslaan") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            var tabelID = 'gebruikersOverzicht';
            //utilities.rijOpslaan(rij, tabelID, titels);
            var cellen = rij.childNodes;
            i2 = -1;
            parameters.forEach(function(cel){
                i2++;
                var cel = cellen[i2];
                var celInhoud = document.querySelector('#' + tabelID + ' [name="' + parameters[i2].toLowerCase() + '"]');
                celWaarde = celInhoud.value;
                //!!!!!!!!!!!!!!!!!! BUG !!!!!!!!!!!!!!!!!!!!!!!!
                alert(RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(celWaarde));
                if(celWaarde.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(celWaarde))
                    celWaarde = utilities.datumFormat(celWaarde).view;
                else
                    cel.innerHTML = '<span>' + celWaarde + '</span>';
            });
            i2++;
            if(titels[i2] === "Acties") {
                cellen[i2].innerHTML = '';
                cellen[i2].append( utilities.actieKnoppenAanmaken("tonen") );
            }
        }
        if(role === "annuleren") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            console.log(backup);
            //utilities.rijHerstellen(rij);
            var cellen = rij.childNodes;
            i2 = -1;
            parameters.forEach(function(parameter){
                i2++;
                cel = cellen[i2];
                cel.innerHTML = backup[i2];
                console.log(cel);
            });
            i2++;
            if(titels[i2] === "Acties") {
                cellen[i2].innerHTML = '';
                cellen[i2].append( utilities.actieKnoppenAanmaken("tonen") );
            }
        }
    }
    
});
//Load
(function() {
    requestData(params);
})();