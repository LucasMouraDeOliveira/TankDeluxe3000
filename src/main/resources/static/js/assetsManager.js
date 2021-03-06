class AssetsManager {
	
	constructor() {
		this.MODELS_URL = "/assets/models";
		this.models = new Map();		// name => model
		this.codes = new Map();			// name => code
		this.images = new Map();		// code => image
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
	
	get = (modelName) => {
		return this.models.get(modelName);
	}
	
	getImage = (code) => {
		return this.images.get(code);
	}
	
	getCode = (modelName, assetName) => {
		return this.codes.get(modelName)[assetName];
	}
	
	loadModel = (modelName) => {
		return $.get(this.MODELS_URL + "/" + modelName);
	}
	
	loadModelsImages = () => {
		this.models.forEach((parts, key) => {
			let images = {};
			let codes = {};
			parts.forEach(part => {
				images[part.name] = this.createImageFromUrl(part.url);
				codes[part.name] = part.code;
				
				this.images.set(part.code, images[part.name]);
			});
				
			this.models.set(key, images);
			this.codes.set(key, codes);
		});
	}
	
	createImageFromUrl = (url) => {
		let image = new Image();
		
		image.src = url;
		
		return image;
	}
	
}