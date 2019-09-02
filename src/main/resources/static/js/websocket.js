var ws;

document.addEventListener("DOMContentLoaded", function() {
  connect();
});

function connect() {
	ws = new WebSocket("ws://localhost:8080/ws");
	ws.onmessage = receiveMessage;
	ws.onopen = function(){
		initControls();
		startUpdating();
	};
}

function receiveMessage(wsMessage) {
	var data = wsMessage.data;
	drawForeground(JSON.parse(data));
}

function sendMessage(wsMessage) {
	ws.send(wsMessage);
}
