const utilities = {
    editID : 0,
    saveID : 0,
    backup : [],
    editRow : [],
    backupData : [],
    pageCounter : 1,
    params : '',
    tableID : '',
    requestData : function(type, target) {
        let hasTarget = false;
        let xhttp = new XMLHttpRequest();
        let data = "";

        if(target !== undefined && target.hasAttribute("name")) hasTarget = true;
        if(hasTarget) target.classList.add("active");

        xhttp.open("POST", "someservlet", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                // Typical action to be performed when the document is ready:
                data = JSON.parse(xhttp.responseText);
                if(hasTarget) target.classList.remove("active");
                console.log(data);
                renderHTML(type, data);
            }
        };
        xhttp.send(utilities.params);
    },
    datumFormat : function(inputFormat) {
        let pad = function(s) { return (s < 10) ? '0' + s : s; };
        if(RegExp("([0-9]{2})\/+([0-9]{2})\/+([0-9]{4})").test(inputFormat))
            inputFormat = inputFormat.split("/").reverse().join("-"); 
        let d = new Date(inputFormat);
        return {
            data : inputFormat,
            view : [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/')
        }
    },
    tabelAanmaken : function(data, parameters, titels){
        let tabel = document.createElement("TABLE");
      	let thead = tabel.createTHead();
      	let tbody = tabel.createTBody();
        let tr;
        
        //thead
        tr = utilities.rijAanmaken("kolom", parameters, titels, thead);
        tabel.appendChild(thead);
        
        //tbody
        data.forEach(function(e) {
            tr = utilities.rijAanmaken(e, parameters, titels, tbody);
        });
        tabel.appendChild(tbody);
        
        return tabel;
    },
    rijAanmaken : function(e, parameters, titels, target) {
        let tr = target.insertRow(0);
        let teller = -1;
        titels.forEach(function(titel){
            teller++;
            let parameter = parameters[teller];
            if(target.tagName === "THEAD") {
                let th = document.createElement("TH");
                let a = document.createElement("A");
                th.scope = "col";
                th.title = titel.toLowerCase();
                a.innerHTML = titel;
                th.appendChild(a);
                tr.appendChild(th);
            } else {
                let td = tr.insertCell(teller);
                td.setAttribute("data-label", titel);

                if(titel === "Acties") {
                    let div = utilities.actieKnoppenAanmaken("tonen", e.jsonID);
                    td.appendChild(div);
                } else {
                    let span = document.createElement("SPAN");
                    let text = "";
                    let celInhoud = e[parameter];
                    if(celInhoud.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(celInhoud))
                        celInhoud = utilities.datumFormat(celInhoud).view;
                    text = document.createTextNode(celInhoud);
                    span.appendChild(text);
                    td.appendChild(span);
                }
            }
        });
        
        return tr;
    },
    tagDetectie : function (titel, meta) {
        if(Object.keys(meta).includes(titel.toLowerCase()))
          return "SELECT";
        else if(titel === "Acties")
          return "DIV"; 
        else
          return "INPUT";
    },
    celAanpassen : function(e, titel, parameter, meta) {
        let tag = utilities.tagDetectie(titel, meta);
        let div, input, span;
        if(tag === "SELECT") {
            let select = document.createElement("SELECT");
            select.name = parameter.toLowerCase();
            meta[titel.toLowerCase()].forEach(function(val){
                let option = document.createElement("OPTION");
                option.value = val.toLowerCase();
                if(val.toLowerCase() === e.toLowerCase())
                    option.setAttribute("selected", true);
                option.innerHTML = val;
                select.appendChild(option);
            });
            return select;
        } else if(tag === "INPUT") {
            input = document.createElement("INPUT");
            input.name = parameter.toLowerCase();
            if(e.length === 10 && RegExp("([0-9]{2})\/+([0-9]{2})\/+([0-9]{4})").test(e)) {
                input.type = "date";
                input.value = utilities.datumFormat(e).data;
            } else {
                input.type = "text";
                input.value = e;
            }
            return input;
        }
    },
    rijAanpassen : function(e, meta, parameters, saveID) {
        let cellen = e.childNodes;
        let i = -1;
        utilities.backup = [];
        utilities.backupData = [];
        utilities.saveID = saveID;
        cellData = {};
        utilities.editRow = [];
        parameters.forEach(function(parameter){
            i++;
            let loc = cellen[i];
            let titel = loc.getAttribute("data-label");
            let value = loc.innerText;
            utilities.backup.push(loc.innerHTML);
            let elem = utilities.celAanpassen(value, titel, parameter, meta);
            while(loc.firstChild)
                loc.removeChild(loc.firstChild);
            loc.appendChild(elem);
            utilities.editRow.push(document.querySelector('[name="' + parameter.toLowerCase() + '"]'));
        });
        i++;
        if(cellen[i].getAttribute("data-label") === "Acties") {
            let div = utilities.actieKnoppenAanmaken("aanpassen", saveID);
            cellen[i].innerHTML = '';
            cellen[i].appendChild(div);
        }
    },
    rijBewerken : function() {
      
    },
    rijHerstellen : function(rij, parameters, titels) {
        let cellen = rij.childNodes;
        let i2 = -1;
        parameters.forEach(function(parameter){
            i2++;
            cel = cellen[i2];
            cel.innerHTML = utilities.backup[i2];
        });
        i2++;
        if(titels[i2] === "Acties") {
            cellen[i2].innerHTML = '';
            cellen[i2].append( utilities.actieKnoppenAanmaken("tonen", utilities.editID) );
        }
    },
    rijOpslaan : function(rij, parameters, titels) {
        let cellen = rij.childNodes;
        let cel;
        let i2 = -1;
        parameters.forEach(function(cel){
            i2++;
            cel = cellen[i2];
            let celInhoud = document.querySelector('#' + utilities.tabelID + ' [name="' + parameters[i2].toLowerCase() + '"]');
            celWaarde = celInhoud.value;
            if(celWaarde.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(celWaarde))
                celWaarde = utilities.datumFormat(celWaarde).view;
            cel.innerHTML = '<span>' + celWaarde + '</span>';
        });
        i2++;
        if(titels[i2] === "Acties") {
            cellen[i2].innerHTML = '';
            cellen[i2].append( utilities.actieKnoppenAanmaken("tonen", utilities.editID) );
        }
    },
    vindRij : function(e) {
        let row = e.parentElement;
        while(row.tagName !== "TR") row = row.parentElement;
        return row;
    },
    actieKnoppenAanmaken : function(status, id) {
        let div = document.createElement("DIV");
        let span = "";
        div.classList.add("actie-images");
        if(status === "aanpassen") {
            span = utilities.actieKnop("idOpslaan", "images/green.png", "opslaan", id);
            div.appendChild(span);
            span = utilities.actieKnop("idAnnuleren", "images/cancel.png", "annuleren", id);
            div.appendChild(span);
        } else {
            span = utilities.actieKnop("idEdit", "images/pencil.png", "aanpassen", id);
            div.appendChild(span);
            span = utilities.actieKnop("idDelete", "images/vuilbak.png", "verwijderen", id);
            div.appendChild(span);
        }

        return div;
    },
    actieKnop : function(name, src, role, id) {
        let span = document.createElement("SPAN");
        let input = document.createElement("INPUT");
        input.type = "image";
        input.name = name;
        input.value = id;
        input.src = src;
        input.setAttribute("role", role);
        span.appendChild(input);
        return span;
    }
};