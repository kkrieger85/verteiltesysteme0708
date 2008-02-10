#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h> // Benoetigt fuer Internet Namensaufloesung
#include <unistd.h> 	// Benoetigt fuers Forken
#include "userData.h"

#define PORT 4242
#define MAX_QUEUE 3			// Maximale Anzahl von wartenden CLients in der Listen-Queue
#define BUF_SIZE 1024			// Maximale groesse der uebertragenden Nachricht
#define FORKCOUNT 10   			// Ist dafuer zustaendig das der Prozess sich maximal 10 mal Reproduziert
int 	listen_sock, send_sock; // eigendliche Sockets

struct sockaddr_in sockaddr_client;	// Adresse des anfragenden Clients
struct sockaddr_in sockaddr_server; // Adresse des Servers

int forking = 0;	// Fork Variabel, diese Gibt an welches Kind gerade am laufen ist
int fehler	= 0;	// Fehlervariable

// Benutzerdatenarray folgt:
const struct userData daten[]	=	{{ "Hugo\n","Balder\n","123456\n","testosteron\n"},
									{ "Master\n","keks\n","4223\n","keks\n"}};
const int	datenGroesse		= 2;

//Funktions-Deklarationen
void aufVerbindungWarten();
void gabeln();
void verbindeMitClient();
int	 authorisiere();
void aktionsWahl();
int  readline(register int fd, register char *ptr, register int maxlen) ;




/**
 * Main-Funktion,
 * Bindet den Serversocket auf Adressen und regt das Lauschen an 
 */
int main(int args, char *argv[]) {	
	
	printf("%i->> initialisieren...\n",forking);

	/* Socket Initialisieren,
	 * PF_INET bedeutet das man die protokollfamilie des 
	 * Internet benutzen moechte
	 */
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
	printf("%i-!> Verbindung Beendet!",forking);
	exit(fehler);
}

void aufVerbindungWarten() {
	printf("%i-!> Auf Verbindung warten...\n",forking);	
	
	/* Socket wird in Listeningmodus versetzt,
	 * dabei werden Maximal MAX_QUEUE viele hineingelassen
	 * ist die Queue voll gibt es einen Fehler
	 */
	if (listen(listen_sock, MAX_QUEUE) == -1){	
		printf("%i-!> listen () fehlgeschalgen!",forking);
	    fehler = 3;
	    return ;
	}
	
	printf("%i-!> Horchen...\n",forking);	
	
	int inAddr = sizeof(sockaddr_client);	// Groesse der Clientadresse	
	
	// Sendesocket erstellen, ab jetzt wartet das Socket aktiv.
	send_sock =	accept(listen_sock, (struct sockaddr *) &sockaddr_client, &inAddr ); 
	if( send_sock < 0 ){
		printf("%i-!> Verbindung konnte nicht hergestellt werden!",forking);
		fehler = 2;
		return ;
	}	
	
	// Client will verbinden, Forken anregen
	gabeln();
}

void gabeln() {
	int vater = 0;  // Identifiziert ob der Prozess Vater oder schon Kind is
	forking++;		// Forking wird erhoeht	
	
	/* Wenn Maximum erreicht, verbinde selbst "Sollte noch geaendert werden,
	 * weil sonst server nach 10 Anfragen aus ist
	 */
	if (forking >= FORKCOUNT) {
		verbindeMitClient();
	}else{
		vater = fork();
		
		// Kind kuemmert sich um den Client		
		if (vater == 0)
			verbindeMitClient();
		// Vater wartet weiter auf kommende Events
		else{
			forking--;
			aufVerbindungWarten();	
		}
	}
}

