var bulletSprite;

class BulletDrawer {
	
    constructor(ctx, drawer, _, camera) {
        this.ctx = ctx;
		this.drawer = drawer;
		this.camera = camera;
        this.initParts();
        
        bulletSprite = new Image();
        
        // Assets loading
        bulletSprite.src = "/assets/img/circle-bullet.png";
    }

    initParts = () => {
        this.BULLET_WIDTH = 12;
        this.BULLET_HEIGHT = 12;
    }
    
    draw = (bullet) => {
		bullet.x -= this.camera.offsetX;
		bullet.y -= this.camera.offsetY;

        let x = bullet.x;
        let y = bullet.y;
        let a = bullet.angle;

        // Rotate context
//        this.ctx.save();
//        this.ctx.translate(x, y);
//        this.ctx.rotate(a);

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
        	this.ctx.drawImage(bulletSprite, bullet.x - Math.round(this.BULLET_WIDTH / 2), bullet.y - Math.round(this.BULLET_WIDTH / 2), this.BULLET_WIDTH, this.BULLET_HEIGHT);
            break;
        }
//        this.ctx.restore();
    }

}