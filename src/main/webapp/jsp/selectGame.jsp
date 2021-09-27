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
							<table id="gameTable" class="table table-striped table-dark">
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
	       			<a id="joinGameButton" href="javascript:joinSelectedGame()" class="btn btn-lg btn-block btn-primary disabled">Rejoindre</a>
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
						<form>
							<div class="form-group">
								<label for="gameName">Choisir le nom de la partie</label>
								<input type="email" class="form-control" id="newGameName" placeholder="Nom...">
							</div>
							<div class="form-group">
								<label for="gameLevel">Choisir le niveau</label>
								<select class="form-control" id="newGameLevelId">
									<option value="1">Crossfire</option>
								</select>
							 </div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
						<button type="button" class="btn btn-primary" onClick="createGame()">Créer</button>
					</div>
				</div>
			</div>
		</div>

    </body>
    <script type="text/javascript">
    	var selectedGameId;
    	
		$(document).ready(function() {
			checkExistingGame();
			setInterval(checkExistingGame, 5000);
		});

		function checkExistingGame() {
			$.get("games/list")
				.done(setupGamesInfo)
				.fail(function() { console.error("Fail to fetch game list"); });	
		}

		function setupGamesInfo(gamesInfo) {
			$("#gameTable tbody").empty();

			if(gamesInfo?.length) {
				$("#noGameLabel").hide();
				gamesInfo.forEach(i => setupGameRow(i));
			} else {
				$("#noGameLabel").show();
			}
		}

		function setupGameRow(info) {
			let name = $("<td>").append(info.gameName);
			let levelName = $("<td>").append(info.levelName);
			let playerNumber = $("<td>").append(info.playerNumber);

			let row = $("<tr>")
						.attr("id", info.gameId)
						.attr("onClick", "selectGame(this)")
						.append(name)
						.append(levelName)
						.append(playerNumber);

			if(selectedGameId == info.gameId) {
				row.addClass("bg-primary");
			}
			
			$("#gameTable").append(row);
		}

		function createGame() {
			let datas = {
						name: $("#newGameName").val(),
						levelId: $("#newGameLevelId").val()
					};

			$.post("/games", datas)
				.done((newGameId) => {
					joinGame(newGameId);
				})
				.fail(() => {
					console.error("Unable to create new game");
				});
		}
		
		function selectGame(row) {
			$("#joinGameButton").removeClass("disabled");
			$("tr").removeClass("bg-primary");
			
			$(row).addClass("bg-primary");
			selectedGameId = $(row).attr("id");
		}

		function joinSelectedGame() {
			if(selectedGameId){
				joinGame(selectedGameId);
			} else {
				alert("Aucune partie selectionnée");
			}
		}
		
		function joinGame(gameId) {
			if(gameId) {
				window.location = "/joinGame/"+ gameId;
			} else {
				console.error("Incorrect game id "+ gameId);
			}
		}
    </script>
</html>