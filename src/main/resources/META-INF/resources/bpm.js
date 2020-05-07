refreshList();

function refreshList() {
    fetch("http://localhost:8080/onboarding")
    .then((response) => {
        return response.json();
    })
    .then((processes) => {
        var theList = "";
        processes.forEach(e => {
            theList += `<li>Navn : ${e.person.name} ${e.person.age} år - <button onclick="finishJob('${e.id}')">Finish JOB</button></li>`
        });
        document.getElementById("the-list").innerHTML = theList;
    })
    ;
}


function finishJob(processId) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8080/onboarding/" + processId + "/tasks", false);
    xmlHttp.send(null);
    var workItemId = Object.keys(JSON.parse(xmlHttp.responseText))[0];
    var reason = document.getElementById("the-reason").value;
    console.log("Godkjenner " + processId + "/" + workItemId + " : " + reason);

    fetch("http://localhost:8080/onboarding/" + processId + "/Task/" + workItemId + "?phase=complete", {
     method: 'post',
     headers: { 'Content-Type': 'application/json' },
     body: JSON.stringify({
         reason: `Godkjent fordi : ${reason}`
     })
    })
    .then((response) => {
        return response.json();
    })
    .then((svar) => {
        document.getElementById("the-textarea").innerHTML = "jada" + JSON.stringify(svar);
    })
    ;

    refreshList();

}