var controls = {};

function initControls() {
	controls.forward = false;
	controls.backward = false;
	controls.left = false;
	controls.right = false;
	controls.shoot = false;
	addPressEvent();
	addReleaseEvent();
}

function startUpdating() {
	setInterval(function() {
		sendMessage(JSON.stringify(controls));
	}, 50);
}

function addPressEvent() {
	document.addEventListener('keydown', (event) => {
		const touch = event.key;
		console.log(touch);
		if (touch === 'ArrowUp') {
			controls.forward = true; 
		}
		if (touch === 'ArrowDown') {
			controls.backward = true; 
		}
		if (touch === 'ArrowRight') {
			controls.right = true;
		}
		if (touch === 'ArrowLeft') {
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
		console.log(touch);
		if (touch === 'ArrowUp') {
			controls.forward = false; 
		}
		if (touch === 'ArrowDown') {
			controls.backward = false; 
		}
		if (touch === 'ArrowRight') {
			controls.right = false;
		}
		if (touch === 'ArrowLeft') {
			controls.left = false;
		}
		if (touch === ' ') {
			controls.shoot = false;
		}
	}, false);
}