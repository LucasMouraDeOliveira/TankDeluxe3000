document.addEventListener("DOMContentLoaded", function(){
	initCanvas();
});

var fCanvas;
var fCtx;

function initCanvas() {
	fCanvas = document.querySelector("#foregroundCanvas");
	fCtx = fCanvas.getContext("2d");
}

function drawForeground(gameState) {
	var tanks = gameState;
	for(var i in tanks) {
		var tank = tanks[i];
		drawTank(tank.x, tank.y);
	}
}

function drawTank(x, y) {
	console.log(x + " " + y);
}