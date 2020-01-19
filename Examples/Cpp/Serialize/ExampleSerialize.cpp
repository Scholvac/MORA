/*
 * Serialize.cpp
 *
 *  Created on: 18.01.2020
 *      Author: sschweigert
 */


#include <loguru.hpp>
#include <thread>
#include <chrono>

#include <Mora.h>

#include <serialisation.h>

using namespace de::sos::mora::examples;
using namespace mora;

class MyEchoManager : public IEchoManager {
public:
	virtual bool echo(bool value) {
		return value;
	}
	virtual ::mora::int8 echo(::mora::int8 value) {
		return value;
	}
	virtual ::mora::int16 echo(::mora::int16 value) {
		return value;
	}
	virtual ::mora::int32 echo(::mora::int32 value) {
		return value;
	}
	virtual ::mora::int64 echo(::mora::int64 value) {
		return value;
	}
	virtual float echo(float value) {
		return value;
	}
	virtual double echo(double value) {
		return value;
	}
	virtual std::string echo(std::string value) {
		return value;
	}
	virtual MyEnum echo(MyEnum value) {
		return value;
	}
	virtual SimpleStruct* echo(SimpleStruct* value) {
		return value;
	}
	virtual ListStruct* echo(ListStruct* value) {
		return value;
	}
	virtual std::vector<bool> echo1(std::vector<bool> value) {
		return value;
	}
	virtual std::vector<::mora::int8> echo2(std::vector<::mora::int8> value) {
		return value;
	}
	virtual std::vector<::mora::int16> echo3(std::vector<::mora::int16> value) {
		return value;
	}
	virtual std::vector<::mora::int32> echo4(std::vector<::mora::int32> value) {
		return value;
	}
	virtual std::vector<::mora::int64> echo5(std::vector<::mora::int64> value) {
		return value;
	}
	virtual std::vector<float> echo6(std::vector<float> value) {
		return value;
	}
	virtual std::vector<double> echo7(std::vector<double> value) {
		return value;
	}
	virtual std::vector<std::string> echo8(std::vector<std::string> value) {
		return value;
	}
	virtual std::vector<MyEnum> echo9(std::vector<MyEnum> value) {
		return value;
	}
	virtual std::vector<SimpleStruct*> echo10(std::vector<SimpleStruct*> value) {
		return value;
	}
	virtual std::vector<ListStruct*> echo11(std::vector<ListStruct*> value) {
		return value;
	}
	ICallbackPtr mCallback;
	virtual void setCallback(ICallbackPtr cb, float firstValue) {
		mCallback = cb;
		cb->onEcho(firstValue);
	}
	virtual ICallbackPtr getCallback() {
		return mCallback;
	}
	virtual void throwUnknownException() {
		throw "The Unknown Exception is a const char*";
	}
};
using namespace mora;
int main(int argc, char* argv[])
{
	loguru::init(argc, argv);
	LOG_F(INFO, "Hello from main.cpp!asdfasdfasdffadsf");

	Options opt(Protocol::TCP, 9242, "localhost");
	Communicator com(opt);
	com.start();

	IEchoManagerPtr myEcho(new MyEchoManager());
	EchoManagerAdapter::createAdapter(&com, myEcho, "myEcho");

	std::this_thread::sleep_for(std::chrono::seconds(600));

	LOG_F(INFO, "main function about to end!");


	return 0;
}