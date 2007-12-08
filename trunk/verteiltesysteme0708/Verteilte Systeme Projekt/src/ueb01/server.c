#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h> // Benötigt für Internet Namensauflösung
#include <unistd.h> 	// Benötigt fürs Forken
#include "userData.h"

/** ToDo:
 * ->	Login Daten des Clients verarbeiten
 * ->	Daten zuruecksenden 
 * ->	Forken korregeiren
 * 		muss nach dem Connect gmacht werden oder wie?
 */

#define PORT 4242
#define MAX_QUEUE 3		// Maximale Anzahl von wartenden CLients in der Listen-Queue
#define BUF_SIZE 1024	// Maximale größe der übertragenden Nachricht
#define FORKCOUNT 10   	// Ist dafür zustaendig das der Prozess sich maximal 10 mal Reproduziert
int listen_sock, send_sock; // eigendliche Sockets

struct sockaddr_in sockaddr_client;	// Adresse des anfragenden Clients
struct sockaddr_in sockaddr_server; // Adresse des Servers

int forking = 0; // Fork-Limit Variabel
int fehler = 0;	// Fehlervariable

//Funktions-Deklarationen
void waitForConnect();
void gabeln();
void connectSocket();

int main(int args, char *argv[]) {
	printf("initialisieren\n");

	//Socket Initialisieren
	listen_sock = socket(PF_INET ,SOCK_STREAM , 0);
	if (listen_sock < 0) {
		perror("Socket konnte nicht geoeffnet werden");
		exit(1);
	}

	// Serversocket Adresse erstellen
	sockaddr_server.sin_family = AF_INET;	// Sagt das uber das Internet Kommuniziert wird
	sockaddr_server.sin_addr.s_addr = INADDR_ANY;
	sockaddr_server.sin_port = htons(PORT);		// Gibt dem Port vor

	// Sockets binden
	if (bind(listen_sock, (struct sockaddr *) &sockaddr_server,
			sizeof(sockaddr_server)) < 0) {
		perror("Sockets konnten nicht gebunden werden");
		exit(1);
	}
	// Auf Port Horchen
	waitForConnect();

	// Fehlerausgabe
	if (fehler != 0)
		printf(strerror(fehler));
	return fehler;
}

void waitForConnect() {
	printf("Auf Verbindung warten\n");	
	
	// Socket wird in Listeningmodus versetzt
	if (listen(listen_sock, MAX_QUEUE) == -1){	
		perror("listen () fehlgeschalgen");
	    fehler = 3;
	}
	//Forken anregen
	gabeln();
}

void gabeln() {
	//	int vater = 0;  // Temporaer fuer Debbiging Zwecke auskommentiert
	printf("Horchen\n");
	forking++;
//	if (forking >= FORKCOUNT) {
		connectSocket();
//	}
	//	else {
	//		vater = fork();
	//		if (vater == 0)
	//			waitForConnect();
	//		else
	//			connectSocket();
	//	}
}

void connectSocket() {
	printf("Verbindung öffnen\n");
	char buffer[BUF_SIZE] = "Test";					// Buffer mit der zu uebertragenden Nachricht	
	int inAddr = sizeof(sockaddr_client);	// Groesse der Clientadresse
	
	// Sendesocket erstellen
	send_sock =	accept(listen_sock, (struct sockaddr *) &sockaddr_client, &inAddr ); 
	if( send_sock < 0 ){
		perror("Verbindung konnte nicht hergestellt werden");
		fehler = 1;
	}
	
	printf("Sende: %c \n", buffer);
	send(send_sock, buffer, BUF_SIZE, 0); 
	
	// Terminieren (Fork läuft ja weiter)
	close(send_sock);
	close(listen_sock);
	exit(0);

}

