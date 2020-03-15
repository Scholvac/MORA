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
using namespace tests;



TEST_CASE("Call Echo Methods") 
{
	
	std::srand(123);

	mora::Options serverOpt; 
	mora::Options clientOpt;

	serverOpt.port = 9449;	
	clientOpt.port = 9450;

	SUBCASE("TCP") {
		serverOpt.protocol = mora::Protocol::TCP;
		clientOpt.protocol = mora::Protocol::TCP;
	}
	/*SUBCASE("UDP") {
		serverOpt.protocol = mora::Protocol::UDP;
		clientOpt.protocol = mora::Protocol::UDP;
	}*/

	mora::Communicator serverCom(serverOpt);
	mora::Communicator clientCom(clientOpt);


	CHECK(serverCom.start());
	CHECK(clientCom.start());


	de::sos::mora::examples::IEchoManagerPtr mgrImpl{ new TestSerializerService{} };
	auto adapter = de::sos::mora::examples::EchoManagerAdapter::createAdapter(mgrImpl, "myEchoManager", serverCom);


	de::sos::mora::examples::IEchoManagerPtr proxy = de::sos::mora::examples::EchoManagerProxy::createProxy("tcp://127.0.0.1:9449/myEchoManager", clientCom);

	CHECK(createSendAndCompareLists<std::string>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo8));

//	CHECK_EQ(proxy->echo(true), true);
//	CHECK_EQ(proxy->echo(false), false);
//	CHECK_EQ(proxy->echo((::mora::int8)42), (::mora::int8)42);
//	CHECK_EQ(proxy->echo((::mora::int16)42), (::mora::int16)42);
//	CHECK_EQ(proxy->echo((::mora::int32)42), (::mora::int32)42);
//	CHECK_EQ(proxy->echo((::mora::int64)42), (::mora::int64)42);
//	CHECK_EQ(proxy->echo(42.0f), 42.0f);
//	CHECK_EQ(proxy->echo(42.0), 42.0);
//	CHECK_EQ(proxy->echo(std::string{ "Hello World" }), std::string{ "Hello World" });
//	de::sos::mora::examples::SimpleStruct* ss = create<de::sos::mora::examples::SimpleStruct*>();
//	CHECK_EQ(*proxy->echo(ss), *ss);
//	delete ss;
//	auto* ls = create<de::sos::mora::examples::ListStruct*>();
//	CHECK_EQ(*proxy->echo(ls), *ls);
//	delete ls;
	
	CHECK(createSendAndCompareLists<bool>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo1));
	CHECK(createSendAndCompareLists<int8>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo2));
	CHECK(createSendAndCompareLists<int16>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo3));
	CHECK(createSendAndCompareLists<int32>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo4));
	CHECK(createSendAndCompareLists<int64>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo5));
	CHECK(createSendAndCompareLists<float>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo6));
	CHECK(createSendAndCompareLists<double>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo7));

	CHECK(createSendAndCompareLists<de::sos::mora::examples::MyEnum>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo9));
	CHECK(createSendAndCompareListsPtr<de::sos::mora::examples::SimpleStruct*>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo10));
	CHECK(createSendAndCompareListsPtr<de::sos::mora::examples::ListStruct*>(proxy.get(), &de::sos::mora::examples::IEchoManager::echo11));


	

	LOG_INFO("Going to stop");
	CHECK(clientCom.stop());
	CHECK(serverCom.stop());
}

class MyCallback : public de::sos::mora::examples::ICallback {
public:
	virtual void onEcho(float value) {
		CHECK_EQ(value, 42.0f);
	}
};

TEST_CASE("Proxy")
{
	mora::Options serverOpt;
	mora::Options clientOpt;

	serverOpt.port = 9449;
	clientOpt.port = 9450;

	SUBCASE("TCP") {
		serverOpt.protocol = mora::Protocol::TCP;
		clientOpt.protocol = mora::Protocol::TCP;
	}
	/*SUBCASE("UDP") {
		serverOpt.protocol = mora::Protocol::UDP;
		clientOpt.protocol = mora::Protocol::UDP;
	}*/

	mora::Communicator serverCom(serverOpt);
	mora::Communicator clientCom(clientOpt);


	CHECK(serverCom.start());
	CHECK(clientCom.start());


	de::sos::mora::examples::IEchoManagerPtr mgrImpl{ new TestSerializerService{} };
	auto adapter = de::sos::mora::examples::EchoManagerAdapter::createAdapter(mgrImpl, "myEchoManager", serverCom);

	de::sos::mora::examples::IEchoManagerPtr proxy = de::sos::mora::examples::EchoManagerProxy::createProxy("tcp://127.0.0.1:9449/myEchoManager", clientCom);

	de::sos::mora::examples::ICallbackPtr myCallback{ new MyCallback() };
	auto cbAdapter = de::sos::mora::examples::CallbackAdapter::createAdapter(myCallback, "foo", clientCom);
	proxy->setCallback(myCallback, 42.f);

	auto returnCallback = proxy->getCallback();
	CHECK_EQ(returnCallback, myCallback);

}
