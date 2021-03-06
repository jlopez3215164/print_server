var socket = new WebSocket("ws://localhost:9090/ProgressWebSocket/progress");
socket.onmessage = onMessage;

function onMessage(event) {
    var btnSubmit = document.getElementById("btnSubmit");
    btnSubmit.disabled = true;
    
    var progress = document.getElementById("progress");
    var data = JSON.parse(event.data);
    progress.value = data.value;
    
    var lblProgress = document.getElementById("lblProgress");
    if(data.value < 100){
        lblProgress.innerHTML = 'Progress: ' + data.value + '%';
    }else{
        btnSubmit.disabled = false;
        lblProgress.innerHTML = "Finish";
    }
    
}

function formSubmit() {
    socket.send("{\"start\":\"true\"}");
}


