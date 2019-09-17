var explosion_1;
var explosion_2;
var explosion_3;
var explosion_4;

var wheelFrontRight;
var wheelFrontLeft;
var wheelBackRight;
var wheelBackLeft;
var tankBodyBlue;
var tankBodyOrange;
var tankBodyYellow;
var tankBodyTurquoise;
var tankBodyGreen;
var tankBodyPurple;
var machineGunBlue;
var machineGunOrange;
var machineGunYellow;
var machineGunTurquoise;
var machineGunPurple;
var machineGunGreen;
var shield;



class TankDrawer {
	
    constructor(ctx) {
        this.ctx = ctx;
        this.initParts();
        
        // Tank assets
        wheelFrontRight = new Image();
        wheelFrontLeft = new Image();
        wheelBackRight = new Image();
        wheelBackLeft = new Image();
        tankBodyBlue = new Image();
        tankBodyOrange = new Image();
        tankBodyYellow = new Image();
        tankBodyTurquoise = new Image();
        tankBodyGreen = new Image();
        tankBodyPurple = new Image();
        machineGunBlue = new Image();
        machineGunOrange = new Image();
        machineGunYellow = new Image();
        machineGunTurquoise = new Image();
        machineGunPurple = new Image();
        machineGunGreen = new Image();
        
        // Equipment
        shield = new Image();
        
        explosion_1 = new Image();
        explosion_2 = new Image();
        explosion_3 = new Image();
        explosion_4 = new Image();
        
    	// Assets loading
    	wheelFrontRight.src = "/assets/img/wheel-front-right-S.png";
    	wheelFrontLeft.src = "/assets/img/wheel-front-left-S.png";
    	wheelBackRight.src = "/assets/img/wheel-back-right-S.png";
    	wheelBackLeft.src = "/assets/img/wheel-back-left-S.png";
    	tankBodyBlue.src = "/assets/img/tank-body-S-blue.png";
    	tankBodyOrange.src = "/assets/img/tank-body-S-orange.png";
    	tankBodyYellow.src = "/assets/img/tank-body-S-yellow.png";
    	tankBodyTurquoise.src = "/assets/img/tank-body-S-turquoise.png";
    	tankBodyGreen.src = "/assets/img/tank-body-S-green.png";
    	tankBodyPurple.src = "/assets/img/tank-body-S-purple.png";
    	machineGunBlue.src = "/assets/img/barrel-S-blue.png";
    	machineGunOrange.src = "/assets/img/barrel-S-orange.png";
    	machineGunYellow.src = "/assets/img/barrel-S-yellow.png";
    	machineGunTurquoise.src = "/assets/img/barrel-S-turquoise.png";
    	machineGunPurple.src = "/assets/img/barrel-S-purple.png";
    	machineGunGreen.src = "/assets/img/barrel-S-green.png";
    	
    	shield.src = "/assets/img/shield-S.png";
    	
    	explosion_1.src = "/assets/img/explosion_1_S.png";
    	explosion_2.src = "/assets/img/explosion_2_S.png";
    	explosion_3.src = "/assets/img/explosion_3_S.png";
    	explosion_4.src = "/assets/img/explosion_4_S.png";
    }

    initParts() {
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

    centerAround(img, x, y, width, height) {
        this.ctx.drawImage(img, x - (width / 2) - (this.TANK_WIDTH / 2) , y - (height / 2) - (this.TANK_HEIGHT / 2));
    }

    draw(tank) {
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
        	this.drawAnimatedWheels(currentStepNumber);
        } else {
        	this.drawStaticWheels();
        }

        switch(tank.color){
        case "BLUE":
        	this.ctx.drawImage(tankBodyBlue, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(machineGunBlue, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "ORANGE":
        	this.ctx.drawImage(tankBodyOrange, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(machineGunOrange, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "YELLOW":
        	this.ctx.drawImage(tankBodyYellow, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(machineGunYellow, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "GREEN":
        	this.ctx.drawImage(tankBodyGreen, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(machineGunGreen, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "PURPLE":
        	this.ctx.drawImage(tankBodyPurple, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
        	this.ctx.rotate(-a);
        	this.drawShield(tank.nbShield);
        	this.ctx.rotate(tAngle);
        	this.centerAround(machineGunPurple, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        case "TURQUOISE":
	        this.ctx.drawImage(tankBodyTurquoise, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
	        this.ctx.rotate(-a);
	        this.drawShield(tank.nbShield);
	        this.ctx.rotate(tAngle);
	        this.centerAround(machineGunTurquoise, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
        	break;
        }
        
        this.ctx.restore();

        if(!alive) {
        	this.drawExplosion(currentStepNumber, tank);
        }
        
    }
    
    drawShield(nbShield) {
    	for (var i = 0; i < nbShield; i++) {
			this.ctx.drawImage(shield, i * 10, this.TANK_HEIGHT / 2, 10, 11);
		}
    }
    
    drawAnimatedWheels(currentStepNumbern) {
    	 switch(currentStepNumber) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
             this.centerAround(wheelBackRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelBackLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelFrontRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelFrontLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             break;
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
             this.centerAround(wheelFrontRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelFrontLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelBackRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelBackLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             break;
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
             this.centerAround(wheelBackRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelBackLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelFrontRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelFrontLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             break;
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
             this.centerAround(wheelFrontRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelFrontLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelBackRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             this.centerAround(wheelBackLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
             break;
         }
    }
    
    drawStaticWheels() {
    	this.centerAround(wheelBackRight, this.BOT_RIGHT_WHEEL_X, this.BOT_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
        this.centerAround(wheelBackLeft, this.BOT_LEFT_WHEEL_X, this.BOT_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
        this.centerAround(wheelFrontRight, this.TOP_RIGHT_WHEEL_X, this.TOP_RIGHT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
        this.centerAround(wheelFrontLeft, this.TOP_LEFT_WHEEL_X, this.TOP_LEFT_WHEEL_Y, this.WHEEL_WIDTH, this.WHEEL_HEIGHT);
    }
    
    drawExplosion(currentStepNumber, tank) {
    	let tmpX = tank.x + this.TANK_WIDTH / 2;
    	let tmpY = tank.y + this.TANK_HEIGHT / 2;
    	
		if(currentStepNumber < 5) {
			this.centerAround(explosion_1, tmpX, tmpY, explosion_1.width, explosion_1.height);
		} else if(currentStepNumber < 10) {
			this.centerAround(explosion_2, tmpX, tmpY, explosion_2.width, explosion_2.height);
		} else if(currentStepNumber < 15) {
			this.centerAround(explosion_3, tmpX, tmpY, explosion_3.width, explosion_3.height);
		} else if(currentStepNumber <= 20) {
			this.centerAround(explosion_4, tmpX, tmpY, explosion_4.width, explosion_4.height);
		}
    }

}