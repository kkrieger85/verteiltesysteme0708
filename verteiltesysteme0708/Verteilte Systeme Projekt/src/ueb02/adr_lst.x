struct ADR_LST_QUERY_RESULT {
  int success;
  char* result;
};

program ADR_LST {
  version ADR_LST_VERS_1 {
    int
    ADR_LST_AUTH(char* username, char* passwd) = 0;

    ADR_LST_QUERY_RESULT
    ADR_LST_QUERY(int handle, int what) = 1;
    
    int
    ADR_LST_LOGOFF(int handle) = 2;
  } = 1;
} = 1;

const ADR_LST_VERS = 1;      /* latest version */