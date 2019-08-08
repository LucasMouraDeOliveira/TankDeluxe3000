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
		drawTank(tank.x, tank.y, tank.angle);
	}
}

// Tank sizing configuration
var width = 50;
var height = 50;

function drawTank(x, y, a) {
	console.log(x + " " + y + " " + a);
	x += 100;			// To remove
	y += 100;			// To remove
	
	
	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);
//	fCtx.rect(10, 10, 10, 10);
//	fCtx.stroke();
	
	fCtx.save();
	fCtx.translate(x, y);
	fCtx.rotate(a);
	
	// Tank Drawing
	fCtx.drawImage(wheelFrontRight, (width / 2), -(height / 2));
	fCtx.drawImage(wheelFrontLeft, -(width / 2), -(height / 2));
	fCtx.drawImage(wheelBackRight, (width / 2), (height / 2));
	fCtx.drawImage(wheelBackLeft, -(width / 2), (height / 2));
	fCtx.drawImage(tankBody, -(width / 2) + 5, -15);
	fCtx.drawImage(machineGun, -4, -30);
	
	fCtx.restore();
	
}