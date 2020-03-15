
include(TargetArch)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

target_architecture(_INT_ARCH)
set(COMPILER_ARCHITECTURE ${_INT_ARCH} CACHE STRING "Architecture ")



macro(add_extra_compiler_option OPTION)
	#TODO: check if the flag is supported
  	set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${OPTION})
endmacro(add_extra_compiler_option OPTION)

if (CMAKE_COMPILER_IS_GNUCXX)
	option(COMPILER_StrongWarngins "enable a high level of compiler warnings" ON)
	if (COMPILER_STRONG_WARNINGS)
		add_extra_compiler_option(-Wall)
	endif (COMPILER_STRONG_WARNINGS)
	option(COMPILER_WarningsAsErrors "handle compiler warnings as error" ON)
	if (COMPILER_WarningsAsErrors)
		add_extra_compiler_option(-Werror)
	endif (COMPILER_WarningsAsErrors)
	
	#add_extra_compiler_option(-ffast-math)
	#add_extra_compiler_option(-msse)
	#add_extra_compiler_option(-msse2)
	#add_extra_compiler_option(-msse3)
endif (CMAKE_COMPILER_IS_GNUCXX)