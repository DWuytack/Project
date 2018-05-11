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
        parameters.forEach(function(t){
            var text = "";
            teller++;
            if(e === "kolom") {
                var th = document.createElement("TH");
                var a = document.createElement("A");
                text = "";
                th.scope = "col";
                th.title = t;
                a.innerHTML = titels[teller];
                th.appendChild(a);
                tr.appendChild(th);
            } else {
                var td = document.createElement("TD");
                var span = document.createElement("SPAN");
                text = "";
                td.setAttribute("data-label", titels[teller]);
                var celInhoud = e[t];
                if(celInhoud.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(celInhoud))
                    celInhoud = utilities.datumFormat(celInhoud).view;
                text = document.createTextNode(celInhoud);
                span.appendChild(text);
                td.appendChild(span);
                tr.appendChild(td);
            }
        });
        if(e === "kolom") {
            var th = document.createElement("TH");
            var a = document.createElement("A");
            text = "";
            th.scope = "col";
            th.title = "acties";
            a.innerHTML = "Acties";
            th.appendChild(a);
            tr.appendChild(th);
        } else {
            var td = document.createElement("TD");
            td.setAttribute("data-label", "Acties");
            var div = utilities.actieKnoppenAanmaken("tonen");
            td.appendChild(div);
            tr.appendChild(td);
        }
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
    celAanpassen : function(e, titel, meta) {
        var tag = utilities.tagDetectie(titel, meta);
        var div, input, span;
        if(tag === "SELECT") {
            var select = document.createElement("SELECT");
            select.name = titel.toLowerCase();
            meta[titel.toLowerCase()].forEach(function(val){
                var option = document.createElement("OPTION");
                option.value = val.toLowerCase();
                if(val.toLowerCase() === e.toLowerCase())
                    option.setAttribute("selected", true);
                option.innerHTML = val;
                select.appendChild(option);
            });
            return select;
        } else if(tag === "DIV" && titel === "Acties") {
            div = utilities.actieKnoppenAanmaken("aanpassen");
            return div;
        } else if(tag === "INPUT") {
            input = document.createElement("INPUT");
            input.name = titel.toLowerCase();
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
    rijAanpassen : function(e, meta) {
        var cellen = e.childNodes;
        var i = -1;
        backup = [];
        cellData = {};
        cellen.forEach(function(loc) {
            i++;
            var titel = loc.getAttribute("data-label");
            var value = loc.innerText;
            backup.push(loc.innerHTML);
            console.log(loc);
            var elem = utilities.celAanpassen(value, titel, meta);
            while(loc.firstChild)
                loc.removeChild(loc.firstChild);
            loc.appendChild(elem);
        });
        console.log(backup);
    },
    rijBewerken : function() {
      
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