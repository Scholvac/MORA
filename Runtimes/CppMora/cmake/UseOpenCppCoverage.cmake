
find_program(OPEN_CPP_COVERAGE_EXE OpenCppCoverage.exe
	HINTS "C:\\Program Files\\OpenCppCoverage" 		#default installation 
)
if (NOT OPEN_CPP_COVERAGE_EXE)
	message(FATAL "Missing OpenCppCoverage please provide path to exe (if not yet installed download here: https://github.com/OpenCppCoverage/OpenCppCoverage ")
else()
	set(COVERAGE_FILE_LIST "" CACHE INTERNAL "")
	set(COVERAGE_TARGET_LIST "" CACHE INTERNAL "")
endif()


macro(add_coverage )
  cmake_parse_arguments(
		COV
		""
		"TARGET;POSTFIX"
		"SOURCES"
		${ARGN}
	)
	if (COV_POSTFIX STREQUAL "")
		set(COV_POSTFIX "_cov")
	endif()
	string(REPLACE "/" "\\" COV_SOURCES ${COV_SOURCES})
	set(COV_FILE ${CMAKE_BINARY_DIR}/Coverage/${COV_TARGET}.cov)
	string(REPLACE "/" "\\" COV_FILE ${COV_FILE})
	
	add_custom_target(${COV_TARGET}_${COV_POSTFIX}
		COMMAND ${OPEN_CPP_COVERAGE_EXE} --export_type=binary:${COV_FILE} --sources ${COV_SOURCES} -- $<TARGET_FILE:${COV_TARGET}>
		COMMENT "COMMAND ${OPEN_CPP_COVERAGE_EXE} --export_type=binary:${COV_FILE} --sources ${COV_SOURCES} -- $<TARGET_FILE:${COV_TARGET}>"
		DEPENDS ${COV_TARGET}
	)
	list(APPEND COVERAGE_FILE_LIST "${COV_FILE}")
	list(APPEND COVERAGE_TARGET_LIST "${COV_TARGET}_${COV_POSTFIX}")
	
	set_target_properties(${COV_TARGET}_${COV_POSTFIX} PROPERTIES FOLDER "Coverage")
#	set_target_properties(${COV_TARGET}_${COV_POSTFIX} PROPERTIES EXCLUDE_FROM_ALL TRUE)
	set_target_properties(${COV_TARGET}_${COV_POSTFIX} PROPERTIES EXCLUDE_FROM_DEFAULT_BUILD TRUE)
endmacro(add_coverage )


macro(add_coverage_report_target )
	set(ALL_COV_INPUTS "")
	foreach(cf ${COVERAGE_FILE_LIST})
		string(REPLACE "/" "\\" ${cf} ${cf})
  		list(APPEND ALL_COV_INPUTS --input_coverage ${cf})
	endforeach(cf ${COVERAGE_FILE_LIST})
	
  	add_custom_target(RUN_COVERAGE 
		COMMAND ${OPEN_CPP_COVERAGE_EXE} ${ALL_COV_INPUTS}
		COMMENT "COMMAND ${OPEN_CPP_COVERAGE_EXE} ${ALL_COV_INPUTS}"
		DEPENDS ${COVERAGE_TARGET_LIST}  
  	)
#  	set_target_properties(RUN_COVERAGE PROPERTIES EXCLUDE_FROM_ALL TRUE)
  	set_target_properties(RUN_COVERAGE PROPERTIES EXCLUDE_FROM_DEFAULT_BUILD TRUE)
  	
endmacro(add_coverage_report_target )