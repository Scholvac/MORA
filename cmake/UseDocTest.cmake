


macro(add_doctest TestName TestFile)
	SET(realTestName TEST_${PROJECT_NAME}_${TestName})
	
	ADD_EXECUTABLE(${realTestName} ${TestFile})
	TARGET_INCLUDE_DIRECTORIES(${realTestName} PUBLIC ${DOCTEST_INCLUDE_DIR}) #DOCTEST_INCLUDE_DIR has been set by calling AddDoctest.cmake
	TARGET_LINK_LIBRARIES(${realTestName} PUBLIC ${CMAKE_PROJECT_NAME})
	TARGET_COMPILE_DEFINITIONS(${realTestName} PUBLIC -DDOCTEST)
	if(ENABLE_SOLUTION_FOLDERS)
		SET_TARGET_PROPERTIES(${realTestName} PROPERTIES FOLDER "TESTS//${CMAKE_PROJECT_NAME}")
	endif(ENABLE_SOLUTION_FOLDERS)
	ADD_TEST(${realTestName} "${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/${realTestName}")
endmacro(add_doctest TestName TestFile)

#
# scans a given directory (${TestDirectory}) for cpp files that 
# do match the pattern: 
#		test_*.cpp
# and calls macro: 
#		add_doctest(${file-name} ${file})
macro(scan_tests TestDirectory)
	message(STATUS "Scan Directory ${TestDirectory} for tests (test_*.cpp)")
	FILE(GLOB testFiles ${TestDirectory}/test_*.cpp)
	message("Found: [${testFiles}]")
	if (testFiles)
		message("Foreach")
		foreach(it "${testFiles}")
			message("IT = ${it}")
			GET_FILENAME_COMPONENT(fn ${it} NAME)
			string(REGEX REPLACE "test_" "" fnwe ${fn})
			string(REGEX REPLACE ".cpp" "" fn ${fnwe})
			add_doctest(${fn} ${it})
		endforeach()
	endif()
endmacro(scan_tests TestDirectory)