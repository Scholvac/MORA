find_package(Git REQUIRED)

set(THIRDPARTY_BASE_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/../Thirdparty" CACHE PATH "Base directory to download build and install third party libraries. Change, if you want to reuse the libraries in other projects.")
macro(FindOrInstall )
	cmake_parse_arguments(
		FOI
		""
		"NAME;GIT_REPOSITORY;GIT_TAG;REL_CMAKE_PATH"
		"COMPONENTS;CMAKE_ARGS"
		${ARGN}
	)
	set(BASE_DIR ${THIRDPARTY_BASE_DIRECTORY}/${FOI_NAME})
	set(SRC_DIR ${BASE_DIR}/source)
	set(BIN_DIR ${BASE_DIR}/build)
  	set(INS_DIR ${BASE_DIR}/install)
	message(STATUS "Find or install library \"${FOI_NAME}\"")
	set(${FOI_NAME}_DIR ${INS_DIR}/${FOI_REL_CMAKE_PATH} CACHE PATH "" )
	if (NOT FOI_COMPONENTS)
		find_package(${FOI_NAME})
	else()
		find_package(${FOI_NAME} COMPONENTS ${FOI_COMPONENTS})
	endif()
	if (NOT ${FOI_NAME}_FOUND)
		message(STATUS "Did not found ${FOI_NAME}, download and install")
		ExternalDownloadAndInstall(
			NAME ${FOI_NAME} 
			GIT_REPOSITORY ${FOI_GIT_REPOSITORY} 
			GIT_TAG ${FOI_GIT_TAG} 
			CMAKE_ARGS ${FOI_CMAKE_ARGS}
			SOURCE_DIR ${SRC_DIR}
			BUILD_DIR ${BIN_DIR}
			INSTALL_DIR ${INS_DIR}	
		)		
		set(${FOI_NAME}_DIR ${INS_DIR}/${FOI_REL_CMAKE_PATH} CACHE PATH "" FORCE)
		if (NOT FOI_COMPONENTS)
			find_package(${FOI_NAME})
		else()
			find_package(${FOI_NAME} COMPONENTS ${FOI_COMPONENTS})
		endif()
		if (NOT  ${FOI_NAME}_FOUND)
			message(FATAL "Could not resolve library ${LIBNAME} after install - something must have gone wrong")
		endif()
	else() 
		message("FOUND ${FOI_NAME}")
	endif (NOT  ${FOI_NAME}_FOUND)

endmacro(FindOrInstall )



macro(ExternalDownloadAndInstall )
	cmake_parse_arguments(
		DAI
		""
		"NAME;GIT_REPOSITORY;GIT_TAG;SOURCE_DIR;BUILD_DIR;INSTALL_DIR"
		"COMPONENTS;CMAKE_ARGS"
		${ARGN}
	)
	set(SRC_DIR ${DAI_SOURCE_DIR})
  	set(BIN_DIR ${DAI_BUILD_DIR})
  	set(INS_DIR ${DAI_INSTALL_DIR})
	
	message(STATUS "	Source path : ${DAI_SOURCE_DIR}")
	message(STATUS "	Build path  : ${DAI_BUILD_DIR}")
	message(STATUS "	Install path: ${INS_DIR}")
  	
  	####################	CLONE Repository 	#######################
  	if (NOT EXISTS ${DAI_SOURCE_DIR})
  		execute_process( COMMAND "${CMAKE_COMMAND}" -E make_directory ${DAI_SOURCE_DIR} )
  		message(STATUS "clone ${DAI_NAME} (Version: ${DAI_GIT_TAG}) from Repository: ${DAI_GIT_REPOSITORY} to: ${DAI_SOURCE_DIR}")
  		message("${GIT_EXECUTABLE} clone --recursive ${DAI_GIT_REPOSITORY} ${DAI_SOURCE_DIR}")
  		execute_process(
            WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
            COMMAND ${GIT_EXECUTABLE} clone --recursive ${DAI_GIT_REPOSITORY} ${DAI_SOURCE_DIR}
            )
        # switch to target TAG and update submodules
        execute_process(
            WORKING_DIRECTORY ${DAI_SOURCE_DIR}
            COMMAND ${GIT_EXECUTABLE} reset --hard origin/${DAI_GIT_TAG}
            COMMAND ${GIT_EXECUTABLE} submodule update --init --force --recursive --remote --merge
            )
  	else()
  		message(STATUS "${DAI_NAME} has been checkedout before")
  	endif()
  	
  	####################	Configure, Build and Install	#######################
  	#create build dir
  	execute_process( COMMAND "${CMAKE_COMMAND}" -E make_directory ${DAI_BUILD_DIR} )
  	#configure
  	if (MSVC) 
	  	execute_process(
			COMMAND "${CMAKE_COMMAND}" -G "${CMAKE_GENERATOR}" -DCMAKE_INSTALL_PREFIX:PATH=${DAI_INSTALL_DIR} ${DAI_CMAKE_ARGS} ../source/
			WORKING_DIRECTORY "${DAI_BUILD_DIR}"
		)	
  	else(MSVC)
	  	execute_process(
			COMMAND "${CMAKE_COMMAND}" -G "${CMAKE_GENERATOR}" -DCMAKE_CONFIGURATION_TYPES:STRING=${BUILD_TYPE} -DCMAKE_INSTALL_PREFIX:PATH=${DAI_INSTALL_DIR} ${DAI_CMAKE_ARGS} ../source/
			WORKING_DIRECTORY "${DAI_BUILD_DIR}"
		)	
  	endif(MSVC)
  		
	if (MSVC)
		execute_process(
			COMMAND "${CMAKE_COMMAND}" --build "." -j8 --target install --config Debug
		    WORKING_DIRECTORY "${DAI_BUILD_DIR}/"
		)
		execute_process(
			COMMAND "${CMAKE_COMMAND}" --build "." -j8 --target install --config Release
		    WORKING_DIRECTORY "${DAI_BUILD_DIR}/"
		)		
	else(MSVC)
		execute_process(
			COMMAND "${CMAKE_COMMAND}" --build "." --target install
		    WORKING_DIRECTORY "${DAI_BUILD_DIR}/"
		)
	endif(MSVC)
  	
endmacro(ExternalDownloadAndInstall )

#------------------------------------------------------------------------------
# This command will clone git repo during cmake setup phase, also adds 
# ${LIBNAME}_update target into general update target.
# Example usage:
#
#   ExternalDownloadNowGit(cpr https://github.com/finkandreas/cpr.git origin/master)
#   add_subdirectory(${cpr_SOURCE_DIR})
#

macro(ExternalDownloadNowGit LIBNAME REPOSITORY GIT_TAG)

    set(${LIBNAME}_SOURCE_DIR ${CMAKE_CURRENT_BINARY_DIR}/Thirdparty/${LIBNAME}/source)
    set(${LIBNAME}_BINARY_DIR ${CMAKE_CURRENT_BINARY_DIR}/Thirdparty/${LIBNAME}/build)

    # clone repository if not done
    if(IS_DIRECTORY ${${LIBNAME}_SOURCE_DIR})
        message(STATUS "Already downloaded: ${REPOSITORY}")
    else()
        message(STATUS "Clonning: ${REPOSITORY}")
        execute_process(
            WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
            COMMAND ${GIT_EXECUTABLE} clone --recursive ${REPOSITORY} ${${LIBNAME}_SOURCE_DIR}
            )
        # switch to target TAG and update submodules
        execute_process(
            WORKING_DIRECTORY ${${LIBNAME}_SOURCE_DIR}
            COMMAND ${GIT_EXECUTABLE} reset --hard origin/${GIT_TAG}
            COMMAND ${GIT_EXECUTABLE} submodule update --init --force --recursive --remote --merge
            )
    endif()
endmacro()