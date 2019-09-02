var bulletSprite;

class BulletDrawer {
	
    constructor(ctx) {
        this.ctx = ctx;
        this.initParts();
        
        bulletSprite = new Image();
        
        // Assets loading
        bulletSprite.src = "/assets/img/bullet.png";
    }

    initParts() {
//        this.WHEEL_HEIGHT = 38;
//        this.WHEEL_WIDTH = 21;
    }
    
//    centerAround(img, x, y, width, height) {
//        this.ctx.drawImage(img, x - (width / 2) - (this.TANK_WIDTH / 2) , y - (height / 2) - (this.TANK_HEIGHT / 2));
//    }

    draw(bullet) {

        let x = bullet.x;
        let y = bullet.y;
        let a = bullet.angle;

        console.log(x + " " + y + " " + a);

        // Rotate context
//        this.ctx.save();
//        this.ctx.rotate(a);

        // Shadow
        this.ctx.shadowOffsetX = 1;
        this.ctx.shadowOffsetY = 1;
        this.ctx.shadowColor = "black";
        this.ctx.shadowBlur = 6;

        // Tank Drawing
        // Animation switch
        switch(currentStepNumber) {
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
        	this.ctx.drawImage(bulletSprite, x, y);
            break;
        }
        
        this.ctx.beginPath();
        this.ctx.arc(x, y, 10, 0, 2 * Math.PI);
        this.ctx.fill();
        this.ctx.stroke();

//        this.ctx.restore();
    }

}