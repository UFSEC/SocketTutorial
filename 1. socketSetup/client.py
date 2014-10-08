# Echo client program
import socket

HOST = 'localhost'		# The 'remote' host
PORT = 3333				# Same port as server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #create a socket
s.connect((HOST, PORT))	#connect to remote host

# do stuff on the socket
print "Connected"

s.shutdown(socket.SHUT_WR)	#prepare server for socket closure
s.close()					#close socket
print "Connection Closed"