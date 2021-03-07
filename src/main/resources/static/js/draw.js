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
	
		this.tankDrawer = new TankDrawer(this.fCtx, this, this.assetsManager);
		this.bulletDrawer = new BulletDrawer(this.fCtx, this, this.assetsManager);
	}
	
	drawForeground = (gameState) => {
		
		// Increment animation step number
		this.currentStepNumber = (this.currentStepNumber + 1) % 20;
		
		// Reset canvas
		this.fCtx.clearRect(0, 0, this.fCanvas.width, this.fCanvas.height);
		this.fCtx.shadowOffsetX = 0;
		this.fCtx.shadowOffsetY = 0;
		this.fCtx.shadowBlur = 0;
	
		let walls = gameState.walls;
		
		// First loop to draw floors
		for(let i in walls) {
			let wall = walls[i];
			this.fCtx.drawImage(this.assetsManager.getImage(wall.code), wall.x * 32, wall.y * 32);
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
	
}