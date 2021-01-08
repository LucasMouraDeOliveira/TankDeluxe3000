class WebSocketClient {
	
	constructor() {
		this.connect();
	}
	
	connect = () => {
		this.ws = new WebSocket("ws://"+ window.location.hostname +":"+ window.location.port +"/ws");
		this.ws.onmessage = this.receiveMessage;
		
		this.ws.onopen = () => {
			this.ws.send(JSON.stringify({type: "name", name : login}));
			initControls();
			startUpdating();
		};
	}
	
	receiveMessage = (wsMessage) => {
		let data = JSON.parse(wsMessage.data);
		drawForeground(data);
		updateScoreDiv(data.scores);
		checkDeath(data.players);
	}

	sendMessage = (wsMessage) => {
		this.ws.send(wsMessage);
	}
}