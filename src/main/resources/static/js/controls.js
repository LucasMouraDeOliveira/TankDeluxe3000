var controls = {};

function initControls() {
	controls.forward = false;
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
	}, false);
}

function addReleaseEvent() {
	document.addEventListener('keyup', (event) => {
		const touch = event.key;
		console.log(touch);
		if (touch === 'ArrowUp') {
			controls.forward = false; 
		}
	}, false);
}