void verbindeMitClient() {
	printf("%i-!> Verbindung oeffnen...\n",forking);
	char buffer[BUF_SIZE] = "";	// Buffer mit der zu uebertragenden Nachricht	
			
	
	// Empfang des Haenmdeschuettelns des Clients
	printf("%i-?> Empfange Nachricht...\n",forking);
	readline(send_sock, buffer, BUF_SIZE); 
	//buffer[BUF_SIZE]= '\0';		// C-String finalisieren		
	printf("%i-$> Erhielt:\t%s \n",forking, buffer);	

	
	// wenn der Cleint Kaese von sich gibt, wird hier Terminiert
	if( strcmp( buffer, "Ja ich bin da.\n" ) != 0 ){
		printf("%i-!> verstehe Client nicht!",forking);
		fehler = 4;
		return ;
	}
	
	// eine Sekunde Schlafen um RaceConditions vorzubeugen
	while( sleep(1) > 0){}
	
	// Senden der Bestaetigung
	char sendBuffer[] = "Ich hoehre...\n";
	printf("%i-?> Sende:\t%s \n",forking, sendBuffer); 
	send(send_sock, sendBuffer, strlen(sendBuffer), 0); 
	
	/* nun kann der Client beginnen sich zu Authorisieren
	 * dem Benutzer werden 3 Logginversuche gestattet bis der 
	 * Socket geschlossen wird
	 */
	int i;
	for(i = 0; (authorisiere()!= 0) &&(i < 3) ; i++){
		printf("%i-!> loggin Daten falsch!",forking);

		// Sage Client das die Daten nicht Stimmen
		char sendBuffer[] = "Falsch\n";
		send(send_sock, sendBuffer, strlen(sendBuffer), 0); 		
	}				
	
	// Sage Client das genug Versucht wurde, und beende die Verbindung
	if (i != 0){
		char ende[] = "Genug, das reicht! Zuviele Versuche\n";
		send(send_sock, ende, strlen(ende), 0);
		fehler = 5;
	}	
}
int authorisiere(){
	char benutzer[BUF_SIZE] = "";	// Buffer mit dem uebertragenen Benutzer
	char passwort[BUF_SIZE] = "";	// Buffer mit dem uebertragenen Passwort
	char bestaetigt[]	= "Stimmt!\n";
			
	// Empfang des Benutzernamens
	printf("%i-?> Empfange Benutzername...\n",forking);
	readline(send_sock, benutzer, BUF_SIZE);  // Warum wird der server nicht fertig mit dem empfangen?
	benutzer[BUF_SIZE]= '\0';		// C-String finalisieren		
	printf("%i-$> Erhielt:\t%s \n",forking, benutzer);
	
	//gehe alle Benutzernamen durch
	int d = 0;
	int gefunden = 0;	// Booleanersatz zum Beenden falls gefunden	
	for(int i = 0; (i < datenGroesse)&&(gefunden == 0) ; i++){ 
		printf("\tEingang  Laenge: %i \n",strlen(benutzer));
		printf("\tBenutzer Laenge: %i \n",strlen(daten[i].name));
		if(strcmp(daten[i].name, benutzer) == 0){
			d=i; // Merke I zum sp�teren Passwortvergleich
			gefunden=1;
		}
	}
	// wenn nichts gefunden wurde ist gefunden gleich 0, also wird Abgebrochen
	if(gefunden == 0)
		return 1;	
	
	// Gebe Anmeldenden Benutzernamen aus
	printf("Benutzer %s meldet sich an\n",daten[d].name);
		
	// Sage Client das die Daten Stimmen
	send(send_sock, bestaetigt,strlen(bestaetigt), 0); 
		
	// Empfang des Passwortes
	printf("%i-?> Empfange Passwort...\n",forking);
	readline(send_sock, passwort, BUF_SIZE); 
	passwort[BUF_SIZE]= '\0';		// C-String finalisieren		
	printf("%i-$> Erhielt:\t%s \n",forking, passwort);	
	
	// Laenge des passwortstrings und des Empfangenen Strigns ausgeben
	printf("\tEingang Laenge: %i \n",strlen(passwort));
	printf("\tPasswort Laenge: %i \n",strlen(daten[d].passwort));	
	
	// Passwort vergleichen, wenn es nicht stimmt, wird Abgebrochen
	if(strcmp(daten[d].passwort, passwort) !=0)
		return 2;	
		
	// Sage Client das die Daten Stimmen
	send(send_sock, bestaetigt,strlen(bestaetigt), 0); 
	
	aktionsWahl(d);
		
	return 0;	
}

void aktionsWahl(int benutzer){
	char auswahl[BUF_SIZE] = "";
	char ende[] = "Ende\n";
	
	/* Empfang der Auswahl, wird solange bis Beendet
	 * 0: Beenden
	 * 1: Datenausgabe
	 */
	while(strcmp("0\n",auswahl)!= 0){
		printf("%i-?> Empfange Auswahl...\n",forking);
		readline(send_sock, auswahl, BUF_SIZE); 
		auswahl[BUF_SIZE]= '\0';		// C-String finalisieren	
		
		if(strcmp("1\n",auswahl)== 0){
			// Sende Accountdaten
			send(send_sock, daten[benutzer].vorname  ,strlen(daten[benutzer].vorname), 0);
			send(send_sock, daten[benutzer].kundenNr ,strlen(daten[benutzer].kundenNr),0); 
		}else
			printf("%i-?> Falsche Parameter\n",forking);			
	}
	send(send_sock, ende ,strlen(ende),0); 
}


/**
 * Erm�glicht das zeilenweise Lesen der eigentlich 
 * charbassierten �bertragung Es werden Zeichen zum Buffer 
 * hinzugef�gt bis ein Zeilenende gefunden wird oder Buffersize
 * ausgereizt ist.�
 * @param: sockel fd, char buffer, int buffersize
 */
int readline(register int fd, register char *ptr, register int maxlen) {
	int n, rc;
	char c;
	for (n = 1; n < maxlen; n++) {
		if ( (rc = read(fd, &c, 1)) == 1) {
			*ptr++ = c;
			if (c == '\n')
				break;
		} else if (rc == 0) {
			if (n == 1)
				return (0); 
			else
				break;
		} else
			return (-1); 
	}
	*ptr = 0;
	return (n);
}
