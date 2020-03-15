cmake_minimum_required (VERSION 3.9)

project (mora VERSION 2.0.0)


set(VERSION_TYPE "beta" CACHE STRING "version type" FORCE)
site_name(VERSION_HOST) # read hostname to VERSION_HOST
set(VERSION_HOST "${VERSION_HOST}" CACHE STRING "host of build" FORCE)

message(STATUS "")
message(STATUS "    == ${PROJECT_NAME} Project configuration ==")
message(STATUS "")


set(CMAKE_MODULE_PATH "${CMAKE_CURRENT_SOURCE_DIR}/cmake")

include(safeguards) #check for in-source-builds and build-type (Release/Debug/...)
include(compilerUtils) #set cpp version, check architecture (x64 / arm / ...), configure warnings

#-----------------------------OPTIONS--------------------------------------------------
option(BUILD_SHARED_LIBS "Build projects as shared library (*.dll)" ON)
option(BUILD_TESTING "Build unit tests" OFF)
option(BUILD_BENCHMARKS "Build benchmarks" OFF)
option(BUILD_PLAYGROUNDS "Build playground projects" OFF)
option(BUILD_DOC "Build doxygen documentation" OFF)

include(installUtils) #set output directories and helper to install targets

include(3rd_party_libs.cmake) #include external projects

set_property(GLOBAL PROPERTY USE_FOLDERS ON)
set_property(GLOBAL PROPERTY PREDEFINED_TARGETS_FOLDER "CMakeTargets")

#=======================================================================================
add_library(${PROJECT_NAME} )

