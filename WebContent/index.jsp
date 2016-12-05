<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Babbla</title>
	<meta name="google-signin-scope" content="profile email">
	<meta name="google-signin-client_id" content="660649166502-qljiqonknrnituk0pm2o33ajl4ne6qif.apps.googleusercontent.com">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://apis.google.com/js/platform.js"></script>
	<script src="resources/JS/login-script.js"></script>
	<link rel="stylesheet" href="resources/CSS/stylesheet.css">
	<script language="JavaScript" type="text/javascript" src="resources/JS/lib/cryptico/jsbn.js"></script>
    <script language="JavaScript" type="text/javascript" src="resources/JS/lib/cryptico/random.js"></script>
    <script language="JavaScript" type="text/javascript" src="resources/JS/lib/cryptico/hash.js"></script>
    <script language="JavaScript" type="text/javascript" src="resources/JS/lib/cryptico/rsa.js"></script>
    <script language="JavaScript" type="text/javascript" src="resources/JS/lib/cryptico/aes.js"></script>
    <script language="JavaScript" type="text/javascript" src="resources/JS/lib/cryptico/api.js"></script>
</head>


<body onload="startUp()">

	<nav class="navbar navbar-default">

	<div class="title">
		<a class="navbar-brand" href="index.jsp">Babbla</a>
	</div>

	<ul class="nav navbar-nav">
		<li>
		<div id="reg-div">
			<form id="user-form" action="loginServlet" method="post">
	
				<input type="hidden" name="username" id="username" />
				 <input	type="hidden" name="email" id="email" />
				 <input type="submit" value="Chat" id="registerBtn" />
	
			</form> 
		</div>
		</li>
		<li><a href="#" onclick="signOut();">Sign out</a></li>
	</ul>
	<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>

 	
	</nav>

	<div id="container">

		<p>${param.message}</p>

		<h3>The Babbla Community</h3>

	</div>

</body>
</html>