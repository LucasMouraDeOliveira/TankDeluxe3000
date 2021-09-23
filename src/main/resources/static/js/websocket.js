class WebSocketClient {
	
	constructor(gameId, drawer, controls, spec) {
		this.gameId = gameId;
		this.drawer = drawer;
		this.controls = controls;
		
		this.connect(spec);
	}
	
	connect = (spec) => {
		this.ws = new WebSocket("ws://"+ window.location.hostname +":"+ window.location.port +"/ws");
		this.ws.onmessage = this.receiveMessage;
		
		this.ws.onopen = () => {
			this.ws.send(JSON.stringify({gameId: "", type: "initializePlayer", name : login, specialization : spec}));
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
		wsMessage.gameId = this.gameId;
		
		this.ws.send(JSON.stringify(wsMessage));
	}
}