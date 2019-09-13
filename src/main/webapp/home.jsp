<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Battle Tank</title>
	
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

	<div id="mainDiv">
		<canvas id="backgroundCanvas" width=1200 height=800></canvas>
		<canvas id="foregroundCanvas" width=1200 height=800></canvas>
		<div id="scoreDiv">
			<h1>Scores : </h1>
			<br/>
			<div id="scoreWrapper"></div>
		</div>
	</div>

	<script type="text/javascript" src="js/drawer/tank-drawer.js"></script>
	<script type="text/javascript" src="js/drawer/bullet-drawer.js"></script>
	<script type="text/javascript" src="js/controls.js"></script>
	<script type="text/javascript" src="js/draw.js"></script>
	<script type="text/javascript" src="js/websocket.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
	
	<script type="text/javascript">
	
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
	
	</script>

</body>
</html>