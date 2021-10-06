class Drawer {
	
	constructor(assetsManager, camera) {
		// Configuration
		this.MAX_ANIMATION_STEP = 20;
		
		this.assetsManager = assetsManager;
		this.camera = camera;
	}
	
	initCanvas = () => {
		// Variable initialization
		this.currentStepNumber = 0;
		
		// Get canvas
		this.fCanvas = document.querySelector("#foregroundCanvas");
		//this.bCanvas = document.querySelector("#backgroundCanvas");
		this.fCtx = this.fCanvas.getContext("2d");
		//this.bCtx = this.bCanvas.getContext("2d");
		
		// Ground assets
		this.ground = this.assetsManager.get("ground").grass;
		this.obstacle = this.assetsManager.get("obstacle").wood;
		
		// Draw background once
		this.ground.onload = () => {
			//this.drawBackground();
		}
	
		this.tankDrawer = new TankDrawer(this.fCtx, this, this.assetsManager, this.camera);
		this.bulletDrawer = new BulletDrawer(this.fCtx, this, this.assetsManager, this.camera);
		this.mineDrawer = new MineDrawer(this.fCtx, this, this.assetsManager, this.camera);
	}
	
	drawForeground = (gameState) => {
		
		// Increment animation step number
		this.currentStepNumber = (this.currentStepNumber + 1) % 20;
		
		// Reset canvas
		this.fCtx.clearRect(0, 0, this.fCanvas.width, this.fCanvas.height);
		this.fCtx.shadowOffsetX = 0;
		this.fCtx.shadowOffsetY = 0;
		this.fCtx.shadowBlur = 0;
	
		if(gameState.walls) {
			this.walls = gameState.walls;
			this.mapWidth = gameState.width * 32;
			this.mapHeight = gameState.height * 32;
			return;
		}
		
		let currentPlayer = gameState.players.find(p => p.self);
		
		this.camera.offsetX = currentPlayer.x - this.camera.width / 2;
		this.camera.offsetX = Math.max(0, this.camera.offsetX);
		this.camera.offsetX = Math.min(this.mapWidth - this.camera.width, this.camera.offsetX);
		
		this.camera.offsetY = currentPlayer.y - this.camera.height / 2;
		this.camera.offsetY = Math.max(0, this.camera.offsetY);
		this.camera.offsetY = Math.min(this.mapHeight - this.camera.height, this.camera.offsetY);
		
		// First loop to draw floors
		for(let i in this.walls) {
			let wall = this.walls[i];
			this.fCtx.drawImage(this.assetsManager.getImage(wall.code), wall.x * 32 - this.camera.offsetX, wall.y * 32 - this.camera.offsetY);
		}
	
		this.fCtx.shadowOffsetX = 1;
		this.fCtx.shadowOffsetY = 1;
		this.fCtx.shadowColor = "black";
		this.fCtx.shadowBlur = 6;
		
		// Second loop to draw walls
//		for(let i in walls) {
//			let wall = walls[i];
//			if(wall.obstacle) {
//				this.fCtx.drawImage(this.obstacle, wall.x * 32, wall.y * 32);
//			}
//		}

		// Draw mines
		gameState.mines.forEach(mine => this.mineDrawer.draw(mine));
		
		// Draw tanks
		gameState.players.forEach(tank => this.tankDrawer.draw(tank));
		
		// Draw bullet
		gameState.bullets.forEach(bullet => this.bulletDrawer.draw(bullet));
	}
	
	drawBackground = () => {
//		let groundPattern = this.bCtx.createPattern(this.ground, 'repeat');
//		
//		this.bCtx.fillStyle = groundPattern;
//		this.bCtx.fillRect(0, 0, this.bCanvas.width, this.bCanvas.height);
	}
	
	transform = (o) => {
		return {x: o.x - camera.offsetX, y: o.y - camera.offsetY};
	}
	
}