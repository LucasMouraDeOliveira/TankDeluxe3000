document.addEventListener("DOMContentLoaded", function(){
	initCanvas();
});

var fCanvas;
var fCtx;
var wheelFrontRight;

function initCanvas() {
	fCanvas = document.querySelector("#foregroundCanvas");
	fCtx = fCanvas.getContext("2d");
	
	wheelFrontRight = new Image();
	wheelFrontLeft = new Image();
	wheelBackRight = new Image();
	wheelBackLeft = new Image();
	tankBody = new Image();
	machineGun = new Image();
	
	wheelFrontRight.src = "/assets/img/wheel-front-right-S.png";
	wheelFrontLeft.src = "/assets/img/wheel-front-left-S.png";
	wheelBackRight.src = "/assets/img/wheel-back-right-S.png";
	wheelBackLeft.src = "/assets/img/wheel-back-left-S.png";
	tankBody.src = "/assets/img/tank-body-S.png";
	machineGun.src = "/assets/img/machine-gun-S.png";
}

function drawForeground(gameState) {
	var tanks = gameState;
	for(var i in tanks) {
		var tank = tanks[i];
		drawTank(tank.x, tank.y);
	}
}

// Tank sizing configuration
var width = 50;
var height = 50;

function drawTank(x, y) {
	console.log(x + " " + y);
	x += 100;			// To remove
	y += 100;			// To remove
	
	
	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);
//	fCtx.rect(10, 10, 10, 10);
//	fCtx.stroke();
	
	fCtx.drawImage(wheelFrontRight, x + (width / 2), y - (height / 2));
	fCtx.drawImage(wheelFrontLeft, x - (width / 2), y - (height / 2));
	fCtx.drawImage(wheelBackRight, x + (width / 2), y + (height / 2));
	fCtx.drawImage(wheelBackLeft, x - (width / 2), y + (height / 2));
	fCtx.drawImage(tankBody, x - (width / 2) + 5, y - 15);
	fCtx.drawImage(machineGun, x - 4, y - 30);
	
}