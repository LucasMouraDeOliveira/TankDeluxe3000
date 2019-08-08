// Configuration
var MAX_ANNIMATION_STEP = 20;

var TANK_WIDTH = 50;
var TANK_HEIGHT = 50;

// Variables
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
var currentStepNumber;


document.addEventListener("DOMContentLoaded", function(){
	initCanvas();
});

function initCanvas() {
	// Varibale initialization
	currentStepNumber = 0;
	
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

	// Increment annimation step number
	currentStepNumber++;
	
	// Reset canvas
	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);

	// Global annimation switch
	switch(currentStepNumber) {
	case 0:
	case 1:
	case 2:
	case 3:
	case 4:
	case 5:
	case 6:
	case 7:
	case 8:
	case 9:
	case 10:
	case 11:
	case 12:
	case 13:
	case 14:
	case 15:
	case 16:
	case 17:
	case 18:
	case 19:
		break;
	default:
		currentStepNumber = 0;
	}
	
	// Draw tanks
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

function drawTank(x, y, a) {
	console.log(x + " " + y + " " + a);
	console.log("steps : " + currentStepNumber);
	x += 100;			// To remove
	y += 100;			// To remove
	
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
	// Annimation switch
	switch(currentStepNumber) {
	case 0:
	case 1:
	case 2:
	case 3:
	case 4:
		fCtx.drawImage(wheelBackRight, (TANK_WIDTH / 2), -(TANK_HEIGHT / 2));
		fCtx.drawImage(wheelBackLeft, -(TANK_WIDTH / 2), -(TANK_HEIGHT / 2));
		fCtx.drawImage(wheelFrontRight, (TANK_WIDTH / 2),  (TANK_HEIGHT / 2));
		fCtx.drawImage(wheelFrontLeft, -(TANK_WIDTH / 2),  (TANK_HEIGHT / 2));
		break;
	case 5:
	case 6:
	case 7:
	case 8:
	case 9:
		fCtx.drawImage(wheelFrontRight, (TANK_WIDTH / 2), -(TANK_HEIGHT / 2));
		fCtx.drawImage(wheelFrontLeft, -(TANK_WIDTH / 2), -(TANK_HEIGHT / 2));
		fCtx.drawImage(wheelBackRight,   (TANK_WIDTH / 2), (TANK_HEIGHT / 2));
		fCtx.drawImage(wheelBackLeft,   -(TANK_WIDTH / 2), (TANK_HEIGHT / 2));
		break;
	case 10:
	case 11:
	case 12:
	case 13:
	case 14:
		fCtx.drawImage(wheelBackRight, (TANK_WIDTH / 2), -(TANK_HEIGHT / 2));
		fCtx.drawImage(wheelBackLeft, -(TANK_WIDTH / 2), -(TANK_HEIGHT / 2));
		fCtx.drawImage(wheelFrontRight,(TANK_WIDTH / 2),  (TANK_HEIGHT / 2));
		fCtx.drawImage(wheelFrontLeft,-(TANK_WIDTH / 2),  (TANK_HEIGHT / 2));
		break;
	case 15:
	case 16:
	case 17:
	case 18:
	case 19:
		fCtx.drawImage(wheelFrontRight, (TANK_WIDTH / 2), -(TANK_HEIGHT / 2));
		fCtx.drawImage(wheelFrontLeft, -(TANK_WIDTH / 2), -(TANK_HEIGHT / 2));
		fCtx.drawImage(wheelBackRight,  (TANK_WIDTH / 2),  (TANK_HEIGHT / 2));
		fCtx.drawImage(wheelBackLeft,  -(TANK_WIDTH / 2),  (TANK_HEIGHT / 2));
		break;
	}
	
	fCtx.drawImage(tankBody, -(TANK_WIDTH / 2) + 5, -15);
	fCtx.drawImage(machineGun, -4, -30);
	
	fCtx.restore();
}