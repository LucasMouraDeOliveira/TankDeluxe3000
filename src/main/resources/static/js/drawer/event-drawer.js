class EventDrawer {
	
	constructor(ctx) {
		this.ctx = ctx;
		this.events = new Array();
		this.index = 0;
	}
	
	function addEvent(e) {
		this.events.add(e);
	}
	
	function start() {
		this.drawEventId = setInterval(drawCurrentEvent, 1000 / 20);
	}
	
	function drawEvent() {
		let event = this.events.get(index);
		let sprite = event.getNextSprite();
		
		this.ctx.drawImage(sprite.img, sprite.x, sprite.y);
		
		if(event.counter <= 0) {
			this.events.pop(index);
		}
		
		index = index % 20;
	}
	
	function stop() {
		clearInterval(this.drawEventId);
	}
}