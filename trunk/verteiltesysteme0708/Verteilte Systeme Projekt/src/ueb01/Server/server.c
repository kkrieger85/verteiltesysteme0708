#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h> // Benötigt für Internet Namensauflösung
#include <unistd.h> 	// Benötigt fürs Forken
#include "userData.h"

/** ToDo:
 * ->	Ursache fuer das fruehzeitige Terminieren
 * 		ohne eingehenden Verbindung finden
 * ->	Login Daten des Clients verarbeiten
 * ->	Daten zuruecksenden 
 */

#define PORT 4242
#define MAX_QUEUE 1
#define BUF_SIZE 1024
#define FORKCOUNT 10   	// Ist dafür zustaendig das der Prozess sich maximal 10 mal Reproduziert
int listen_sock, send_sock; // eigendliche Sockets

struct sockaddr_in sockaddr_client;	// Adresse des anfragenden Clients
struct sockaddr_in sockaddr_server; // Adresse des Servers

int forking = 0; // Fork-Limit Variabel

//Funktions-Deklarationen
void waitForConnect();
void gabeln();
void connectSocket();

int main(int args, char *argv[]) {
	int fehler = 0;

	//Socket Initialisieren
	listen_sock = socket(AF_INET ,SOCK_STREAM , 0);
	if (listen_sock < 0) {
		perror("Socket konnte nicht geoeffnet werden");
		exit(1);
	}

	// Serversocket Adresse erstellen
	sockaddr_server.sin_family = AF_INET;	// Sagt das uber das Internet Kommuniziert wird
	sockaddr_server.sin_addr.s_addr = INADDR_ANY;
	sockaddr_server.sin_port = PORT;		// Gibt dem Port vor

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
	listen(listen_sock, MAX_QUEUE);
	//uh da is ja schon einer, Schnell ma Forken
	gabeln();
}

void gabeln() {
	//	int vater = 0;  // Temporaer fuer Debbiging Zwecke auskommentiert
	forking++;
	if (forking >= FORKCOUNT) {
		connectSocket();
	}
	//	else {
	//		vater = fork();
	//		if (vater == 0)
	//			waitForConnect();
	//		else
	//			connectSocket();
	//	}
}

void connectSocket() {
	
	char* buffer = "Test";					// Buffer mit der zu uebertragenden Nachricht	
	int inAddr = sizeof(sockaddr_client);	// Groesse der Clientadresse
	
	// Sendesocket erstellen
	send_sock =	accept(listen_sock, (struct sockaddr *) &sockaddr_client, &inAddr ); 
	if( send_sock < 0 ){
		perror("Verbindung konnte nicht hergestellt werden");
		exit(1);
	}
		
	//send_sock = accept(listen_sock, 0, 0); // Aufgeschanppte Methoden
	//recv (int, void *__buff, size_t __len, int __flags);
	send(send_sock, *buffer, BUF_SIZE, 0); // Provoziert Warnung(trotzdem laufffähig) wegen Ganzzahl Typisierung?
	
	// Terminieren (Fork läuft ja weiter)
	close(send_sock);
	close(listen_sock);
	exit(0);

}

