/* <![CDATA[ */

var wsocket;
var serviceLocation = "ws://" + document.location.host + "/Babbla-project/chat/";
var $nickName;
var $message;
var $chatWindow;
var room = 'java';
var RSAKey2;
var publicKeyString2;
var message;
var otherUsersPublicKey;

function onMessageReceived(evt) {
	var msg = JSON.parse(evt.data);
	console.log(msg);
	if (msg.publicKey != publicKeyString2) {
		otherUsersPublicKey = msg.publicKey;
	}
	if (msg.message == "Passing keys...") {
		console.log("msg e undefined" );
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ "Passing keys..." +' </p><hr>');
		$chatWindow.append($messageLine);
	}else if(msg.sender != $nickName.val()){
		var decryptedMsg = decryptMessage(msg.message);
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ decryptedMsg +' </p><hr>');
		$chatWindow.append($messageLine);
	}else {
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ message +' </p><hr>');
		$chatWindow.append($messageLine);
	}
	
		
//		var messageWithOnlyKeys =  $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
//				+ '<p> '+ message +' </p><hr>');
	$chatWindow.append($messageLine);
	scrollToChatContainerBottom();
}

function sendKey() {
	var msg = '{"message":"' + "Passing keys..." + '", "sender":"'
	+ $nickName.val() + '", "received":"", "publicKey":"'+ publicKeyString2 +'"}';

	wsocket.send(msg);
	$message.val('').focus();
}

function sendMessage() {
	message = $message.val();
	
	var encryptedMessage = encryptMessage(message);
	var msg = '{"message":"' + encryptedMessage + '", "sender":"'
			+ $nickName.val() + '", "received":"", "publicKey":"'+ publicKeyString2 +'"}';

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
	$('#sendMessage').prop("disabled",true);
	$nickName = $('#nickname');
	$message = $('#message');
	$chatWindow = $('#response');
	generateKeys();
	//connectToChatserver();
	$nickName.focus();
	$('.chat-wrapper').hide();
	
	$('#enterRoom').click(function(evt) {
		evt.preventDefault();
		connectToChatserver();
		$('.chat-wrapper h2').text('Chat # '+$nickName.val() + "@" + room);
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
	RSAKey2 = cryptico.generateRSAKey(passPhrase, 512);
	publicKeyString2 = cryptico.publicKeyString(RSAKey2);
}

function encryptMessage(message) {
	var encryptionResult = cryptico.encrypt(message, otherUsersPublicKey);
	return encryptionResult.cipher;
}

function decryptMessage(message) {
	var decryptResult = cryptico.decrypt(message, RSAKey2);
	return decryptResult.plaintext;
}
/* ]]> */
