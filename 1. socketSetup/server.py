# Echo server program
import socket
HOST = ''			# 'localhost' but visible to network
PORT = 3333			# Arbitrary non-privileged port

ss = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #create a socket (to be used as a serversocket)
ss.bind((HOST, PORT))	#bind SS to a host:port pair
ss.listen(1)			#listen for connections to port with queue of size 1
s, addr = ss.accept()	#accept the first connection creating a communication socket and an address object

# receive stuff on the socket
print "Connected by:", addr

s.shutdown(socket.SHUT_WR)	#prepare remote client for socket closure
s.close()					#close socket
print "Connection Closed"