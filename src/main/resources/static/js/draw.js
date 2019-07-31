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
	var myTank = gameState.myTank;
	drawTank("red", myTank.x, myTank.y);
}

function drawTank(color, x, y) {
	
}