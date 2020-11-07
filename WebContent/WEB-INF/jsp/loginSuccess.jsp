<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
	box-sizing: border-box
}

body, html {
	height: 100%;
	margin: 0;
	font-family: Arial;
}

.tablink {
	baqckground-color: #777777;
	color: black;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	font-size: 17px;
	width: 16.66%;
}

.tablink:hover {
	background-color: #777;
}

.tabcontent {
	color: white;
	display: none;
	padding: 100px 20px;
	height: 100%;
}

#allEmpFont {
	font-size: 22px;
}

#allEmpFont a {
	color: #4CAF50;
	font-weight: bold;
}

#Leave, #Roster, #ManageLeaves, #ViewRoster, #EmpInfo {
	background-color: #DAE9BC;
}

/* #EmpInfo {
	background-color: #777;
} */
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
	background-color: #FAFAD2;
	padding: 20px;
}
</style>

<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<link rel="stylesheet" href="css/jquery.dataTables.min.css">
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>


<script>
	function empIdEmptyCheck() {
		var eid = document.getElementById("employeeId").value;
		if (!eid || 0 === eid.length) {
			alert("please enter employee Id... ");
			return false;
		}

	}

	function empsIdEmptyCheck() {
		var eid = document.getElementById("employeeId1").value;
		if (!eid || 0 === eid.length) {
			alert("please enter employee Id. ");
			return false;
		}
	}
	function empIdsEmptyCheck() {
		var eid = document.getElementById("employeeId2").value;
		if (!eid || 0 === eid.length) {
			alert("please enter employee Id. ");
			return false;
		}
	}

	function dateCheck(selectedObject) {
		var startDate = document.getElementById("sDate").value;
		var endDate = document.getElementById("eDate").value;

		if ((Date.parse(endDate) < Date.parse(startDate))) {
			alert("End date should be greater than Start date");
			document.getElementById("eDate").value = "";
		}
	}

	function compoff(selectObject) {
		if (selectObject.value == "comp-offs") {
			comoffWorkedDate.innerHTML = "<td>CompoffWorkedDate : </td>"
					+ "<td><input type='date' name='compoffWorkedDate' required></td>"
		} else {
			comoffWorkedDate.innerHTML = "<td>Reason for Leave :</td>"
					+ "<td><input type='textarea' id='rson' name='reason' required/></td>"
		}
	}

	function openPage(pageName, elmnt, color) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}

		tablinks = document.getElementsByClassName("tablink");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].style.backgroundColor = "";
		}

		document.getElementById(pageName).style.display = "block";
		elmnt.style.backgroundColor = color;

	}
	document.getElementById("defaultOpen").click();
