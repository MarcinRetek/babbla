var isUserLoggedIn = false

function startUp() {
	console.log('tja');
    if(isUserLoggedIn = true ){
    	var btn = document.getElementById("registerBtn");
    	btn.disabled = true;
    }else{
    	btn.disabled = false;
    }
}

function onSignIn(googleUser) {

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
    
    isUserLoggedIn = true;
    
    if(isUserLoggedIn = true ){
    	var btn = document.getElementById("registerBtn");
    	btn.disabled = false;
    }else{
    	btn.disabled = true;
    }

}


function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');

        if(isUserLoggedIn){
        	var btn = document.getElementById("registerBtn");
        	btn.disabled = true;
        }else{
        	btn.disabled = false;
        }
        isUserLoggedIn = false;
    });
}






