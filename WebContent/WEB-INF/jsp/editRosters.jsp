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
	width: 83%;
}
</style>
<script type="text/javascript">
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
</script>

</head>
<body>
	<div class="container">
		<form:form action="/TrackingApp/editRoster.do" method="POST">
			<h2 style="color: #3A01DF">EDIT ROSTER DETAILS</h2>
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
							required="required" onkeypress="return isNumber(event)" /></td>
					<td><form:hidden path="userid" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td>Roster Date :</td>
					<td><form:input type="date" path="rosterDate"
							required="required" /></td>
					<td>PickUp :</td>
					<td><form:input type="time" path="pickUp" required="required" /></td>
					<td>Drop :</td>
					<td><form:input type="time" path="dropTime"
							required="required" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td>Location :</td>
					<td><form:textarea id="loc" path="location"
							required="required" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td><input type="submit" id="editRosters" value="Edit Save" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>