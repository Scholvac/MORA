include(UseCoverage)


macro(add_doctest TestName TestFile)
	cmake_parse_arguments(
		""
		""
		""
		"DEPENDS;INCLUDE_DIRECTORIES"
		${ARGN}
	)
	set(realTestName TEST_${PROJECT_NAME}_${TestName})
	message(STATUS "Add Test ${realTestName} to ${PROJECT_NAME}")
	add_executable(${realTestName} ${TestFile})
	target_link_libraries(${realTestName} PRIVATE doctest::doctest ${PROJECT_NAME})
	target_compile_definitions(${realTestName} PUBLIC -DDOCTEST)
	
	target_include_directories(${realTestName} 
		PUBLIC 
			${DOCTEST_INCLUDE_DIR}#DOCTEST_INCLUDE_DIR has been set by calling AddDoctest.cmake
	)
	
	set_target_properties(${realTestName} PROPERTIES FOLDER "Tests")
	
	if (_DEPENDS)
		target_link_libraries(${realTestName} PRIVATE ${_DEPENDS})
	endif(_DEPENDS)
	if (_INCLUDE_DIRECTORIES)
		target_include_directories(${realTestName} PRIVATE {_INCLUDE_DIRECTORIES})
	endif (_INCLUDE_DIRECTORIES)
	
	
	add_test(${realTestName} "${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/${realTestName}")
	if (BUILD_COVERAGE)
		add_coverage(TARGET ${realTestName} 
			SOURCES ${PROJECT_SOURCE_DIR}
			POSTFIX _cov
		)		
	endif (BUILD_COVERAGE)
endmacro(add_doctest TestName TestFile)

#
# scans a given directory (${TestDirectory}) for cpp files that 
# do match the pattern: 
#		test_*.cpp
# and calls macro: 
#		add_doctest(${file-name} ${file})
macro(scan_tests TestDirectory)
	message(STATUS "Scan Directory ${CMAKE_CURRENT_SOURCE_DIR}/${TestDirectory} for tests (test_*.cpp) of target ${PROJECT_NAME}")
	file(GLOB testFiles ${TestDirectory}/test_*.cpp)
	message(STATUS "Found: [${testFiles}]")
	if (testFiles)
		foreach(it ${testFiles})
			get_filename_component(fn ${it} NAME)
			string(REGEX REPLACE "test_" "" fnwe ${fn})
			string(REGEX REPLACE ".cpp" "" fn ${fnwe})
			add_doctest(${fn} ${it} ${ARGN})
		endforeach()
	endif()
endmacro(scan_tests TestDirectory)