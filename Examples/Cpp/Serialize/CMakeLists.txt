cmake_minimum_required(VERSION 3.10)

# set the project name
project(CppMoraExamples_Serialize)

set(MORA_FILE "${CMAKE_SOURCE_DIR}/Examples/Mora/serialisation.mora")
generate_mora("${MORA_FILE}")

include_directories(${CMAKE_SOURCE_DIR}/Runtimes/CppMora/include)
INCLUDE_DIRECTORIES(${CMAKE_SOURCE_DIR}/Runtimes/CppMora/ThirdParty/LogGuru/)
include_directories(${MORA_GENERATED_FILES_DIRECTORY})

add_executable(${PROJECT_NAME} ExampleSerialize.cpp ${MORA_FILE} ${MORA_GENERATED_SOURCES} ${MORA_GENERATED_HEADER})
target_link_libraries(${PROJECT_NAME} CppMoraRuntime) 