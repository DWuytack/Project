var pageCounter = 1;
var params = 'page=' + pageCounter;
var titels;
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
    var parameters = ["voorNaam", "achternaam", "login", "rol", "geboorteDatumValue", "email"];
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
            rij.classList.add("edit");
            utilities.rijAanpassen(rij, meta);
        }
        if(role === "opslaan") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            //rijOpslaan();
        }
        if(role === "annuleren") {
            rij = utilities.vindRij(target);
            rij.classList.remove("edit");
            console.log(backup);
            utilities.rijHerstellen(rij);
            /* var cellen = rij.childNodes;
            i2 = -1;
            cellen.forEach(function(cell){
                i2++;
                cell.innerHTML = backup[i2];
                console.log(backup[i2]);
                //console.log(cell);
                //console.log(backup[i2]);
            });*/
        }
    }
    
});
//Load
(function() {
    requestData(params);
})();