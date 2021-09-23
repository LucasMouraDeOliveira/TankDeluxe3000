class Controls {
	
	constructor(camera) {
		this.controls = {};
		this.camera = camera;
		
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
		this.controls.dash = false;
		this.controls.charging = false;
		this.controls.aim = {x: 0, y: 0};
	
		this.addPressEvent();
		this.addReleaseEvent();
		this.addTrackMouseEvent();
		this.addMouseClickEvent();
	}
	
	startUpdating = (webSocketClient) => {
		setInterval(() => {
			webSocketClient.sendMessage(this.controls);
		}, 50);
	}
	
	addPressEvent = () => {
		document.addEventListener('keydown', (event) => {
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
		}, false);
	}
	
	addReleaseEvent = () => {
		document.addEventListener('keyup', (event) => {
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
		});
		this.fCanvas.addEventListener('mouseup', () => {
			this.controls.shoot = false;
			this.controls.charging = false
		});
	}
}


//var controls = {};

//function initControls() {
//	controls.forward = false;
//	controls.backward = false;
//	controls.left = false;
//	controls.right = false;
//	controls.shoot = false;
//	controls.aim = {x: 0, y: 0};
//
//	addPressEvent();
//	addReleaseEvent();
//	addTrackMouseEvent();
//	addMouseClickEvent();
//}

//function startUpdating() {
//	setInterval(function() {
//		webSocketClient.sendMessage(JSON.stringify(controls));
//	}, 50);
//}

//function addPressEvent() {
//	document.addEventListener('keydown', (event) => {
//		const touch = event.key;
//		if (touch === 'ArrowUp' || touch === 'Z') {
//			controls.forward = true; 
//		}
//		if (touch === 'ArrowDown' || touch === 'S') {
//			controls.backward = true; 
//		}
//		if (touch === 'ArrowRight' || touch === 'D') {
//			controls.right = true;
//		}
//		if (touch === 'ArrowLeft' || touch === 'Q') {
//			controls.left = true;
//		}
//		if (touch === ' ') {
//			controls.shoot = true;
//		}
//	}, false);
//}

//function addReleaseEvent() {
//	document.addEventListener('keyup', (event) => {
//		const touch = event.key;
//		console.log(touch)
//		if (touch === 'ArrowUp' || touch === 'z') {
//			controls.forward = false; 
//		}
//		if (touch === 'ArrowDown' || touch === 's') {
//			controls.backward = false; 
//		}
//		if (touch === 'ArrowRight' || touch === 'd') {
//			controls.right = false;
//		}
//		if (touch === 'ArrowLeft' || touch === 'q') {
//			controls.left = false;
//		}
//		if (touch === ' ') {
//			controls.shoot = false;
//		}
//	}, false);
//}

//function addTrackMouseEvent() {
//	fCanvas.addEventListener('mousemove', (event) => {
//		controls.aim.x = event.offsetX;
//		controls.aim.y = event.offsetY;
//	}, false);
//}

//function addMouseClickEvent() {
//	fCanvas.addEventListener('mousedown', (event) => {
//		controls.shoot = true;
//	});
//	fCanvas.addEventListener('mouseup', (event) => {
//		controls.shoot = false;
//	});
//	
//}