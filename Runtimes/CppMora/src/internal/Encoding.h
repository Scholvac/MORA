#ifndef MORA_ENCODING_H_
#define MORA_ENCODING_H_

#include <MORA.h>
#include <string>
#include <vector>

namespace mora {
	

	class Encoding {
	public:
		static inline int readInt(int8* b, int& offset){
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
			for (int i = 0; i < s; i++, offset+=2)
				writeChar(buffer, offset, value[i]);
		}
	};
}


#endif //MORA_ENCODING_H_
