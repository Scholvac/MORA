if (BUILD_TESTING)
	option(BUILD_COVERAGE "Calculate test coverage" OFF)
	if (BUILD_COVERAGE)
		if (MSVC)
			include(useOpenCppCoverage)
		else(MSVC) 
			message(STATUS "Code coverage currently only supported for MSVC/nmake")
		endif(MSVC)
	endif()
endif(BUILD_TESTING)




if (NOT MSVC)
	macro(add_coverage_report_target )
		if (BUILD_COVERAGE)
			message("Code coverage only supported for MSVC/nmake compiler")
		endif(BUILD_COVERAGE)
	endmacro(add_coverage_report_target )
endif(NOT MSVC)