<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#myTable').dataTable();
	});
</script>

<style>
.container {
	background-color: #d5dfec;
	box-shadow: 2px 1px 5px 0px #365596;
	margin-top: 2%;
	padding: 10px;
	overflow-x: scroll;
}

.divcolor {
	background-color: #365596;
	height: 40px;
	font-size: 20px;
	padding: 10px 5px;
	color: white;
	text-align: center;
}
</style>

</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${!(isSessionAlive==true)}">
				<%@ include file="index.jsp"%>
			</c:when>
			<c:otherwise>
				<div class="divcolor">Edit/Delete task details</div>
				<h4 align="center" style="color: red">
					<c:if test="${not empty message}">
    ${message}
   </c:if>
				</h4>
				<table id="myTable" class="table table-striped">
					<thead>
						<tr>
							<th>USER ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>EmployeeId</th>
							<th>Cluster</th>
							<th>Sub Cluster</th>
							<th>Shift Date</th>
							<th>Shift Start Time</th>
							<th>Shift End Time</th>
							<th>Task Type</th>
							<th>Task Id</th>
							<th>Task Description</th>
							<th>Edit</th>
							<th>Delete</th>
							<th>Export</th>
						</tr>
					<thead>
					<tbody>
						<c:forEach var="tasks" items="${tasksDetails}" varStatus="status">
							<c:set var="count" scope="session" value="${status.index}" />

							<tr>
								<td>${tasks.userid}</td>
								<td>${tasks.firstName}</td>
								<td>${tasks.lastName}</td>
								<td>${tasks.empId}</td>
								<td>${tasks.cluster}</td>
								<td>${tasks.subCluster}</td>
								<td>${tasks.startDate}</td>
								<td>${tasks.pickUp}</td>
								<td>${tasks.dropTime}</td>
								<td>${tasks.taskType}</td>
								<td>${tasks.taskId}</td>
								<td>${tasks.taskDescription}</td>
								<td><a
									href="<c:url value='/retrieveTask/${tasks.userid}'/>">Edit</a></td>
								<td><a href="<c:url value='/deleteTasks/${tasks.userid}'/>"
									onclick="return confirm('Are you sure want to Delete ?')">Delete</a></td>
								<td><a href="<c:url value='/exportTask/${tasks.empId}'/>">Export</a></td>
							</tr>
						</c:forEach>
					<tbody>
				</table>
				<div align="center">
					<c:if test="${count > 0}">
						<a href="<c:url value='/exportAllTasks'/>"><b
							style="color: #3A01DF">Export All</b></a>&nbsp;&nbsp;
					</c:if>
					<a href="/TrackingApp/home.do"> <b style="color: #3A01DF">Back</b></a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>