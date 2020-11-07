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
				<div class="divcolor">Edit/Delete Leaves details</div>
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
							<th>Leave Type</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>NoOfDays</th>
							<th>Compoff WorkedDate</th>
							<th>Reason</th>
							<th>Leave Status</th>
							<th>Comments</th>
							<th>Edit</th>
							<th>Delete</th>
							<th>Export</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="leaves" items="${leavesdetails}"
							varStatus="status">
							<c:set var="count" scope="session" value="${status.index}" />

							<tr>
								<td>${leaves.userid}</td>
								<td>${leaves.firstName}</td>
								<td>${leaves.lastName}</td>
								<td>${leaves.empId}</td>
								<td>${leaves.cluster}</td>
								<td>${leaves.subCluster}</td>
								<td>${leaves.leaveType}</td>
								<td>${leaves.startDate}</td>
								<td>${leaves.endDate}</td>
								<td>${leaves.noOfDays}</td>
								<td>${leaves.compoffWorkedDate}</td>
								<td>${leaves.reason}</td>
								<c:choose>
									<c:when
										test="${leaves.leaveType == 'Casual leave'||leaves.leaveType == 'comp-offs'|| leaves.leaveType == 'Flexi-leave'}">
										<c:if test="${leaves.leaveStatus=='Pending'}">
											<td style="color: orange"><b>${leaves.leaveStatus}</b></td>
										</c:if>
										<c:if test="${leaves.leaveStatus=='Accepted'}">
											<td style="color: #3A01DF"><b>${leaves.leaveStatus}</b></td>
										</c:if>
										<c:if test="${leaves.leaveStatus=='Rejected'}">
											<td style="color: red"><b>${leaves.leaveStatus}</b></td>
										</c:if>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
								<td>${leaves.comments}</td>

								<td><a
									href="<c:url value='/retrieveLeave/${leaves.userid}'/>">Edit</a></td>
								<td><a
									href="<c:url value='/deleteLeaves/${leaves.userid}'/>"
									onclick="return confirm('Are you sure want to Delete ?')">Delete</a></td>
								<td><a href="<c:url value='/exportLeave/${leaves.empId}'/>">Export</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div align="center">
					<c:if test="${count > 0}">
						<a href="<c:url value='/exportAllLeaves'/>"><b
							style="color: #3A01DF">Export All</b></a>&nbsp;&nbsp;
					</c:if>
					<a href="/TrackingApp/home.do"> <b style="color: #3A01DF">Back</b></a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>