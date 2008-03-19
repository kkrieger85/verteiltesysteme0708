#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#define BUF_SIZ 4096

// Umsetzung von Charweisem lesen zu zeilenweisem Lesen
int readline(register int fd, register char *ptr, register int maxlen){
int n, rc;
char c;
for (n = 1; n < maxlen; n++) {
if ( (rc = read(fd, &c, 1)) == 1) {
*ptr++ = c;
if (c == '\n')
break;
} else if (rc == 0) {
if (n == 1)
return(0); /* EOF, no data read */
else
break; /* EOF, some data was read */
} else
return(-1); /* error */
}
*ptr = 0;
return(n);
}

// Umsezung des Sendens an Client
int writen(register int fd, register char *ptr, register int nbytes){
int nleft, nwritten;
nleft = nbytes;
while (nleft > 0) {
nwritten = write(fd, ptr, nleft);
if (nwritten <= 0)
return(nwritten); /* error */
nleft -= nwritten;
ptr += nwritten;
}
return(nbytes - nleft);
}







int handle_client(const int sock)
{
    char buffer[BUF_SIZ];
    int n;

    /* Erinnerung: wenn recv() als Rueckgabe 0 liefert, dann
     * wurde die Verbindung geschlossen. Daher hier auf > 0 und
     * nicht auf == -1 testen.
     */
    /* while((bytes = recv(sock, buffer, sizeof(buffer), 0)) > 0)
    {
    	printf(buffer);
    	send(sock, buffer, bytes, 0);
    } */
	
    while (1) {
    	n = readline(sock, buffer, 4096);
    	
    	if (n == 0)
    		return 0;
    	
    	if (n < 0) {
    		printf("read error!");
    		return 0;
    	}
    	
    	if (writen(sock, buffer, n) != n)
    		printf("write error!");
    }
	
    return 0;
}

int main(void)
{
    int s, c, addr_len;
    struct sockaddr_in addr;

    s = socket(PF_INET, SOCK_STREAM, 0);
    if (s == -1)
    {
        perror("socket() failed");
        return 1;
    }

    addr.sin_addr.s_addr = INADDR_ANY;
    addr.sin_port = htons(7000);
    addr.sin_family = AF_INET;

    if (bind(s, (struct sockaddr*)&addr, sizeof(addr)) == -1)
    {
        perror("bind() failed");
        return 2;
    }

    if (listen(s, 3) == -1)
    {
        perror("listen() failed");
        return 3;
    }

    for(;;)
    {
        addr_len = sizeof(addr);
        c = accept(s, (struct sockaddr*)&addr, &addr_len);
        if (c == -1)
        {
            perror("accept() failed");
            continue;
        }

        printf("V2 - Client from %s\n", inet_ntoa(addr.sin_addr));
        handle_client(c);

        close(c);
    }

    close(s);
    return 0;
}

