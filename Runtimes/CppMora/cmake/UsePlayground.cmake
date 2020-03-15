include(UseCoverage)


macro(add_playground)
	cmake_parse_arguments(
		""
		""
		"NAME"
		"DEPENDS;INCLUDE_DIRECTORIES;SOURCES"
		${ARGN}
	)
	set(realPGName PLAYGROUND_${PROJECT_NAME}_${_NAME})
	message(STATUS "Add Playground ${realPGName} to ${PROJECT_NAME}")
	add_executable(${realPGName} ${_SOURCES})
	target_link_libraries(${realPGName} PRIVATE ${PROJECT_NAME})
	target_compile_definitions(${realPGName} PUBLIC -DPLAYGROUND)
	
	set_target_properties(${realPGName} PROPERTIES FOLDER "Playgrounds")
	
	if (_DEPENDS)
		target_link_libraries(${realPGName} PRIVATE ${_DEPENDS})
	endif(_DEPENDS)
	if (_INCLUDE_DIRECTORIES)
		target_include_directories(${realPGName} PRIVATE {_INCLUDE_DIRECTORIES})
	endif (_INCLUDE_DIRECTORIES)	
endmacro(add_playground )

#
# scans a given directory (${TestDirectory}) for cpp files that 
# do match the pattern: 
#		test_*.cpp
# and calls macro: 
#		add_doctest(${file-name} ${file})
macro(scan_playgrounds PlaygroundDirectory)
	message(STATUS "Scan Directory ${CMAKE_CURRENT_SOURCE_DIR}/${PlaygroundDirectory} for benchmarks (benchmark_*.cpp) of target ${PROJECT_NAME}")
	file(GLOB benchFiles ${PlaygroundDirectory}/playground_*.cpp)
	message(STATUS "Found: [${benchFiles}]")
	if (benchFiles)
		foreach(it ${benchFiles})
			get_filename_component(fn ${it} NAME)
			string(REGEX REPLACE "playground_" "" fnwe ${fn})
			string(REGEX REPLACE ".cpp" "" fn ${fnwe})
			add_playground(NAME ${fn} SOURCES ${it})
		endforeach()
	endif()
endmacro(scan_playgrounds PlaygroundDirectory)