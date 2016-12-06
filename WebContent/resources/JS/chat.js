/* <![CDATA[ */

var wsocket;
var serviceLocation = "wss://" + document.location.host + "/Babbla-project/chat/";
var $nickName;
var $message;
var $chatWindow;
var room = 'java';
var privateKey;
var publicKeyString;
var message;
var otherUsersPublicKey;

function onMessageReceived(evt) {
	
	checkConnection()
	
	var msg = JSON.parse(evt.data);

	if (msg.publicKey != publicKeyString) {
		otherUsersPublicKey = msg.publicKey;
	}
	
	if (msg.message == "Passing keys...") {
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ "Passing keys..." +' </p><hr>');
		$chatWindow.append($messageLine);
		
	}else if(msg.sender != $nickName.val()){
		var decryptedMsg = decryptMessage(msg.message);
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ decryptedMsg +' </p><hr>');
		$chatWindow.append($messageLine);
	
	}else if(msg.message == "The room is full, please change"){
		alert("The room is full. Please change");
		
	}else {
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ message +' </p><hr>');
		$chatWindow.append($messageLine);
	}

	$chatWindow.append($messageLine);
	scrollToChatContainerBottom();
}

function checkConnection() {
	if(wsocket.readyState === wsocket.CLOSED ){
		alert("Chat room is full");
	}
}

function sendKey() {
	var msg = '{"message":"' + "Passing keys..." + '", "sender":"'
	+ $nickName.val() + '", "received":"", "publicKey":"'+ publicKeyString +'"}';
	
	wsocket.send(msg);
	$message.val('').focus();
}

function sendMessage() {
	message = $message.val();
	
	var encryptedMessage = encryptMessage(message);
	var msg = '{"message":"' + encryptedMessage + '", "sender":"'
			+ $nickName.val() + '", "received":"", "publicKey":"'+ publicKeyString +'"}';

	wsocket.send(msg);
	$message.val('').focus();
}

function connectToChatserver() {
	room = $('#chatroom option:selected').val();
	wsocket = new WebSocket(serviceLocation + room); 
	wsocket.onmessage = onMessageReceived;
}

function scrollToChatContainerBottom() {
	$('#response').stop().animate({
		scrollTop: $('#response')[0].scrollHeight
		}, 800);
}

function leaveRoom() {
	wsocket.close();
	$chatWindow.empty();
	$('.chat-wrapper').hide();
	$('.chat-signin').show();
	$nickName.focus();
}

$(document).ready(function() {
	$('.chat-wrapper').hide();
	$('#sendMessage').prop("disabled",true);
	$nickName = $('#nickname');
	$message = $('#message');
	$chatWindow = $('#response');
	$nickName.focus();
	
	generateKeys();
	$('#enterRoom').click(function(evt) {
		evt.preventDefault();
		connectToChatserver();
		$('.chat-wrapper h2').text(''+$nickName.val() + " @ " + room);
		$('.chat-signin').hide();
		$('.chat-wrapper').show();
		$message.focus();
	});
	
	$('#sendKey').click(function(){
		$('#sendMessage').prop("disabled",false);
	});
	$('#do-chat').submit(function(evt) {
			evt.preventDefault();
			sendMessage();
			
		});
	
	$('#leave-room').click(function(){
		leaveRoom();
	});
});
	
function generateKeys() {
	var passPhrase = $nickName.val();
	privateKey = cryptico.generateRSAKey(passPhrase, 1024);
	publicKeyString = cryptico.publicKeyString(privateKey);
}

function encryptMessage(message) {
	var encryptionResult = cryptico.encrypt(message, otherUsersPublicKey);
	return encryptionResult.cipher;
}

function decryptMessage(message) {
	var decryptResult = cryptico.decrypt(message, privateKey);
	return decryptResult.plaintext;
}
/* ]]> */
