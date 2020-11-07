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

#content {
	background-image:url("${pageContext.request.contextPath}/images/register1.jpg");
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

</head>

<body>
	<div>
		<div>
			<form:form method="POST" action="/TrackingApp/editEmpInfo.do">

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
								required="required" readonly="true" />(Tcs employee Id
							readOnly)</td>
					</tr>

					<tr>
						<td>GpId</td>
						<td><form:input type="text" path="gpId"
								placeholder="enter Gpid" id="gpId" maxlength="8"
								required="required" /></td>
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
						<td><form:select path="cluster" required="required">
								<form:option value="" label="Select" />
								<form:options items="${clusterList}" />
							</form:select></td>
					</tr>

					<tr>
						<td>SubCluster</td>
						<td><form:select path="subCluster" required="required">
								<form:option value="" label="Select" />
								<form:options items="${subClusterList}" />
							</form:select></td>
					</tr>
					<tr>
						<td>Reporting To</td>
						<td><form:input type="text" path="reportingTo"
								placeholder="enter Reporting To" name="reportingTo"
								required="required" /></td>
					</tr>

					<tr>
						<td>Designation</td>
						<td><form:input type="text" path="role"
								placeholder="enter Designatin" name="role" required="required" /></td>
					</tr>

					<tr>
						<td>Primary Skill</td>
						<td><form:textarea id="sSkil" path="primarySkils"
								placeholder="enter Primary Skills" required="required" /></td>
					</tr>

					<tr>
						<td>Secondary Skill</td>
						<td><form:textarea id="pSkil" path="secondarySkils"
								placeholder="enter Secondary Skills" required="required" /></td>
					</tr>

					<tr>
						<td>Admin</td>
						<td>Yes<form:radiobutton path="admin" value="Y"
								required="required" /> <form:radiobutton path="admin" value="N"
								required="required" />No
						</td>

					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Edit Save">
					</tr>
				</table>

			</form:form>

		</div>


	</div>
</body>
</html>