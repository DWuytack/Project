var pageCounter = 1;
var params = 'page=' + pageCounter;

var requestData = function(params) {
    var xhttp = new XMLHttpRequest();
    var data = "";
    //params = 'page=1';

    xhttp.open("POST", "someservlet", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
           // Typical action to be performed when the document is ready:
           data = JSON.parse(xhttp.responseText);
           renderHTML(data);
        }
    };
    xhttp.send(params);
};

var renderHTML = function(data) {
    htmlString = "";
    var temp = "";
    var keuzes = ["admin", "leerkracht", "cursist", "secretariaat"];
    var titels = ["Achternaam", "Voornaam", "Login", "Rol", "GebtDatum", "Email", "Acties"];
    var h = "";

    for(titel of titels) {

        h += '<th scope="col" title="' + titel + '"><a>' + titel + '</a></th>';
    }
    var thead = '<thead><tr>' + h + '</tr></thead>';

    data.forEach(function(e) {
        console.log(e);
        /*
        var td = document.createElement("TD");
        var input = document.createElement("INPUT");
        var span = document.createElement("SPAN");
        td.setAttribute("data-label", titels[0]);
        input.type = "text";
        input.name = "achternaam";
        input.value = "" + e.achternaam + "";
        */
        
        var options = function(e, keuzes) {
            da = "";
            for(option of keuzes) {
                da += '<option value="' + option + '"' + selected(e, option) + '>' + option + '</option>';
            }
            return da;
        };
        var selected = function(rol, d){
            if(rol === d)
                return " selected";
            else
                return "";
        };
        temp += '<td data-label="' + titels[0] + '"><input type="text" name="achternaam" value="' + e.achternaam + '"><span style="display: none">' + e.achternaam + '</span></td>';
        temp += '<td data-label="' + titels[1] + '"><input type="text" name="voornaam" value="' + e.voorNaam + '"><span style="display: none">' + e.voorNaam + '</span></td>'; 
        temp += '<td data-label="' + titels[2] + '"> <input type="text" name="login"  value="' + e.login + '"><span style="display: none">' + e.login + '</span></td>';
        temp += '<td data-label="' + titels[3] + '"> <select name="rol" value="' + e.rol + '">' + options(e.rol, keuzes) + '</select><span style="display: none">' + e.rol + '</span></td>';
        var date = new Date();  
        var dateFormat = function(d) { 
            var t = new Date(d);
            return {
                date: t,
                local: t.toLocaleDateString('sgn-NL', {day: '2-digit', month: '2-digit', year: 'numeric'}).replace(new RegExp('-', 'g'), '\/')
            }
        };
        //Opmerking: Automatische aanvulling vind waarde niet!!!! "e.geboorteDatumValue"
        temp += '<td data-label="' + titels[4] + '"> <input type="date" name="geboorteDatum" value="' + e.geboorteDatumValue + '"><span style="display: none">' + dateFormat(e.geboorteDatumValue).local + '</span></td>';
        temp += '<td data-label="' + titels[5] + '"> <input type="text" name="email" value="' + e.email + '"><span style="display: none">' + e.email + '</span></td>';
        temp += '<td data-label="' + titels[6] + '"><div class="actie-images">';
        temp += '<span> <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src="images/pencil.png"> </span>';
        temp += '<span> <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src="images/vuilbak.png"> </span>';
        temp += '</div></td>';
        htmlString += '<tr>' + temp + '</tr>';
        temp = "";
    });
    htmlString = thead + '<tbody>' + htmlString + '</tbody>';
    htmlString = '<table>' + htmlString + '</table>';
    document.getElementById("gebruikersOverzicht").innerHTML = htmlString;
};

document.addEventListener("click", function(e){
    if( e.target.id === "somebutton" ) {
        pageCounter++;
        params = 'page=' + pageCounter;
        requestData(params);
    }
});
//Load
(function() {
    requestData(params);
})();

