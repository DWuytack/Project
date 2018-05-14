var utilities = {
    datumFormat : function(inputFormat) {
        var pad = function(s) { return (s < 10) ? '0' + s : s; };
        if(RegExp("([0-9]{2})\/+([0-9]{2})\/+([0-9]{4})").test(inputFormat))
            inputFormat = inputFormat.split("/").reverse().join("-"); 
        var d = new Date(inputFormat);
        return {
            data : inputFormat,
            view : [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/')
        }
    },
    tabelAanmaken : function(data, parameters, titel){
        var tabel = document.createElement("TABLE");
        var thead = document.createElement("THEAD");
        var tbody = document.createElement("TBODY");
        var tr = "";
        tr = utilities.rijAanmaken("kolom", parameters, titel);
        thead.appendChild(tr);
        tabel.appendChild(thead);
        data.forEach(function(e) {
            tr = utilities.rijAanmaken(e, parameters, titel);
            tbody.appendChild(tr);
        });
        tabel.appendChild(tbody);
        return tabel;
    },
    rijAanmaken : function(e, parameters, titels) {
        var tr = document.createElement("TR");
        var teller = -1;
        titels.forEach(function(titel){
            var text = "";
            teller++;
            var parameter = parameters[teller];
            if(e === "kolom") {
                var th = document.createElement("TH");
                var a = document.createElement("A");
                text = "";
                th.scope = "col";
                th.title = titel.toLowerCase();
                a.innerHTML = titels[teller];
                th.appendChild(a);
                tr.appendChild(th);
            } else {
                var td = document.createElement("TD");
                td.setAttribute("data-label", titels[teller]);
                if(titels[teller] === "Acties") {
                    var div = utilities.actieKnoppenAanmaken("tonen");
                    td.appendChild(div);
                } else {
                    var span = document.createElement("SPAN");
                    text = "";
                    var celInhoud = e[parameter];
                    if(celInhoud.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(celInhoud))
                        celInhoud = utilities.datumFormat(celInhoud).view;
                    text = document.createTextNode(celInhoud);
                    span.appendChild(text);
                    td.appendChild(span);
                }
                tr.appendChild(td); 
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
        var tag = utilities.tagDetectie(titel, meta);
        var div, input, span;
        if(tag === "SELECT") {
            var select = document.createElement("SELECT");
            select.name = parameter.toLowerCase();
            meta[titel.toLowerCase()].forEach(function(val){
                var option = document.createElement("OPTION");
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
        var cellen = e.childNodes;
        var i = -1;
        backup = [];
        cellData = {};
        parameters.forEach(function(parameter){
            i++;
            var loc = cellen[i];
            var titel = loc.getAttribute("data-label");
            var value = loc.innerText;
            backup.push(loc.innerHTML);
            var elem = utilities.celAanpassen(value, titel, parameter, meta);
            while(loc.firstChild)
                loc.removeChild(loc.firstChild);
            loc.appendChild(elem);
        });
        i++;
        if(cellen[i].getAttribute("data-label") === "Acties") {
            var div = utilities.actieKnoppenAanmaken("aanpassen");
            cellen[i].innerHTML = '';
            cellen[i].appendChild(div);
        }
    },
    rijBewerken : function() {
      
    },
    rijHerstellen : function(rij, parameters, titels, backup) {
        var cellen = rij.childNodes;
        var i2 = -1;
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
        var cellen = rij.childNodes;
        var i2 = -1;
        parameters.forEach(function(cel){
            i2++;
            var cel = cellen[i2];
            var celInhoud = document.querySelector('#' + tabelID + ' [name="' + parameters[i2].toLowerCase() + '"]');
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
        var row = e.parentElement;
        while(row.tagName !== "TR") row = row.parentElement;
        return row;
    },
    actieKnoppenAanmaken : function(status) {
        var div = document.createElement("DIV");
        var span = "";
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
        var span = document.createElement("SPAN");
        var input = document.createElement("INPUT");
        input.type = "image";
        input.name = name;
        input.value = "1000";
        input.src = src;
        input.setAttribute("role", role);
        span.appendChild(input);
        return span;
    }
};