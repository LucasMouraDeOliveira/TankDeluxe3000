<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TankDeluxe3000</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		
		<!-- CSS -->
		<link rel="stylesheet" href="/css/tank-delux.css" />
		<link rel="stylesheet" href="/webjars/font-awesome/css/font-awesome.min.css"/>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
        
		<style>
		
			html, body {
				height: 100%;
			}
		
			canvas {
	 		   cursor: url(/assets/img/aim.png), auto;
			}
		</style>
	
	</head>

	<body>

<%-- 		<jsp:include page="/jsp/header.jsp"></jsp:include> --%>

		<div class="container-fluid h-100">
			<div class="row h-100" style="background-color: darkslategrey;">
				<div class="col-9 h-100 w-100 my-auto text-center">
					<canvas id="foregroundCanvas" class="mt-5" width=1300 height=800></canvas>
				</div>
				<div class="col-3">
					<div class="card mt-5 mr-3">
						<div class="card-title mt-2">
							<h1 class="text-center">Scores</h1>
						</div>
						<div id="scoreWrapper" class="card-body">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Respawn Modal -->
		<div id="respawnModal" class="modal fade show" tabindex="-1" aria-labelledby="respawnModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="respawnModalLabel">Respawn ?</h5>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" onclick="respawn()" data-dismiss="modal">Yeah !</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Select spec Modal -->
		<div id="specModal" class="modal fade show" tabindex="-1" aria-labelledby="respawnModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="respawnModalLabel">Choose your specialization !</h5>
					</div>
					<div class="modal-body">
						<div class="row">
						
							<div class="card col-5 m-2 shadow-sm" onclick='$(this).find("input").prop("checked", true)'>
								<div class="card-body">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3" value="SHOOTER">

										<h5 class="card-title">Shooter</h5>
										<h6 class="card-subtitle mb-2 text-muted">Two more bullets</h6>
									</div>
									
								</div>
							</div>
							<div class="card col-5 m-2 shadow-sm" onclick='$(this).find("input").prop("checked", true)'>
								<div class="card-body">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault4" value="QUICKER">
									
										<h5 class="card-title">Quicker</h5>
										<h6 class="card-subtitle mb-2 text-muted">Faster bullet</h6>
									</div>
								</div>
							</div>
							<div class="card disabled col-5 m-2 shadow-sm" onclick='$(this).find("input").prop("checked", true)'>
								<div class="card-body">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value="SNIPER">

										<h5 class="card-title">Sniper</h5>
										<h6 class="card-subtitle mb-2 text-muted">See where bullets goes</h6>
									</div>
									
								</div>
							</div>
							<div class="card col-5 m-2 shadow-sm" onclick='$(this).find("input").prop("checked", true)'>
								<div class="card-body">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value="MINER">

										<h5 class="card-title">Miner</h5>
										<h6 class="card-subtitle mb-2 text-muted">One mine to drop</h6>
									</div>
									
								</div>
							</div>
							
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" onclick="specChoosed()" data-dismiss="modal">Yeah !</button>
					</div>
				</div>
			</div>
		</div>
	
		<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/js/drawer/tank-drawer.js"></script>
		<script type="text/javascript" src="/js/drawer/bullet-drawer.js"></script>
		<script type="text/javascript" src="/js/drawer/mine-drawer.js"></script>
		<script type="text/javascript" src="/js/controls.js"></script>
		<script type="text/javascript" src="/js/assetsManager.js"></script>
		<script type="text/javascript" src="/js/draw.js"></script>
		<script type="text/javascript" src="/js/websocket.js"></script>
		<script type="text/javascript">
		
			var login = "${login}";
			var playerDied = false;
			var assetsManager = new AssetsManager();
			var camera = {width: 1300, height: 800, offsetX: 0, offsetY: 0};
			var drawer = new Drawer(assetsManager, camera);
			var controls = new Controls(camera);
			var webSocketClient;
			
			// Load all assets
			assetsManager.loadAssets(assetsLoaded);

			// Once assets are loaded
			function assetsLoaded() {
				// Init game canvas
				drawer.initCanvas();

				// Show spec modal
				$("#specModal").modal("show");
			}

			function specChoosed() {
				let spec = $('input[name="flexRadioDefault"]:checked').val();

				Promise.all([initializePlayer(spec), loadMap()]).then(plugWebsocket);
			}

			function initializePlayer(spec) {
				return $.ajax({
					type: "POST",
					contentType: 'application/json',
					url: "/games/${gameId}/player", 
					data: JSON.stringify({userId: "${userId}", specialization: spec})
				});
			}

			function loadMap() {
				return $.get("/games/${gameId}").done((map) => drawer.drawForeground(JSON.parse(map)));
			}

			function plugWebsocket() {
				webSocketClient = new WebSocketClient("${gameId}", "${userId}", drawer, controls);
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
					let content = $("<div>");
					let name = $("<strong>").append(playerScore.name);
					let score = $("<span>").text(" " + playerScore.score + " points");
					content.append(name);
					content.append(score);
					scoreDiv.append(content);
				}
			}
			
			function checkDeath(players) {
				
				if(playerDied) {
					return;
				}
				
				for(var i in players) {
					var p = players[i];
					if(!p.alive && p.name == login) {
						playerDied = true;
						displayRespawnModal();
					}
				}
			}
			
			function displayRespawnModal() {
				$("#respawnModal").modal("show");
			}

			function respawn() {
				$.ajax({
					type: "PUT",
					url: "/games/${gameId}/player/${userId}", 
				})
				.done(() => playerDied = false);
			}
		</script>
	
	</body>
</html>