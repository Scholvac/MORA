cmake_minimum_required(VERSION 3.10)

# set the project name
project(CppMoraRuntime)

FILE( GLOB LOGGURU ${CMAKE_CURRENT_SOURCE_DIR}/ThirdParty/LogGuru/*.cpp ${CMAKE_CURRENT_SOURCE_DIR}/ThirdParty/LogGuru/*.hpp )
SOURCE_GROUP("ThirdParty/LogGuru" FILES ${LOGGURU})
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/ThirdParty/LogGuru/)

####################	CL Sockets ##########################
FILE( GLOB CLSOCKET ${CMAKE_CURRENT_SOURCE_DIR}/ThirdParty/clsocket/*.cpp ${CMAKE_CURRENT_SOURCE_DIR}/ThirdParty/clsocket/*.h)
SOURCE_GROUP("ThirdParty/CLSocket" FILES ${CLSOCKET})
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/ThirdParty/clsocket/)
# OS and compiler checks.
if(UNIX)
    # linux / normal unix
    ADD_DEFINITIONS(-D_LINUX)
    if(CYGWIN)
        # Special Cygwin stuff here
    elseif(APPLE)
        # Special Apple stuff here
        REMOVE_DEFINITIONS(-D_LINUX)
        ADD_DEFINITIONS(-D_DARWIN)
    endif()
elseif(WIN32)
    ADD_DEFINITIONS(-DWIN32)
    SET(PROJECT_LIBS Ws2_32.lib)
    if(MINGW)
        # Special MINGW stuff here
        ADD_DEFINITIONS(-D_WINSOCK_DEPRECATED_NO_WARNINGS)
    elseif(MSVC)
        # Special MSVC stuff here
        ADD_DEFINITIONS(-D_WINSOCK_DEPRECATED_NO_WARNINGS)
    else()
        # No idea what it is, but there's a good chance it's too weird.
        MESSAGE( FATAL_ERROR "Using unknown WIN32 compiler... NOT. Please add to build system." )
    endif()
endif()

####################	CL Sockets ##########################
FILE( GLOB THREAD_POOL ${CMAKE_CURRENT_SOURCE_DIR}/ThirdParty/ThreadPool/*.h)
SOURCE_GROUP("ThirdParty/ThreadPool" FILES ${THREAD_POOL})
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/ThirdParty/ThreadPool/)

####################	MORA  ##########################
FILE(GLOB MORA_H ${CMAKE_CURRENT_SOURCE_DIR}/include/*.h)
SOURCE_GROUP("Public Header" FILES ${MORA_H})

FILE(GLOB MORA_C ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)
SOURCE_GROUP("Sources" FILES ${MORA_C})
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/include)

FILE(GLOB INTERNAL_H ${CMAKE_CURRENT_SOURCE_DIR}/src/internal/*.h)
SOURCE_GROUP("Internal Header" FILES ${INTERNAL_H})

SET(AllSources ${LOGGURU} ${CLSOCKET} ${THREAD_POOL} ${MORA_H} ${MORA_C} ${INTERNAL_H})
# add the executable
add_library(${PROJECT_NAME} ${AllSources})
TARGET_LINK_LIBRARIES(${PROJECT_NAME} ${PROJECT_LIBS} ${CMAKE_THREAD_LIBS_INIT})
IF (UNIX)
	TARGET_LINK_LIBRARIES(${PROJECT_NAME} pthread dl)
ENDIF(UNIX)


IF (BUILD_TESTING)
	SCAN_TESTS(${CMAKE_CURRENT_SOURCE_DIR}/tests)
ENDIF(BUILD_TESTING)