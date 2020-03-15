find_package(Java) #required to execute the generator
if (NOT Java_JAVA_EXECUTABLE)
	message(FATAL_ERROR "Could not find the jre to execute the MORA generator")
endif()

set(MORA_GENERATOR "" CACHE FILEPATH "path to mora generator")
set(MORA_GENERATED_FILES_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/gen/mora")
set(MORA_INCLUDE_DIRECTORY "")

#
#	@NOTE on MINGW the generated files ${MORA_GENERATED_FILES} need to be added to the target
#		otherwise the command will not be executed. 
macro(generate_mora MoraFiles)
  	if (NOT EXISTS ${MORA_GENERATOR})
  		message(FATAL_ERROR "No Mora generator defined, please set MORA_GENERATOR variable")
 	endif()
 	foreach(mf ${MoraFiles})
 		get_filename_component(fn ${mf} NAME_WE)
		set(mora_gen_parameter "-cpp -cppdir ${MORA_GENERATED_FILES_DIRECTORY}")
		if (MORA_INCLUDE_DIRECTORY)
			message(STATUS "Add include directory ${MORA_INCLUDE_DIRECTORY}")
			set(mora_gen_parameter "${mora_gen_parameter} -i ${MORA_INCLUDE_DIRECTORY}")
		endif(MORA_INCLUDE_DIRECTORY) 	
		
		#predict the filenames based on the input file (cpp-generator specific)	
 		set(MORA_GENERATED_SOURCES "${MORA_GENERATED_FILES_DIRECTORY}/${fn}.cpp")
 		set(MORA_GENERATED_HEADER "${MORA_GENERATED_FILES_DIRECTORY}/${fn}.h")
 		get_filename_component(MORA_GENERATED_INCLUDE_DIR ${MORA_GENERATED_HEADER} DIRECTORY) 
 		set(MORA_GENERATED_FILES ${MORA_GENERATED_HEADER} ${MORA_GENERATED_SOURCES})
 		message("Use Mora generator with: [${Java_JAVA_EXECUTABLE} -jar ${MORA_GENERATOR} -cpp -cppdir ${MORA_GENERATED_FILES_DIRECTORY} ${mf}]")
  		add_custom_command(
  			OUTPUT ${MORA_GENERATED_FILES}
 			COMMAND ${Java_JAVA_EXECUTABLE} -jar ${MORA_GENERATOR} -cpp -cppdir ${MORA_GENERATED_FILES_DIRECTORY} ${mf}
  			DEPENDS ${mf}
  			COMMENT "Use Mora generator with: [${Java_JAVA_EXECUTABLE} -jar ${MORA_GENERATOR} -cpp -cppdir ${MORA_GENERATED_FILES_DIRECTORY} ${mf}]"
  			USES_TERMINAL
  		)
	endforeach()
endmacro(generate_mora MoraFiles)
