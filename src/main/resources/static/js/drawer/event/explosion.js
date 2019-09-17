class Explosion {
	
	constructor(spriteSources, x, y) {
		this.index = 0;
		this.sprites = spriteSources;
		this.counter = spriteSOurces.length;
	}
	
	function getNextSprite() {
		let currentSprite = sprites[index];
		
		--counter;
		++index;
		
		return currentSprite;
	}
}