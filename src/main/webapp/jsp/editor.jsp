<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Battle Tank</title>
		
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="/css/tank-delux.css"/>
		
		<style>
			canvas {
				position: absolute;
			}
		</style>
	</head>
	<body>
		<div class="row p-2 h-100">
			<div class="col-8 scrollable-x scrollable-y" style="min-height: 832px">
				<div class="">
					<canvas id="backgroundCanvas" width=1200 height=800></canvas>
					<canvas id="foregroundCanvas" width=1200 height=800></canvas>
					<canvas id="gridCanvas" width=1200 height=800></canvas>
				</div>
			</div>
			<div id="assetsPanel" class="col-4">
				<button class="float-right btn btn-primary" data-toggle="modal" data-target=".bd-export-modal-lg" data-action="export">Exporter</button>
				<button class="float-right btn btn-primary mr-2" data-toggle="modal" data-target=".bd-export-modal-lg" data-action="import">Importer</button>
				
				<h1>Assets</h1>
				<hr>
				
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item">
						<a class="nav-link active" id="ground-tab" data-toggle="tab" href="#ground-assets" role="tab" aria-controls="ground" aria-selected="true">Sol</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" id="obstacle-tab" data-toggle="tab" href="#obstacle-assets" role="tab" aria-controls="obstacle" aria-selected="false">Obstacle</a>
					</li>
				</ul>
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade pt-2 show active" id="ground-assets" role="tabpanel" aria-labelledby="ground-tab"></div>
					<div class="tab-pane fade pt-2" id="obstacle-assets" role="tabpanel" aria-labelledby="obstacle-tab"></div>
				</div>
				
				<%-- Asset card template --%>
				<div id="assetCardTemplate" class="card text-center shadow-sm d-none" style="width: 10rem;">
					<div class="card-body">
						<h5 class="card-title"></h5>
					</div>
				</div>
				<%-- ------------------- --%>
				
			</div>
			
		</div>
		
		<%-- Export level modal --%>
		<div id="exportLevelModal" class="modal fade bd-export-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
				        <h5 class="modal-title">Export du niveau</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				        	<span aria-hidden="true">&times;</span>
				        </button>
					</div>
					<div class="modal-body">
						<textarea id="exportLevelTextArea" class="w-100" style="height: 200px"></textarea>
		     		</div>
		     		<div class="modal-footer">
		     			<button onClick="importLevel()" data-dismiss="modal">Valider</button>
		     		</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/draw.js"></script>
		<script type="text/javascript" src="js/assetsManager.js"></script>
		<script type="text/javascript" src="js/editor.js"></script>
	</body>
</html>