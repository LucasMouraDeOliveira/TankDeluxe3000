var fCanvas;
var bCanvas;
var fCtx;
var bCtx;
var wheelFrontRight;
var wheelFrontLeft;
var wheelBackRight;
var wheelBackLeft;
var tankBody;
var machineGun;
var ground;


document.addEventListener("DOMContentLoaded", function(){
	initCanvas();
});

function initCanvas() {
	// Get canvas
	fCanvas = document.querySelector("#foregroundCanvas");
	bCanvas = document.querySelector("#backgroundCanvas");
	fCtx = fCanvas.getContext("2d");
	bCtx = bCanvas.getContext("2d");
	
	// Tank assets
	wheelFrontRight = new Image();
	wheelFrontLeft = new Image();
	wheelBackRight = new Image();
	wheelBackLeft = new Image();
	tankBody = new Image();
	machineGun = new Image();
	
	// Ground assets
	ground = new Image();
	
	// Assets loading
	wheelFrontRight.src = "/assets/img/wheel-front-right-S.png";
	wheelFrontLeft.src = "/assets/img/wheel-front-left-S.png";
	wheelBackRight.src = "/assets/img/wheel-back-right-S.png";
	wheelBackLeft.src = "/assets/img/wheel-back-left-S.png";
	tankBody.src = "/assets/img/tank-body-S.png";
	machineGun.src = "/assets/img/machine-gun-S.png";
	ground.src = "/assets/img/ground-1.jpg";
	
	// Draw background once
	ground.onload = function() {
		drawBackground();
	}
}

function drawForeground(gameState) {
	var tanks = gameState;
	for(var i in tanks) {
		var tank = tanks[i];
		drawTank(tank.x, tank.y, tank.angle);
	}
}

function drawBackground() {
	let groundPattern = bCtx.createPattern(ground, 'repeat');
	bCtx.fillStyle = groundPattern;
	bCtx.fillRect(0, 0, bCanvas.width, bCanvas.height);
}

// Tank sizing configuration
var width = 50;
var height = 50;

function drawTank(x, y, a) {
	console.log(x + " " + y + " " + a);
	x += 100;			// To remove
	y += 100;			// To remove
	
	
	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);

	// Rotate context
	fCtx.save();
	fCtx.translate(x, y);
	fCtx.rotate(a);
	
	// Shadow
	fCtx.shadowOffsetX = 1;
	fCtx.shadowOffsetY = 1;
	fCtx.shadowColor = "black";
	fCtx.shadowBlur = 6;
	
	// Tank Drawing
	fCtx.drawImage(wheelFrontRight, (width / 2), -(height / 2));
	fCtx.drawImage(wheelFrontLeft, -(width / 2), -(height / 2));
	fCtx.drawImage(wheelBackRight, (width / 2), (height / 2));
	fCtx.drawImage(wheelBackLeft, -(width / 2), (height / 2));
	fCtx.drawImage(tankBody, -(width / 2) + 5, -15);
	fCtx.drawImage(machineGun, -4, -30);
	
	fCtx.restore();
	
}