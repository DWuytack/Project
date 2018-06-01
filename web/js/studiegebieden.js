function toggle(Button) {
    var Klasse = Button.parentElement.parentElement.className;
    if (Klasse != "taak") {
        if (Button.textContent === "+") {   // LAAD ALLE OPLEIDINGEN IN TABEL

            var xhttp = new XMLHttpRequest();

            if (window.XMLHttpRequest) {
                // code voor moderne browsers
                xhttp = new XMLHttpRequest();
            } else {
                // code voor oude IE browsers
                xhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            //open(method,url,async)
            var ID = Button.parentElement.parentElement.dataset.id;
            xhttp.open("POST", "StudiegebiedenServlet?" + Klasse + "=" + ID, true);
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
                        button.setAttribute("onclick", "toggle(this)");
                        button.textContent = "+";

                        td = document.createElement('td');
                        textNode = document.createTextNode(data[i].naam);

                        tr = document.createElement('tr');
                        tr.dataset.id = data[i].opleidingID;
                        if (Klasse === "studiegebied") {
                            tr.className = "opleiding";
                            td.appendChild(button);
                        } else if (Klasse === "opleiding") {
                            tr.className = "module";
                            td.appendChild(button);
                        } else if (Klasse === "module") {
                            tr.className = "doelstelling";
                            td.appendChild(button);
                        } else if (Klasse === "doelstellig") {
                            tr.className = "taak";
                        }

                        
                        td.appendChild(textNode);
                        tr.appendChild(td);
                        table.appendChild(tr);
                    }
                    Button.textContent = "-";
                    Button.parentElement.appendChild(table);
                }
            };
        } else if (Button.textContent === "-") {
            Button.parentElement.removeChild(Button.parentElement.childNodes[2]);
            Button.textContent = "+";
        }
    }
}