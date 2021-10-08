class Controls {
	
	constructor(camera) {
		this.controls = {};
		this.camera = camera;
//		this.sendControlPause = 1000;
		this.sendAimPause = 50;
		this.previsousAim = {x: -1, y: -1};		// To detect if aim has changed
		
		document.addEventListener("DOMContentLoaded", () => {
			this.fCanvas = document.querySelector("#foregroundCanvas");
			this.bCanvas = document.querySelector("#backgroundCanvas");
		
			this.initControls();
		});
	}
	
	initControls = () => {
		this.controls.forward = false;
		this.controls.backward = false;
		this.controls.left = false;
		this.controls.right = false;
		this.controls.shoot = false;
		this.controls.place_mine = false;
		this.controls.dash = false;
		this.controls.charging = false;
		this.controls.aim = {x: 0, y: 0};
	}
	
	startUpdating = (webSocketClient) => {
		this.webSocketClient = webSocketClient;

		this.addPressEvent();
		this.addReleaseEvent();
		this.addTrackMouseEvent();
		this.addMouseClickEvent();
		
		this.sendControls();

//		setInterval(this.sendControls, this.sendControlPause);
		setInterval(this.sendAim, this.sendAimPause);
		
	}
	
	sendControls = () => {
		this.webSocketClient.sendMessage(this.controls);
	}
	
	sendAim = () => {
		if(this.previsousAim.x != this.controls.aim.x || this.previsousAim.y != this.controls.aim.y) {
			this.webSocketClient.sendMessage({aim: this.controls.aim});
			this.previsousAim.x = this.controls.aim.x;
			this.previsousAim.y = this.controls.aim.y;
		}
	}
	
	addPressEvent = () => {
		document.addEventListener('keydown', (event) => {
			if(!event.repeat) {
				const touch = event.key;
				if (touch === 'ArrowUp' || touch === 'Z' || touch === 'z') {
					this.controls.forward = true; 
				}
				if (touch === 'ArrowDown' || touch === 'S' || touch === 's') {
					this.controls.backward = true; 
				}
				if (touch === 'ArrowRight' || touch === 'D' || touch === 'd') {
					this.controls.right = true;
				}
				if (touch === 'ArrowLeft' || touch === 'Q' || touch === 'q') {
					this.controls.left = true;
				}
				if (touch === ' ' || touch === 'Control') {
					this.controls.dash = true;
				}
				if (touch === 'M') {
					this.controls.place_mine = true;
				}
				
				this.sendControls();
			}
			
		}, false);
	}
	
	addReleaseEvent = () => {
		document.addEventListener('keyup', (event) => {
			if(!event.repeat) {
				const touch = event.key;
				if (touch === 'ArrowUp' || touch === 'Z' || touch === 'z') {
					this.controls.forward = false; 
				}
				if (touch === 'ArrowDown' || touch === 'S' || touch === 's') {
					this.controls.backward = false; 
				}
				if (touch === 'ArrowRight' || touch === 'D' || touch === 'd') {
					this.controls.right = false;
				}
				if (touch === 'ArrowLeft' || touch === 'Q' || touch === 'q') {
					this.controls.left = false;
				}
				if (touch === ' ' || touch === 'Control') {
					this.controls.dash = false;
				}
				if (touch === 'M' || touch === 'm') {
					this.controls.place_mine = false;
				}
				
				this.sendControls();
			}
			
		}, false);
	}
	
	addTrackMouseEvent = () => {
		this.fCanvas.addEventListener('mousemove', (event) => {
			this.controls.aim.x = event.offsetX + this.camera.offsetX;
			this.controls.aim.y = event.offsetY + this.camera.offsetY;
		}, false);
	}
	
	addMouseClickEvent = () => {
		this.fCanvas.addEventListener('mousedown', () => {
			this.controls.shoot = true;
			this.controls.charging = true;
			
			this.sendControls();
		});
		this.fCanvas.addEventListener('mouseup', () => {
			this.controls.shoot = false;
			this.controls.charging = false
			
			this.sendControls();
		});
	}
}