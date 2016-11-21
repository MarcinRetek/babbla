
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
    
//    var userElement = document.createElement('input');
//    userElement.setAttribute("id", "username");
//    var username = document.createTextNode(user.username);
//    userElement.appendChild(username);
//    document.getElementById("user-form").appendChild(userElement);
//
//    var emailElement = document.createElement('input');
//    emailElement.setAttribute("id","email");
//    var email = document.createTextNode(user.email);
//    emailElement.appendChild(email);
//    document.getElementById("user-form").appendChild(emailElement);
   
}


function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}






