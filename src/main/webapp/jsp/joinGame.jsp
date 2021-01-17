<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TankDeluxe3000</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <!-- JS -->
		<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
		
		<!-- CSS -->
		<link rel="stylesheet" href="/css/main.css" />
		<link rel="stylesheet" href="/webjars/font-awesome/css/font-awesome.min.css"/>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
        
		<style>
		
			#mainDiv {
				width: 1200px;
				height: 800px;
				border: 1px solid black;
				background-color: white;
				position: relative;
			}
			
	 		#mainDiv canvas {
	 			position: absolute;
	 			top: 0;
	 			left: 0;
	 		}
	 		
	 		#scoreDiv {
		 		width: 400px;
		    	height: 800px;
		    	border: 1px solid black;
		    	position: absolute;
		    	left: 1200px;
		    	background-color: lightsalmon;
	 		}
	 		
	 		#scoreDiv h1 {
	 			text-align: center;
	 		}
		
		</style>
	
	</head>

	<body>

		<jsp:include page="/jsp/header.jsp"></jsp:include>
		
		<div id="mainDiv">
			<canvas id="backgroundCanvas" width=1200 height=800></canvas>
			<canvas id="foregroundCanvas" width=1200 height=800></canvas>
			<div id="scoreDiv">
				<h1>Scores : </h1>
				<br/>
				<div id="scoreWrapper"></div>
			</div>
		</div>
		
		<div id="respawnModal" style="display: none;">
			<p>Vous êtes mort. Cliquez ici pour réapparaitre</p>
			<button onclick="window.location.reload();">Respawn</button>	
		</div>
	
		<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="js/drawer/tank-drawer.js"></script>
		<script type="text/javascript" src="js/drawer/bullet-drawer.js"></script>
		<script type="text/javascript" src="js/controls.js"></script>
		<script type="text/javascript" src="js/assetsManager.js"></script>
		<script type="text/javascript" src="js/draw.js"></script>
		<script type="text/javascript" src="js/websocket.js"></script>
		<script type="text/javascript">
		
			var login = "${login}";
			var playerDied = false;
			var assetsManager = new AssetsManager();
			var drawer = new Drawer(assetsManager);
			var controls = new Controls();
			var webSocketClient;
			
			// Load all assets
			assetsManager.loadAssets(assetsLoaded);

			// Once assets are loaded
			function assetsLoaded() {
				// Init game canvas
				drawer.initCanvas();

				// Plug view on websocket
				webSocketClient = new WebSocketClient(drawer, controls);
			}
			
			function updateScoreDiv(scores) {
				
				var bestPlayer = scores.bestPlayer;
				var bestScore  = scores.bestScore;
				
				var scoreDiv = $("#scoreWrapper");
				scoreDiv.empty();
				
				if(bestPlayer) {
					scoreDiv.append($("<h3>").text("Meilleur score : " + bestPlayer + " (" + bestScore + ")"));
				}
				
				for(var i in scores.players) {
					var playerScore = scores.players[i];
					scoreDiv.append($("<p>").text(playerScore.name + " (" + playerScore.score + ")"));
				}
				
			}
			
			function checkDeath(players) {
				
				if(playerDied) {
					return;
				}
				
				for(var i in players) {
					var p = players[i];
					if(!p.alive && p.name == name) {
						playerDied = true;
						displayRespawnModal();
					}
				}
			}
			
			function displayRespawnModal() {
				$("#respawnModal").css("display", "block");
			}
		
		</script>
	
	</body>
</html>