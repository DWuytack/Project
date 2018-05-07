/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.addEventListener("click", function(e) {
  var target = e.target;
  if (target.classList.contains("toggle")) {
    var parent = target.parentElement;
    if (parent.hasAttribute("role") && parent.getAttribute("role") === "menu-header") {
      var loc = parent.nextSibling.nextSibling; /* Bypass hidden element */
      if (loc.hasAttribute("role") && loc.getAttribute("role") === "menu-content")
        loc.classList.toggle("active");
    }
  }
});

function findMenuHeader(target) {
	var parent = target.parentElement;
  if (parent.hasAttribute("role") && parent.getAttribute("role") === "menu-header")
  	return parent;
  console.error("Error: locatie van menu-header is niet gevonden.");
  return false;
}

function findMenuContent(header) {
	for (var e of header.parentElement.children) {
    if (e.hasAttribute("role") && e.getAttribute("role") === "menu-content")
      return e;
  }
  console.error("Error: locatie van menu-content is niet gevonden.");
  return false;
}

function createBrance(target, name, view, d) {
  var value = target.value;
  var parent = findMenuHeader(target); //menu-header
  if (parent) {
    var loc = findMenuContent(parent); //menu-content
    if (loc) {
      if (target.value === "0") {
        loc.classList.remove("active");
      } else {
        loc.classList.add("active");
        var header = document.createElement("DIV");
        var content = document.createElement("DIV");
        header.setAttribute("role", "menu-header")
        content.setAttribute("role", "menu-content")

        var inhoud = "";
        var select = document.createElement("SELECT");
        var option = document.createElement("OPTION");
        select.name = name;
        option.value = "0";
        option.innerHTML = view;
        select.appendChild(option);

        for (var val of d[value]) {
          option = document.createElement("OPTION");
          option.value = val.value;
          option.innerHTML = val.view;
          select.appendChild(option);
        }
        header.appendChild(select);
        loc.innerHTML = "";
        loc.appendChild(header);
        loc.appendChild(content);
      }
    }
  }
}

document.addEventListener("change", function(e) {
  var target = e.target;

  if (target.name === "studiegebieden")
  	createBrance(target, "opleidingen", "Kies Uw Opleiding", data.opleiding);
  else if (target.name === "opleidingen")
  	createBrance(target, "modules", "Kies Uw Module", data.module);
  else if (target.name === "modules")
    createBrance(target, "doelstellingen", "Kies Uw Doelstelling", data.doelstelling);
 else if (target.name === "doelstellingen")
    createBrance(target, "taken", "Kies Uw Taak", data.taak);
    
});

