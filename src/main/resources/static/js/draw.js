// Configuration
var MAX_ANIMATION_STEP = 20;

// Variables
var fCanvas;
var bCanvas;
var fCtx;
var bCtx;
var ground;
var obstacle;

var currentStepNumber;

var tankDrawer;
var bulletDrawer;

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
	
	// Ground assets
	ground = new Image();
	obstacle = new Image();
	ground.src = "/assets/img/ground-1.jpg";
	obstacle.src = "/assets/img/wood-S.png";
	
	// Draw background once
	ground.onload = function() {
		drawBackground();
	}

	tankDrawer = new TankDrawer(fCtx);
	bulletDrawer = new BulletDrawer(fCtx);

}

function drawForeground(gameState) {
	var tanks = gameState.players;
	var bullets = gameState.bullets;
	var walls = gameState.walls;
	
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
	
	fCtx.shadowOffsetX = 1;
	fCtx.shadowOffsetY = 1;
	fCtx.shadowColor = "black";
	fCtx.shadowBlur = 6;

	for(var i in walls) {
		var wall = walls[i];
		fCtx.drawImage(obstacle, wall.x - 16, wall.y - 16);
	}
	
	// Draw tanks
	for(var i in tanks) {
		var tank = tanks[i];
		tankDrawer.draw(tank);
	}
	
	// Draw bullet
	for(var j in bullets) {
		var bullet = bullets[j];
		bulletDrawer.draw(bullet);
	}
}

function drawBackground() {
	let groundPattern = bCtx.createPattern(ground, 'repeat');
	bCtx.fillStyle = groundPattern;
	bCtx.fillRect(0, 0, bCanvas.width, bCanvas.height);
}