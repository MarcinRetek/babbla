var isUserLoggedIn = false


function startUp() {
	var hideContent = document.getElementById('container');
	hideContent.style.display = 'none';	
    
//	if(isUserLoggedIn = true ){
//    	var btn = document.getElementById("registerBtn");
//    	btn.disabled = true;
//    }else{
//    	btn.disabled = false;
//    }
}

function onSignIn(googleUser) {
	isUserLoggedIn = true;  
	var showContent = document.getElementById('container');
	showContent.style.display = 'block';	

    var profile = googleUser.getBasicProfile();

    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Email: ' + profile.getEmail());

    var user = {
        username: profile.getName(),
        email: profile.getEmail()
    }
    
    var userInput = document.getElementById("username");
    userInput.value = userInput.value = user.username;
    
    var emailInput = document.getElementById("email");
    emailInput.value = emailInput.value = user.email;
    
    
  

}


function signOut() {	
	var hideContent = document.getElementById('container');
	hideContent.style.display = 'none';	
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');

    });
}






