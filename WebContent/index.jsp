<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Time Track app</title>
<style type="text/css">
html, #page {
	padding: 0;
	margin: 0;
}

body {
	margin: 0;
	padding: 0;
	width: 100%;
	color: #959595;
	font: normal 12px/2.0em Sans-Serif;
}

h1, h2, h3, h4, h5, h6 {
	color: darkgreen;
}

#page {
	background: #fff;
	margin-top: 20px;
}

#header, #footer, #top-nav, #content, #content #contentbar, #content #sidebar
	{
	margin: 0;
	padding: 0;
}

/* Logo */
#logo {
	padding: 0;
	width: auto;
	float: left;
}

#logo h1 a, h1 a:hover {
	color: darkgreen;
	text-decoration: none;
}

#logo h1 span {
	color: #BCCE98;
}

/* Header */
#header {
	background: #fff;
}

#header-inner {
	margin: 0 auto;
	padding: 0;
	width: 970px;
}

/* Feature */
.feature {
	background: green;
	padding: 18px;
}

.feature-inner {
	margin: auto;
	width: 970px;
}

.feature-inner h1 {
	color: #DAE9BC;
	font-size: 32px;
}

/* Menu */
#top-nav {
	margin: 0 auto;
	padding: 0px 0 0;
	height: 37px;
	float: right;
}

#top-nav ul {
	list-style: none;
	padding: 0;
	height: 37px;
	float: left;
}

#top-nav ul li {
	margin: 0;
	padding: 0 0 0 8px;
	float: left;
}

#top-nav ul li a {
	display: block;
	margin: 0;
	padding: 8px 20px;
	color: green;
	text-decoration: none;
}

#top-nav ul li.active a, #top-nav ul li a:hover {
	color: #BCCE98;
}

/* Content */
#content-inner {
	margin: 0 auto;
	padding: 10px 0;
	width: 970px;
	background: #fff;
}

#content #contentbar {
	margin: 0;
	padding: 0;
	float: right;
	width: 760px;
}

#content #contentbar .article {
	margin: 0 0 24px;
	padding: 0 20px 0 15px;
}

#content #sidebar {
	padding: 0;
	float: left;
	width: 200px;
}

#content #sidebar .widget {
	margin: 0 0 12px;
	padding: 8px 8px 8px 13px;
	line-height: 1.4em;
}

#content #sidebar .widget ul {
	margin: 0;
	padding: 0;
	list-style: none;
	color: #959595;
}

#content #sidebar .widget ul li {
	margin: 0;
}

#content #sidebar .widget ul li {
	padding: 5px 0;
	width: 185px;
}

#content #sidebar .widget ul li a {
	color: green;
	text-decoration: none;
	margin-left: -16px;
	padding: 4px 8px 4px 16px;
	font-size: 14px;
}

#content #sidebar .widget ul li a:hover {
	color: #BCCE98;
	font-weight: bold;
	text-decoration: none;
}

/* Footerblurb */
#footerblurb {
	background: #DAE9BC;
	color: green;
}

#footerblurb-inner {
	margin: 0 auto;
	width: 922px;
	padding: 24px;
}

#footerblurb .column {
	margin: 0;
	text-align: justify;
	float: left;
	width: 250px;
	padding: 0 24px;
}

/* Footer */
#footer {
	background: #fff;
}

#footer-inner {
	margin: auto;
	text-align: center;
	padding: 12px;
	width: 922px;
}

#footer a {
	color: green;
	text-decoration: none;
}

/* Clear both sides to assist with div alignment  */
.clr {
	clear: both;
	padding: 0;
	margin: 0;
	width: 100%;
	font-size: 0px;
	line-height: 0px;
}

.imgcontainer {
	text-align: center;
	margin: 20px 0 10px 0;
}

img.avatar {
	width: 5%;
	border-radius: 60%;
}
</style>
<script type="text/javascript">
	function setContent(div, type) {
		if (type == "M") {
			document.getElementById("managers").innerHTML = "<marquee><b>Pepsico Manager Names</b><br>Amit<br>Shilpa<br>Sunitha<br>Biju<br>Sukshma<br>Majed<br>Jody<br>Prasad</marquee>";

		} else if (type == "L") {
			document.getElementById("managers").innerHTML = "<marquee><b>Pepsico Lead Names</b><br>Sharma<br>Hanumantha Rao<br>xxxx<br>yyyy<br>zzzz</marquee>";
		}

	}
</script>
</head>
<body>
	<div id="page">
		<div class="feature">
			<div class="feature-inner">
				<h1><img src="images/Pepsico.png" alt="" class="avatar">Pepsico Employee Portal</h1>
			</div>
		</div>


		<div id="content">
			<div id="content-inner">

				<main id="contentbar">
				<div class="article">
					<p id="managers"></p>
				</div>
				</main>

				<nav id="sidebar">
					<div class="widget">
						<ul>
							<li>New User? <a href="signUp.do"><b>Sign Up</b></a> Here..
							</li>
							<li>Existing User ? <a href="logIn.do"><b>Log In</b></a>
								Here..
							</li>
							<li><a onclick="setContent('managers','M')">Pepsico
									Managers</a></li>
							<li><a onclick="setContent('managers','L')">Pepsico
									Leads</a></li>
						</ul>
					</div>
				</nav>

				<div class="clr"></div>
			</div>
		</div>

		<div id="footerblurb">
			<div id="footerblurb-inner">

				<div class="column">
					<h2>
						<span>About</span>
					</h2>
					<p id="heading1">this is about pepsico.. need to add more..</p>
				</div>
				<div class="column">
					<h2>
						<span>Contact</span>
					</h2>
					<p>This is pepsico</p>
				</div>
				<div class="column">
					<h2>
						<span>Help</span>
					</h2>
					<p>this is pepsico Site</p>
				</div>

				<div class="clr"></div>
			</div>
		</div>
		<footer id="footer">
			<div id="footer-inner">
				<p>
					&copy; Copyright <a href="#">Portal & Collab</a> &#124; <a href="#">Terms
						of Use</a> &#124; <a href="#">Privacy Policy</a>
				</p>
				<div class="clr"></div>
			</div>
		</footer>
	</div>
</body>
</html>