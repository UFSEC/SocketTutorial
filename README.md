SocketTutorial
==============
A Socket Tutorial in Python and Java. While I've included files for setting up servers in Python and Java, and clients in Python and Java, I intend to only go over a Python server with a Java client. Feel free to play with these as you will.

*Read the corresponding web version [here](http://ufsdc.github.io/articles/2014/10/15/network-programming/).*

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
<li>More with Sockets
	<ul>
	<li>Threading: see Baker's presentation next week</li>
	<li>ObjectOutputStream and ObjectInputStream</li>
	</ul>
</li>
<li>Examples
	<ul>
	<li>GUI Chat (see 4. ChatGUI directory)</li>
	<li>Chess (https://github.com/koceskik/Chess.git)</li>
	<li>Conway's Game of Life (https://github.com/devanp92/Concurrent-Game-of-Life)</li>
	</ul>
</li>
</ol>

####References
<ul>
<li>
Python socket documentation:
	https://docs.python.org/2/library/socket.html
</li>
<li>
Java socket tutorial:
	http://docs.oracle.com/javase/tutorial/networking/sockets/
</li>
<li>
Java socket API:
	http://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
</li>
</ul>
