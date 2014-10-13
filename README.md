SocketTutorial
==============
A Socket Tutorial in Python and Java. While I've included files for setting up servers in Python and Java, and clients in Python and Java, I intend to only go over a Python server with a Java client. Feel free to play with these as you will.

##Tutorial:
<ol>

<li>What are sockets?
	<ul>
	<li>http://en.wikipedia.org/wiki/Network_socket</li>
	<li>An endpoint of a network communication</li>
	<li>Composed/identified by an address and port.</li>
	</ul>
</li>
<li>Connection Process: http://www.tutorialspoint.com/images/perl_socket.jpg
</li>
<li>Server Sockets: Python
	<ul>
	<li>User programs' port numbers should be >1000</li>
	<li>creation, bind, listen, accept, <send data>, close</li>
	</ul>
</li>
<li>[Client] Sockets: Java
	<ul>
	<li>HOST and PORT must match the server</li>
	<li>creation, connection, <send data>, close</li>
	</ul>
</li>
<li>Sending and receiving data to/from a host: echoServer
</li>
<li>Sending and receiving data to/from a host: chat service
</li>
<li>Sockets and Threads</li>
<li>Example: GUIChat</li>
<li>Example: Chess (https://github.com/koceskik/Chess.git)
	ObjectOutputStream and ObjectInputStream (see src/networking/Connection.java)
	Threading: see Baker's presentation next week
</li>

</ol>

####References
Python socket documentation:
	https://docs.python.org/2/library/socket.html
Java socket tutorial:
	http://docs.oracle.com/javase/tutorial/networking/sockets/
Java socket API:
	http://docs.oracle.com/javase/7/docs/api/java/net/Socket.html