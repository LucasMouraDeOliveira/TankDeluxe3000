// Variable
var fCanvas;
var bCanvas;
var fCtx;
var bCtx;
var fLayout;
var bLayout;

var nbBlockX = 1216 / 32;
var nbBlockY = 800 / 32;

var selectedAssetCode;
var selectedLayout;

var assetsManager = new AssetsManager();

// Load all assets
assetsManager.loadAssets(setupAssetsPanel);

document.addEventListener("DOMContentLoaded", function(){
	initCanvas();
});

$('#exportLevelModal').on('show.bs.modal', function () {
	$(this).find(".modal-body").empty().append(calculateMap());
});

function initCanvas() {
	// Get canvas
	fCanvas = document.querySelector("#foregroundCanvas");
	bCanvas = document.querySelector("#backgroundCanvas");
	fCtx = fCanvas.getContext("2d");
	bCtx = bCanvas.getContext("2d");
	
	initializeLevel();
	drawGrid();
	
	// Click event
	document.querySelector("#gridCanvas").addEventListener('mousedown', clickEvent);
	
	selectedLayout = "ground";
}

function setupAssetsPanel() {
	addAssetPanel("ground", assetsManager.models.get("ground"));
	addAssetPanel("obstacle", assetsManager.models.get("obstacle"));
}

function addAssetPanel(layoutName, assets) {
	let assetPanel = $("#"+ layoutName +"-assets");
	
	Object.keys(assets).forEach(assetName => {
		let assetImage = assets[assetName];
		let assetCode = assetsManager.getCode(layoutName, assetName);
	
		// Build asset card
		let assetCard = $("#assetCardTemplate").clone()
								.attr("id", "card-" + assetName)
								.attr("data-code", assetCode)
								.removeClass("d-none");
		assetCard.on("click", () => selectCard(assetCard, layoutName));
		assetCard.find(".card-title").append(assetName);
		assetCard.find(".card-body").append(assetImage);
		
		assetPanel.append(assetCard);
	});
}

function clickEvent(event) {
	let x = parseInt(event.offsetX / 32);
	let y = parseInt(event.offsetY / 32);
	let layout;
	let refreshFunction;
	
	if(selectedLayout == "ground") {
		layout = bLayout;
		refreshFunction = drawBackground;
	} else if(selectedLayout == "obstacle") {
		layout = fLayout;
		refreshFunction = drawForeground;
	} else {
		console.error("Unknwon layout " + selectedLayout);
		return;
	}
	
	if(layout[x][y]) {
		layout[x][y] = undefined;
	} else {
		layout[x][y] = selectedAssetCode;
	}
	
	refreshFunction();
}

function drawGrid() {
	let gCtx = document.querySelector("#gridCanvas").getContext("2d");
	
	// x
	for(var i = 32; i < 1200; i += 32) {
		gCtx.beginPath();
		gCtx.lineWidth = "2";
		gCtx.strokeStyle = "black";
		gCtx.rect(i, 0, i, 800);
		gCtx.stroke();
	}
	// y
	for(var j = 32; j < 800; j += 32) {
		gCtx.beginPath();
		gCtx.lineWidth = "2";
		gCtx.strokeStyle = "black";
		gCtx.rect(0, j, 1200, j);
		gCtx.stroke();
	}
}

function drawForeground() {
	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);
	
	// fLayout
	for (var x = 0; x < fLayout.length; x++) {
		for (var y = 0; y < fLayout[0].length; y++) {
			if(fLayout[x][y]) {
				fCtx.drawImage(assetsManager.getImage(fLayout[x][y]), x * 32, y * 32);
			}
		}
	}
}

function drawBackground() {
	bCtx.clearRect(0, 0, bCanvas.width, bCanvas.height);
	
	// bLayout
	for (var x = 0; x < bLayout.length; x++) {
		for (var y = 0; y < bLayout[0].length; y++) {
			if(bLayout[x][y]) {
				bCtx.drawImage(assetsManager.getImage(bLayout[x][y]), x * 32, y * 32);
			}
		}
	}
}

function initializeLevel() {
	fLayout = new Array(nbBlockX);
	for (var i = 0; i < fLayout.length; i++) {
		fLayout[i] = new Array(nbBlockY);
	}
	
	bLayout = new Array(nbBlockX);
	for (var i = 0; i < bLayout.length; i++) {
		bLayout[i] = new Array(nbBlockY);
	}
}

function addMapBoundaries() {
	for (var x = 0; x < fLayout.length; x++) {
		fLayout[x][0] = true;
		fLayout[x][fLayout[0].length - 1] = true;
	}
	for (var y = 0; y < fLayout[0].length; y++) {
		fLayout[0][y] = true;
		fLayout[fLayout.length - 1][y] = true;
	}
}

function selectCard(card, layoutName) {
	$(".card").removeClass("border-secondary");
	card.addClass("border-secondary");
	
	selectedAssetCode = card.attr("data-code");
	selectedLayout = layoutName;
}

function calculateMap() {
	let str = "";
	for (var i = 0; i < fLayout[0].length; i++) {
		for(var j = 0; j < fLayout.length; j++) {
			str += fLayout[j][i] ?  1 : 0;
		}
		str += "\n";
	}
	
	return str;
}