file(GLOB MORA_PUBLIC_HEADER ${CMAKE_CURRENT_SOURCE_DIR}/include/*.h)
file(GLOB MORA_PRIVATE_HEADER ${CMAKE_CURRENT_SOURCE_DIR}/src/private/*.h)
file(GLOB MORA_SOURCES ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)
source_group("Mora\\include" FILES ${MORA_PUBLIC_HEADER})
source_group("Mora\\source" FILES ${MORA_SOURCES})
source_group("Mora\\include\\internal" FILES ${MORA_PRIVATE_HEADER})

target_sources(${PROJECT_NAME} PRIVATE ${MORA_PUBLIC_HEADER})
target_sources(${PROJECT_NAME} PRIVATE ${MORA_SOURCES} ${MORA_PRIVATE_HEADER})

target_include_directories(${PROJECT_NAME} PUBLIC
	$<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/include>  
    $<INSTALL_INTERFACE:include/>  # <prefix>/include/mylib
)
target_link_libraries(${PROJECT_NAME} PUBLIC Poco::Net)

if (BUILD_SHARED_LIBS)
	target_compile_definitions(${PROJECT_NAME} PRIVATE -DMora_EXPORTS -DBUILD_DLL)	
else()
endif()

#-----------------------------TEST-Common-----------------------------------------------------
if (BUILD_TESTING OR BUILD_BENCHMARKS OR BUILD_PLAYGROUNDS)
	set(MORA_GENERATOR "${CMAKE_CURRENT_SOURCE_DIR}/../../Examples/Mora/MORAGenerator.jar" CACHE FILEPATH "Mora generator")
	include(UseMora)
	#--- Build a small library containing an MORA interface, to be used in some of the tests 
	
	set(MORA_FILE ${CMAKE_CURRENT_SOURCE_DIR}/../../Examples/Mora//serialisation.mora)
	file(GLOB MORA_TESTSERIALIZER_IMPL ${CMAKE_CURRENT_SOURCE_DIR}/tests/TestSerializer/*.cpp ${CMAKE_CURRENT_SOURCE_DIR}/tests/TestSerializer/*.h)
	generate_mora(${MORA_FILE})
	add_library(TestSerializer STATIC ${MORA_GENERATED_FILES} ${MORA_FILE} ${MORA_TESTSERIALIZER_IMPL} ${CMAKE_CURRENT_SOURCE_DIR}/tests/TestUtils.h)
	target_include_directories(TestSerializer PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/tests/TestSerializer/)
	target_include_directories(TestSerializer PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/tests/)
	target_include_directories(TestSerializer PUBLIC ${MORA_GENERATED_INCLUDE_DIR})
	
	target_link_libraries(TestSerializer PUBLIC ${PROJECT_NAME})
	target_link_libraries(TestSerializer PUBLIC ${CMAKE_THREAD_LIBS_INIT})
endif()

#-----------------------------PLAYGROUNDS----------------------------------------------
if (BUILD_PLAYGROUNDS)
	include(UsePlayground)
	
	add_playground(NAME EchoServer
		SOURCES playgrounds/playground_java.cpp
		DEPENDS TestSerializer
	)
endif (BUILD_PLAYGROUNDS)
#-----------------------------TEST-----------------------------------------------------
if (BUILD_TESTING)

	scan_tests(tests #scan folder "tests" for files starting with "test_" and create a CMake test out of them
		DEPENDS TestSerializer
		INCLUDE_DIRECTORIES ${MORA_GENERATED_INCLUDE_DIR}  ${CMAKE_CURRENT_SOURCE_DIR}/tests/TestSerializer/
	)
	
	if (BUILD_COVERAGE)
		add_coverage_report_target()
	endif(BUILD_COVERAGE)
endif()

#-----------------------------BENCHMARKS-----------------------------------------------------
if (BUILD_BENCHMARKS)	
	scan_benchmarks(benchmarks #scan folder "tests" for files starting with "test_" and create a CMake test out of them
		DEPENDS TestSerializer
		INCLUDE_DIRECTORIES ${MORA_GENERATED_INCLUDE_DIR} ${CMAKE_CURRENT_SOURCE_DIR}/tests/TestSerializer/
	)
endif()
#-----------------------------DOCUMENTATION--------------------------------------------------
if (BUILD_DOC)
	find_package(Doxygen)
	if (DOXYGEN_FOUND)
		set(DOC_OUTPUT_DIR ${CMAKE_INSTALL_PREFIX}/doc)
		set(DOC_SRC_PATH ${CMAKE_CURRENT_SOURCE_DIR})
		configure_file(${CMAKE_CURRENT_SOURCE_DIR}/Doxyfile.in ${CMAKE_CURRENT_BINARY_DIR}/Doxyfile  @ONLY)
		 add_custom_target( doc_doxygen ALL
		        COMMAND $<$<CONFIG:Release>:${DOXYGEN_EXECUTABLE} ${CMAKE_CURRENT_BINARY_DIR}/Doxyfile> $<$<NOT:$<CONFIG:Release>>:${CMAKE_COMMAND} -E echo "Only done in Release builds">
		        WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
		        COMMENT "Generating API documentation with Doxygen"
		        VERBATIM )
	else(DOXYGEN_FOUND)
		message("Doxygen need to be installed to generate the doxygen documentation")
	endif(DOXYGEN_FOUND)
endif(BUILD_DOC)
#-----------------------------INSTALL--------------------------------------------------
install(TARGETS ${PROJECT_NAME}
	EXPORT ${PROJECT_NAME}Targets
	LIBRARY DESTINATION lib
	ARCHIVE DESTINATION lib
	RUNTIME DESTINATION bin
	INCLUDES DESTINATION include
)
install(FILES ${MORA_PUBLIC_HEADER} DESTINATION "include")
install(FILES ${CMAKE_CURRENT_SOURCE_DIR}/../../LICENSE DESTINATION ".")
install(FILES ${CMAKE_CURRENT_SOURCE_DIR}/../../README.md DESTINATION ".")


include(CMakePackageConfigHelpers)
write_basic_package_version_file(
    				${PROJECT_NAME}ConfigVersion.cmake
    VERSION 		${PACKAGE_VERSION}
    COMPATIBILITY 	AnyNewerVersion
)
install(EXPORT  	${PROJECT_NAME}Targets
        FILE 		${PROJECT_NAME}Config.cmake
        NAMESPACE 	mora::
        DESTINATION lib/cmake/${PROJECT_NAME}
)
#-----------------------------Packaging--------------------------------------------------
set(CPACK_PACKAGE_VENDOR "Vendor name")
set(CPACK_PACKAGE_DESCRIPTION_SUMMARY "Some summary")
set(CPACK_PACKAGE_VERSION_MAJOR ${PROJECT_VERSION_MAJOR})
set(CPACK_PACKAGE_VERSION_MINOR ${PROJECT_VERSION_MINOR})
set(CPACK_PACKAGE_VERSION_PATCH ${PROJECT_VERSION_PATCH})
#set(CPACK_RESOURCE_FILE_LICENSE "${CMAKE_CURRENT_SOURCE_DIR}/../../LICENSE")
set(CPACK_RESOURCE_FILE_README "${CMAKE_CURRENT_SOURCE_DIR}/../../README.md")
set(CPACK_SOURCE_GENERATOR "TGZ;ZIP")
set(CPACK_SOURCE_IGNORE_FILES
    /.git
    /dist
    /.*build.*
    /\\\\.DS_Store
)
include(CPack)

#=======================================================================================
# Wrap up of settings printed on build
message(STATUS "")
message(STATUS "    == Final overview for ${PROJECT_NAME} ==")
message(STATUS "Version:              	${PROJECT_VERSION_MAJOR}.${PROJECT_VERSION_MINOR}.${PROJECT_VERSION_PATCH} ${VERSION_TYPE} @ ${VERSION_HOST}")
message(STATUS "Install prefix:       	${CMAKE_INSTALL_PREFIX}")
message(STATUS "Compiler:             	${CMAKE_CXX_COMPILER}")
message(STATUS "Architecture           	${COMPILER_ARCHITECTURE}")
message(STATUS "CMAKE_BUILD_TYPE:     	${CMAKE_BUILD_TYPE}")
message(STATUS "")


