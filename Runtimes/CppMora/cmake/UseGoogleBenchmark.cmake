include(UseCoverage)


macro(add_benchmark TestName TestFile)
	cmake_parse_arguments(
		""
		""
		""
		"DEPENDS;INCLUDE_DIRECTORIES"
		${ARGN}
	)
	set(realBenchName BENCHMARK_${PROJECT_NAME}_${TestName})
	message(STATUS "Add Benchmark ${realBenchName} to ${PROJECT_NAME}")
	add_executable(${realBenchName} ${TestFile})
	target_link_libraries(${realBenchName} PRIVATE benchmark::benchmark ${PROJECT_NAME})
	target_compile_definitions(${realBenchName} PUBLIC -DBENCHMARK)
	
	target_include_directories(${realBenchName} 
		PUBLIC 
			${DOCTEST_INCLUDE_DIR}#DOCTEST_INCLUDE_DIR has been set by calling AddDoctest.cmake
	)
	
	set_target_properties(${realBenchName} PROPERTIES FOLDER "Benchmarks")
	
	if (_DEPENDS)
		target_link_libraries(${realBenchName} PRIVATE ${_DEPENDS})
	endif(_DEPENDS)
	if (_INCLUDE_DIRECTORIES)
		target_include_directories(${realBenchName} PRIVATE {_INCLUDE_DIRECTORIES})
	endif (_INCLUDE_DIRECTORIES)	
endmacro(add_benchmark TestName TestFile)

#
# scans a given directory (${TestDirectory}) for cpp files that 
# do match the pattern: 
#		test_*.cpp
# and calls macro: 
#		add_doctest(${file-name} ${file})
macro(scan_benchmarks BenchDirectory)
	message(STATUS "Scan Directory ${CMAKE_CURRENT_SOURCE_DIR}/${BenchDirectory} for benchmarks (benchmark_*.cpp) of target ${PROJECT_NAME}")
	file(GLOB benchFiles ${BenchDirectory}/benchmark_*.cpp)
	message(STATUS "Found: [${benchFiles}]")
	if (benchFiles)
		foreach(it ${benchFiles})
			get_filename_component(fn ${it} NAME)
			string(REGEX REPLACE "benchmark_" "" fnwe ${fn})
			string(REGEX REPLACE ".cpp" "" fn ${fnwe})
			add_benchmark(${fn} ${it} ${ARGN})
		endforeach()
	endif()
endmacro(scan_benchmarks BenchDirectory)