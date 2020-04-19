/*
 * PreReq.h
 *
 *  Created on: Apr 19, 2020
 *      Author: sschweigert
 */

#ifndef SRC_PRIVATE_LOGDEF_H_
#define SRC_PRIVATE_LOGDEF_H_

#include <MoraLogging.h>

#define LOG_TRACE(...) /* mora_log(0 , __FILE__, __LINE__, __VA_ARGS__) */

#define LOG_DEBUG(...) ::mora::_log(1 , __FILE__, __LINE__, __VA_ARGS__)

#define LOG_INFO(...) ::mora::_log(2, __FILE__, __LINE__, __VA_ARGS__)

#define LOG_WARN(...) ::mora::_log(3, __FILE__, __LINE__, __VA_ARGS__)

#define LOG_ERROR(...) ::mora::_log(4, __FILE__, __LINE__, __VA_ARGS__)


#endif /* SRC_PRIVATE_LOGDEF_H_ */
