var controls = {};

function initControls() {
	controls.forward = false;
	controls.backward = false;
	controls.left = false;
	controls.right = false;
	controls.shoot = false;
	controls.aim = {x: 0, y: 0};

	addPressEvent();
	addReleaseEvent();
	addTrackMouseEvent();
	addMouseClickEvent();
}

function startUpdating() {
	setInterval(function() {
		sendMessage(JSON.stringify(controls));
	}, 50);
}

function addPressEvent() {
	document.addEventListener('keydown', (event) => {
		const touch = event.key;
		if (touch === 'ArrowUp' || touch === 'Z') {
			controls.forward = true; 
		}
		if (touch === 'ArrowDown' || touch === 'S') {
			controls.backward = true; 
		}
		if (touch === 'ArrowRight' || touch === 'D') {
			controls.right = true;
		}
		if (touch === 'ArrowLeft' || touch === 'Q') {
			controls.left = true;
		}
		if (touch === ' ') {
			controls.shoot = true;
		}
	}, false);
}

function addReleaseEvent() {
	document.addEventListener('keyup', (event) => {
		const touch = event.key;
		console.log(touch)
		if (touch === 'ArrowUp' || touch === 'z') {
			controls.forward = false; 
		}
		if (touch === 'ArrowDown' || touch === 's') {
			controls.backward = false; 
		}
		if (touch === 'ArrowRight' || touch === 'd') {
			controls.right = false;
		}
		if (touch === 'ArrowLeft' || touch === 'q') {
			controls.left = false;
		}
		if (touch === ' ') {
			controls.shoot = false;
		}
	}, false);
}

function addTrackMouseEvent() {
	fCanvas.addEventListener('mousemove', (event) => {
		controls.aim.x = event.offsetX;
		controls.aim.y = event.offsetY;
	}, false);
}

function addMouseClickEvent() {
	fCanvas.addEventListener('mousedown', (event) => {
		controls.shoot = true;
	});
	fCanvas.addEventListener('mouseup', (event) => {
		controls.shoot = false;
	});
	
}