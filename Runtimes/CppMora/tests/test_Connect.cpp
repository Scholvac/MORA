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

TEST_CASE("Connection") 
{
	LOG_INFO("Connection");
	mora::Options serverOpt; serverOpt.port = 9542; serverOpt.timeout = mora::duration{ 1000 * 60 * 10 };
	mora::Communicator serverCom(serverOpt);

	mora::Options clientOpt; clientOpt.port = 9543;
	mora::Communicator clientCom(clientOpt);

	CHECK(serverCom.start());
	CHECK(clientCom.start());


	de::sos::mora::examples::IEchoManagerPtr mgrImpl{ new TestSerializerService{} };
	auto adapter = de::sos::mora::examples::EchoManagerAdapter::createAdapter(mgrImpl, "myEchoManager", serverCom);

	de::sos::mora::examples::IEchoManagerPtr proxy = de::sos::mora::examples::EchoManagerProxy::createProxy("tcp://127.0.0.1:9542/myEchoManager", clientCom);

	for (int i = 0; i < 10; i++){
		CHECK(proxy->echo(true));
		CHECK_FALSE(proxy->echo(false));
		std::this_thread::sleep_for(std::chrono::milliseconds(100));
	}

	CHECK(clientCom.stop());
	CHECK(serverCom.stop());
	//CHECK_EQ(42, proxy->echo((int)42));
	
}

TEST_CASE("Double_initialisation")
{
	LOG_INFO("Start_Double_initialisation");
	mora::Options serverOpt; serverOpt.port = 9542; 
	mora::Communicator serverCom(serverOpt);

	mora::Options clientOpt; clientOpt.port = 9543;
	mora::Communicator clientCom(clientOpt);

	CHECK(serverCom.start());
	CHECK(clientCom.start());


	de::sos::mora::examples::IEchoManagerPtr mgrImpl{ new TestSerializerService{} };
	auto adapter = de::sos::mora::examples::EchoManagerAdapter::createAdapter(mgrImpl, "myEchoManager", serverCom);

	de::sos::mora::examples::IEchoManagerPtr proxy = de::sos::mora::examples::EchoManagerProxy::createProxy("tcp://127.0.0.1:9542/myEchoManager", clientCom);

	CHECK(proxy->echo(true));
	CHECK_FALSE(proxy->echo(false));

	CHECK(clientCom.stop());
	CHECK(serverCom.stop());
	//CHECK_EQ(42, proxy->echo((int)42));

	CHECK_FALSE(de::sos::mora::examples::EchoManagerProxy::createProxy("tcp://127.0.0.1:9542/myEchoManager", clientCom));
	
	LOG_INFO("FINISH");
}
