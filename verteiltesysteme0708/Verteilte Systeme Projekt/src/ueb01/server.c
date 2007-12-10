#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h> // Benötigt für Internet Namensauflösung
#include <unistd.h> 	// Benötigt fürs Forken
#include "userData.h"

/** ToDo:
 * ->	Daten zuruecksenden 
 * ->	Forken korregieren
 */
/** Erklaerung zu der Ausagebe des laufenden Programm:
 * 	Die Zahl an erster Stelle sagt welcher Fork er ist.
 * 
 * 	Warum Forkt nur der Prozess 0?
 *  Damit dieser die Forks entsprechend Zählen kann und
 *  bei der Maximalzahl dicht macht
 */

#define PORT 4242
#define MAX_QUEUE 3		// Maximale Anzahl von wartenden CLients in der Listen-Queue
#define BUF_SIZE 1024	// Maximale größe der übertragenden Nachricht
#define FORKCOUNT 10   	// Ist dafür zustaendig das der Prozess sich maximal 10 mal Reproduziert
int listen_sock, send_sock; // eigendliche Sockets

struct sockaddr_in sockaddr_client;	// Adresse des anfragenden Clients
struct sockaddr_in sockaddr_server; // Adresse des Servers

int forking = 0;// Fork-Limit Variabel
int fehler = 0;	// Fehlervariable

// Benutzerdatenarray folgt:
const struct userData daten[]	=	{{ "Hugo","Balder",123456,"testosteron"},
									{ "Master","keks",4223,"keks"}};
const int	datenGroesse		= 2;

//Funktions-Deklarationen
void aufVerbindungWarten();
void gabeln();
void verbindeMitClient();
int	 authorisiereUndSende();

int main(int args, char *argv[]) {
	printf("%i->> initialisieren...\n",forking);

	// Socket Initialisieren,
	// PF_INET bedeutet das man die protokollfamilie des 
	// Internet benutzen moechte
	listen_sock = socket(PF_INET ,SOCK_STREAM , 0);
	if (listen_sock < 0) {
		printf("%i-!> Socket konnte nicht geoeffnet werden!", forking);
		exit(1);
	}

	// Serversocket Adresse erstellen
	sockaddr_server.sin_family = AF_INET;	// Sagt das uber das Internet Kommuniziert wird
	sockaddr_server.sin_addr.s_addr = INADDR_ANY;
	sockaddr_server.sin_port = htons(PORT);		// Gibt dem Port vor

	// Sockets binden, d.h. Socket Adressbereiche zuweisen
	if (bind(listen_sock, (struct sockaddr *) &sockaddr_server,
			sizeof(sockaddr_server)) < 0) {
		printf("%i-!> Sockets konnten nicht gebunden werden!",forking);
		exit(1);
	}
	// Auf Port Horchen
	aufVerbindungWarten();

	// Fehlerausgabe
	if (fehler != 0)
		printf(strerror(fehler));
	close(send_sock);
	close(listen_sock);	
	return fehler;
}

void aufVerbindungWarten() {
	printf("%i-!> Auf Verbindung warten...\n",forking);	
	
	// Socket wird in Listeningmodus versetzt,
	// dabei werden Maximal MAX_QUEUE viele hineingelassen
	// ist die Queue voll gibt es einen Fehler
	if (listen(listen_sock, MAX_QUEUE) == -1){	
		printf("%i-!> listen () fehlgeschalgen!",forking);
	    fehler = 3;
	    return ;
	}
	//Forken anregen
	gabeln();
}

void gabeln() {
	int vater = 0;  // Temporaer fuer Debbiging Zwecke auskommentiert
	printf("%i-!> Horchen...\n",forking);	
	forking++;
	
	int inAddr = sizeof(sockaddr_client);		// Groesse der Clientadresse	
	// Sendesocket erstellen, ab jetzt wartet das Socket aktiv.
	send_sock =	accept(listen_sock, (struct sockaddr *) &sockaddr_client, &inAddr ); 
	if( send_sock < 0 ){
		printf("%i-!> Verbindung konnte nicht hergestellt werden!",forking);
		fehler = 2;
		return ;
	}
	// Wenn Maximum erreicht, verbinde selbst "Sollte noch geändert werden,
	// weil sonst server nach 10 Anfragen aus ist
	if (forking >= FORKCOUNT) {
		verbindeMitClient();
	}	
	else {
		vater = fork();
		// Vater wartet weiter auf kommende Events
		if (vater == 0)
			aufVerbindungWarten();
		// Kind kümmert sich um den Client
		else			
			verbindeMitClient();
	}
}

void verbindeMitClient() {
	printf("%i-!> Verbindung öffnen...\n",forking);
	char buffer[BUF_SIZE] = " ";	// Buffer mit der zu uebertragenden Nachricht	
			
	
	// Empfang des Hänmdeschüttelns des Clients
	printf("%i-?> Empfange Nachricht...\n",forking);
	recv(send_sock, buffer, BUF_SIZE, 0); 
	buffer[BUF_SIZE]= '\0';		// C-String finalisieren		
	printf("%i-$> Erhielt:\t%s \n",forking, buffer);
	
	// wenn der Cleint Käse von sich gibt, wird hier Terminiert
	if( strcmp( buffer, "Ja ich bin da." ) != 0 ){
		printf("%i-!> verstehe Client nicht!",forking);
		fehler = 4;
		return ;
	}
	
	// Senden der Bestätigung
	char sendBuffer[BUF_SIZE] = "Ich hoehre...";
	printf("%i-?> Sende:\t%s \n",forking, sendBuffer); 
	send(send_sock, sendBuffer, strlen(sendBuffer), 0); 
	
	// nun kann der Client beginnen sich zu Authorisieren
	// dem Benutzer werden 3 Logginversuche gestattet bis der 
	// Socket geschloßen wird
	for(int i = 0; (authorisiereUndSende()!= 0) &&(i < 3) ; i++){
		printf("%i-!> loggin Daten falsch!",forking);
		
	}				
	
	// wenn der Durchlauf hier endet ist was Schief gegangen
	fehler = 5;
	return;
}
int authorisiereUndSende(){
	char buffer[BUF_SIZE] = " ";	// Buffer mit der zu uebertragenden Nachricht				
		
	// Empfang des Benutzernamens
	printf("%i-?> Empfange Benutzername...\n",forking);
	recv(send_sock, buffer, BUF_SIZE, 0); 
	buffer[BUF_SIZE]= '\0';		// C-String finalisieren		
	printf("%i-$> Erhielt:\t%s \n",forking, buffer);
	
	//gehe alle Benutzernamen durch
	int i;
	int d = 0;
	for(i = 0; i < datenGroesse ; i++){ 
		if(strcmp(daten[i].name, buffer) !=0)
			d++;			
	}
	// wenn nichts gefunden wurde ist d = i
	if(d == i)
		return 1;
	
	// Empfang des Passwortes
	printf("%i-?> Empfange Passwort...\n",forking);
	recv(send_sock, buffer, BUF_SIZE, 0); 
	buffer[BUF_SIZE]= '\0';		// C-String finalisieren		
	printf("%i-$> Erhielt:\t%s \n",forking, buffer);
	
	// Passwort vergleichen
	if(strcmp(daten[i].passwort, buffer) !=0)
		return 2;
	
	// Hier muss noch das senden der Daten an den Client hin
	
	return 0;
	
	exit(0);
}

