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
    
       	<div class="row p-5">
       	
        	<div class="col">
	        	<div class="row justify-content">
	        		<div class="col-8">
						<div class="card">
							<h5 class="card-header">Bienvenue sur TankDeluxe3000, le meilleur jeu de tank sur navigateur !</h5>
		   					<div class="card-body">
								<p>Description du jeu</p>
		   					</div>
		   				</div>
	   				</div>
	       		</div>
	       		
	       		<div class="row justify-content mt-3">
	       			<div class="col-4">
		       			<a href="/selectGame" class="btn btn-lg btn-block btn-primary">Play</a>
	       			</div>
	       			<div class="col-4">
		       			<a href="/editor" class="btn btn-lg btn-block btn-light">Editor</a>
	       			</div>
	       		</div>
       		</div>
       		
       		<div class="col">
       			<jsp:include page="/jsp/fragment/leaderboard.jsp"></jsp:include>
       		</div>
   		</div>
        
    </body>
</html>