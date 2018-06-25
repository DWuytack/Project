
document.addEventListener('DOMContentLoaded', laadBijStart());
var aantalGebruikers=2;



function laadBijStart() {

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "GebruikersServlet?actie=laadGebruikers", true);
    xhttp.send();
    //als de doelstellingen arriveren...
    xhttp.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            gebruikers = JSON.parse(xhttp.responseText);
            for (x = 0; x < gebruikers.length; x++) {
                
                aantalGebruikers=aantalGebruikers+1;
               laadLijn(gebruikers[x],aantalGebruikers);
            }
        }
    };

function laadLijn(gebruiker,rijnr){
    
     
    overzichtTable = document.getElementById("overzichtTable");
    //maak een extra rij
    var row = overzichtTable.insertRow(rijnr);
    if (rijnr % 2 !== 0)
        row.style.backgroundColor = "#ceccca";
    row.id = "row" + rijnr;
    row.name = "rij" + rijnr;
    row.style.height = "30px";
    //lege cel
    row.insertCell(0);
    var voornaamVak = row.insertCell(1);
    voornaamVak.innerHTML=gebruiker.voornaam;
    //lege cel
    row.insertCell(2);
    var achternaamVak=row.insertCell(3);
    achternaamVak.innerHTML=gebruiker.achternaam;
    //lege cel
    row.insertCell(4);
    //we voorzien een vak voor onze kernvakjes
    var loginVak = row.insertCell(5);
    loginVak.innerHTML=gebruiker.login;
    //lege cel
    row.insertCell(6);
    //we voorzien een vak voor de scores
    var rolVak = row.insertCell(7);
    rolVak.innerHTML=gebruiker.rol;
    //lege cel
    row.insertCell(8);
    //we voorzien een vak voor commentaar;
    var geboorteDatumVak = row.insertCell(9);
    geboorteDatumVak.innerHTML=gebruiker.geboorteDatum;
    //lege cel
    row.insertCell(10);
    //we trekken er een streep onder
    var emailVak=row.insertCell(11);
    emailVak.innerHTML=gebruiker.email;
    //lege cel
    row.insertCell(12);
    var actiesVak=row.insertCell(13);
    actiesVak.style.display="inline";
    var potlood=document.createElement("INPUT");
    potlood.setAttribute("type", "image"); 
    potlood.src='images/pencil.png';
    actiesVak.appendChild(potlood);           
    var vuilbak=document.createElement("INPUT");
    vuilbak.setAttribute("type", "image");
    vuilbak.src='images/vuilbak.png';
    actiesVak.appendChild(vuilbak);
    row.insertCell(14);
    
   
}   






}