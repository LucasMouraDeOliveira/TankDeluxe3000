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

var clickInfo;

var assetsManager = new AssetsManager();

// Load all assets
assetsManager.loadAssets(setupAssetsPanel);

document.addEventListener("DOMContentLoaded", function(){
	initCanvas();
});

$('#exportLevelModal').on('show.bs.modal', function (e) {
	let action = $(e.relatedTarget).data("action");
	
	if(action == "export") {
		$(this).find("textarea").empty().append(calculateMap());
		$(this).find(".modal-footer").hide();
		
	} else if(action == "import") {
		$(this).find("textarea").empty();
		$(this).find(".modal-footer").show();
	}
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
	document.querySelector("#gridCanvas").addEventListener('mousedown', mouseDown);
	document.querySelector("#gridCanvas").addEventListener('mousemove', mouseMove);
	document.querySelector("#gridCanvas").addEventListener('mouseup', mouseUp);
	
	selectedLayout = "ground";
}

function setupAssetsPanel() {
	addAssetPanel("ground", assetsManager.models.get("ground"));
	addAssetPanel("obstacle", assetsManager.models.get("obstacle"));
}

function addAssetPanel(layoutName, assets) {
	let assetPanel = $("#"+ layoutName +"-assets");
	let row = $("<div>").addClass("row");
	
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
		
		row.append(assetCard);
	});
	assetPanel.append(row);
}

function mouseDown(event) {
	// Get coordinates
	let x = parseInt(event.offsetX / 32);
	let y = parseInt(event.offsetY / 32);
	let action = getSelectedLayout()[x][y] == undefined ? "fill" : "clean"; 
	
	clickInfo = {
		startX: x,
		startY: y,
		x: x,
		y: y,
		action: action,
	};
	
}

function mouseMove(event) {
	if(clickInfo) {
		let x = parseInt(event.offsetX / 32);
		let y = parseInt(event.offsetY / 32);
		
		if(x != clickInfo.x || y != clickInfo.y) {
			clickInfo.x = x;
			clickInfo.y = y;
			
			getRefreshFunction()();
		}
	}
}

function mouseUp() {
	fill();
	clickInfo = undefined;
	getRefreshFunction()();
}


function fill() {
	let layout = getSelectedLayout();
	let assetCode = clickInfo.action == "fill" ? selectedAssetCode : undefined;
	let coord = reorderCoords(clickInfo.startX, clickInfo.startY, clickInfo.x, clickInfo.y);
	 
	// Fill by copying top left corner
	for(let x = coord.x1; x <= coord.x2; x++) {
		for(let y = coord.y1; y <= coord.y2; y++) {
			layout[x][y] = assetCode;
		}
	}
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
	// Selected area
	let coord = clickInfo ? reorderCoords(clickInfo.startX, clickInfo.startY, clickInfo.x, clickInfo.y) : undefined;

	fCtx.clearRect(0, 0, fCanvas.width, fCanvas.height);
	
	// fLayout
	for (let x = 0; x < fLayout.length; x++) {
		for (let y = 0; y < fLayout[0].length; y++) {
			if(selectedLayout == "obstacle" && clickInfo && x >= coord.x1 && x <= coord.x2 && y >= coord.y1 && y <= coord.y2) {
				if(clickInfo.action == "fill") {
					fCtx.drawImage(assetsManager.getImage(selectedAssetCode), x * 32, y * 32);
				}
			} else if(fLayout[x][y]) {
				fCtx.drawImage(assetsManager.getImage(fLayout[x][y]), x * 32, y * 32);
			}
		}
	}
}

function drawBackground() {
	// Selected area
	let coord = clickInfo ? reorderCoords(clickInfo.startX, clickInfo.startY, clickInfo.x, clickInfo.y) : undefined;
	
	bCtx.clearRect(0, 0, bCanvas.width, bCanvas.height);
	
	// bLayout
	for (let x = 0; x < bLayout.length; x++) {
		for (let y = 0; y < bLayout[0].length; y++) {
			if(selectedLayout == "ground" && clickInfo && x >= coord.x1 && x <= coord.x2 && y >= coord.y1 && y <= coord.y2) {
				if(clickInfo.action == "fill") {
					bCtx.drawImage(assetsManager.getImage(selectedAssetCode), x * 32, y * 32);
				}
			} else if(bLayout[x][y]) {
				bCtx.drawImage(assetsManager.getImage(bLayout[x][y]), x * 32, y * 32);
			} 
		}
	}
}

function initializeLevel() {
	fLayout = new Array(nbBlockX);
	for (let i = 0; i < fLayout.length; i++) {
		fLayout[i] = new Array(nbBlockY);
	}
	
	bLayout = new Array(nbBlockX);
	for (let i = 0; i < bLayout.length; i++) {
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

function getSelectedLayout() {
	if(selectedLayout == "ground") {
		return bLayout;
	} else if(selectedLayout == "obstacle") {
		return fLayout;
	} else {
		console.error("Unknwon layout " + selectedLayout);
		return;
	}
}

function getRefreshFunction() {
	if(selectedLayout == "ground") {
		return drawBackground;
	} else if(selectedLayout == "obstacle") {
		return drawForeground;
	} else {
		console.error("Unknwon layout " + selectedLayout);
		return;
	}
}

function calculateMap() {
	let map = {
		widht: nbBlockX,
		height: nbBlockY,
		ground: bLayout,
		obstacle: fLayout
	};
	
	return window.btoa(JSON.stringify(map));
}

function importLevel() {
	let map = JSON.parse(window.atob($("#exportLevelTextArea").val()));
	
	nbBlockX = map.width;
	nbBlockY = map.height;
	bLayout = map.ground;
	fLayout = map.obstacle;
	
	drawBackground();
	drawForeground();
}

function reorderCoords(x1, y1, x2, y2) {
	let coords = {};
	
	coords.x1 = Math.min(x1, x2);
	coords.y1 = Math.min(y1, y2);
	coords.x2 = Math.max(x1, x2);
	coords.y2 = Math.max(y1, y2);
	
	return coords;
}