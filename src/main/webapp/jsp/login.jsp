<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
         <!-- JS -->
		<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
		
		<!-- CSS -->
		<link rel="stylesheet" href="/css/main.css" />
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    </head>
    <body>
    
         <div class="container m-2">
        	<div class="row justify-content">
				<div class="col-md-8">
					<div class="card">
						<h5 class="card-header">Login</h5>
       					<div class="card-body">
       						<form class="form-horizontal" method="post" action="login">
       							<div class="form-group">
					            	<label for="username" class="cols-sm-2 control-label">Username :</label>
					            	<div class="cols-sm-10">
					            		<div class="input-group">
                                            <input type="text" class="form-control" name="username"/>
					            		</div>
					            	</div>
					            </div>
       							<div class="form-group">
					            	<label for="password" class="cols-sm-2 control-label">Password :</label>
					            	<div class="cols-sm-10">
					            		<div class="input-group">
                                            <input type="password" class="form-control" name="password"/>
					            		</div>
					            	</div>
					            </div>
					            <div class="form-group">
					            	<input class="btn btn-primary btn-lg btn-block" type="submit" value="Sign In"/>
					            </div>
       						</form>
       					</div>
       					 <div class="card-footer">
       					 	<span class="text-danger">Invalid username and password</span>
				        </div>
       				</div>
       			</div>
       		</div>
       	</div>
    </body>
</html>