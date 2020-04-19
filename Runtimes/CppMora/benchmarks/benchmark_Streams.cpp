#include <benchmark/benchmark.h>


#include <Mora.h>
#include <MoraStreams.h>
#include <TestUtils.h>
#include "LogDef.h"


template<typename TYPE>
bool testType() {
	std::vector<TYPE> write = ::tests::create<TYPE>(20, 100);
	mora::OutputStream os{};
	os << write;
	
	mora::InputStream is{ os.length() };
	is.append(os.bytes(), 0, 0, os.length());
	std::vector<TYPE> read;
	is >> read;

	
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


static void BM_Stream_bool(benchmark::State& state) {
	for (auto _ : state)
		testType<bool>();
}
BENCHMARK(BM_Stream_bool);

static void BM_Stream_int8(benchmark::State& state) {
	for (auto _ : state)
		testType<mora::int8>();
}
BENCHMARK(BM_Stream_int8);

static void BM_Stream_int16(benchmark::State& state) {
	for (auto _ : state)
		testType<mora::int16>();
}
BENCHMARK(BM_Stream_int16);


static void BM_Stream_int32(benchmark::State& state) {
	for (auto _ : state)
		testType<mora::int32>();
}
BENCHMARK(BM_Stream_int32);

static void BM_Stream_int64(benchmark::State& state) {
	for (auto _ : state)
		testType<mora::int64>();
}
BENCHMARK(BM_Stream_int64);


static void BM_Stream_float(benchmark::State& state) {
	for (auto _ : state)
		testType<float>();
}
BENCHMARK(BM_Stream_float);

static void BM_Stream_double(benchmark::State& state) {
	for (auto _ : state)
		testType<double>();
}
BENCHMARK(BM_Stream_double);


BENCHMARK_MAIN();