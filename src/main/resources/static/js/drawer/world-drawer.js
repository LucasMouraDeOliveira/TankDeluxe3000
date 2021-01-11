class WorldDrawer {
	
	constructor(ctx) {
		this.ctx = ctx;
		this.drewBackground = false;
		this.loadImages();
	}
	
	loadImages() {
		this.backgrounds = new Map();
		loadBackground(0, "grass");
	}
	
	loadBackground(index, filename) {
		
	}
	
	draw(cells) {
		
		if(!this.drewBackground) {
			cells.forEach(cell => drawBackground(cell));
			this.drewBackground = true;
		}
		
		cells.forEach(cell => drawForeground(cell));
		
	}
	
	drawBackground(cell) {
		
	}
	
	drawForeground(cell) {
		if(cell.obstacle) {
			ctx.drawImage(obstacle, cell.x * 32, cell.y * 32);
		}
	}
	
}