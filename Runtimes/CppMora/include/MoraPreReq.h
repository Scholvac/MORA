/*
 * Mora.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORA_PREREQ_H_
#define INCLUDE_MORA_PREREQ_H_


#ifdef WIN32
	#ifdef BUILD_DLL
		#ifdef Mora_EXPORTS
			#define MORA_API __declspec(dllexport)
		#else
			#define MORA_API __declspec(dllimport)
		#endif
	#else //BUILD_DLL
		#define MORA_API /*static lib*/
	#endif //BUILD_DLL
#else //WIN32
	#define MORA_API /*whatever todo on linux and mac*/
#endif //WIN32

#define SAVE_DELETE(X) { if (X) {delete X; X = NULL; } }

#pragma warning (disable: 4251) //stl import
#pragma warning (disable: 4275) //no dll import for std namespace elements

#include <inttypes.h>
#include <string>
#include <memory>
#include <map>
#include <vector>
#include <chrono>

namespace mora {

	typedef int8_t	int8;
	typedef int16_t int16;
	typedef int32_t int32;
	typedef int64_t int64;

	using duration 	= std::chrono::milliseconds;

	using TypeIdentifier = std::string;
	using IdentityType = std::string;
	using SignatureType = std::string;
}


#endif /* INCLUDE_MORA_PREREQ_H_ */
