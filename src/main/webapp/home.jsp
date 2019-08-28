<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Battle Tank</title>
	
	<style>
	
		#mainDiv {
			width: 800px;
			height: 640px;
			margin: auto;
			border: 1px solid black;
			background-color: white;
			position: relative;
		}
		
 		#mainDiv canvas {
 			position: absolute;
 			top: 0;
 			left: 0;
 		}
	
	</style>
	
</head>
<body>

	<div id="mainDiv">
		<canvas id="backgroundCanvas" width=800 height=640></canvas>
		<canvas id="foregroundCanvas" width=800 height=640></canvas>
	</div>

	<script type="text/javascript" src="js/drawer/tank-drawer.js"></script>
	<script type="text/javascript" src="js/drawer/bullet-drawer.js"></script>
	<script type="text/javascript" src="js/controls.js"></script>
	<script type="text/javascript" src="js/draw.js"></script>
	<script type="text/javascript" src="js/websocket.js"></script>

</body>
</html>