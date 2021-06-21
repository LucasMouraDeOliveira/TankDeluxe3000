class WebSocketClient {
	
	constructor(drawer, controls, spec) {
		this.drawer = drawer;
		this.controls = controls;
		
		this.connect(spec);
	}
	
	connect = (spec) => {
		this.ws = new WebSocket("ws://"+ window.location.hostname +":"+ window.location.port +"/ws");
		this.ws.onmessage = this.receiveMessage;
		
		this.ws.onopen = () => {
			this.ws.send(JSON.stringify({type: "initializePlayer", name : login, specialization : spec}));
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