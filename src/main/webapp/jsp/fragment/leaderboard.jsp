<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<table id="leaderboard" class="table table-striped table-dark">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Score</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Matthieu</td>
				<td>Ho !</td>
			</tr>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$.get("/leaderboard")
		.done(setupLeaderboard);
	});

	function setupLeaderboard(datas) {
		datas.forEach(setupLeaderboardRow);
	}

	function setupLeaderboardRow(row) {
		let name = $("<td>").append(row.username);
		let score = $("<td>").append(row.score);
		let tr = $("<tr>").append(name).append(score);
		
		$("#leaderboard").find("tbody").append(tr);
	}
</script>