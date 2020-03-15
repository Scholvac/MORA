/*
 * MoraObject.h
 *
 *  Created on: 04.03.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORAOBJECT_H_
#define INCLUDE_MORAOBJECT_H_

#include <MoraPreReq.h>
#include <MoraIdentifier.h>

namespace mora {

	class MoraObject {
	protected:
		/** unique (for one communicator) identifier of this adapter*/
		mora::RemoteObject			mIdentity;
		/** Type of this adapter */
		const mora::TypeIdentifier	mType;
		const bool					mProxy;

		MoraObject(const mora::RemoteObject& identity, const mora::TypeIdentifier& type, bool isProxy);
	public:
		virtual ~MoraObject();

		mora::RemoteObject& identity() { return mIdentity; }
		const mora::RemoteObject& identity() const { return mIdentity; }
		const mora::TypeIdentifier& type() const { return mType; }
		bool isProxy() const { return mProxy; }
	};

} /* namespace mora */

#endif /* INCLUDE_MORAOBJECT_H_ */
