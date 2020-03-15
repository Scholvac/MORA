/*
 * MoraRemoteMethodCall.h
 *
 *  Created on: 23.02.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORAREMOTEMETHODCALL_H_
#define INCLUDE_MORAREMOTEMETHODCALL_H_

#include <MoraPreReq.h>
#include <MoraIdentifier.h>
#include <MoraStreams.h>
#include <MoraException.h>

namespace mora {

	class Communicator;
	class IncommingMethodCall; //private class that is used for incoming method calls

	class MORA_API IRemoteMethodCall {
		friend class Communicator;
		friend class IncommingMethodCall;
	private:
		int16					mMagic{ -1 }; //set by communicator
		mora::Communicator&		mCommunicator;

		const RemoteMethod&		mTargetMethod;
	
		mora::OutputStream		mOutputStream;
		mora::InputStream*		mInputStream;

	protected:
		IRemoteMethodCall(const RemoteMethod& method, Communicator& communicator); //used for outgoing calls
		IRemoteMethodCall(const RemoteMethod& method, int16 magic, InputStream* in, Communicator& communicator); //used for incoming calls
	public:
		virtual ~IRemoteMethodCall();

		const RemoteMethod& target() const { return mTargetMethod; }

		bool isValid() const;
		const std::string qualifiedTarget() const;

		int16 magic() const { return mMagic; }
		const SignatureType signature() const;

		//////////////////////////////////////////////////////////////////////////////////
		//				Different Views on the remote method call						//
		//////////////////////////////////////////////////////////////////////////////////
		inline mora::OutputStream& outputStream() { return mOutputStream; }
		inline const mora::OutputStream& outputStream() const { return mOutputStream; }
		inline mora::InputStream& inputStream() { if (mInputStream == nullptr) throw MoraException("Invalid inputstream"); else return *mInputStream; }

		//---------	writing, e.g. sending the remote method call
		inline mora::OutputStream& parameterOutStream() { return mOutputStream; }
		inline mora::InputStream& responseInStream() { if (mInputStream == nullptr) throw MoraException("Invalid inputstream"); else return *mInputStream; }
		//--------- reading, e.g. receiving a remote method call
		inline mora::InputStream& parameterInStream() { if (mInputStream == nullptr) throw MoraException("Invalid inputstream"); else return *mInputStream; }
		inline mora::OutputStream& responseOutStream() { return mOutputStream; }


		inline mora::Communicator& communicator() { return mCommunicator; }

		virtual bool isResponse() { return false; }
		void handleResponse(InputStream* stream);
		virtual void handleResponse() = 0;

	};

} /* namespace mora */

#endif /* INCLUDE_MORAREMOTEMETHODCALL_H_ */
