/*
 * test_StartStop.cpp
 *
 *  Created on: 16.01.2020
 *      Author: sschweigert
 */


#define DOCTEST_CONFIG_IMPLEMENT_WITH_MAIN
#include "doctest/doctest.h"

#include <Mora.h>
#include <MoraStreams.h>

#include <chrono>
#include <thread>

#include <TestUtils.h>


template<typename TYPE>
bool testType() {
	std::vector<TYPE> write = ::tests::create<TYPE>(20, 100);
	mora::OutputStream os{};
	os << write;
	REQUIRE(os.length() > 0);
	mora::InputStream is{ os.length()};
	is.append(os.bytes(), 0, 0, os.length());
	std::vector<TYPE> read;
	is >> read;

	REQUIRE(read.size() == write.size());
	for (int i = 0; i < read.size(); i++) {
		if (read[i] != write[i]) {
			LOG_ERROR("Failed at index: %i", i);
			return false;
		}
	}
	return true;
}

template<typename T>
bool testType(int runs) {
	bool res = true;
	for (int i = 0; i < runs; i++) {
		res &= testType<T>();
	} 
	return res;
}


TEST_CASE("Strings") {
	const std::string str{"74Vxr0cp8YpfI7jbUdyRBzkawidiJqlQt"};
	mora::OutputStream os{};
	os << str;
	REQUIRE(os.length() > 0);
	mora::InputStream is{ os.length()};
	is.append(os.bytes(), 0, 0, os.length());
	std::string read;
	is >> read;

	REQUIRE(read == str);
}
TEST_CASE("StringVector") {
	const std::string str{"74Vxr0cp8YpfI7jbUdyRBzkawidiJqlQt"};
	std::vector<std::string> vec;
	vec.push_back(str);
	mora::OutputStream os{};
	os << vec;
	REQUIRE(os.length() > 0);

	mora::InputStream is{ os.length()};
	is.append(os.bytes(), 0, 0, os.length());
	std::vector<std::string> read;
	is >> read;

	REQUIRE(read.size() == vec.size());
	REQUIRE(read[0] == vec[0]);
}
TEST_CASE("ListSerialisation") 
{
	using namespace mora;

	CHECK(testType<bool>(100));
	CHECK(testType<int8>(100));
	CHECK(testType<int16>(100));
	CHECK(testType<int32>(100));
	CHECK(testType<int64>(100));
	CHECK(testType<float>(100));
	CHECK(testType<double>(100));
	CHECK(testType<std::string>(100));
	
}
