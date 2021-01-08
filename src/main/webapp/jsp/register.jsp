<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        
       <!-- JS -->
		<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
		
		<!-- CSS -->
		<link rel="stylesheet" href="../../css/main.css" />
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    </head>
    <body>
    
    	<jsp:include page="/jsp/header.jsp"></jsp:include>
    	
        <div class="container m-2">
        	<div class="row justify-content">
				<div class="col-md-8">
					<div class="card">
						<h5 class="card-header">New account</h5>
       					<div class="card-body">
       						<form class="form-horizontal" action="/register" method="post">
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
					            	<label for="confirm-password" class="cols-sm-2 control-label">Confirm password :</label>
					            	<div class="cols-sm-10">
					            		<div class="input-group">
                                            <input type="password" class="form-control" name="confirm-password"/>
					            		</div>
					            	</div>
					            </div>
       							<div class="form-group">
					            	<label for="email" class="cols-sm-2 control-label">Email :</label>
					            	<div class="cols-sm-10">
					            		<div class="input-group">
                                            <input type="text" class="form-control" name="email"/>
					            		</div>
					            	</div>
					            </div>
					            <div class="form-group">
					            	<input class="btn btn-primary btn-lg btn-block" type="submit" value="Register"/>
					            </div>
       						</form>
       					</div>
       					<c:if test="${error}">
       						 <div class="card-footer">
					        	<span class="text-danger">${error}</span>
					        </div>
       					</c:if>
					</div>
				</div>
        	</div>
        </div>
       	
    </body>
</html>