/* <![CDATA[ */
	var wsocket;
	var serviceLocation = "ws://" + document.location.host + "/Babbla-project/chat/";
	var $nickName;
	var $message;
	var $chatWindow;
	var room = 'java';
 
	function onMessageReceived(evt) {
		var msg = JSON.parse(evt.data); // native API
		var $messageLine = $('<h4>' + msg.sender + ' | <em>' + msg.received + '</em></h4>'
				+ '<p> '+ msg.message +' </p><hr>');
//		var $messageLine = $('<tr><td class="received">' + msg.received
//				+ '</td><td class="user label label-info">' + msg.sender
//				+ '</td><td class="message badge">' + msg.message
//				+ '</td></tr>');
		$chatWindow.append($messageLine);
		scrollToChatContainerBottom();
	}
	function sendMessage() {
		var msg = '{"message":"' + $message.val() + '", "sender":"'
				+ $nickName.val() + '", "received":""}';
		console.log($nickName.val());
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
 
	$(document).ready(function() {
		connectToChatserver();
		$nickName = $('#nickname');
		$message = $('#message');
		$chatWindow = $('#response');
		$nickName.focus();
		$('#do-chat').submit(function(evt) {
			evt.preventDefault();
			sendMessage()
		});
 
	});
/* ]]> */
