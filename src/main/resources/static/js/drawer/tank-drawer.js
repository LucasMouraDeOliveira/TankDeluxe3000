class TankDrawer {
	
    constructor(ctx, drawer, assetsManager) {
        this.ctx = ctx;
		this.drawer = drawer;
		this.tankModel = assetsManager.get("tank");
		
        this.initParts();
    }

    initParts = () => {
        this.WHEEL_HEIGHT = 38;
        this.WHEEL_WIDTH = 21;

        this.TANK_WIDTH = 60;
        this.TANK_HEIGHT = 76;

        this.TURRET_WIDTH = 29;
        this.TURRET_HEIGHT = 66;
        this.TURRET_X = 30;
        this.TURRET_Y = 20;

        this.TOP_LEFT_WHEEL_X = 7;
        this.TOP_LEFT_WHEEL_Y = 14;

        this.TOP_RIGHT_WHEEL_X = 54;
        this.TOP_RIGHT_WHEEL_Y = 14;

        this.BOT_LEFT_WHEEL_X = 7;
        this.BOT_LEFT_WHEEL_Y = 54;

        this.BOT_RIGHT_WHEEL_X = 54;
        this.BOT_RIGHT_WHEEL_Y = 54;
    }

    centerAround = (img, x, y, width, height) => {
        this.ctx.drawImage(img, x - (width / 2) - (this.TANK_WIDTH / 2) , y - (height / 2) - (this.TANK_HEIGHT / 2));
    }

    draw = (tank) => {
        let x = tank.x;
        let y = tank.y;
        let a = tank.angle;
        let tAngle = tank.turretAngle;
        let alive = tank.alive;

        // Rotate context
        this.ctx.save();
        this.ctx.translate(x, y);
        this.ctx.rotate(a);

        // Shadow
        this.ctx.shadowOffsetX = 1;
        this.ctx.shadowOffsetY = 1;
        this.ctx.shadowColor = "black";
        this.ctx.shadowBlur = 6;

        // Tank Drawing
        // Animation switch
        
        if(alive) {
        	this.drawAnimatedWheels();
        } else {
        	this.drawStaticWheels();
        }

        switch(tank.color){
        case "BLUE":
        	this.ctx.drawImage(this.tankModel.tankBodyBlue, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(this.tankModel.machineGunBlue, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "ORANGE":
        	this.ctx.drawImage(this.tankModel.tankBodyOrange, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(this.tankModel.machineGunOrange, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "YELLOW":
        	this.ctx.drawImage(this.tankModel.tankBodyYellow, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(this.tankModel.machineGunYellow, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "GREEN":
        	this.ctx.drawImage(this.tankModel.tankBodyGreen, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(this.tankModel.machineGunGreen, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "PURPLE":
        	this.ctx.drawImage(this.tankModel.tankBodyPurple, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(this.tankModel.machineGunPurple, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "TURQUOISE":
	        this.ctx.drawImage(this.tankModel.tankBodyTurquoise, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
	        this.ctx.rotate(-a);
	        this.drawShield(tank.nbShield);
	        this.ctx.rotate(tAngle);
	        this.centerAround(this.tankModel.machineGunTurquoise, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        }
        
        this.ctx.restore();

        if(!alive) {
        	this.drawExplosion(tank);
        }
        
    }
    
    drawShield = (nbShield) => {
    	for (var i = 0; i < nbShield; i++) {
			this.ctx.drawImage(this.tankModel.shield, i * 10, this.TANK_HEIGHT / 2, 10, 11);
		}
    }
    
    drawAnimatedWheels = () => {
    	 switch(this.drawer.currentStepNumber) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
             this.centerAround(this.tankModel.wheelBackRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelBackLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelFrontRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelFrontLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             break;
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
             this.centerAround(this.tankModel.wheelFrontRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelFrontLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelBackRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelBackLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             break;
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
             this.centerAround(this.tankModel.wheelBackRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelBackLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelFrontRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelFrontLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             break;
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
             this.centerAround(this.tankModel.wheelFrontRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelFrontLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelBackRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(this.tankModel.wheelBackLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             break;
         }
    }
    
    drawStaticWheels = () => {
    	this.centerAround(this.tankModel.wheelBackRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
        this.centerAround(this.tankModel.wheelBackLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
        this.centerAround(this.tankModel.wheelFrontRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
        this.centerAround(this.tankModel.wheelFrontLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
    }
    
    drawExplosion = (tank) => {
    	let tmpX = tank.x + this.TANK_WIDTH / 2;
    	let tmpY = tank.y + this.TANK_HEIGHT / 2;
    	
		if(this.drawer.currentStepNumber < 5) {
			this.centerAround(this.tankModel.explosion_1, tmpX, tmpY, this.tankModel.explosion_1.width, this.tankModel.explosion_1.height);
		} else if(this.drawer.currentStepNumber < 10) {
			this.centerAround(this.tankModel.explosion_2, tmpX, tmpY, this.tankModel.explosion_2.width, this.tankModel.explosion_2.height);
		} else if(this.drawer.currentStepNumber < 15) {
			this.centerAround(this.tankModel.explosion_3, tmpX, tmpY, this.tankModel.explosion_3.width, this.tankModel.explosion_3.height);
		} else if(this.drawer.currentStepNumber <= 20) {
			this.centerAround(this.tankModel.explosion_4, tmpX, tmpY, this.tankModel.explosion_4.width, this.tankModel.explosion_4.height);
		}
    }

}