var isUserLoggedIn = false
var passPhrase;
var RSAKey;
var publicKeyString;
var encryptionResult;

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
    
   // generateKeys(user.username);
    
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
/*
function generateKeys(username) {
	  var passPhrase = username;
	  RSAKey = cryptico.generateRSAKey(passPhrase, 512);
	  publicKeyString = cryptico.publicKeyString(RSAKey);
	  var publicKey = document.getElementById("public-key");
	  publicKey.value = publicKey.value = publicKeyString
	}

*/



