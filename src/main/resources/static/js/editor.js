// Variable
var fCanvas;
var bCanvas;
var fCtx;
var bCtx;
var ground;
var obstacle;

var nbBlockX = 1216 / 32;
var nbBlockY = 800 / 32;

var level;

document.addEventListener("DOMContentLoaded", function(){
	initCanvas();
});

function initCanvas() {
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
		drawGrid();
	}
	
	// Click event
	fCanvas.addEventListener('mousedown', clickEvent);
	
	// Build level
	level = new Array(nbBlockX);
	for (var i = 0; i < level.length; i++) {
		level[i] = new Array(nbBlockY);
	}
	
	for (var x = 0; x < level.length; x++) {
		level[x][0] = true;
		level[x][level[0].length - 1] = true;
	}
	for (var y = 0; y < level[0].length; y++) {
		level[0][y] = true;
		level[level.length - 1][y] = true;
	}
	
	drawForeground();
}

function clickEvent(event) {
	var x = parseInt(event.offsetX / 32);
	var y = parseInt(event.offsetY / 32);
	
	if(level[x][y]) {
		level[x][y] = false;
	} else {
		level[x][y] = true;
	}
	
	drawForeground();
}

function printMap() {
	let str = "";
	for (var i = 0; i < level[0].length; i++) {
		for(var j = 0; j < level.length; j++) {
			str += level[j][i] ?  1 : 0;
		}
		str += "\n";
	}
	console.log(str);
}

function drawForeground() {
	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);
	
	// Level
	for (var x = 0; x < level.length; x++) {
		for (var y = 0; y < level[0].length; y++) {
			if(level[x][y]) {
				fCtx.drawImage(obstacle, x * 32, y * 32);
			}
		}
	}
	
	drawGrid();
}

function drawGrid() {
	// x
	for(var i = 32; i < 1200; i += 32) {
		fCtx.beginPath();
		fCtx.lineWidth = "2";
		fCtx.strokeStyle = "black";
		fCtx.rect(i, 0, i, 800);
		fCtx.stroke();
	}
	// y
	for(var j = 32; j < 800; j += 32) {
		fCtx.beginPath();
		fCtx.lineWidth = "2";
		fCtx.strokeStyle = "black";
		fCtx.rect(0, j, 1200, j);
		fCtx.stroke();
	}
}

function drawBackground() {
	let groundPattern = bCtx.createPattern(ground, 'repeat');
	bCtx.fillStyle = groundPattern;
	bCtx.fillRect(0, 0, bCanvas.width, bCanvas.height);
}