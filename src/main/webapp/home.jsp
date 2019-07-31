<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Battle Tank</title>
	
	<style>
	
		#mainDiv {
			width: 640px;
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
			width: 100%;
			height: 100%;
		}
	
	</style>
	
</head>
<body>

	<div id="mainDiv">
		<canvas id="backgroundCanvas"></canvas>
		<canvas id="foregroundCanvas"></canvas>
	</div>
	
	<script type="text/javascript" src="js/controls.js"></script>
	<script type="text/javascript" src="js/draw.js"></script>
	<script type="text/javascript" src="js/websocket.js"></script>

</body>
</html>