/*
 * TestSerializerService.cpp
 *
 *  Created on: 16.02.2020
 *      Author: sschweigert
 */

#include "TestSerializerService.h"

using namespace mora;
using namespace de::sos::mora::examples;

TestSerializerService::TestSerializerService() {
	// TODO Auto-generated constructor stub

}

TestSerializerService::~TestSerializerService() {
	// TODO Auto-generated destructor stub
}


bool TestSerializerService::echo(bool value) { return value; }
::mora::int8 TestSerializerService::echo(::mora::int8 value){ return value; }
::mora::int16 TestSerializerService::echo(::mora::int16 value){ return value; }
::mora::int32 TestSerializerService::echo(::mora::int32 value){ return value; }
::mora::int64 TestSerializerService::echo(::mora::int64 value){ return value; }
float TestSerializerService::echo(float value){ return value; }
double TestSerializerService::echo(double value){ return value; }
std::string TestSerializerService::echo(std::string value){ return value; }
MyEnum TestSerializerService::echo(MyEnum value){ return value; }
SimpleStruct* TestSerializerService::echo(SimpleStruct* value){ return value; }
ListStruct* TestSerializerService::echo(ListStruct* value){ return value; }
std::vector<bool> TestSerializerService::echo1(std::vector<bool> value){ return value; }
std::vector<::mora::int8> TestSerializerService::echo2(std::vector<::mora::int8> value){ return value; }
std::vector<::mora::int16> TestSerializerService::echo3(std::vector<::mora::int16> value){ return value; }
std::vector<::mora::int32> TestSerializerService::echo4(std::vector<::mora::int32> value){
	return value;
}
std::vector<::mora::int64> TestSerializerService::echo5(std::vector<::mora::int64> value){ return value; }
std::vector<float> TestSerializerService::echo6(std::vector<float> value){ return value; }
std::vector<double> TestSerializerService::echo7(std::vector<double> value){ return value; }
std::vector<std::string> TestSerializerService::echo8(std::vector<std::string> value){
	return value;
}
std::vector<MyEnum> TestSerializerService::echo9(std::vector<MyEnum> value){ return value; }
std::vector<SimpleStruct*> TestSerializerService::echo10(std::vector<SimpleStruct*> value){ return value; }
std::vector<ListStruct*> TestSerializerService::echo11(std::vector<ListStruct*> value){ return value; }


void TestSerializerService::setCallback(ICallbackPtr cb, float firstValue){
	mCallback = cb;
}
ICallbackPtr TestSerializerService::getCallback(){ return mCallback; }
void TestSerializerService::throwUnknownException(){ throw std::runtime_error("Some Exception"); }
