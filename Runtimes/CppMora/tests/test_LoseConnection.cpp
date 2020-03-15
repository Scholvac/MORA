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

TEST_CASE("Connection_Lost_different_port") 
{
	mora::Options serverOpt; serverOpt.port = 9447;
	mora::Communicator serverCom(serverOpt);

	mora::Options clientOpt1; clientOpt1.port = 9448;
	mora::Communicator clientCom1(clientOpt1);

	CHECK(serverCom.start());
	CHECK(clientCom1.start());


	de::sos::mora::examples::IEchoManagerPtr mgrImpl{ new TestSerializerService{} };
	auto adapter = de::sos::mora::examples::EchoManagerAdapter::createAdapter(mgrImpl, "myEchoManager", serverCom);

	de::sos::mora::examples::IEchoManagerPtr proxy1 = de::sos::mora::examples::EchoManagerProxy::createProxy("tcp://127.0.0.1:9447/myEchoManager", clientCom1);

	CHECK(proxy1->echo(true));
	CHECK_FALSE(proxy1->echo(false));

	LOG_INFO("Going to stop");
	CHECK(clientCom1.stop());

	mora::Options clientOpt2; clientOpt2.port = 9444;
	mora::Communicator clientCom2(clientOpt2);
	CHECK(clientCom2.start());

	de::sos::mora::examples::IEchoManagerPtr proxy2 = de::sos::mora::examples::EchoManagerProxy::createProxy("tcp://127.0.0.1:9447/myEchoManager", clientCom2);
	CHECK(proxy2 != nullptr);

	CHECK(proxy2->echo(true));
	CHECK_FALSE(proxy2->echo(false));

	CHECK(clientCom2.stop());
	CHECK(serverCom.stop());
}

