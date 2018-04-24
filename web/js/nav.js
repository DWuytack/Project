document.addEventListener("click", function(e){
    console.log(e.target.id);
    if(e.target.id === "bt-profiel") {
        e.target.blur();
        var loc = document.querySelector("#profiel");
        if(!loc.className.includes("active"))
            loc.className += " active";
        else
            loc.classList.remove("active");
    }
    if(e.target.id === "bt-profiel") {
        
    }
    /*
    if(e.target.id === "bt-zoeken") {
        e.target.blur();
        var loc = document.querySelector(".table-nav-header");
        if(!loc.className.includes("search"))
            loc.className += " search";
        else
            loc.classList.remove("search");
    }
    */
    if(e.target.id === "bt-gebruiker_toevoegen") {
        e.target.blur();
        var popup = document.querySelector("#popup");
        var loc = document.querySelector("#gebruiker_toevoegen");
        if(!loc.className.includes("active")) {
            popup.classList.add("active");
            loc.classList.add("active");
        } else {
            popup.classList.remove("active");
            loc.classList.remove("active");
        }
    }
    if(e.target.id === "popup") {
        var popup = document.querySelector("#popup");
        var loc = document.querySelector("#gebruiker_toevoegen");
        popup.classList.remove("active");
        loc.classList.remove("active");
    }
});

