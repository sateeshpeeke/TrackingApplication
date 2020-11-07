<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>registration Process</title>
<style type="text/css">
#center {
	margin: 0 auto;
}

body {
	background-image: url(images/registration.jpg);
	background-size: cover;
	background-repeat: no-repeat;
}

h4 {
	color: darkgreen;
}

table {
	font-family: Calibri;
	color: white;
	font-size: 11pt;
	font-style: normal;
	font-weight: bold;
	background: rgba(0, 0, 0, 0.5);
	border-collapse: collapse;
	padding: 30px
}

table td {
	padding: 6px;
}

img.avatar {
	width: 2%;
}
</style>

<script type="text/javascript">
	function Validate() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		if (password != confirmPassword) {
			alert("Passwords do not match.");
			return false;
		}

		return true;
	}
	
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
	<a href="home.do"><img src="images/home1.jpg" alt="" class="avatar"></a>
	<div>
		<div>
			<form:form method="POST" action="/TrackingApp/registerUser.do">

				<h4 align="center" style="color: red">
					<c:if test="${not empty message}">
   							 ${message}
  						 </c:if>
				</h4>
				<table id="center">

					<tr>
						<td>First Name</td>
						<td><form:input type="text" path="firstName"
								placeholder="enter Firstname" name="fname" maxlength="32"
								required="required" /> (max 32 characters a-z and A-Z)</td>
					</tr>

					<tr>
						<td>Last Name</td>
						<td><form:input type="text" path="lastName"
								placeholder="enter Lastname" name="lname" maxlength="32"
								required="required" /> (max 32 characters a-z and A-Z)</td>

					</tr>

					<tr>
						<td>TcsEmployeeId</td>
						<td><form:input type="text" path="empId"
								placeholder="enter Empid" id="empId" maxlength="7"
								required="required" onkeypress="return isNumber(event)" /></td>
					</tr>

					<tr>
						<td>GpId</td>
						<td><form:input type="text" path="gpId"
								placeholder="enter Gpid" id="gpId" maxlength="8"
								required="required" onkeypress="return isNumber(event)" /></td>
					</tr>

					<tr>
						<td>MobileNumber</td>
						<td><form:input type="text" path="mobileNumber"
								placeholder="enter MobileNumber" name="mobileNumber"
								maxlength="10" required="required" /></td>
					</tr>

					<tr>
						<td>TcsMail</td>
						<td><form:input type="email" path="tcsMail"
								placeholder="enter Tcs Mail" name="tcsMail" required="required" /></td>
					</tr>

					<tr>
						<td>PepsicoMail</td>
						<td><form:input type="email" path="pepsicoMail"
								placeholder="enter Pepsico Mail" name="pepsicoMail"
								required="required" /></td>
					</tr>

					<tr>
						<td>Cluster</td>
						<td><select name="cluster" required>
								<option value="">Please select</option>
								<option>Web and Digital</option>
								<option>Integration</option>
								<option>FLNA</option>
								<option>Latam-legacy</option>
								<option>SAP Enterprise</option>
								<option>AMENA Non-Enterprise?</option>
						</select></td>
					</tr>

					<tr>
						<td>SubCluster</td>
						<td><select name="subCluster" required>
								<option value="">Please select</option>
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
						<td>Reporting To</td>
						<td><form:input type="text" path="reportingTo"
								placeholder="enter Reporting To" name="reportingTo"
								maxlength="32" required="required" /></td>
					</tr>

					<tr>
						<td>Designation</td>
						<td><form:input type="text" path="role"
								placeholder="enter Designatin" name="role" maxlength="32"
								required="required" /></td>
					</tr>

					<tr>
						<td>Primary Skill</td>
						<td><form:textarea id="sSkil" path="primarySkils"
								placeholder="enter Primary Skills" maxlength="200"
								required="required" /></td>
					</tr>

					<tr>
						<td>Secondary Skill</td>
						<td><form:textarea id="pSkil" path="secondarySkils"
								placeholder="enter Secondary Skills" maxlength="200"
								required="required" /></td>
					</tr>

					<tr>
						<td>Admin</td>
						<td>Yes<form:radiobutton path="admin" value="Y"
								required="required" /> <form:radiobutton path="admin" value="N"
								required="required" />No
						</td>

					</tr>

					<tr>
						<td>Password</td>
						<td><form:input type="password" path="password"
								placeholder="enter Password" id="password" maxlength="32"
								required="required" /></td>
					</tr>

					<tr>
						<td>ConfirmPassword</td>
						<td><form:input type="password" path="confirmPassword"
								placeholder="Re Enter Password" id="confirmPassword"
								maxlength="32" required="required" /></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Submit" onclick="return Validate()"> <input
							type="reset" value="Reset"></td>
					</tr>
				</table>

			</form:form>

		</div>




	</div>
</body>
</html>