// gebruikersOverzicht.js

(function() {
    
    const   titels = ["Achternaam", "Voornaam", "Login", "Rol", "GebtDatum", "Email", "Acties"],
            keys = ["achternaam", "voornaam", "login", "rol", "geboorteDatum", "email"],
            meta = {
                rol: ["admin", "leerkracht", "cursist", "secretariaat"]
            };
    let     loc, tabel;
    
    var tid = setInterval(function() {
        if ( document.readyState !== 'complete' ) return;
        clearInterval( tid );
        
        //run
        loc = document.getElementById("gebruikersOverzicht");
        let tabel = new Tabel(loc, "gebruikersOverzicht", titels, keys);
        tabel.ajaxData.meta = meta;
        console.log(tabel);
        tabel.requestTabel();
        
        document.addEventListener("click", function(e) {
            let klikRecht = tabel.rechten.klik;
            let focus = tabel.focus;
            let actie = tabel.actie;
            
            console.log("START - ACTIE & FOCUS");
            console.log(tabel.actie);
            console.log(tabel.focus);
            console.log("START - ACTIE & FOCUS static");
            console.log(actie);
            console.log(focus);
            
            let gebruikerToevoegen = target => {
                if(!target.classList.contains("active")) {
                    tabel.target.container.setAttribute("actie", "tfootFocus");
                    tabel.target.table.classList.remove("rijOverzicht");
                    tabel.target.zoeken.classList.remove("active");
                    target.classList.add("active");
                    tabel.focus = "tabel";
                    tabel.actie = "toevoegen";
                } else {
                    target.classList.remove("active");
                    tabel.target.container.removeAttribute("actie", "tfootFocus");
                    tabel.target.table.classList.add("rijOverzicht");
                    tabel.focus = "all";
                    tabel.actie = "";
                }
            };
            let aanpassen = target => {
                tabel.target.container.setAttribute("actie", "rijFocus");
                rij = vindRij(target);
                rij.classList.add("edit");
                let editID = target.getAttribute("jsonID");
                tabel.ajaxData.editID = editID;
                tabel.rijAanpassen(rij, editID);
                tabel.focus = "tabel";
                tabel.actie = "aanpassen";
            };
            let verwijderen = target => {
                tabel.target.container.setAttribute("actie", "rijFocus");
                rij = vindRij(target);
                rij.classList.add("delete");
                let editID = target.getAttribute("jsonID");
                tabel.ajaxData.editID = editID;
                tabel.rijMarkeren(rij);
                tabel.focus = "tabel";
                tabel.actie = "verwijderen";
            };
            let opslaan = target => {
                tabel.target.container.removeAttribute("actie");
                rij = vindRij(target);
                rij.classList.remove("edit");
                tabel.rijOpslaan(rij);
                tabel.focus = "all";
                tabel.actie = "";
            };
            let annuleren = target => {
                tabel.target.container.removeAttribute("actie");
                rij = vindRij(target);
                rij.classList.remove("edit");
                tabel.rijHerstellen(rij);
                tabel.focus = "all";
                tabel.actie = "";
            };
            let verwijderenBevestigen = target => {
                tabel.target.container.removeAttribute("actie");
                rij = vindRij(target);
                rij.classList.remove("delete");
                rij.parentNode.removeChild(rij);
                tabel.focus = "all";
                tabel.actie = "";
            };
            let verwijderenAnnuleren = target => {
                tabel.target.container.removeAttribute("actie");
                rij = vindRij(target);
                rij.classList.remove("delete");
                tabel.rijTerugzetten(rij);
                tabel.focus = "all";
                tabel.actie = "";
            };
            let gebruikerOpslaan = target => {
                tabel.target.knoppen["gebruiker_toevoegen"].classList.remove("active");
                tabel.target.container.removeAttribute("actie");
                tabel.target.table.classList.add("rijOverzicht");
                tabel.focus = "all";
                tabel.actie = "";
            };
            let gebruikerAnnuleren = target => {
                tabel.target.knoppen["gebruiker_toevoegen"].classList.remove("active");
                tabel.target.container.removeAttribute("actie");
                tabel.target.table.classList.add("rijOverzicht");
                tabel.focus = "all";
                tabel.actie = "";
            };
            
            if (klikRecht) {
                let target = e.target;
                let rij;

                if (target.hasAttribute("role")) {
                    let role = target.getAttribute("role");
                    /* Opmerking:
                              Bij het gebruiken van links wees zeker dat de css lijn:
                              'pointer-events: none;' aanstaan bij i elementen. Deze kan afwezig
                              zijn bij oudere browsers
                     */

                    if (role === "button") {

                        if (focus === "all") {
                            
                            if (target.name === "Eerste" && tabel.bladz !== 1) {
                                tabel.bladz = 1;
                                tabel.params = 'page=' + tabel.bladz;
                            } else if (target.name === "Vorige" && tabel.bladz !== 1) {
                                tabel.bladz--;
                                tabel.params = 'page=' + tabel.bladz;
                            } else if (target.name === "Volgende" && tabel.bladz !== tabel.aantalBladz) {
                                tabel.bladz++;
                                tabel.params = 'page=' + tabel.bladz;
                                let loc = document.getElementById("tC-gebruikersOverzicht");
                                tabel.rijenContentAanmaken(loc);
                            } else if (target.name === "Laatste" && tabel.bladz !== tabel.aantalBladz) {
                                tabel.bladz = tabel.aantalBladz;
                                tabel.params = 'page=' + tabel.aantalBladz;
                            } else if (target.name === "aanpassen") aanpassen(target);
                            else if (target.name === "verwijderen") verwijderen(target);
                            else if (target.name === "Gebruiker_toevoegen") gebruikerToevoegen(target);
                            
                        } else if (focus === "tabel") {
                            
                            if (target.name === "opslaan") opslaan(target);
                            else if (target.name === "annuleren") annuleren(target);
                            else if (target.name === "verwijderen_bevestigen") verwijderenBevestigen(target);
                            else if (target.name === "verwijderen_annuleren") verwijderenAnnuleren(target);
                            else if (target.name === "gebruiker_opslaan") gebruikerOpslaan(target);
                            else if (target.name === "gebruiker_annuleren") gebruikerAnnuleren(target);
                            else if(tabel.actie !== "aanpassen" && tabel.actie !== "verwijderen") gebruikerToevoegen(target);
                            
                        }

                    }
                }
                if(target.name === "Zoeken" && actie !== "toevoegen") {
                    if(window.getComputedStyle(tabel.target.zoekterm, null).getPropertyValue("display") === "none") {
                        tabel.target.zoeken.classList.add("active");
                        tabel.target.zoekterm.focus();
                    } else if(tabel.target.zoeken.classList.contains("active")) {
                        tabel.target.zoeken.classList.remove("active");
                    } else {
                        tabel.target.zoeken.classList.add("active");
                    }
                }
                if(target.name === "zoekterm") {
                    tabel.target.zoeken.classList.add("focus");
                    //if(target.value.length > 0) tabel.target.zoeken.classList.add("active");
                }
            }
            console.log("END - ACTIE & FOCUS");
            console.log(tabel.actie);
            console.log(tabel.focus);
            console.log("START - ACTIE & FOCUS static");
            console.log(actie);
            console.log(focus);

        });
        document.addEventListener("keyup", function(e) {
            let target = e.target;
            if(target.name === "zoekterm") {
                if(target.value.length > 0)
                    tabel.target.zoeken.classList.add("active");
                else
                    tabel.target.zoeken.classList.remove("active");
            }
        });
        console.log(tabel);
    }, 100 );
})();
/*
console.log(tabel);
console.log(tabel.ajaxData.data);
console.log(tabel.ajaxData.keys);
*/
//tabel.request();
//console.log(tabel);