/*
 * TestSerializerService.h
 *
 *  Created on: 16.02.2020
 *      Author: sschweigert
 */

#ifndef BASE_MORA_TESTS_SER_TESTSERIALIZERSERVICE_H_
#define BASE_MORA_TESTS_SER_TESTSERIALIZERSERVICE_H_

#include <stdlib.h>
#include <limits>
#include "loguru.h"


//#define LOG_DEBUG(...) VLOG_F(1 , __VA_ARGS__)
//
//#define LOG_INFO(...) LOG_F(INFO, __VA_ARGS__)
//
//#define LOG_WARN(...) LOG_F(WARNING, __VA_ARGS__)
//
//#define LOG_ERROR(...) LOG_F(ERROR, __VA_ARGS__)

#include <serialisation.h>
#include "TestUtils.h"

using namespace mora;



namespace tests {

	template<> inline de::sos::mora::examples::MyEnum create() {
		return de::sos::mora::examples::MyEnumUtil::valueOf(iRand<int8>(0, 1));
	}
	template<> inline de::sos::mora::examples::SimpleStruct* create() {
		de::sos::mora::examples::SimpleStruct* ss = new de::sos::mora::examples::SimpleStruct();
		ss->boolValue = create<bool>();
		ss->byteValue = create<int8>();
		ss->shortValue = create<int16>();
		ss->intValue = create<int32>();
		ss->longValue = create<int64>();
		ss->floatValue = create<float>();
		ss->doubleValue = create<double>();
		ss->stringValue = create<std::string>();
		if (create<bool>())
			ss->structValue = create<de::sos::mora::examples::SimpleStruct*>();
		return ss;
	}

	template<> inline de::sos::mora::examples::ListStruct* create() {
		de::sos::mora::examples::ListStruct* ls = new de::sos::mora::examples::ListStruct();
		ls->boolListValue = create<bool>(0, 100);
		ls->byteListValue = create<int8>(0, 100);
		ls->shortListValue = create<int16>(0, 100);
		ls->intListValue = create<int32>(0, 100);
		ls->longListValue = create<int64>(0, 100);
		ls->floatListValue = create<float>(0, 100);
		ls->doubleListValue = create<double>(0, 100);
		ls->stringListValue = create<std::string>(0, 100);
		ls->structListValue = create<de::sos::mora::examples::SimpleStruct*>(0, 100);
		return ls;
	}

}
class TestSerializerService : public de::sos::mora::examples::IEchoManager
{
private:
	de::sos::mora::examples::ICallbackPtr		mCallback;
public:
	TestSerializerService();
	virtual ~TestSerializerService();

	virtual bool echo(bool value);
	virtual ::mora::int8 echo(::mora::int8 value);
	virtual ::mora::int16 echo(::mora::int16 value);
	virtual ::mora::int32 echo(::mora::int32 value);
	virtual ::mora::int64 echo(::mora::int64 value);
	virtual float echo(float value);
	virtual double echo(double value);
	virtual std::string echo(std::string value);
	virtual de::sos::mora::examples::MyEnum echo(de::sos::mora::examples::MyEnum value);
	virtual de::sos::mora::examples::SimpleStruct* echo(de::sos::mora::examples::SimpleStruct* value);
	virtual de::sos::mora::examples::ListStruct* echo(de::sos::mora::examples::ListStruct* value);
	virtual std::vector<bool> echo1(std::vector<bool> value);
	virtual std::vector<::mora::int8> echo2(std::vector<::mora::int8> value);
	virtual std::vector<::mora::int16> echo3(std::vector<::mora::int16> value);
	virtual std::vector<::mora::int32> echo4(std::vector<::mora::int32> value);
	virtual std::vector<::mora::int64> echo5(std::vector<::mora::int64> value);
	virtual std::vector<float> echo6(std::vector<float> value);
	virtual std::vector<double> echo7(std::vector<double> value);
	virtual std::vector<std::string> echo8(std::vector<std::string> value);
	virtual std::vector<de::sos::mora::examples::MyEnum> echo9(std::vector<de::sos::mora::examples::MyEnum> value);
	virtual std::vector<de::sos::mora::examples::SimpleStruct*> echo10(std::vector<de::sos::mora::examples::SimpleStruct*> value);
	virtual std::vector<de::sos::mora::examples::ListStruct*> echo11(std::vector<de::sos::mora::examples::ListStruct*> value);
	virtual void setCallback(de::sos::mora::examples::ICallbackPtr cb, float firstValue);
	virtual de::sos::mora::examples::ICallbackPtr getCallback();
	virtual void throwUnknownException();
};



#endif /* BASE_MORA_TESTS_SER_TESTSERIALIZERSERVICE_H_ */
