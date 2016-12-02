var isUserLoggedIn = false

function startUp() {
	var hideContent = document.getElementById('reg-div');
	hideContent.style.display = 'none';	
}

function onSignIn(googleUser) {
	isUserLoggedIn = true;  
	var showContent = document.getElementById('reg-div');
	showContent.style.display = 'block';	

    var profile = googleUser.getBasicProfile();

    var user = {
        username: profile.getName(),
        email: profile.getEmail()
    }
    
    var userInput = document.getElementById("username");
    userInput.value = userInput.value = user.username;
    
    var emailInput = document.getElementById("email");
    emailInput.value = emailInput.value = user.email;
    
    generateKeys(user.username);
    
}


function signOut() {	
	var hideContent = document.getElementById('reg-div');
	hideContent.style.display = 'none';	
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');

    });
}




