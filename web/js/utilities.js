let utilities = {
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
                    let div = utilities.actieKnoppenAanmaken("tonen");
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
    rijAanpassen : function(e, meta, parameters) {
        let cellen = e.childNodes;
        let i = -1;
        backup = [];
        cellData = {};
        parameters.forEach(function(parameter){
            i++;
            let loc = cellen[i];
            let titel = loc.getAttribute("data-label");
            let value = loc.innerText;
            backup.push(loc.innerHTML);
            let elem = utilities.celAanpassen(value, titel, parameter, meta);
            while(loc.firstChild)
                loc.removeChild(loc.firstChild);
            loc.appendChild(elem);
        });
        i++;
        if(cellen[i].getAttribute("data-label") === "Acties") {
            let div = utilities.actieKnoppenAanmaken("aanpassen");
            cellen[i].innerHTML = '';
            cellen[i].appendChild(div);
        }
    },
    rijBewerken : function() {
      
    },
    rijHerstellen : function(rij, parameters, titels, backup) {
        let cellen = rij.childNodes;
        let i2 = -1;
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
    },
    rijOpslaan : function(rij, tabelID, parameters, titels) {
        let cellen = rij.childNodes;
        let cel;
        let i2 = -1;
        parameters.forEach(function(cel){
            i2++;
            cel = cellen[i2];
            let celInhoud = document.querySelector('#' + tabelID + ' [name="' + parameters[i2].toLowerCase() + '"]');
            celWaarde = celInhoud.value;
            if(celWaarde.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(celWaarde))
                celWaarde = utilities.datumFormat(celWaarde).view;
            cel.innerHTML = '<span>' + celWaarde + '</span>';
        });
        i2++;
        if(titels[i2] === "Acties") {
            cellen[i2].innerHTML = '';
            cellen[i2].append( utilities.actieKnoppenAanmaken("tonen") );
        }
    },
    vindRij : function(e) {
        let row = e.parentElement;
        while(row.tagName !== "TR") row = row.parentElement;
        return row;
    },
    actieKnoppenAanmaken : function(status) {
        let div = document.createElement("DIV");
        let span = "";
        div.classList.add("actie-images");
        if(status === "aanpassen") {
            span = utilities.actieKnop("idOpslaan", "images/green.png", "opslaan");
            div.appendChild(span);
            span = utilities.actieKnop("idAnnuleren", "images/cancel.png", "annuleren");
            div.appendChild(span);
        } else {
            span = utilities.actieKnop("idEdit", "images/pencil.png", "aanpassen");
            div.appendChild(span);
            span = utilities.actieKnop("idDelete", "images/vuilbak.png", "verwijderen");
            div.appendChild(span);
        }

        return div;
    },
    actieKnop : function(name, src, role) {
        let span = document.createElement("SPAN");
        let input = document.createElement("INPUT");
        input.type = "image";
        input.name = name;
        input.value = "1000";
        input.src = src;
        input.setAttribute("role", role);
        span.appendChild(input);
        return span;
    }
};