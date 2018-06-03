/*************************
 * NIEUWE VERSIE
 */

// utilities

function capFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function vindRij(e) {
    let rij = e.parentElement;
    while (rij.tagName !== "TR")
        rij = rij.parentElement;
    return rij;
}

function datumFormat(inputFormat) {
    let pad = function (s) {
        return (s < 10) ? '0' + s : s;
    };
    if (RegExp("([0-9]{2})\/+([0-9]{2})\/+([0-9]{4})").test(inputFormat))
        inputFormat = inputFormat.split("/").reverse().join("-");
    let d = new Date(inputFormat);
    return {
        data: inputFormat,
        view: [pad(d.getDate()), pad(d.getMonth() + 1), d.getFullYear()].join('/')
    }
}

// tabel klasse

class Tabel {

    /* CONSTRUCTOR
     ========================================================================== */

    constructor(target, id, titels, keys) {
        this.id = id;
        this.titels = titels;
        this.ajaxData = {
            data: {
                "bladz": 1,
                "aantalBladz": 4,
                "toonGebruikers": 8,
                "aantalGebruikers": 30,
                "lijst": [{
                        "jsonID": 112,
                        "rol": "cursist",
                        "voornaam": "Stela",
                        "achternaam": "Cozari",
                        "geboorteDatum": "1965-06-18",
                        "email": "Stela.Cozari@test.be",
                        "login": "Stela",
                        "geldig": false
                    }, {
                        "jsonID": 72,
                        "rol": "cursist",
                        "voornaam": "Delina",
                        "achternaam": "DeLoose",
                        "geboorteDatum": "1976-03-28",
                        "email": "Delina.DeLoose@test.be",
                        "login": "Delina",
                        "geldig": false
                    }, {
                        "jsonID": 124,
                        "rol": "leerkracht",
                        "voornaam": "Patrick",
                        "achternaam": "DeMol",
                        "geboorteDatum": "1958-01-02",
                        "email": "Patrick.DeMol@test.be",
                        "login": "Patrick",
                        "geldig": false
                    }, {
                        "jsonID": 116,
                        "rol": "cursist",
                        "voornaam": "Caro",
                        "achternaam": "DeTroetsel",
                        "geboorteDatum": "1997-06-04",
                        "email": "Caro.DeTroetsel@test.be",
                        "login": "Caro",
                        "geldig": false
                    }, {
                        "jsonID": 132,
                        "rol": "leerkracht",
                        "voornaam": "Adelheid",
                        "achternaam": "Dhondt",
                        "geboorteDatum": "1978-08-14",
                        "email": "Adelheid.Dhondt@test.be",
                        "login": "Adelheid",
                        "geldig": false
                    }, {
                        "jsonID": 96,
                        "rol": "cursist",
                        "voornaam": "Eliza",
                        "achternaam": "Dhont	",
                        "geboorteDatum": "1998-09-27",
                        "email": "Eliza.Dhont@test.be",
                        "login": "Eliza",
                        "geldig": false
                    }, {
                        "jsonID": 104,
                        "rol": "cursist",
                        "voornaam": "Morgane",
                        "achternaam": "Doudet",
                        "geboorteDatum": "1998-08-19",
                        "email": "Morgan.Doudet@test.be",
                        "login": "Morgane",
                        "geldig": false
                    }, {
                        "jsonID": 12,
                        "rol": "admin",
                        "voornaam": "Aaron",
                        "achternaam": "Draye",
                        "geboorteDatum": "1997-01-08",
                        "email": "aarondraye@gmail.com",
                        "login": "Aaroenn",
                        "geldig": false
                    }]
            },
            meta: {},
            params: 'page=1',
            keys: keys,
            type: 'tabel',
            editID: 0,
            saveID: 0,
            bladz: 1,
            aantalBladz: 0,
            rijData: {},
            requestCount: 0,
            knopTarget : null,
            requestType : 'container'
        };
        this.rechten = {
            klik: true
        };
        this.focus = "all";
        this.actie = 'fdfdfd';
        this.target = {
            section: target,
            knoppen: {},
            rij: {}
        };
        this.type = {
            rij: {}
        };
        this.backup = {
            rij: {}
        };
    }

