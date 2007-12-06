/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "adr_lst.h"
#include <stdio.h>
#include <stdlib.h>
#include <rpc/pmap_clnt.h>
#include <string.h>
#include <memory.h>
#include <sys/socket.h>
#include <netinet/in.h>

#ifndef SIG_PF
#define SIG_PF void(*)(int)
#endif

static void
adr_lst_1(struct svc_req *rqstp, register SVCXPRT *transp)
{
	union {
		adr_lst_auth_data adr_lst_auth_1_arg;
		adr_lst_query_data adr_lst_query_1_arg;
		int adr_lst_logoff_1_arg;
	} argument;
	char *result;
	xdrproc_t _xdr_argument, _xdr_result;
	char *(*local)(char *, struct svc_req *);

	switch (rqstp->rq_proc) {
	case ADR_LST_AUTH:
		_xdr_argument = (xdrproc_t) xdr_adr_lst_auth_data;
		_xdr_result = (xdrproc_t) xdr_int;
		local = (char *(*)(char *, struct svc_req *)) adr_lst_auth_1_svc;
		break;

	case ADR_LST_QUERY:
		_xdr_argument = (xdrproc_t) xdr_adr_lst_query_data;
		_xdr_result = (xdrproc_t) xdr_adr_lst_query_result;
		local = (char *(*)(char *, struct svc_req *)) adr_lst_query_1_svc;
		break;

	case ADR_LST_LOGOFF:
		_xdr_argument = (xdrproc_t) xdr_int;
		_xdr_result = (xdrproc_t) xdr_int;
		local = (char *(*)(char *, struct svc_req *)) adr_lst_logoff_1_svc;
		break;

	default:
		svcerr_noproc (transp);
		return;
	}
	memset ((char *)&argument, 0, sizeof (argument));
	if (!svc_getargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		svcerr_decode (transp);
		return;
	}
	result = (*local)((char *)&argument, rqstp);
	if (result != NULL && !svc_sendreply(transp, (xdrproc_t) _xdr_result, result)) {
		svcerr_systemerr (transp);
	}
	if (!svc_freeargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		fprintf (stderr, "%s", "unable to free arguments");
		exit (1);
	}
	return;
}

int
main (int argc, char **argv)
{
	register SVCXPRT *transp;

	pmap_unset (ADR_LST, ONE);

	transp = svcudp_create(RPC_ANYSOCK);
	if (transp == NULL) {
		fprintf (stderr, "%s", "cannot create udp service.");
		exit(1);
	}
	if (!svc_register(transp, ADR_LST, ONE, adr_lst_1, IPPROTO_UDP)) {
		fprintf (stderr, "%s", "unable to register (ADR_LST, ONE, udp).");
		exit(1);
	}

	transp = svctcp_create(RPC_ANYSOCK, 0, 0);
	if (transp == NULL) {
		fprintf (stderr, "%s", "cannot create tcp service.");
		exit(1);
	}
	if (!svc_register(transp, ADR_LST, ONE, adr_lst_1, IPPROTO_TCP)) {
		fprintf (stderr, "%s", "unable to register (ADR_LST, ONE, tcp).");
		exit(1);
	}

	svc_run ();
	fprintf (stderr, "%s", "svc_run returned");
	exit (1);
	/* NOTREACHED */
}
