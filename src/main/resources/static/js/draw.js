// Configuration
var MAX_ANIMATION_STEP = 20;

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

var tankDrawer;

document.addEventListener("DOMContentLoaded", function(){
	initCanvas();
});

function initCanvas() {
	// Variable initialization
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

	tankDrawer = new TankDrawer(fCtx);

}

function drawForeground(gameState) {
	var tanks = gameState;

	// Increment animation step number
	currentStepNumber++;
	
	// Reset canvas
	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);

	// Global animation switch
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
		tankDrawer.draw(tank);
	}
	
}

function drawBackground() {
	let groundPattern = bCtx.createPattern(ground, 'repeat');
	bCtx.fillStyle = groundPattern;
	bCtx.fillRect(0, 0, bCanvas.width, bCanvas.height);
}