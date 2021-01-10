class WebSocketClient {
	
	constructor(drawer, controls) {
		this.drawer = drawer;
		this.controls = controls;
		
		this.connect();
	}
	
	connect = () => {
		this.ws = new WebSocket("ws://"+ window.location.hostname +":"+ window.location.port +"/ws");
		this.ws.onmessage = this.receiveMessage;
		
		this.ws.onopen = () => {
			this.ws.send(JSON.stringify({type: "name", name : login}));
			this.controls.initControls();
			this.controls.startUpdating(this);
		};
	}
	
	receiveMessage = (wsMessage) => {
		let data = JSON.parse(wsMessage.data);
		this.drawer.drawForeground(data);
		updateScoreDiv(data.scores);
		checkDeath(data.players);
	}

	sendMessage = (wsMessage) => {
		this.ws.send(wsMessage);
	}
}