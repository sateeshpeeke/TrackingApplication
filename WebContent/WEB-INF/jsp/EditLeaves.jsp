<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
input {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 6px;
}

textarea {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 6px;
}

input[type=submit] {
	background-color: #4CAF50;
	color: white;
}

.container {
	background-color: #d5dfec;
	padding: 10px;
	margin-right: auto;
	margin-left: auto;
	margin-top: 60px;
	width: 83%;
	height: 55%;
}
</style>
<script>
	function dateCheck(selectedObject) {
		var startDate = document.getElementById("sDate").value;
		var endDate = document.getElementById("eDate").value;

		if ((Date.parse(endDate) < Date.parse(startDate))) {
			alert("End date should be greater than Start date");
			document.getElementById("eDate").value = "";
		}
	}
	
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}

	function compoff(selectobject) {

		var ltype = document.getElementById("ltype").value;

		if (ltype == "comp-offs") {
			comoffWorkedDate.innerHTML = "<td>CompoffWorkedDate : </td>"
					+ "<td><input type='date' name='compoffWorkedDate' required></td>"
		} else {

			comoffWorkedDate.innerHTML = "<td>Reason for Leave :</td>"
					+ "<td><input type='textarea' id='rson' name='reason'  required></td>"
		}

	}
</script>
</head>
<body>
	<div class="container">
		<form:form action="/TrackingApp/editLeave.do" method="POST">
			<h2 style="color: #3A01DF">Edit LEAVE FORECAST</h2>
			<table>
				<tr>
					<td>First Name :</td>
					<td><form:input id="fName" path="firstName" type="text"
							name="fname" required="required" /></td>

					<td>Last Name :</td>
					<td><form:input id="lName" path="lastName" type="text"
							name="lname" required="required" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td>Emp Id :</td>
					<td><form:input id="eid" path="empId" type="text"
							required="required" onkeypress="return isNumber(event)" /> <form:hidden
							path="userid" /></td>
					<td>Cluster :</td>
					<td><form:select path="cluster" required="required">
							<form:option value="" label="Select" />
							<form:options items="${clusterList}" />
						</form:select></td>
					<td>Sub Cluster :</td>
					<td><form:select path="subCluster" required="required">
							<form:option value="" label="Select" />
							<form:options items="${subClusterList}" />
						</form:select></td>
				</tr>
				<tr></tr>
				<tr>
					<td>Start Date :</td>
					<td><form:input type="date" path="startDate" id="sDate"
							required="required" /></td>
					<td>End Date :</td>
					<td><form:input type="date" path="endDate" id="eDate"
							onchange="dateCheck(this)" required="required" /></td>
					<td>Leave Type :</td>
					<td><form:select path="leaveType" id="ltype"
							onchange="compoff(this)" required="required">
							<form:option value="" label="Select" />
							<form:options items="${leaveTypeList}" />
						</form:select></td>
				</tr>
				<tr></tr>
				<tr id="comoffWorkedDate">

					<c:if test="${reason1 == 'null'}">
						<td>CompoffWorkedDate :</td>
						<td><form:input type="date" path="compoffWorkedDate"
								id="cDate" required="required" /></td>
					</c:if>
					<c:if test="${!(reason1 == 'null')}">
						<td>Reason :</td>
						<td><form:input id="rson" path="reason" type="textarea"
								required="required" /></td>
					</c:if>
					<td>Leave Status :</td>
					<c:choose>
						<c:when
							test="${leaveTypes=='Casual leave'||leaveTypes=='comp-offs'||leaveTypes=='Flexi-leave'}">
							<td><form:select path="leaveStatus" required="required">
									<form:option value="" label="Select" />
									<form:options items="${leaveStatusList}" />
								</form:select></td>
						</c:when>
						<c:otherwise>
							<td><form:select path="leaveStatus" required="required"
									disabled="true">
									<form:option value="" label="Select" />
									<form:options items="${leaveStatusList}" />
								</form:select> <input type="hidden" name="leaveStatus" value="${leaveStatuss}" />
							</td>
						</c:otherwise>
					</c:choose>
					<td>Comments :</td>
					<c:choose>
					<c:when test="${isAdmin=='Y'}">
					<td><form:input id="comment" path="comments" type="textarea"
								required="required" /></td>
					</c:when>
					<c:otherwise>
					<td><form:input id="comment" path="comments" type="textarea"
								required="required" disabled="true"/></td>
					</c:otherwise>
					</c:choose>
						
						

				</tr>

				<tr>
					<td><input type="submit" id="editLeaves" value="Edit Save" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>