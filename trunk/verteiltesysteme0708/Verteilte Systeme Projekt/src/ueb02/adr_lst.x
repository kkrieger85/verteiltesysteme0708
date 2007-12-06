struct adr_lst_auth_data {
  char username[20];
  char passwd[32];
};

struct adr_lst_query_data {
  int handle;
  int what;
};

struct adr_lst_query_result {
  int success;
  char result[255];
};

program ADR_LST {
  version ONE {
    int
    ADR_LST_AUTH(adr_lst_auth_data *authdata) = 0;

    adr_lst_query_result
    ADR_LST_QUERY(adr_lst_query_data *querydata) = 1;
    
    int
    ADR_LST_LOGOFF(int handle) = 2;
  } = 1;
} = 1004711;
