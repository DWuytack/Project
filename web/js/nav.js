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
});

