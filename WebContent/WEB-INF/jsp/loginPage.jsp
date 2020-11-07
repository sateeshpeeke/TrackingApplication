<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Login Form</title>
<style type="text/css">
body {
	background-image: url(images/login.jpg);
	background-size: cover;
	background-repeat: no-repeat;
}

.LoginDiv {
	width: 35%;
	margin-left: auto;
	margin-right: auto;
	margin-top: 16%;
	background: #0c0c0cc4;
	padding: 33px;
}

.btn-primary {
	color: #fff;
	background-color: #428bca;
	border-color: #357ebd;
	width: 100%;
}

img.avatar {
	width: 2%;
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
	<a href="home.do"><img src="images/home1.jpg" alt="" class="avatar"></a>
	<form class="form-horizontal LoginDiv"
		action="/TrackingApp/loginAction.do" method="post">

		<h4 align="center" style="color: red">
			<c:if test="${not empty message}">
    				${message}
  				 </c:if>
		</h4>

		<div class="form-group ">
			<div class="col-md-12">
				<input id="textinput" name="empId" type="text"
					placeholder="Tcs EmpId" class="form-control input-md"
					required="required" onkeypress="return isNumber(event)">
			</div>
		</div>

		<!-- Text input-->
		<div class="form-group" style="text-align: center">
			<div class="col-md-12">
				<input id="textinput" name="password" type="text"
					placeholder="enter Password" class="form-control input-md"
					required="required">
			</div>
		</div>

		<!-- Button -->
		<div class="form-group" style="text-align: center">
			<label class="col-md-12 control-label" for="singlebutton"></label>
			<div class="col-md-12">
				<button id="singlebutton" name="singlebutton"
					class="btn btn-primary">Log in</button>
			</div>
			 <a href="forgotPwd.do" style="color:orange">Forgot Password?</a>
		</div>

	</form>

</body>
</html>