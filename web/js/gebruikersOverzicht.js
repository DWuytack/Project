// gebruikersOverzicht.js

/*
let datat = {
                "bladz": 1,
                "aantalBladz": 4,
                "toonGebruikers": 8,
                "aantalGebruikers": 30,
                "lijst": [{
                        "jsonID": 112,
                        "rol": "cursist",
                        "voornaam": "Stelaooooooooooooooooooooooooo",
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
            };
*/

(function () {

    const   titels = ["Achternaam", "Voornaam", "Login", "Rol", "GebtDatum", "Email", "Acties"],
            keys = ["achternaam", "voornaam", "login", "rol", "geboorteDatum", "email"],
            meta = {
                rol: ["admin", "leerkracht", "cursist", "secretariaat"]
            };
    let     loc, tabel;

    let tid = setInterval(function () {
        if (document.readyState !== 'complete')
            return;
        clearInterval(tid);

        //run
        let id = "gebruikersOverzicht";
        loc = document.getElementById(id);
        let tabel = new Tabel(loc, id, titels, keys);
        tabel.ajaxData.meta = meta;
        console.log(tabel);
        //tabel.requestTabel(tabel.ajaxData.data);
        //tabel.renderContainer(tabel.ajaxData.data);
        tabel.requestContainer();

        document.addEventListener("click", function (e) {
            let klikRecht = tabel.rechten.klik;
            let focus = tabel.focus;
            let actie = tabel.actie;
            let loc;

            /*
             console.log("START - ACTIE & FOCUS");
             console.log(tabel.actie);
             console.log(tabel.focus);
             console.log("START - ACTIE & FOCUS static");
             console.log(actie);
             console.log(focus);
             */

            let gebruikerToevoegen = target => {
                if (!target.classList.contains("active")) {
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

                            if (target.name === "Eerste" && tabel.bladz !== 1) tabel.eerstePagina();
                            else if (target.name === "Vorige" && tabel.bladz !== 1) tabel.vorigePagina();
                            else if (target.name === "Volgende" && tabel.bladz !== tabel.aantalBladz) tabel.volgendePagina();
                            else if (target.name === "Laatste" && tabel.bladz !== tabel.aantalBladz) tabel.laatstePagina();
                            else if (target.name === "aanpassen") aanpassen(target);
                            else if (target.name === "verwijderen") verwijderen(target);
                            else if (target.name === "Gebruiker_toevoegen") gebruikerToevoegen(target);

                        } else if (focus === "tabel") {

                            if (target.name === "opslaan")
                                opslaan(target);
                            else if (target.name === "annuleren")
                                annuleren(target);
                            else if (target.name === "verwijderen_bevestigen")
                                verwijderenBevestigen(target);
                            else if (target.name === "verwijderen_annuleren")
                                verwijderenAnnuleren(target);
                            else if (target.name === "gebruiker_opslaan")
                                gebruikerOpslaan(target);
                            else if (target.name === "gebruiker_annuleren")
                                gebruikerAnnuleren(target);
                            else if (tabel.actie !== "aanpassen" && tabel.actie !== "verwijderen")
                                gebruikerToevoegen(target);

                        }

                    }
                }
                if (target.name === "Zoeken" && actie !== "toevoegen") {
                    if (window.getComputedStyle(tabel.target.zoekterm, null).getPropertyValue("display") === "none") {
                        tabel.target.zoeken.classList.add("active");
                        tabel.target.zoekterm.focus();
                    } else if (tabel.target.zoeken.classList.contains("active")) {
                        tabel.target.zoeken.classList.remove("active");
                    } else {
                        tabel.target.zoeken.classList.add("active");
                    }
                }
                if (target.name === "zoekterm") {
                    tabel.target.zoeken.classList.add("focus");
                }
            }

            /*
             console.log("END - ACTIE & FOCUS");
             console.log(tabel.actie);
             console.log(tabel.focus);
             console.log("START - ACTIE & FOCUS static");
             console.log(actie);
             console.log(focus);
             */

        });
        document.addEventListener("keyup", function (e) {
            let target = e.target;
            if (target.name === "zoekterm") {
                if (target.value.length > 0) {
                    tabel.target.zoeken.classList.add("active");
                    tabel.target.zoeken.setAttribute("results", "first");
                    /* For demo */
                    if (target.value.length >= 4 && target.value.length <= 6) {
                        target.classList.add("tussen");
                        tabel.target.zoeken.setAttribute("results", "middle");
                    } else if (target.value.length > 6) {
                        target.classList.add("error");
                        tabel.target.zoeken.setAttribute("results", "none");
                    }
                } else  {
                    tabel.target.zoeken.classList.remove("active");
                    tabel.target.zoeken.removeAttribute("results");
                }
            }
        });
        console.log(tabel);
    }, 100);
})();
/*
 console.log(tabel);
 console.log(tabel.ajaxData.data);
 console.log(tabel.ajaxData.keys);
 */
//tabel.request();
//console.log(tabel);