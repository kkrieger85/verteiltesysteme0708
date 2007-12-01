/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "adr_lst.h"

bool_t
xdr_ADR_LST_QUERY_RESULT(XDR *xdrs, ADR_LST_QUERY_RESULT *objp)
{

	if (!xdr_int(xdrs, &objp->success))
		return (FALSE);
	if (!xdr_pointer(xdrs, (char **)&objp->result, sizeof(char), (xdrproc_t)xdr_char))
		return (FALSE);
	return (TRUE);
}

bool_t
xdr_adr_lst_auth_1_argument(XDR *xdrs, adr_lst_auth_1_argument *objp)
{
	if (!xdr_pointer(xdrs, (char **)&objp->username, sizeof(char), (xdrproc_t)xdr_char))
		return (FALSE);
	if (!xdr_pointer(xdrs, (char **)&objp->passwd, sizeof(char), (xdrproc_t)xdr_char))
		return (FALSE);
	return (TRUE);
}

bool_t
xdr_adr_lst_query_1_argument(XDR *xdrs, adr_lst_query_1_argument *objp)
{
	if (!xdr_int(xdrs, &objp->handle))
		return (FALSE);
	if (!xdr_int(xdrs, &objp->what))
		return (FALSE);
	return (TRUE);
}