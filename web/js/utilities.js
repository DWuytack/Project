var utilities = {
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
        text = document.createTextNode(e[t]);
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
        th.title = "Acties";
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
        if(val === e.toLowerCase())
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
      input.value = e;
      if(e.length === 10 && RegExp("([0-9]{4})\-+([0-9]{2})\-+([0-9]{2})").test(e))
        input.type = "date";	
      else
        input.type = "text";
      return input;
    }
  },
  rijAanpassen : function(e, meta) {
    var cellen = e.childNodes;
    var i = -1;
    backup = [];
    cellen.forEach(function(loc) {
      i++;
      var titel = loc.getAttribute("data-label");
      var value = loc.innerText;
      while(loc.firstChild)
        loc.removeChild(loc.firstChild);
      var elem = utilities.celAanpassen(value, titel, meta);
      loc.appendChild(elem);
      backup.push(loc.innerHTML);
    });
    console.log(backup);
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