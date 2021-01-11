<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Battle Tank</title>
		
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
		
		<style>
			canvas {
				position: absolute;
			}
		</style>
	</head>
	<body>
		
		<div class="row p-2">
			<div class="col-10 canvasContainer">
				<canvas id="backgroundCanvas" width=1200 height=800></canvas>
				<canvas id="foregroundCanvas" width=1200 height=800></canvas>
			</div>
			<div class="col-2">
				<h1>Assets</h1>
			</div>
		</div>
		
		<button onclick="printMap()">Générer</button>
	
		<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="js/draw.js"></script>
		<script type="text/javascript" src="js/editor.js"></script>
		<script type="text/javascript" src="js/assetsManager.js"></script>
		<script type="text/javascript">
			
			var assetsManager = new AssetsManager();

			// Load all assets
			assetsManager.loadAssets(() => console.log(assetsManager.get("tank")));
		</script>
	
	</body>
</html>