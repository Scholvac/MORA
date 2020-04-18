#include <MoraLogging.h>
#include <stdlib.h>
#include <stdarg.h>
#include <stdio.h>
#include <memory>
#include <iostream>

namespace mora {

	static ILogHandler* sLogHandler{ nullptr };

	ILogHandler* setLogHandler(ILogHandler* lh) {
		ILogHandler* old = sLogHandler;
		sLogHandler = lh;
		return old;
	}
	void _log(int verbosity, const char* file, unsigned line, const char* format, ...) {
		if (sLogHandler && sLogHandler->isLevelEnabled(verbosity)) {

			va_list vlist;
			va_start(vlist, format);
#ifdef _WIN32
			int bytes_needed = _vscprintf(format, vlist);
			if (bytes_needed < 0)
				throw "Bad string format";
			char* buff = (char*)malloc(bytes_needed + 1);
			vsnprintf(buff, bytes_needed + 1, format, vlist);
#else
			char* buff = nullptr;
			int result = vasprintf(&buff, format, vlist);
			if (result < 0)
				throw "Bad string format";
#endif
			sLogHandler->log(verbosity, buff);

			va_end(vlist);
			free(buff);
		}
		
	}

}