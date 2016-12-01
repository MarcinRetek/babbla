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
	
	function onMessageReceived(evt) {
		var msg = JSON.parse(evt.data);

		var decrypedMsg = decryptMessage(msg.message, msg.publicKey, msg.sender);
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ decrypedMsg +' </p><hr>');

		$chatWindow.append($messageLine);
		scrollToChatContainerBottom();
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
		wsocket = new WebSocket(serviceLocation + room);
		wsocket.onmessage = onMessageReceived;
	}

	
	function scrollToChatContainerBottom() {
		$('#response').stop().animate({
			scrollTop: $('#response')[0].scrollHeight
		}, 800);
	}
 
	$(document).ready(function() {
		connectToChatserver();
		$nickName = $('#nickname');
		$message = $('#message');
		$chatWindow = $('#response');
		$nickName.focus();
		//generateKeys();
		$('#do-chat').submit(function(evt) {
			evt.preventDefault();
			sendMessage();
			
		});
 
	});
	
	function generateKeys() {
		passPhrase = $nickName.val();
		RSAKey2 = cryptico.generateRSAKey(passPhrase, 512);
		publicKeyString2 = cryptico.publicKeyString(RSAKey2);
		console.log("KEY 1: " + publicKeyString2);
	}
	
	function generatepub() {
		pass = "hej";
		var RSAKey3 = cryptico.generateRSAKey(pass, 512);
		publicKeyString3 = cryptico.publicKeyString(RSAKey3);
		console.log("KEY 2: " + publicKeyString3);
	}
	
	function encryptMessage(message) {
		encryptionResult = cryptico.encrypt(message, publicKeyString2);
		return encryptionResult.cipher;
	}
	
	function decryptMessage(message, otherUserPublicKey, sender) {
		if (passPhrase == sender) {
			console.log("equals own pasphrase");
			var decryptResult = cryptico.decrypt(message, RSAKey2);
			//var decryptResult = cryptico.decrypt(message, RSAKey2); works with own msg but not for the other guy
			console.log("STATUS: " + decryptResult.status);
			return decryptResult.plaintext;
		}else{
			console.log("NOT equals own phassphrase");
			var decryptResult = cryptico.decrypt(message, otherUserPublicKey);
			//var decryptResult = cryptico.decrypt(message, RSAKey2); works with own msg but not for the other guy
			console.log("STATUS: " + decryptResult.status);
			return decryptResult.plaintext;	
		}
		
		
	}
/* ]]> */
