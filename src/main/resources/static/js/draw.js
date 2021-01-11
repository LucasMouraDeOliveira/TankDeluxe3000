class Drawer {
	
	constructor(assetsManager) {
		// Configuration
		this.MAX_ANIMATION_STEP = 20;
		
		this.assetsManager = assetsManager;
	}
	
	initCanvas = () => {
		// Variable initialization
		this.currentStepNumber = 0;
		
		// Get canvas
		this.fCanvas = document.querySelector("#foregroundCanvas");
		this.bCanvas = document.querySelector("#backgroundCanvas");
		this.fCtx = this.fCanvas.getContext("2d");
		this.bCtx = this.bCanvas.getContext("2d");
		
		// Ground assets
		this.ground = this.assetsManager.get("ground").grass;
		this.obstacle = this.assetsManager.get("obstacle").wood;
		
		// Draw background once
		this.ground.onload = () => {
			this.drawBackground();
		}
	
		this.tankDrawer = new TankDrawer(this.fCtx, this);
		this.bulletDrawer = new BulletDrawer(this.fCtx, this);
	}
	
	drawForeground = (gameState) => {
		let tanks = gameState.players;
		let bullets = gameState.bullets;
		let walls = gameState.walls;
		
		// Increment animation step number
		this.currentStepNumber++;
		
		// Reset canvas
		this.fCtx.clearRect(0, 0, this.fCanvas.width, this.fCanvas.height);
	
		// Global animation switch
		switch(this.currentStepNumber) {
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
			this.currentStepNumber = 0;
		}
		
		this.fCtx.shadowOffsetX = 1;
		this.fCtx.shadowOffsetY = 1;
		this.fCtx.shadowColor = "black";
		this.fCtx.shadowBlur = 6;
	
		for(let i in walls) {
			let wall = walls[i];
			if(wall.obstacle) {
				this.fCtx.drawImage(this.obstacle, wall.x * 32, wall.y * 32);
			}
		}
		
		// Draw tanks
		for(let i in tanks) {
			let tank = tanks[i];
			this.tankDrawer.draw(tank);
		}
		
		// Draw bullet
		for(let j in bullets) {
			let bullet = bullets[j];
			this.bulletDrawer.draw(bullet);
		}
	}
	
	drawBackground = () => {
		let groundPattern = this.bCtx.createPattern(this.ground, 'repeat');
		
		this.bCtx.fillStyle = groundPattern;
		this.bCtx.fillRect(0, 0, this.bCanvas.width, this.bCanvas.height);
	}
}




// Configuration
//var MAX_ANIMATION_STEP = 20;

// Variables
//var fCanvas;
//var bCanvas;
//var fCtx;
//var bCtx;
//var ground;
//var obstacle;

//var currentStepNumber;

//var tankDrawer;
//var bulletDrawer;

//document.addEventListener("DOMContentLoaded", function(){
//	initCanvas();
//});

//function initCanvas() {
	// Variable initialization
//	currentStepNumber = 0;
	
	// Get canvas
//	fCanvas = document.querySelector("#foregroundCanvas");
//	bCanvas = document.querySelector("#backgroundCanvas");
//	fCtx = fCanvas.getContext("2d");
//	bCtx = bCanvas.getContext("2d");
	
	// Ground assets
//	ground = new Image();
//	obstacle = new Image();
//	ground.src = "/assets/img/grass.png";
//	obstacle.src = "/assets/img/wood-S.png";
	
	// Draw background once
//	ground.onload = function() {
//		drawBackground();
//	}

//	tankDrawer = new TankDrawer(fCtx);
//	bulletDrawer = new BulletDrawer(fCtx);

//}

//function drawForeground(gameState) {
//	var tanks = gameState.players;
//	var bullets = gameState.bullets;
//	var walls = gameState.walls;
//	
//	// Increment animation step number
//	currentStepNumber++;
//	
//	// Reset canvas
//	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);
//
//	// Global animation switch
//	switch(currentStepNumber) {
//	case 0:
//	case 1:
//	case 2:
//	case 3:
//	case 4:
//	case 5:
//	case 6:
//	case 7:
//	case 8:
//	case 9:
//	case 10:
//	case 11:
//	case 12:
//	case 13:
//	case 14:
//	case 15:
//	case 16:
//	case 17:
//	case 18:
//	case 19:
//		break;
//	default:
//		currentStepNumber = 0;
//	}
//	
//	fCtx.shadowOffsetX = 1;
//	fCtx.shadowOffsetY = 1;
//	fCtx.shadowColor = "black";
//	fCtx.shadowBlur = 6;
//
//	for(var i in walls) {
//		var wall = walls[i];
//		if(wall.obstacle) {
//			fCtx.drawImage(obstacle, wall.x * 32, wall.y * 32);
//		}
//	}
//	
//	// Draw tanks
//	for(var i in tanks) {
//		var tank = tanks[i];
//		tankDrawer.draw(tank);
//	}
//	
//	// Draw bullet
//	for(var j in bullets) {
//		var bullet = bullets[j];
//		bulletDrawer.draw(bullet);
//	}
//}

//function drawBackground() {
//	let groundPattern = bCtx.createPattern(ground, 'repeat');
//	bCtx.fillStyle = groundPattern;
//	bCtx.fillRect(0, 0, bCanvas.width, bCanvas.height);
//}