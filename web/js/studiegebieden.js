function toggleOpleidingen(StudiegebiedBtn) {
    if (StudiegebiedBtn.textContent === "+") {   // LAAD ALLE OPLEIDINGEN IN TABEL

        var xhttp = new XMLHttpRequest();

        if (window.XMLHttpRequest) {
            // code voor moderne browsers
            xhttp = new XMLHttpRequest();
        } else {
            // code voor oude IE browsers
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        //open(method,url,async)
        var StudiegebiedID = StudiegebiedBtn.parentElement.parentElement.dataset.id;
        xhttp.open("POST", "StudiegebiedenServlet?studiegebied=" + StudiegebiedID, true);
        xhttp.send();
        xhttp.onreadystatechange = function () {

            //200: "OK"
            //403: "Forbidden"
            //404: "Not Found"

            //0: request not initialized 
            //1: server connection established
            //2: request received 
            //3: processing request 
            //4: request finished and response is ready

            if (this.readyState === 4 && this.status === 200) {

                const data = JSON.parse(xhttp.responseText);
                table = document.createElement('table');

                for (let i = 0; i < data.length; i++) {

                    button = document.createElement('button');
                    button.setAttribute("onclick", "toggleOpleidingen(this)");
                    button.textContent = "+";

                    td = document.createElement('td');
                    td.appendChild(button);
                    textNode = document.createTextNode(data[i].naam);
                    td.appendChild(textNode);

                    tr = document.createElement('tr');
                    tr.dataset.id = data[i].opleidingID;
                    tr.className = "opleiding";
                    tr.appendChild(td);

                    table.appendChild(tr);
                }
                StudiegebiedBtn.textContent = "-";
                StudiegebiedBtn.parentElement.appendChild(table);
            }
        };
    } else if (StudiegebiedBtn.textContent === "-") {
        StudiegebiedBtn.parentElement.removeChild(StudiegebiedBtn.parentElement.childNodes[2]);
        StudiegebiedBtn.textContent = "+";
    }
}