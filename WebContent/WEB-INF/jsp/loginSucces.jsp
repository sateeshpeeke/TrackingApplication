<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.io.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style type="text/css">
.navbar {
	margin-top: 20px;
}

* {
	box-sizing: border-box
}

body, html {
	height: 100%;
	margin: 0;
	font-family: Arial;
}

#center {
	margin: 0 auto;
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

#Leave, #Roster, #ManageLeaves, #ViewRoster, #EmpInfo, #LogOut,
	#ChangePwd {
	background-color: white;
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

.container1 {
	background-color: #d5dfec;
	padding: 10px;
	margin-right: auto;
	margin-left: auto;
	width: 74%;
	height: 55%;
}

.eInfo {
	display: none;
}

.container:hover .eInfo {
	display: block;
}
</style>

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
	
	function empIdsEmptyCheckView() {
		var eid = document.getElementById("employeeId3").value;
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

	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}
	
	function Validate() {
		var newPass = document.getElementById("newPass").value;
		var confirmPass = document.getElementById("confirmPass").value;
		if (newPass != confirmPass) {
			alert("newPassword and confirmPassword do not match.");
			return false;
		}

		return true;
	}

	function compoff(selectObject) {
		if (selectObject.value == "comp-offs") {
			comoffWorkedDate.innerHTML = "<td>Compoff Worked Date : </td>"
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
	<c:choose>
		<c:when test="${isAdmin==null}">
			<%@ include file="index.jsp"%>
		</c:when>
		<c:otherwise>

			<div class="container">
				<nav class="navbar navbar-inverse">
					<div id="navbarCollapse" class="collapse navbar-collapse">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#addLeave" data-toggle="tab">Add
									Leave</a></li>
							<li><a href="#addRoster" data-toggle="tab">Add Roster</a></li>

							<li><a href="#addTask" data-toggle="tab">Add Task</a></li>

							<li><a href="#manageLeaves" data-toggle="tab">Manage
									Leaves</a></li>
							<li><a href="#viewRoster" data-toggle="tab">View Roster</a></li>
							<li><a href="#viewTask" data-toggle="tab">View Tasks</a></li>
							<li><a href="#empInfo" data-toggle="tab">Emp Info</a></li>
							<li><a href="#changePassword" data-toggle="tab">Change
									Password</a></li>
							<!-- <li class="dropdown"><a data-toggle="dropdown"
								class="dropdown-toggle" href="#">Messages <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="#">Inbox</a></li>
									<li><a href="#">Drafts</a></li>
									<li><a href="#">Sent Items</a></li>
									<li class="divider"></li>
									<li><a href="#">Trash</a></li>
								</ul></li> -->

						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="logOut.do"><span class="eInfo">${pMail}(<c:if
											test="${isAdmin=='Y'}">Admin</c:if> <c:if
											test="${isAdmin=='N'}">Employee</c:if>).
								</span>logout</a></li>
						</ul>
					</div>
				</nav>
			</div>
			<div class="tab-content container1">

				<div class="tab-pane active" id="addLeave">

					<form:form action="/TrackingApp/addLeave.do" method="POST">
						<h4 style="color: #3A01DF" align="center">ADD LEAVE FORECAST</h4>
						<table id="center">
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
										maxlength="7" required="required"
										onkeypress="return isNumber(event)" /></td>
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
										<option>Flexi-leave</option>
										<option>WFH</option>
										<option>Weekend-support</option>
								</select></td>
							</tr>
							<tr></tr>
							<tr id="comoffWorkedDate">

							</tr>
							<tr></tr>
							<tr>
								<td><input type="submit" id="addLeaves" value="Apply Leave" /></td>
							</tr>
						</table>
					</form:form>
				</div>
				<div class="tab-pane" id="addRoster">

					<form:form action="/TrackingApp/addAdhoc.do" method="POST">
						<h4 style="color: #3A01DF" align="center">AD HOC REQUEST</h4>
						<table id="center">
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
									required onkeypress="return isNumber(event)" /></td>
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

				<div class="tab-pane" id="addTask">

					<form:form action="/TrackingApp/addTask.do" method="POST">
						<h4 style="color: #3A01DF" align="center">ADD TASK DETAILS</h4>
						<table id="center">
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
										maxlength="7" required="required"
										onkeypress="return isNumber(event)" /></td>
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
								<td>Shift Date :</td>
								<td><input type="date" name="startDate" id="sDate"
									required="required" /></td>
								<td>Shift Start Time :</td>
								<td><input type="time" name="pickUp" required /></td>
								<td>Shift End Time :</td>
								<td><input type="time" name="dropTime" required /></td>
							</tr>
							<tr>
								<td>Task Type :</td>
								<td><select name="taskType" required>
										<option value="">Please Select</option>
										<option>Incident</option>
										<option>Service Request</option>
								</select></td>
								<td>Task Id :</td>
								<td><form:input id="tId" path="taskId" type="text"
										name="lname" required="required"
										onkeypress="return isNumber(event)" /></td>
								<td>Task Description :</td>
								<td><textarea id="tDsc" name="taskDescription" required></textarea></td>
							</tr>
							<tr></tr>
							<tr>
								<td><input type="submit" id="addTaskss" value="Add Task" /></td>
							</tr>
						</table>
					</form:form>
				</div>


				<div class="tab-pane" id="manageLeaves">

					<form action="/TrackingApp/viewLeaves.do" method="POST"
						onsubmit="return(empIdEmptyCheck())">
						<table id="center">
							<tr>
								<td>TcsEmpId<font color="red">*</font></td>
								<td><input type="text" name="empId" id="employeeId"
									maxlength="7" onkeypress="return isNumber(event)"></td>
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

				<div class="tab-pane" id="viewRoster">

					<form action="/TrackingApp/viewRoster.do" method="POST"
						onsubmit="return(empsIdEmptyCheck())">
						<table id="center">
							<tr>
								<td>TcsEmpId<font color="red">*</font></td>
								<td><input type="text" name="empId" id="employeeId1"
									maxlength="7" onkeypress="return isNumber(event)"></td>
							</tr>
							<tr>
								<td><input type="submit" value="View Roster By EmpId"
									id="viewRosters" /></td>
								<c:if test="${isAdmin=='Y'}">
									<td id="allEmpFont"><a href="viewAllRosters.do">OR
											View All Employees Rosters</a></td>
								</c:if>
							</tr>

						</table>
					</form>
				</div>

				<div class="tab-pane" id="viewTask">

					<form action="/TrackingApp/viewTask.do" method="POST"
						onsubmit="return(empIdsEmptyCheckView())">
						<table id="center">
							<tr>
								<td>TcsEmpId<font color="red">*</font></td>
								<td><input type="text" name="empId" id="employeeId3"
									maxlength="7" onkeypress="return isNumber(event)"></td>
							</tr>
							<tr>
								<td><input type="submit" value="View Tasks By EmpId"
									id="viewTasks" /></td>
								<c:if test="${isAdmin=='Y'}">
									<td id="allEmpFont"><a href="viewAllTasks.do">OR View
											All Employees Tasks</a></td>
								</c:if>
							</tr>

						</table>
					</form>
				</div>


				<div class="tab-pane" id="empInfo">

					<form action="/TrackingApp/viewRegEmpInfo.do" method="POST"
						onsubmit="return(empIdsEmptyCheck())">
						<table id="center">
							<tr>
								<td>TcsEmpId<font color="red">*</font></td>
								<td><input type="text" name="empId" id="employeeId2"
									maxlength="7" onkeypress="return isNumber(event)"></td>
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
				<div class="tab-pane" id="changePassword">

					<form action="/TrackingApp/changePassword.do" method="post">
						<table id="center">
							<tr>
								<td>Current Password</td>
								<td><input type="password" name=password required></td>
							</tr>
							<tr>
								<td>New Password</td>
								<td><input type="password" name="newPwd" id="newPass"
									required></td>
							</tr>
							<tr>
								<td>Confirm Password</td>
								<td><input type="password" name="confirmPassword"
									id="confirmPass" required></td>
							</tr>
							<tr>
								<td><input type="submit" value="Change Password"
									onclick="return Validate()"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

</body>
</html>