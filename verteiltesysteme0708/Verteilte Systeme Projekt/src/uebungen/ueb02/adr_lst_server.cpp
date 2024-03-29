/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "adr_lst.h"
#include <cstring>
#include <unistd.h>
#include <iostream>
#include <map>

using namespace std;

/* Anzahl Sekunden, die bei einem ungueltigen Handle / Login mit
 * nichtvorhandenen Benutzer-Passwort-Daten gewartet wird. Dient
 * als primitiver Schutz gegen Brute-Force-Angriffe.
 */
static const int access_denied_delay = 3;


/* Liste von Benutzername-Passwort-Daten-Tupel der vorhandenen 
 * Loginkennungen.
 */
static const char* logins[][3] = {
   { "foo", "bar", "Ein guter Freund des Hauses." },
   { "bar", "foo", "Ein gutes Haus des Freundes." },
   { "peter", "morphose", "Ein alter Bekannter." },
   { "butter", "brot", "Schmeckt gut." },
   { NULL, NULL, NULL }
};


/* Abbildung von Handles -> index ins logins-Array fuer gerade im
 * System eingeloggte Benutzer.
 */
static map<int, int> handles;


/* Authorisierung anhand von Benutzername und Passwort.
 * Weist dem Benutzer ein Handle zu und gibt dessen ID zurueck.
 */
int *
adr_lst_auth_1_svc(adr_lst_auth_data *argp, struct svc_req *rqstp)
{
	static int result;
   result = -1; // Muss eigenes Statement sein um bei JEDEM Aufruf neu zu initialisieren.
   
   /* Alle Loginkennungen durchsuchen um einen Eintrag mit passendem Benutzernamen und
    * Passwort zu finden. Der Index in das logins-Array wird mitgespeichert.
    */
   int idx;
   for (int i = 0; result == -1; i++) {
      const char** login = logins[i];
      const char* user = login[0];
      const char* pass = login[1];
      
      if (!user || !pass)
         break;
      
      // Login OK?
      if (!strcmp(user, argp->username) && !strcmp(pass, argp->passwd)) {
         result = 0;
         idx = i;
      }
   }
   
   // Login OK?
   if (result != -1) {
      int handle;
      
      // Ungenutztes zufaelliges handle finden.
      do {
         handle = rand();
      } while (handles.count(handle) > 0);
      
      // Handle zuweisen
      handles[handle] = idx;
      cout << "Handle for " << argp->username << " with " << argp->passwd << " = " << handle << endl;
      
      result = handle;
   }
   
   // Primitiver Schutz gegen Brute-Force-Angriffe.
   sleep(access_denied_delay);

	return &result;
}


/* Fragt die Informationen fuer den aktuellen Benutzer ab und
 * gibt sie als String zurueck.
 */
adr_lst_query_result *
adr_lst_query_1_svc(int *argp, struct svc_req *rqstp)
{
	static adr_lst_query_result result;
   result.error = 0;
   
   std::map<int, int>::const_iterator pos = handles.find(*argp);
   
   // Ungueltiges Handle?
   if (pos == handles.end()) {
      result.error = -1;

      // Primitiver Schutz gegen Brute-Force-Angriffe.
      sleep(access_denied_delay);
   } else {
      strncpy(result.result, logins[pos->second][2], sizeof(result.result) - 1);
   }

	return &result;
}


/* Meldet einen Benutzer vom System ab und traegt das Handle aus
 * der Handle-Liste aus.
 */
int *
adr_lst_logoff_1_svc(int *argp, struct svc_req *rqstp)
{
   int handle = *argp;
	static int result = 0;
   
   // Ungueltiges Handle?
   if (handles.count(handle) == 0) {
      result = -1;
      
      // Primitiver Schutz gegen Brute-Force-Angriffe.
      sleep(access_denied_delay);
   } else {
      handles.erase(handle);
   }

	return &result;
}
