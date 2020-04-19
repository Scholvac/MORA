/*
 * MoraLogging.h
 *
 * Author: sschweigert
 */

#ifndef INCLUDE_MORALOGGING_H_
#define INCLUDE_MORALOGGING_H_

#include <MoraPreReq.h>
 
namespace mora {
	class ILogHandler {
	private:
		int			mLogLevel{ 3 };
	public:
		ILogHandler() {}
		virtual ~ILogHandler() {}

		inline void setLogLevel(int ll) { mLogLevel = ll; }
		inline int getLogLevel() const { return mLogLevel; }

		inline bool isLevelEnabled(int level) const { return level >= mLogLevel; }
	public:
		virtual void log(int verbosity, const char* message) = 0;
	};

	MORA_API void _log(int verbosity, const char* file, unsigned line, const char* format, ...);
	MORA_API ILogHandler* setLogHandler(ILogHandler* handler);
}

//
//#define LOG_TRACE(...) /* mora_log(0 , __FILE__, __LINE__, __VA_ARGS__) */
//
//#define LOG_DEBUG(...) ::mora::_log(1 , __FILE__, __LINE__, __VA_ARGS__)
//
//#define LOG_INFO(...) ::mora::_log(2, __FILE__, __LINE__, __VA_ARGS__)
//
//#define LOG_WARN(...) ::mora::_log(3, __FILE__, __LINE__, __VA_ARGS__)
//
//#define LOG_ERROR(...) ::mora::_log(4, __FILE__, __LINE__, __VA_ARGS__)


#endif /* INCLUDE_MORALOGGING_H_ */
