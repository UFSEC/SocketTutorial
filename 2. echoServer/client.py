# Echo client program
import socket

HOST = 'localhost'		# The 'remote' host
PORT = 3333				# Same port as server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #create a socket
s.connect((HOST, PORT))	#connect to remote host

# do stuff on the socket
print "Connected"

print "Type 'exit' to exit"
val = ""
while val != "exit": 
	val = raw_input('')
	s.send(val + "\n")	#newline is needed for a Java server
	print ' Response:', s.recv(1024)


s.shutdown(socket.SHUT_WR)	#prepare server for socket closure
s.close()					#close socket
print "Connection Closed"