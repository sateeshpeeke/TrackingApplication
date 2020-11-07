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
				<div class="divcolor">View/Edit/Delete/Export Employee details</div>
				<h4 align="center" style="color: red">
					<c:if test="${not empty message}">
    ${message}
   </c:if>
				</h4>

				<table id="myTable" class="table table-striped">
					<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Employee Id</th>
							<th>Gp ID</th>
							<th>Mobile Number</th>
							<th>Tcs Mail</th>
							<th>Pepsico Mail</th>
							<th>Cluster</th>
							<th>Sub Cluster</th>
							<th>Reporting To</th>
							<th>Primary Skills</th>
							<th>Secondary Skills</th>
							<th>Role</th>
							<th>Edit</th>
							<th>Delete</th>
							<th>Export</th>
							<!-- <th>Delete</th>  -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="eDetails" items="${employeeList}"
							varStatus="status">
							<c:set var="count" scope="session" value="${status.index}" />
							<tr>
								<td>${eDetails.firstName}</td>
								<td>${eDetails.lastName}</td>
								<td>${eDetails.empId}</td>
								<td>${eDetails.gpId}</td>
								<td>${eDetails.mobileNumber}</td>
								<td>${eDetails.tcsMail}</td>
								<td>${eDetails.pepsicoMail}</td>
								<td>${eDetails.cluster}</td>
								<td>${eDetails.subCluster}</td>
								<td>${eDetails.reportingTo}</td>
								<td>${eDetails.primarySkils}</td>
								<td>${eDetails.secondarySkils}</td>
								<td>${eDetails.role}</td>
								<td><a
									href="<c:url value='/retrieveEmpInfo/${eDetails.empId}'/>">Edit</a></td>
								<td><a
									href="<c:url value='/deleteEmpInfo/${eDetails.empId}'/>">Delete</a></td>
								<td><a href="<c:url value='/export/${eDetails.empId}'/>">Export</a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
				<div align="center">
					<c:if test="${count > 0}">
						<a href="<c:url value='/exportAll'/>"><b
							style="color: #3A01DF">Export All</b></a>&nbsp;&nbsp;
	</c:if>
					<a href="/TrackingApp/home.do"> <b style="color: #3A01DF">Back</b></a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>