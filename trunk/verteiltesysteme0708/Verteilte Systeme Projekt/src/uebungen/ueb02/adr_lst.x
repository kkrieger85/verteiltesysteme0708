struct adr_lst_auth_data {
  char username[20];
  char passwd[32];
};

struct adr_lst_query_result {
  int error;
  char result[255];
};


program ADR_LST {
  version ONE {
    /* Authentifizierung durchfuehren.
     *
     * Erhaelt ein adr_lst_auth_data struct (mit Username und Passwort)
     * und gibt im Erfolgsfall ein handle zurueck. Dieses Handle ist
     * solange gueltig bis LOGOFF aufgerufen wird. Bei Misserfolg wird
     * -1 zurueckgegeben.
     */
    int
    ADR_LST_AUTH(adr_lst_auth_data *authdata) = 0;

    /* Abfrage durchfuehren.
     *
     * Erhaelt ein gueltiges handle. Gibt ein adr_lst_query_result
     * (mit error und result) zurueck. Im Erfolgsfall ist error = 0
     * und result enthaelt einen Informationsstring zum aktuellen
     * Benutzer. Im Misserfolgsfall (ungueltiges handle) ist error = -1.
     */
    adr_lst_query_result
    ADR_LST_QUERY(int handle) = 1;
    
    /* Abmeldung durchfuehren.
     *
     * Erhaelt ein gueltiges handle. Meldet den durch das handle
     * identifizierten Benutzer vom System ab. Im Erfolgsfall wird 0
     * zurueckgegeben. Im Misserfolgsfall (ungueltiges handle) wird
     * -1 zurueckgegeben.
     */
    int
    ADR_LST_LOGOFF(int handle) = 2;
  } = 1;
} = 1004711;
