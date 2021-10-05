var mineSprite;

class MineDrawer {

	constructor(ctx, drawer, _, camera) {
		this.ctx = ctx;
		this.drawer = drawer;
		this.camera = camera;
		this.initParts();

		mineSprite = new Image();

		// Assets loading
		mineSprite.src = "/assets/img/circle-bullet.png";
	}
	
	initParts = () => {
        this.MINE_WIDTH = 12;
        this.MINE_HEIGHT = 12;
    }
    
    draw = (mine) => {
		mine.x -= this.camera.offsetX;
		mine.y -= this.camera.offsetY;

        // Shadow
        this.ctx.shadowOffsetX = 5;
        this.ctx.shadowOffsetY = 5;
        this.ctx.shadowColor = "black";
        this.ctx.shadowBlur = 6;

        // Tank Drawing
        // Animation switch
        switch(this.drawer.currentStepNumber) {
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
        	this.ctx.drawImage(mineSprite, mine.x - Math.round(this.MINE_WIDTH / 2), mine.y - Math.round(this.MINE_WIDTH / 2), this.MINE_WIDTH, this.MINE_HEIGHT);
            break;
        }
    }

}