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
    if(e.target.id === "bt-zoeken") {
        e.target.blur();
        var loc = document.querySelector("#side-nav");
        if(!loc.className.includes("search"))
            loc.className += " search";
        else
            loc.classList.remove("search");
    }
});

