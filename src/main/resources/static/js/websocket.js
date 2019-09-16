var ws;
var name;

document.addEventListener("DOMContentLoaded", function() {
  connect();
});

function connect() {
	
	name = window.prompt("Pseudo");
	
	ws = new WebSocket("ws://localhost:8080/ws");
	ws.onmessage = receiveMessage;
	ws.onopen = function(){
		ws.send(JSON.stringify({type: "name", name : name}));
		initControls();
		startUpdating();
	};
}

function receiveMessage(wsMessage) {
	var data = JSON.parse(wsMessage.data);
	drawForeground(data);
	updateScoreDiv(data.scores);
	checkDeath(data.players);
}

function sendMessage(wsMessage) {
	ws.send(wsMessage);
}
