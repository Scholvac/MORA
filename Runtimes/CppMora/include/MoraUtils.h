/*
 * MoraUtils.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORAUTILS_H_
#define INCLUDE_MORAUTILS_H_

#include <MoraPreReq.h>
#include <MoraIdentifier.h>


namespace mora {

class MORA_API MoraUtils {
	public:
		static std::string createRandomIdentifier(int length = 5);
		static std::string toString(const Protocol prot);
		static int8 toByte(const Protocol prot);
		static Protocol toProtocol(const std::string& prot);
		static Protocol toProtocol(int8 prot);
		static int16 generateShortMagic();




		static inline int readInt(int8* b, int& offset) {
			int i0 = b[offset++] & 0xFF;
			int i1 = b[offset++] & 0xFF;
			int i2 = b[offset++] & 0xFF;
			int i3 = b[offset++] & 0xFF;
			int res =
				(i0 << 24) +
				(i1 << 16) +
				(i2 << 8) +
				i3;

			return res;
			/*return (int)(
				((int)((b[offset++] & 0xFF) << 24)) +
				((int)((b[offset++] & 0xFF) << 16)) +
				((int)((b[offset++] & 0xFF) << 8)) +
				((int)((b[offset++] & 0xFF) << 0))
				);*/
		}

		static inline int16 readShort(int8* b, int& offset) {
			int16 i0 = b[offset++] & 0xFF;
			int16 i1 = b[offset++] & 0xFF;
			int16 res =
				(i0 << 8) +
				i1;
			return res;
			/*return (short)(
				((b[offset++] & 0xFF) << 8) +
				((b[offset++] & 0xFF) << 0)
				);*/
		}

		static inline void writeShort(int8* buffer, int offset, int16 value) {
			buffer[offset + 0] = (int8)((value >> 8) & 0xFF);
			buffer[offset + 1] = (int8)((value >> 0) & 0xFF);
		}
		static inline void writeInt(int8* buffer, int offset, int32 value) {
			buffer[offset + 0] = (int8)((value >> 24) & 0xFF);
			buffer[offset + 1] = (int8)((value >> 16) & 0xFF);
			buffer[offset + 2] = (int8)((value >> 8) & 0xFF);
			buffer[offset + 3] = (int8)((value >> 0) & 0xFF);
		}
		static inline void writeChar(int8* buffer, int offset, const char& value) {
			buffer[offset + 0] = (int8)((value >> 8) & 0xFF);
			buffer[offset + 1] = (int8)((value >> 0) & 0xFF);
		}
		static inline void writeString(int8* buffer, int offset, const std::string& value) {
			int32 s = (int32)value.length();
			writeInt(buffer, offset, s);
			offset += 4;
			for (int i = 0; i < s; i++, offset += 2)
				writeChar(buffer, offset, value[i]);
		}
		static inline void writeString(std::vector<int8>& buffer, int offset, const std::string& value) {
			int32 s = (int32)value.length();
			int nl = offset + s * 2 + 4;
			if (buffer.capacity() < nl)
				buffer.resize(nl);
			writeInt(&buffer[0], offset, s);
			offset += 4;
			for (int i = 0; i < s; i++, offset += 2)
				writeChar(&buffer[0], offset, value[i]);
		}
	};

} /* namespace mora */

#endif /* INCLUDE_MORAUTILS_H_ */
