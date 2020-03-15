/*
 * MoraException.cpp
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#include <MoraException.h>
#include <Poco/Format.h>

namespace mora {

MoraException::MoraException(const char* msg) 
	:	std::runtime_error(msg)
{
}



MoraException::~MoraException() {
	// TODO Auto-generated destructor stub
}


MoraDeserialisationException::MoraDeserialisationException(const char* msg, int e, int a)
	: MoraException(Poco::format(msg, e, a).c_str())
{}

MoraTransportException::MoraTransportException(const char* msg) 
	:	MoraException(msg)
{}

} /* namespace mora */
