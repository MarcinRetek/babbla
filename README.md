# Babbla

## Application
Babbla is a platform for users to chat with each other. 
It is required that the user has a google email-account to be able to login and use our chat service.
This is a school project and a final exam in the course Secure Software

### Techniques
* Java Enterprise Edition	
	* Session Beans, Enterprise Java Beans, Websocket	
	
* Java Server Faces 
	* Primefaces
	* Java Backing Beans

* Java 8
* Java Servlets
* JavaScript
* jQuery
* MySQL
* Maven
* Application Server
	* Glassfish
	
### Method
To log in we use Googles API for a secure log in. Babbla only have access to the users email and name through Google and NOT their Google password, nor any other delicate information.  

The chat client uses the RSA algorithm for encryption and decryption.
The users first of all have to send their public key to the other recipient. When both have received the other users public key they are able to encrypt messages. The receiving user decrypt with his/hers own private key. It's all done automatically, the users doesn't hold their own keys, which makes the chat a bit more insecure in case an authority wants to claim the keys from the developers.   
RSA is implemented by the help of a javascript library called Cryptico.

Because the encryption is done front end there might be a security risk. Additional measures have been taken to prevent that, users no longer have the ability to right-click to inspect or to change elements in their browsers 

There are four rooms that the users are able to chat. Only two can chat at the same time in one room, otherwise their keys get mixed up and the encryption doesn't work. When a third user try to connect a full room he/she will get an alert saying this.   
  
Babbla uses HTTPS and WSS (WebSocket Secure), both secure protocol, and even if the user tries to access the original protocol and insecure a port he/she gets redirected to the secure protocol.


	
	
#### Creators
	* Johannes Klint - johannesklint@gmail.com
	* Marlon Jakobsson - jakobssonmarlon@gmail.com
	* Marcin Retek - marcinretek@hotmail.com
	
