class AssetsManager {
	
	constructor() {
		this.MODELS_URL = "/assets/models";
		this.ASSETS_URL = "/assets/img";
		this.models = new Map();
	}
	
	loadAssets = (assetsLoadedCallback) => {
		$.get(this.MODELS_URL)
		.done((models) => {
			Promise.all(models.map(model => this.loadModel(model)))
					.then((values) => values.forEach((value) => value.assets.map((part) => {name: part.name, image: this.createImageFromUrl(part.url)} ).then((loaded) => this.models.set(value.name, loaded))))
					.then(assetsLoadedCallback);
		});
	}
	
	loadModel = (modelName) => {
		return $.get(this.MODELS_URL + "/" + modelName);
	}
	
	createImageFromUrl = (url) => {
		let image = new Image();
		
		image.src = url;
		
		return image;
	}
	
}