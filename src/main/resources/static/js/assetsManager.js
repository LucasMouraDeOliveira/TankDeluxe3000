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
					.then((jsonModels) => jsonModels.forEach((model) => this.models.set(model.name, model.assets)))
					.then(this.loadModelsImages)
					.then(assetsLoadedCallback);
		});
	}
	
	getModel = (modelName) => {
		return this.models.get(modelName);
	}
	
	loadModel = (modelName) => {
		return $.get(this.MODELS_URL + "/" + modelName);
	}
	
	createImageFromUrl = (url) => {
		let image = new Image();
		
		image.src = url;
		
		return image;
	}
	
	loadModelsImages = () => {
		this.models.forEach((parts, key) => this.models.set(key, parts.map((part) => { return {name: part.name, image: this.createImageFromUrl(part.url)}})));
	}
	
}