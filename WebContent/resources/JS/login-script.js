$(document).ready(function() {
	$(document).bind("contextmenu",function(e) {
	    e.preventDefault();
	});
	
	$(document).keydown(function(e){
	    if(e.which === 123){
	       return false;
	    }
	});
});

var isUserLoggedIn = false


function startUp() {
	if (location.protocol != 'https:'){
	 location.href = "https://localhost:8181/Babbla-project/faces/index.jsp";
	}
		var hideContent = document.getElementById('reg-div');
		hideContent.style.display = 'none';
		var hideSignOut = document.getElementById('signout');
		hideSignOut.style.display = 'none';
}


function onSignIn(googleUser) {
	isUserLoggedIn = true;
	
	var showContent = document.getElementById('reg-div');
	showContent.style.display = 'block';
	
	var showSignOut = document.getElementById('signout');
	showSignOut.style.display = 'block';

    var profile = googleUser.getBasicProfile();

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
	var hideContent = document.getElementById('reg-div');
	hideContent.style.display = 'none';	
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}



