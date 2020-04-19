/*
 * MoraGen.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORAGEN_H_
#define INCLUDE_MORAGEN_H_


#include <MoraPreReq.h>
#include <MoraStreams.h>

#include <MoraAdapter.h>
#include <MoraProxy.h>
#include <MoraCommunicator.h>
#include <MoraException.h>
#include <MoraUtils.h>


namespace mora {
	const int8 STRUCT_START = (int8)0xAF;
	const int8 STRUCT_END = (int8)0xFF;
	const int8 STRUCT_NULL = (int8)0x00;
}

#define SERIALIZER_CHECK_EQ(actual, expected, msg) if (actual != expected) throw ::mora::MoraDeserialisationException(msg, expected, actual);

#endif /* INCLUDE_MORAGEN_H_ */
