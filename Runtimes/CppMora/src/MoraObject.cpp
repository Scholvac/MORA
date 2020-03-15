/*
 * MoraObject.cpp
 *
 *  Created on: 04.03.2020
 *      Author: sschweigert
 */

#include <MoraObject.h>

using namespace mora;

MoraObject::MoraObject(const mora::RemoteObject& identity, const mora::TypeIdentifier& type, bool ip) 
	: mIdentity{ identity }, mType{ type }, mProxy{ ip }
{
}

MoraObject::~MoraObject() {
}
