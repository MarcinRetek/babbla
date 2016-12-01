/* <![CDATA[ */
	var wsocket;
	var serviceLocation = "ws://" + document.location.host + "/Babbla-project/chat/";
	var $nickName;
	var $message;
	var $chatWindow;
	var room = 'java';
	var RSAKey;
	var RSAKey2;
	var publicKeyString2;
	var publicKeyString;
	var encryptionResult;
	var encryptedMessage;
	var decryptedMessage;
	var passPhrase;  
	var newPath;
	function onMessageReceived(evt) {
		var msg = JSON.parse(evt.data);
		var decrypedMsg = decryptMessage(msg.message);
		console.log("DECMSG: " + decrypedMsg);
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ decrypedMsg +' </p><hr>');

		$chatWindow.append($messageLine);
		scrollToChatContainerBottom();
	}
	
	function onOpen(data) {
		console.log("ON OPEN");
		var urlArray = data.currentTarget.url.split("/");
		newPath = urlArray[5];
		console.log(newPath);
	}
	
	function sendMessage() {
		var message = $message.val();
		encryptedMessage = encryptMessage(message);
		
		var msg = '{"message":"' + encryptedMessage + '", "sender":"'
				+ $nickName.val() + '", "received":"", "publicKey":"'+ RSAKey2 +'"}';

		wsocket.send(msg);
		$message.val('').focus();
	}
 
	function connectToChatserver() {
		//room = $('#chatroom option:selected').val();
		wsocket = new WebSocket(serviceLocation + publicKeyString2); //used to be room
		wsocket.onopen = onOpen;
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
		RSAKey2 = cryptico.generateRSAKey(passPhrase, 128);
		publicKeyString2 = cryptico.publicKeyString(RSAKey2);
		console.log("KEY 1: " + publicKeyString2);
	}
	
	function encryptMessage(message) {
		console.log("NEWPATH: " + newPath);
		encryptionResult = cryptico.encrypt(message, newPath);
		console.log("ENCRYPT: " + encryptionResult.cipher);
		return encryptionResult.cipher;
	}
	
	function decryptMessage(message) {
		var decryptResult = cryptico.decrypt(message, RSAKey2);
		console.log("STATUS: " + decryptResult.status);
		return decryptResult.plaintext;
	}
/* ]]> */
