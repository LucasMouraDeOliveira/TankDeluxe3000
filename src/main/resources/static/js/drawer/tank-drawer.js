class TankDrawer {
	
    constructor(ctx, drawer, assetsManager, camera) {
        this.ctx = ctx;
		this.drawer = drawer;
		this.camera = camera;
		this.tankModel = assetsManager.get("tank");
		
        this.initParts();
    }

    initParts = () => {
        this.WHEEL_HEIGHT = 30;
        this.WHEEL_WIDTH = 17;

        this.TANK_WIDTH = 48;
        this.TANK_HEIGHT = 61;

        this.TURRET_WIDTH = 23;
        this.TURRET_HEIGHT = 53;
        this.TURRET_X = 24;
        this.TURRET_Y = 16;

        this.TOP_LEFT_WHEEL_X = 6;
        this.TOP_LEFT_WHEEL_Y = 11;

        this.TOP_RIGHT_WHEEL_X = 43;
        this.TOP_RIGHT_WHEEL_Y = 11;

        this.BOT_LEFT_WHEEL_X = 6;
        this.BOT_LEFT_WHEEL_Y = 43;

        this.BOT_RIGHT_WHEEL_X = 43;
        this.BOT_RIGHT_WHEEL_Y = 43;
    }

    centerAround = (img, x, y, width, height) => {
        this.ctx.drawImage(img, x - (width / 2) - (this.TANK_WIDTH / 2) , y - (height / 2) - (this.TANK_HEIGHT / 2), width, height);
    }

    draw = (tank) => {
		tank.x = tank.x - this.camera.offsetX;
		tank.y = tank.y - this.camera.offsetY;
	
        let x = tank.x;
        let y = tank.y;
        let a = tank.angle;
        let tAngle = tank.turretAngle;
        let alive = tank.alive;
		let charging = tank.shooting;

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

		let tankBodySprite;
		let turretSprite;
		
        switch(tank.color){
        case "BLUE":
			tankBodySprite = this.tankModel.tankBodyBlue;
			turretSprite = this.tankModel.machineGunBlue;
        	break;
        case "ORANGE":
			tankBodySprite = this.tankModel.tankBodyOrange;
			turretSprite = this.tankModel.machineGunOrange;
        	break;
        case "YELLOW":
			tankBodySprite = this.tankModel.tankBodyYellow;
			turretSprite = this.tankModel.machineGunYellow;
        	break;
        case "GREEN":
			tankBodySprite = this.tankModel.tankBodyGreen;
			turretSprite = this.tankModel.machineGunGreen;
        	break;
        case "PURPLE":
			tankBodySprite = this.tankModel.tankBodyPurple;
			turretSprite = this.tankModel.machineGunPurple;
        	break;
        case "TURQUOISE":
			tankBodySprite = this.tankModel.tankBodyTurquoise;
			turretSprite = this.tankModel.machineGunTurquoise;
        	break;
        }
 
		// Draw tank body
		this.ctx.drawImage(tankBodySprite, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2));
		
		// Draw arc
		if(alive && charging && !tank.invincible) {
			this.drawArc(tank);
		}

		// Draw shield
		this.ctx.rotate(-a);
		this.drawShield(tank.nbShield);
		
		// Draw username
		this.ctx.font = '12px sans serif';
		this.ctx.shadowColor = "white";
		this.ctx.shadowBlur = 3;
		this.ctx.fillText(tank.name, -(this.TANK_WIDTH / 2), -(this.TANK_HEIGHT / 2), 50);
		this.ctx.shadowBlur = 0;
		

		// Draw turret
		this.ctx.rotate(tAngle);
		this.centerAround(turretSprite, this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
		if(tank.charge >= 285) {	// 285 = 2000 / 7 (max_charge / nb of heat level)
			this.centerAround(this.getTurretHeatSprite(tank.charge), this.TURRET_X, this.TURRET_Y, this.TURRET_WIDTH, this.TURRET_HEIGHT);
		}
        this.ctx.restore();

        if(!alive) {
        	this.drawExplosion(tank);
        } else if(tank.invincible) {
			this.ctx.drawImage(this.tankModel.bubbleShield, x-(this.TANK_WIDTH / 2)-15, y-(this.TANK_HEIGHT / 2)-10, this.TANK_HEIGHT + 20, this.TANK_HEIGHT + 20);
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

	drawArc = (tank) => {
		
		let arcLevel;
		if(tank.charge < 285){
			return;
		} else if(tank.charge < 1000) {
			arcLevel = 1;
		} else if(tank.charge < 2000) {
			arcLevel = 2;
		} else {
			arcLevel = 3;
		}

		let tmpX = this.TANK_WIDTH / 1.2;
    	let tmpY = this.TANK_HEIGHT / 1.2;
		let factor = 1.6;
		
		let sprite;
		switch(this.drawer.currentStepNumber) {
			case 0:
			case 1:
				sprite = this.tankModel["arc"+arcLevel+"A"];
				break;
			case 2:
			case 3:
				sprite = this.tankModel["arc"+arcLevel+"B"];
				break;
			case 4:
			case 5:
				sprite = this.tankModel["arc"+arcLevel+"C"];
				break;
			case 6:
			case 7:
				sprite = this.tankModel["arc"+arcLevel+"D"];
				break;
			case 8:
			case 9:
				sprite = this.tankModel["arc"+arcLevel+"E"];
				break;
			case 10:
			case 11:
				sprite = this.tankModel["arc"+arcLevel+"F"];
				break;
			case 12:
			case 13:
				sprite = this.tankModel["arc"+arcLevel+"A"];
				break;
			case 14:
			case 15:
				sprite = this.tankModel["arc"+arcLevel+"B"];
				break;
			case 16:
			case 17:
				sprite = this.tankModel["arc"+arcLevel+"C"];
				break;
			case 18:
			case 19:
				sprite = this.tankModel["arc"+arcLevel+"D"];
				break;
			default:
				sprite = this.tankModel["arc"+arcLevel+"F"];
		}
		
		this.ctx.drawImage(sprite, -tmpX, -tmpY, sprite.width / factor, sprite.height / factor);
	}
	
	getTurretHeatSprite = (charge) => {
		let heatLevel = Math.min(7, Math.floor(charge / 285));
		return this.tankModel["barrelHeat"+ heatLevel];
	}

}