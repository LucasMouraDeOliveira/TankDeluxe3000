<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	</head>
	<body>
	
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="/">TankDeluxe3000</a>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav mr-auto">
					<sec:authorize access="isAuthenticated()">
						<li class="nav-item"><a class="nav-link" href="/profile">Profile</a></li>
						<li class="nav-item"><a class="nav-link" href="/editor">Editeur</a></li>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" role="button" data-toggle="dropdown">
								Play
							</a>
							<div class="dropdown-menu">
					          <!-- <a class="dropdown-item" href="/createGame">Create a game</a>-->
					          <a class="dropdown-item" href="/joinGame">Join a game</a>
					        </div>
						</li>
					</sec:authorize>
				</ul>
				<sec:authorize access="isAuthenticated()">
					<form id="form" method="post" action="#" th:action="@{/logout}">
						<button class="btn btn-outline-dark" type="submit">Disconnect</button>
					</form>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<div class="btn-group">
						<a class="btn btn-primary" href="/register" th:href="@{/register}">Register</a>
						<a class="btn btn-outline-dark" href="/login" th:href="@{/login}">Connect</a>
					</div>
				</sec:authorize>
			</div>
		</nav>
			
	</body>
</html>