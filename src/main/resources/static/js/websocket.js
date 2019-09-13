var ws;

document.addEventListener("DOMContentLoaded", function() {
  connect();
});

function connect() {
	ws = new WebSocket("ws://localhost:8080/ws");
	ws.onmessage = receiveMessage;
	ws.onopen = function(){
		
		var pseudo = window.prompt("Pseudo");
		ws.send(JSON.stringify({type: "name", name : pseudo}));
		
		initControls();
		startUpdating();
	};
}

function receiveMessage(wsMessage) {
	var data = JSON.parse(wsMessage.data);
	drawForeground(data);
	updateScoreDiv(data.scores);
}

function sendMessage(wsMessage) {
	ws.send(wsMessage);
}
