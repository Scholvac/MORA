/*
 * test_StartStop.cpp
 *
 *  Created on: 16.01.2020
 *      Author: sschweigert
 */


#define DOCTEST_CONFIG_IMPLEMENT_WITH_MAIN
#include "doctest/doctest.h"

#include <Mora.h>

#include <chrono>
#include <thread>

#include <TestSerializerService.h>

using namespace de::sos::mora::examples;

TEST_CASE("Start_Stop"
			*	doctest::timeout(5)
		) 
{
	using namespace mora;
	Options opt;
	Communicator com{ opt };

	CHECK(com.start());
	std::this_thread::sleep_for(std::chrono::milliseconds(200));
	CHECK(com.stop());	
}


TEST_CASE("Start_Already_Running_Communicator"
		*	doctest::timeout(5)
		)
{
	using namespace mora;
	Options opt;
	Communicator com{ opt };

	CHECK(com.start());
	std::this_thread::sleep_for(std::chrono::milliseconds(200));
	CHECK_FALSE(com.start());

	CHECK(com.stop());
}

//TEST_CASE("Double_initialisation"
//		*	doctest::timeout(5)
//		)
//{
//	using namespace mora;
//	Options opt;
//	opt.port = 9283;
//	Communicator com1{ opt };
//	Communicator com2{ opt };
//
//	CHECK(com1.start());
//	std::this_thread::sleep_for(std::chrono::milliseconds(200));
//	CHECK_FALSE(com2.start());
//
//	CHECK(com1.stop());
//	CHECK(com2.start());
//
//	CHECK(com1.stop());
//	CHECK(com2.stop());
//}


TEST_CASE("Not_Started_Proxy_Creation"
		*	doctest::timeout(5)
		)
{
	using namespace mora;
	Options opt;
	opt.timeout = mora::duration{ 200 }; //ms
	Communicator com{ opt };

	CHECK_FALSE(com.isRunning());

	std::this_thread::sleep_for(std::chrono::milliseconds(200));
	IEchoManagerPtr proxy = EchoManagerProxy::createProxy("tcp://127.0.0.1:9442/myEchoManager", com);
	CHECK(!proxy);

	CHECK(com.start());
	//CHECK_THROWS(proxy = EchoManagerProxy::createProxy(&com, "tcp://127.0.0.1:9442/myEchoManager")); //no server available
	//CHECK(proxy != nullptr);

	CHECK(com.stop());
}