</script>
</head>
<body>

	<button class="tablink" onclick="openPage('Leave',this,'	#90EE90')">Add
		Leave</button>
	<button class="tablink" onclick="openPage('Roster',this,'green')"
		id="defaultOpen">Add Roster</button>
	<button class="tablink"
		onclick="openPage('ManageLeaves',this,'lightblue')">Manage
		Leaves</button>
	<button class="tablink" onclick="openPage('ViewRoster',this,'orange')">View
		Roster</button>
	<button class="tablink" onclick="openPage('EmpInfo',this,'red')">Emp
		Info</button>
	<a href="logOut.do" class="tablink"
		style="text-decoration: none; background-color: #555;"
		onclick="openPage('LogOut',this,'lightgreen')">Log Out</a>

	<div id="Leave" class="tabcontent">
		<div class="container">
			<form:form action="/TrackingApp/addLeave.do" method="POST">
				<h2 style="color: #3A01DF">ADD LEAVE FORECAST</h2>
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
								maxlength="7" required="required" /></td>
						<td>Cluster :</td>
						<td><select name="cluster" required>
								<option value="">Please Select</option>
								<option>Web and Digital</option>
								<option>Integration</option>
								<option>FLNA</option>
								<option>Latam-legacy</option>
								<option>SAP Enterprise</option>
								<option>AMENA Non-Enterprise?</option>
						</select></td>

						<td>Sub Cluster :</td>
						<td><select name="subCluster" required>
								<option value="">Please Select</option>
								<option>Portal and Collaboration</option>
								<option>Imaging</option>
								<option>Security</option>
								<option>MQ Services</option>
								<option>Informatica</option>
								<option>EDW</option>
								<option>Layer7 API</option>
								<option>ECG</option>
								<option>TIBCO</option>
								<option>RPA and UI Tools</option>
								<option>WebLogi and Apache</option>
								<option>GEC</option>
						</select></td>

					</tr>
					<tr>

					</tr>
					<tr>
						<td>Start Date :</td>
						<td><input type="date" name="startDate" id="sDate"
							required="required" /></td>
						<td>End Date :</td>
						<td><input type="date" name="endDate" id="eDate"
							onchange="dateCheck(this)" required="required" /></td>
						<td>Leave Type :</td>
						<td><select name="leaveType" onchange="compoff(this)"
							required>
								<option value="">Please Select</option>
								<option>Casual leave</option>
								<option>Sick leave</option>
								<option>comp-offs</option>
						</select></td>
					</tr>
					<tr></tr>
					<tr id="comoffWorkedDate">

					</tr>
					<tr></tr>
					<tr>
						<td><input type="submit" id="addLeaves" value="Register" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>

	<div id="Roster" class="tabcontent">
		<div class="container">
			<form:form action="/TrackingApp/addAdhoc.do" method="POST">
				<h2 style="color: #3A01DF">AD HOC REQUEST</h2>
				<table>
					<tr>
						<td>First Name :</td>
						<td><input id="fName" name="firstName" type="text" required /></td>

						<td>Last Name :</td>
						<td><input id="lName" name="lastName" type="text" required />
						</td>
					</tr>
					<tr></tr>
					<tr>
						<td>Emp Id :</td>
						<td><input id="eid" name="empId" type="text" maxlength="7"
							required /></td>
					</tr>
					<tr></tr>
					<tr>
						<td>Roster Date :</td>
						<td><input type="date" name="rosterDate" required></td>
						<td>PickUp :</td>
						<td><input type="time" name="pickUp" required /></td>
						<td>Drop :</td>
						<td><input type="time" name="dropTime" required /></td>
					</tr>
					<tr></tr>
					<tr>
						<td>Location :</td>
						<td><textarea id="loc" name="location" required></textarea></td>
					</tr>
					<tr></tr>
					<tr>
						<td><input type="submit" value="register" id="register" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>

	<div id="ManageLeaves" class="tabcontent">
		<div class="container">
			<form action="/TrackingApp/viewLeaves.do" method="POST"
				onsubmit="return(empIdEmptyCheck())">
				<table align="center">
					<tr>
						<td>TcsEmpId<font color="red">*</font></td>
						<td><input type="text" name="empId" id="employeeId"
							maxlength="7"></td>
					</tr>
					<tr>
						<td><input type="submit" value="View Leaves By EmpId"
							id="viewLeaves" /></td>
						<c:if test="${isAdmin=='Y'}">
							<td id="allEmpFont"><a href="viewAllLeaves.do">OR View
									All Employees Leaves</a></td>
						</c:if>

					</tr>

				</table>
			</form>
		</div>

	</div>

	<div id="ViewRoster" class="tabcontent">
		<div class="container">
			<form action="/TrackingApp/viewRoster.do" method="POST"
				onsubmit="return(empsIdEmptyCheck())">
				<table align="center">
					<tr>
						<td>TcsEmpId<font color="red">*</font></td>
						<td><input type="text" name="empId" id="employeeId1"
							maxlength="7"></td>
					</tr>
					<tr>
						<td><input type="submit" value="View Roster By EmpId"
							id="viewRosters" /></td>
						<c:if test="${isAdmin=='Y'}">
							<td id="allEmpFont"><a href="viewAllRosters.do">OR View
									All Employees Rosters</a></td>
						</c:if>
					</tr>

				</table>
			</form>
		</div>
	</div>

	<div id="EmpInfo" class="tabcontent">
		<div class="container">
			<form action="/TrackingApp/viewRegEmpInfo.do" method="POST"
				onsubmit="return(empIdsEmptyCheck())">
				<table align="center">
					<tr>
						<td>TcsEmpId<font color="red">*</font></td>
						<td><input type="text" name="empId" id="employeeId2"
							maxlength="7"></td>
					</tr>
					<tr>
						<td><input type="submit" value="View emp Info By EmpId"
							id="viewEmpInfo" /></td>
						<c:if test="${isAdmin=='Y'}">
							<td id="allEmpFont"><a href="viewAllRegEmployees.do">OR
									View All Reg Employees Details</a></td>
						</c:if>

					</tr>

				</table>
			</form>
		</div>
	</div>


	<div id="LogOut" class="tabcontent">
		<div class="container1"></div>
	</div>

</body>
</html>