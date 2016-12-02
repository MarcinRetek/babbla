/* <![CDATA[ */

var wsocket;
var serviceLocation = "ws://" + document.location.host + "/Babbla-project/chat/";
var $nickName;
var $message;
var $chatWindow;
var room = 'java';
var RSAKey2;
var publicKeyString2;
var encryptionResult;
var encryptedMessage;
var decryptedMessage;
var passPhrase;  
var message;
var otherUsersPublicKey;

function onMessageReceived(evt) {
	var msg = JSON.parse(evt.data);
	
	if (msg.publicKey != publicKeyString2) {
		otherUsersPublicKey = msg.publicKey;
	}
	if (msg.sender != $nickName.val()) {
		var decrypedMsg = decryptMessage(msg.message);
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ decrypedMsg +' </p><hr>');
	}else {
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ message +' </p><hr>');
	}

	$chatWindow.append($messageLine);
	scrollToChatContainerBottom();
}

function sendMessage() {
	message = $message.val();
	encryptedMessage = encryptMessage(message);
	var msg = '{"message":"' + encryptedMessage + '", "sender":"'
			+ $nickName.val() + '", "received":"", "publicKey":"'+ publicKeyString2 +'"}';

	wsocket.send(msg);
	$message.val('').focus();
}
 
	function connectToChatserver() {
		//room = $('#chatroom option:selected').val();
	wsocket = new WebSocket(serviceLocation + "Hej"); //used to be room
	wsocket.onmessage = onMessageReceived;
	
}

function scrollToChatContainerBottom() {
	$('#response').stop().animate({
		scrollTop: $('#response')[0].scrollHeight
		}, 800);
}
 
$(document).ready(function() {
	$nickName = $('#nickname');
	$message = $('#message');
	$chatWindow = $('#response');
	generateKeys();
	connectToChatserver();
	$nickName.focus();
	$('#do-chat').submit(function(evt) {
			evt.preventDefault();
			sendMessage();
			
		});
});
	
function generateKeys() {
	passPhrase = $nickName.val();
	RSAKey2 = cryptico.generateRSAKey(passPhrase, 512);
	publicKeyString2 = cryptico.publicKeyString(RSAKey2);
}

function encryptMessage(message) {
	encryptionResult = cryptico.encrypt(message, otherUsersPublicKey);
	return encryptionResult.cipher;
}

function decryptMessage(message) {
	var decryptResult = cryptico.decrypt(message, RSAKey2);
	return decryptResult.plaintext;
}
/* ]]> */
