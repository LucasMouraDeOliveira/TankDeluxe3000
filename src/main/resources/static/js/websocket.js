class WebSocketClient {
	
	constructor(gameId, userId, drawer, controls) {
		this.gameId = gameId;
		this.userId = userId;
		this.drawer = drawer;
		this.controls = controls;
		
		this.connect();
	}
	
	connect = () => {
		this.ws = new WebSocket("ws://"+ window.location.hostname +":"+ window.location.port +"/ws");
		this.ws.onmessage = this.receiveMessage;
		
		this.ws.onopen = () => {
			this.controls.initControls();
			this.controls.startUpdating(this);
		};
	}
	
	receiveMessage = (wsMessage) => {
		let data = JSON.parse(wsMessage.data);
		this.drawer.drawForeground(data);
		
		if(data.scores) {
			updateScoreDiv(data.scores);
		}

		checkDeath(data.players);
	}

	sendMessage = (wsMessage) => {
		wsMessage.gameId = this.gameId;
		wsMessage.userId = this.userId;
		
		this.ws.send(JSON.stringify(wsMessage));
	}
}