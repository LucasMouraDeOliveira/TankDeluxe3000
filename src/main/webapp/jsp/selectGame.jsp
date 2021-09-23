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
		<link rel="stylesheet" href="/css/index.css" />
		<link rel="stylesheet" href="/webjars/font-awesome/css/font-awesome.min.css"/>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
        
    </head>
    <body>
    
    	<jsp:include page="/jsp/header.jsp"></jsp:include>
    	
        <div class="container m-2">
        	<div class="row justify-content">
        		<div class="col-8">
					<div class="card">
						<h5 class="card-header">Selectionner une partie ou créez en une nouvelle.</h5>
	   					<div class="card-body">
							<table id="gameTable" class="table table-striped">
								<thead>
									<tr>
										<th>Nom</th>
										<th>Niveau</th>
										<th>Nombre de joueur</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
							<em id="noGameLabel">Aucune partie exitante</em>
	   					</div>
	   				</div>
   				</div>
       		</div>
       		
       		<div class="row justify-content mt-3">
       			<div class="col-4">
	       			<a href="javascript:joinGame()" class="btn btn-lg btn-block btn-primary">Rejoindre</a>
       			</div>
       			<div class="col-4">
	       			<a data-toggle="modal" data-target="#createGameModal" class="btn btn-lg btn-block btn-light">Créer une partie</a>
       			</div>
       		</div>
       	</div>
        
        <%-- Create game modal --%>
        <div class="modal fade" id="createGameModal" tabindex="-1" aria-labelledby="createGameModalLabel" aria-hidden="true">
        	<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="createGameModalLabel">Créer une partie</h5>
						<button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
						<button type="button" class="btn btn-primary">Créer</button>
					</div>
				</div>
			</div>
		</div>

    </body>
    <script type="text/javascript">
    	var selectedGameId;
    	
		$(document).ready(function() {
			setInterval(checkExistingGame, 5000);
		});

		function checkExistingGame() {
			$.get("games/list")
				.done(setupGamesInfo)
				.fail(function() { console.log("Fail to fetch game list"); });	
		}

		function setupGamesInfo(gamesInfo) {
			$("#gameTable tbody").empty();

			if(gamesInfo.length()) {
				$("#noGameLabel").hide();
				gamesInfo.forEach(e => setupGameRow(e));
			} else {
				$("#noGameLabel").show();
			}
		}

		function setupGameRow(info) {
			let name = $("td").append(info.gameName);
			let levelName = $("td").append(info.levelName);
			let playerNumber = $("td").append(info.playerNumber);

			let row = $("td")
						.attr("id", info.gameId)
						.attr("onClick", "selectGame(this)")
						.append(name)
						.append(levelName)
						.append(playerNumber);

			$("#gameTable").append(row);
		}

		function selectGame(row) {
			selectedGameId = $(row).attr("id");
		}
		
		function joinGame() {
			if(selectedGameId) {
				window.location = "/joinGame/"+ selectedGameId;
			} else {
				alert("Aucune partie sélectionnée");
			}
		}
    </script>
</html>