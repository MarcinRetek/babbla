<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Babbla</title>
<meta name="google-signin-scope" content="profile email"></meta>
<meta name="google-signin-client_id" content="660649166502-qljiqonknrnituk0pm2o33ajl4ne6qif.apps.googleusercontent.com"></meta>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link>
	<script src="https://apis.google.com/js/platform.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="resources/JS/login-script.js"></script>
</head>
<body>

			<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>


	<form id="user-form" action="loginServlet" method="post">
		<input type="text" name="username" id="username"/>
		<input type="text" name="email" id="email"/>
		<input type="submit" value="Register"/>
	</form>

</body>
</html>