    /* GETTERS & SETTERS
     ========================================================================== */

    get aantalBladz() {
        return this.ajaxData.aantalBladz;
    }

    set aantalBladz(aantalBladz) {
        this.ajaxData.aantalBladz = aantalBladz;
    }

    get bladz() {
        return this.ajaxData.bladz;
    }

    set bladz(bladz) {
        this.ajaxData.bladz = bladz;
    }

    get params() {
        return this.ajaxData.params
    }

    set params(params) {
        this.ajaxData.params = params;
    }

    get request() {
        let temp;
        let target = this.target.section;
        this.ajaxData.requestCount++;
        if (this.ajaxData.requestCount === 1)
            this.requestData('tabel', target);
        else
            this.requestData('tabel', target);
    }

    /* FUNCTIES - Utilities
     ========================================================================== */

    tagDetectie(key) {
        let meta = this.ajaxData.meta;
        console.log("meta");
        console.log(meta);
        if (Object.keys(meta).includes(key))
            return "SELECT";
        else if (key !== undefined || key === '' || key === null)
            return "INPUT";
        else
            return "";
    }

    /* FUNCTIES - Requests */
    
    requestData(target, actie) {
        let hasTarget = false;
        let xhttp = new XMLHttpRequest();
        let data = "";
        let thisClass = this;

        //if(target !== undefined && target.hasAttribute("name")) hasTarget = true;
        //if(hasTarget) target.classList.add("active");

        xhttp.open("POST", "someservlet", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                // Typical action to be performed when the document is ready:
                data = JSON.parse(xhttp.responseText);
                //if(hasTarget) target.classList.remove("active");
                
                console.log("data servlet");
                console.log(data);
                
                //DEVa-1-2 tijdelijk verwijderen als interface af is--------------
                thisClass.ajaxData.data = data;
                //--------------------------------------------------------------
                
                if(actie === "render") thisClass.renderHTML(target, data);
            }
        };
        xhttp.send(this.ajaxData.params);
    }
    
    requestContainer() {
        //this.ajaxData.params = 'page=1';
        //this.ajaxData.requestType = 'container';
        //this.target.section = target;
        this.requestData(this.target.section, "render");
    }
    
    requestRijenContent(target) {
        this.ajaxData.requestType = 'rijen';
        //this.target.section = target;
        this.requestData(target, "render");
    }

    requestTabel() {
        
    }
    
    /* FUNCTIES - Rendering
     ========================================================================== */
    
    renderTabel(target, data, knopTarget) {
        
    }
    
    renderRijenContent(target, data) {
        this.ajaxData.bladz = data.bladz;
        this.ajaxData.aantalBladz = data.aantalBladz;
        this.ajaxData.toonGebruikers = data.toonGebruikers;
        this.ajaxData.aantalGebruikers = data.aantalGebruikers;
        
        this.target.aantalGebruikers.innerHTML = data.toonGebruikers + '/' + data.aantalGebruikers;
        
        //html aanmaken
        //let loc = document.getElementById("tC-gebruikersOverzicht");
        let content = this.rijenContentAanmaken(target, data);    
    }
    
    renderContainer(data) {
        //Parameters updaten
        
        /* serverside update required
         * 
        this.ajaxData.meta = data.meta;
        this.ajaxData.keys = data.keys;
        this.ajaxData.titels = data.titels;
        */
        
        this.ajaxData.bladz = data.bladz;
        this.ajaxData.aantalBladz = data.aantalBladz;
        this.ajaxData.toonGebruikers = data.toonGebruikers;
        this.ajaxData.aantalGebruikers = data.aantalGebruikers;
        
        let keys = this.ajaxData.keys;
        let type = this.type;
        keys.forEach(key => {
            if (key.toLowerCase().includes("datum"))
                type.rij[key] = "datum";
        });
        
        //html aanmaken
        let content = this.containerAanmaken(data);
        //this.tabelHTML = this.tabelAanmaken();
        
        //html toevoegen aan pagina
        this.target.section.appendChild(content);    
    }

    renderHTML(target, data) {
        let requestType = this.ajaxData.requestType;
        
        //DEVa-2-2 tijdelijk verwijderen als interface af is--------------
        data = this.ajaxData.data;
        //--------------------------------------------------------------
        
        if(requestType === 'container') this.renderContainer(data);
        else if(requestType === 'rijen') this.renderRijenContent(target, data);

    }

    /* FUNCTIES - Aanmaken
     ========================================================================== */

    navKnop(naam) {
        let a = document.createElement("A");
        let i = document.createElement("I");
        a.name = capFirstLetter(naam);
        a.setAttribute("role", "button");
        a.classList.add('bt-' + naam);
        //i.classList.add("material-icons");
        a.appendChild(i);
        this.target.knoppen[naam] = a;
        return a;
    }

    actieKnop(naam, id) {
        let a = document.createElement("A");
        let i = document.createElement("I");
        a.name = naam;
        a.setAttribute("role", "button");
        a.setAttribute("jsonID", id);
        a.appendChild(i);
        return a;
    }

    actieKnoppenAanmaken(id, actie) {
        let div = document.createElement("DIV");
        let a;
        div.classList.add("actie-images");
        if (actie === "aanpassen") {
            a = this.actieKnop("opslaan", id);
            div.appendChild(a);
            a = this.actieKnop("annuleren", id);
            div.appendChild(a);
        } else if (actie === "verwijderen") {
            a = this.actieKnop("verwijderen_bevestigen", id);
            div.appendChild(a);
            a = this.actieKnop("verwijderen_annuleren", id);
            div.appendChild(a);
        } else if (actie === "toevoegen") {
            a = this.actieKnop("gebruiker_opslaan", id);
            div.appendChild(a);
            a = this.actieKnop("gebruiker_annuleren", id);
            div.appendChild(a);
        } else {
            a = this.actieKnop("aanpassen", id);
            div.appendChild(a);
            a = this.actieKnop("verwijderen", id);
            div.appendChild(a);
        }

        return div;
    }

    kolommenAanmaken(target) {
        let tr = document.createElement("TR");
        //let tr = target.insertRow(0);
        let titels = this.titels;
        let titel;
        let th;
        let a;
        let val;

        for (let titel of titels) {
            th = document.createElement("TH");
            a = document.createElement("A");

            th.scope = "col";
            th.title = titel;
            a.innerHTML = titel;

            th.appendChild(a);
            tr.appendChild(th);
        }
        target.appendChild(tr);
    }

    celAanmaken(target, inhoud, key, rijNr, celNr, actie) {
        let cel = document.createElement("TD");
        //let cel = target.insertCell(celNr);
        let titel = this.titels[celNr];
        let type = this.type.rij[key];

        if (inhoud === undefined)
            inhoud = '';

        cel.setAttribute("data-label", titel);

        if (actie === "aanpassen" || actie === "toevoegen") {
            if (key !== undefined) {

                let meta = this.ajaxData.meta;
                let tag = this.tagDetectie(key);

                if (tag === "SELECT") {
                    let select = document.createElement("SELECT");
                    select.name = key;
                    meta[key].forEach(function (val) {
                        let option = document.createElement("OPTION");
                        option.value = val;
                        if (val.toLowerCase() === inhoud.toLowerCase())
                            option.setAttribute("selected", true);
                        option.innerHTML = val;
                        select.appendChild(option);
                    });
                    cel.appendChild(select);
                    this.target.rij[key] = select;
                }

                if (tag === "INPUT") {
                    let input = document.createElement("INPUT");
                    input.name = key;
                    if (type === "datum" || (inhoud.length === 10 && RegExp("([0-9]{2})\/+([0-9]{2})\/+([0-9]{4})").test(inhoud))) {
                        input.type = "date";
                        input.value = datumFormat(inhoud).data;
                    } else {
                        input.type = "text";
                        input.value = inhoud;
                    }
                    cel.appendChild(input);
                    this.target.rij[key] = input;
                }

                this.ajaxData.rijData[key] = inhoud;
            } else if (titel === "Acties") {
                let saveID = 1;
                let div = this.actieKnoppenAanmaken(saveID, actie);
                cel.appendChild(div);
            }

        } else {
            if (titel === "Acties")
                inhoud = this.actieKnoppenAanmaken(this.ajaxData.data.lijst[rijNr].jsonID);
            else if (type === "datum" || (inhoud.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(inhoud)))
                inhoud = document.createTextNode(datumFormat(inhoud).view);
            else
                inhoud = document.createTextNode(inhoud);
            cel.appendChild(inhoud);
        }
        target.appendChild(cel);
        return cel;
    }

    rijAanmaken(target, record, rijNr, actie) {
        let rij = document.createElement("TR");
        //let rij = target.insertRow(rijNr);
        let titels = this.titels;
        let keys = this.ajaxData.keys;
        let cel;
        let key;
        let inhoud;

        for (let celNr = 0; celNr < titels.length; celNr++) {
            key = keys[celNr]
            inhoud = record[key];
            cel = this.celAanmaken(rij, inhoud, key, rijNr, celNr, actie);
        }
        target.appendChild(rij);
        return rij;
    }

    /*
     rijAanmaken(target, record, rijNr) {
     let key;
     let tr = document.createElement("TR");
     //let tr = target.insertRow(rijNr);
     
     let titels = this.titels,
     typeLijst = this.type,
     titel,
     type,
     cel,
     val;
     
     for (let celNr = 0; celNr < titels.length; celNr++) {
     titel = titels[celNr];
     type = typeLijst[key];
     key = this.ajaxData.keys[celNr];
     val = record[key];
     
     if (titel === "Acties")
     val = this.actieKnoppenAanmaken(this.ajaxData.data.lijst[rijNr].jsonID);
     else if (type === "datum" || (val.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(val)))
     val = document.createTextNode(datumFormat(val).view);
     else
     val = document.createTextNode(val);
     
     cel = tr.insertCell(celNr);
     cel.setAttribute("data-label", titel)
     cel.appendChild(val);
     }
     target.appendChild(tr);
     return tr;
     }
     */
    rijenContentAanmaken(target, data) {
        target.innerHTML = '';
        let lijst = data.lijst,
                tr,
                i = -1;
        for (let record of lijst) {
            i++;
            tr = this.rijAanmaken(target, record, i);
        }
        return target;
    }

    rijenAanmaken(target, data, type) {
        let tag = document.createElement(type);

        if (type === "THEAD") {
            this.kolommenAanmaken(tag);
            this.target.thead = tag;
        } else if (type === "TFOOT") {
            let tr = this.rijAanmaken(tag, '', 0, "toevoegen");
        } else if (type === "TBODY") {
            tag = this.rijenContentAanmaken(tag, data);
        }
        target.appendChild(tag);
        return tag;
    }

    tabelAanmaken(data) {
        let tabel = document.createElement("TABLE");
        let thead = this.rijenAanmaken(tabel, data, "THEAD");
        let tfoot = this.rijenAanmaken(tabel, data, "TFOOT");
        let tbody = this.rijenAanmaken(tabel, data, "TBODY");
        tabel.id = 't-' + this.id;
        tabel.classList.add("rijOverzicht");
        this.target.table = tabel;
        tbody.id = 'tC-' + this.id;

        return tabel;
    }

    containerAanmaken(data) {
        
        let ajaxData = this.ajaxData;

        let container = document.createElement("DIV");
        container.id = 'c-' + this.id;
        container.setAttribute("role", "container");
        container.setAttribute("modus", "kort");
        container.classList.add("tabel-container");
        this.target.container = container;

        let header = document.createElement("DIV");
        header.setAttribute("role", "header");
        header.classList.add("tabel-nav");
        this.target.header = header;

        let zoeken = document.createElement("DIV");
        zoeken.setAttribute("role", "wrapper");
        zoeken.classList.add("w-zoeken");
        this.target.zoeken = zoeken;

        let btZoeken = this.navKnop("zoeken");
        let zoekterm = document.createElement("INPUT");
        zoekterm.name = "zoekterm";
        zoekterm.type = "text";
        this.target.zoekterm = zoekterm;

        let knoppen = document.createElement("DIV");
        knoppen.setAttribute("role", "wrapper");
        knoppen.classList.add("w-knoppen");

        let btGebruikerToevoegen = this.navKnop("gebruiker_toevoegen");
        let btEerste = this.navKnop("eerste");
        let btVorige = this.navKnop("vorige");
        let btVolgende = this.navKnop("volgende");
        let btLaatste = this.navKnop("laatste");
        if (ajaxData.bladz === 1) {
            btEerste.classList.add("inactive");
            btVorige.classList.add("inactive");
        }
        if (ajaxData.bladz === ajaxData.aantalBladz) {
            btVolgende.classList.add("inactive");
            btLaatste.classList.add("inactive");
        }

        let content = document.createElement("DIV");
        content.setAttribute("role", "content");

        let contentWrapper = document.createElement("DIV");
        contentWrapper.setAttribute("role", "wrapper");
        contentWrapper.classList.add('w-' + this.id);
        this.target.contentWrapper = contentWrapper;

        let footer = document.createElement("DIV");
        footer.setAttribute("role", "footer");
        this.target.footer = footer;

        let aantalGebruikersContainer = document.createElement("P");
        aantalGebruikersContainer.classList.add("aantalGebruikersContainer");
        this.target.aantalGebruikersContainer = aantalGebruikersContainer;

        let aantalGebruikersTekst = document.createTextNode("Aantal gebruikers: ");
        let aantalGebruikers = document.createElement("SPAN");
        aantalGebruikers.innerHTML = this.ajaxData.data.toonGebruikers + '/' + this.ajaxData.data.aantalGebruikers;
        this.target.aantalGebruikers = aantalGebruikers;

        knoppen.appendChild(btGebruikerToevoegen);
        knoppen.appendChild(btEerste);
        knoppen.appendChild(btVorige);
        knoppen.appendChild(btVolgende);
        knoppen.appendChild(btLaatste);

        zoeken.appendChild(btZoeken);
        zoeken.appendChild(zoekterm);

        header.appendChild(zoeken);
        header.appendChild(knoppen);

        content.appendChild(contentWrapper);

        contentWrapper.appendChild(this.tabelAanmaken(data));

        aantalGebruikersContainer.appendChild(aantalGebruikersTekst);
        aantalGebruikersContainer.appendChild(aantalGebruikers);

        footer.appendChild(aantalGebruikersContainer);

        container.appendChild(header);
        container.appendChild(content);
        container.appendChild(footer);

        return container;
    }

    /* FUNCTIES - Aanpassen
     ========================================================================== */

    celAanpassen(cel, celNr) {
        let key = this.ajaxData.keys[celNr];
        let titel = this.titels[celNr];
        let type = this.type.rij[key];
        let dataLabel = cel.getAttribute("data-label");
        let inhoud = cel.innerText;
        cel.innerHTML = '';
        console.log(dataLabel);

        if (dataLabel === null || dataLabel === titel) {

            if (key !== undefined) {
                let meta = this.ajaxData.meta;
                let tag = this.tagDetectie(key);
                console.log(tag);

                if (tag === "SELECT") {
                    let select = document.createElement("SELECT");
                    select.name = key;
                    meta[key].forEach(function (val) {
                        let option = document.createElement("OPTION");
                        option.value = val;
                        if (val.toLowerCase() === inhoud.toLowerCase())
                            option.setAttribute("selected", true);
                        option.innerHTML = val;
                        select.appendChild(option);
                    });
                    cel.appendChild(select);
                    this.target.rij[key] = select;
                }

                if (tag === "INPUT") {
                    let input = document.createElement("INPUT");
                    input.name = key;
                    if (type === "datum" || (inhoud.length === 10 && RegExp("([0-9]{2})\/+([0-9]{2})\/+([0-9]{4})").test(inhoud))) {
                        input.type = "date";
                        input.value = datumFormat(inhoud).data;
                    } else {
                        input.type = "text";
                        input.value = inhoud;
                    }
                    cel.appendChild(input);
                    this.target.rij[key] = input;
                }

                this.ajaxData.rijData[key] = inhoud;
            } else if (titel === "Acties") {
                let saveID = 1;
                let div = this.actieKnoppenAanmaken(saveID, "aanpassen");
                cel.appendChild(div);
            }
        }

        console.log(this.target.rij);

        return cel;
    }

    rijAanpassen(rij, saveID) {
        let cellen = rij.cells;
        let cel;
        let keys = this.ajaxData.keys;
        let key;
        this.backup.rij = {};

        for (let celNr = 0; celNr < cellen.length; celNr++) {
            key = keys[celNr];
            cel = cellen[celNr];
            this.backup.rij[key] = cel.innerHTML;
            cel = this.celAanpassen(cel, celNr);
        }

    }

    celMarkeren(cel, celNr) {
        let titel = this.titels[celNr];
        let dataLabel = cel.getAttribute("data-label");
        cel.innerHTML = '';

        if (dataLabel === titel) {
            let editID = 1;
            let div = this.actieKnoppenAanmaken(editID, "verwijderen");
            cel.appendChild(div);
        }
        return cel;
    }

    rijMarkeren(rij, editID) {
        let cellen = rij.cells;
        let celNr = cellen.length - 1;
        let cel = cellen[celNr];
        let keys = this.ajaxData.keys;
        let key = keys[celNr];
        this.backup.rij = {};
        this.backup.rij[key] = cel;
        cel = this.celMarkeren(cel, celNr);
    }

    /* FUNCTIES - Opslaan
     ========================================================================== */

    celOpslaan(cellen, celNr) {
        let cel = cellen[celNr];
        let titel = this.titels[celNr];
        let key = this.ajaxData.keys[celNr];
        let type = this.type.rij[key];
        let dataLabel = cel.getAttribute("data-label");
        let tag = this.tagDetectie(key);
        let inhoud = '';

        if (dataLabel === titel) {
            if (tag === 'INPUT') {
                inhoud = this.target.rij[key].value;
                if (tag === 'INPUT' && inhoud.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(inhoud))
                    inhoud = datumFormat(inhoud).view;
                //inhoud = document.createTextNode(inhoud);
            } else if (tag === 'SELECT') {
                inhoud = this.target.rij[key].value;
                //inhoud = document.createTextNode(inhoud);
            } else if (titel === "Acties") {
                let backup = this.backup.rij;
                inhoud = this.backup.rij[key];
                //this.actieKnoppenAanmaken(this.ajaxData.editID);
            }
        }
        cellen[celNr].innerHTML = inhoud;
        console.log(inhoud);
    }

    rijOpslaan(rij) {
        let cellen = rij.cells;
        for (let celNr = 0; celNr < cellen.length; celNr++) {
            this.celOpslaan(cellen, celNr);
        }
    }

    /* FUNCTIES - Herstellen
     ========================================================================== */

    rijHerstellen(rij) {
        let cellen = rij.cells;
        let cel;
        let keys = this.ajaxData.keys;
        let key;
        for (let celNr = 0; celNr < cellen.length; celNr++) {
            key = keys[celNr];
            cel = cellen[celNr];
            cel.innerHTML = this.backup.rij[key];
        }
    }

    celTerugzetten(cel, celNr) {
        let titel = this.titels[celNr];
        let dataLabel = cel.getAttribute("data-label");
        cel.innerHTML = '';

        if (dataLabel === titel) {
            let editID = 1;
            let div = this.actieKnoppenAanmaken(editID);
            cel.appendChild(div);
        }
        return cel;
    }

    rijTerugzetten(rij) {
        let cellen = rij.cells;
        let celNr = cellen.length - 1;
        let cel = cellen[celNr];
        cel = this.celTerugzetten(cel, celNr);
    }

    /* FUNCTIES - Verwijderen
     ========================================================================== */

    rijVerwijderen(rij) {}

    /*
     requestData(type, target) {
     let hasTarget = false;
     let xhttp = new XMLHttpRequest();
     let data = "";
     
     if (target !== undefined && target.hasAttribute("name"))
     hasTarget = true;
     else
     hasTarget = false;
     if (hasTarget) target.classList.add("active");
     
     xhttp.open("POST", "someservlet", true);
     xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
     xhttp.onreadystatechange = function() {
     if (this.readyState === 4 && this.status === 200) {
     data = JSON.parse(xhttp.responseText);
     if (hasTarget) target.classList.remove("active");
     console.log(data);
     renderHTML(type, data);
     }
     };
     xhttp.send(this.params);
     }
     
     renderHTML(type, data) {
     if (type === "tabel") {
     this.ajaxData.meta = {
     rol: ["admin", "leerkracht", "cursist", "secretariaat"]
     };
     this.ajaxData.aantalBladz = data.aantalBladz;
     let lijst = data.lijst;
     let tabel = this.tabelAanmaken(lijst, parameters, titels);
     let inhoud = document.getElementById(this.tabelID);
     if (!inhoud.contains(tabel)) {
     while (inhoud.firstChild) inhoud.removeChild(inhoud.firstChild);
     inhoud.appendChild(tabel);
     } else {
     inhoud = document.querySelector('#'+ this.tabelID +' tbody');
     lijst.forEach(function(e) {
     let tr = this.rijAanmaken(e, parameters, titels, inhoud);
     });
     }
     } else if (type === "aanpassen")
     this.rijAanpassen(rij, meta, parameters, lijst);
     else if (type === "opslaan")
     this.rijOpslaan(rij, parameters, titels);
     else
     console.log(data);
     document.getElementById("gebruikers").innerHTML = data.toonGebruikers + '/' + data.aantalGebruikers;
     }
     */
    
    /* FUNCTIES - onclick
     ========================================================================== */
    
    paginaKnoppenRechten() {
        let ajaxData = this.ajaxData;
        let knoppen = this.target.knoppen;
        if (ajaxData.bladz === 1) {
            knoppen.eerste.classList.add("inactive");
            knoppen.vorige.classList.add("inactive");
            knoppen.volgende.classList.remove("inactive");
            knoppen.laatste.classList.remove("inactive");
        } else if (ajaxData.bladz === ajaxData.aantalBladz) {
            knoppen.eerste.classList.remove("inactive");
            knoppen.vorige.classList.remove("inactive");
            knoppen.volgende.classList.add("inactive");
            knoppen.laatste.classList.add("inactive");
        } else {
            knoppen.eerste.classList.remove("inactive");
            knoppen.vorige.classList.remove("inactive");
            knoppen.volgende.classList.remove("inactive");
            knoppen.laatste.classList.remove("inactive");
        }
    }
    
    eerstePagina() {
        let loc = document.getElementById("tC-gebruikersOverzicht");
        let bladz = 1;
        this.ajaxData.bladz = bladz;
        this.ajaxData.params = 'page=' + bladz;
        this.requestRijenContent(loc);
        this.paginaKnoppenRechten();
    }
    vorigePagina() {
        this.ajaxData.bladz--;
        let loc = document.getElementById("tC-gebruikersOverzicht");
        this.ajaxData.params = 'page=' + this.ajaxData.bladz;
        this.requestRijenContent(loc);
        this.paginaKnoppenRechten();
    }
    volgendePagina() {
        this.ajaxData.bladz++;
        let loc = document.getElementById("tC-gebruikersOverzicht");
        this.ajaxData.params = 'page=' + this.ajaxData.bladz;
        this.requestRijenContent(loc);
        this.paginaKnoppenRechten();
    }
    laatstePagina() {
        let loc = document.getElementById("tC-gebruikersOverzicht");
        let bladz = this.ajaxData.aantalBladz;
        this.ajaxData.bladz = bladz;
        this.ajaxData.params = 'page=' + bladz;
        this.requestRijenContent(loc);
        this.paginaKnoppenRechten();
    }

}