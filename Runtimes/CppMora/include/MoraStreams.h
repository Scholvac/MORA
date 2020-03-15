#ifndef MORA_STREAMS_H_
#define MORA_STREAMS_H_

#include <MoraPreReq.h>

#include <vector>
#include <string>
#include <cstring>//memcpy

namespace mora {
	

	class InputStream {		
	public:
		using value_type = ::mora::int8;
		using iterator = value_type*;
		using const_iterator = const iterator;
	private:
		std::vector<value_type>	mBuffer;
		::mora::int32			mPos 		{0};
	public:
		InputStream(int capcity)
			: mBuffer(std::vector<int8>(capcity)),
			mPos(0)
		{}
		
		~InputStream() {

		}

		int append(const std::vector<value_type>& data, int srcOffset, int offset, int length) {
			size_t l = data.size();
			mBuffer.insert(mBuffer.begin() + offset, data.begin() + srcOffset, data.begin()+srcOffset+length);
			return (int)l;
		}

	public:
		inline InputStream& operator>>(bool& val) {
			val = mBuffer[mPos++] == 1;
			return *this;
		}
		inline InputStream& operator>>(int8& val) {
			val = mBuffer[mPos++];
			return *this;
		}
		inline InputStream& operator>>(int16& val) {
			val = (int16)(
				((mBuffer[mPos] & 0xFF) << 8) +
				((mBuffer[mPos+1] & 0xFF) << 0)
				);
			mPos += 2;
			return *this;
		}
		inline InputStream& operator>>(char& val) {
			val = (char)(
				((mBuffer[mPos] & 0xFF) << 8) +
				((mBuffer[mPos+1] & 0xFF) << 0)
				);
			mPos += 2;
			return *this;
		}
		inline InputStream& operator>>(int32& val) {
			val = (int32)(
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			return *this;
		}
		inline InputStream& operator>>(int64& val) {
			val = (((int64)mBuffer[mPos] << 56) +
				((int64)(mBuffer[mPos+1] & 255) << 48) +
				((int64)(mBuffer[mPos+2] & 255) << 40) +
				((int64)(mBuffer[mPos+3] & 255) << 32) +
				((int64)(mBuffer[mPos+4] & 255) << 24) +
				((mBuffer[mPos+5] & 255) << 16) +
				((mBuffer[mPos+6] & 255) << 8) +
				((mBuffer[mPos+7] & 255) << 0));
			mPos += 8;
			return *this;
		}
		inline InputStream& operator>>(float& val) {
			int32 tval = (int32)(
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val = *((float*)&tval);
			return *this;
		}
		inline InputStream& operator>>(double& val) {
			int64 tval = (((int64)mBuffer[mPos] << 56) +
				((int64)(mBuffer[mPos+1] & 255) << 48) +
				((int64)(mBuffer[mPos+2] & 255) << 40) +
				((int64)(mBuffer[mPos+3] & 255) << 32) +
				((int64)(mBuffer[mPos+4] & 255) << 24) +
				((mBuffer[mPos+5] & 255) << 16) +
				((mBuffer[mPos+6] & 255) << 8) +
				((mBuffer[mPos+7] & 255) << 0));
			mPos += 8;
			val = *((double*)&tval);
			return *this;
		}
		inline InputStream& operator>>(std::string& val) {
			std::vector<char> chars;
			operator >>(chars);
			val = std::string(chars.begin(), chars.end());
			return *this;
		}


		inline InputStream& operator>>(std::vector<bool>& val) {
			int32 l = (int32)( //size - same as operator >> (int32& val)
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++)
				val[i] = mBuffer[mPos++] == 1;
			return *this;
		}
		inline InputStream& operator>>(std::vector<int8>& val) {
			int32 l = (int32)( //size - same as operator >> (int32& val)
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++)
				val[i] = mBuffer[mPos++];
			return *this;
		}
		inline InputStream& operator>>(std::vector<int16>& val) {
			int32 l = (int32)(
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++){
				val[i] = (int16)(
					((mBuffer[mPos] & 0xFF) << 8) +
					((mBuffer[mPos+1] & 0xFF) << 0)
					);
				mPos += 2;
			}
			return *this;
		}
		inline InputStream& operator>>(std::vector<char>& val) {
			int32 l = (int32)( //size - same as operator >> (int32& val)
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++){
				val[i] = (char)(
					((mBuffer[mPos] & 0xFF) << 8) +
					((mBuffer[mPos+1] & 0xFF) << 0)
					);
				mPos += 2;
			}
			return *this;
		}
		inline InputStream& operator>>(std::vector<int32>& val) {
			int32 l = (int32)( //size - same as operator >> (int32& val)
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++){
				val[i] = (int32)(
					((int32)((mBuffer[mPos] & 0xFF) << 24)) +
					((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
					((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
					((int32)((mBuffer[mPos+3] & 0xFF) << 0))
					);
				mPos += 4;
			}
			return *this;
		}
		inline InputStream& operator>>(std::vector<int64>& val) {
			int32 l = (int32)( //size - same as operator >> (int32& val)
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++){
				val[i] = (((int64)mBuffer[mPos] << 56) +
					((int64)(mBuffer[mPos+1] & 255) << 48) +
					((int64)(mBuffer[mPos+2] & 255) << 40) +
					((int64)(mBuffer[mPos+3] & 255) << 32) +
					((int64)(mBuffer[mPos+4] & 255) << 24) +
					((mBuffer[mPos+5] & 255) << 16) +
					((mBuffer[mPos+6] & 255) << 8) +
					((mBuffer[mPos+7] & 255) << 0));
				mPos += 8;
			}
			return *this;
		}
		inline InputStream& operator>>(std::vector<float>& val) {
			int32 l = (int32)( //size - same as operator >> (int32& val)
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++) {
				int32 tval = (int32)(
					((int32)((mBuffer[mPos] & 0xFF) << 24)) +
					((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
					((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
					((int32)((mBuffer[mPos+3] & 0xFF) << 0))
					);
				mPos += 4;
				val[i] = *((float*)&tval);
			}
			return *this;
		}
		inline InputStream& operator>>(std::vector<double>& val) {
			int32 l = (int32)( //size - same as operator >> (int32& val)
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++) {
				int64 tval = (((int64)mBuffer[mPos] << 56) +
					((int64)(mBuffer[mPos+1] & 255) << 48) +
					((int64)(mBuffer[mPos+2] & 255) << 40) +
					((int64)(mBuffer[mPos+3] & 255) << 32) +
					((int64)(mBuffer[mPos+4] & 255) << 24) +
					((mBuffer[mPos+5] & 255) << 16) +
					((mBuffer[mPos+6] & 255) << 8) +
					((mBuffer[mPos+7] & 255) << 0));
				mPos += 8;

				val[i] = *((double*)&tval);
			}
			return *this;
		}
		inline InputStream& operator>>(std::vector<std::string>& val) {
			int32 l = (int32)( //size - same as operator >> (int32& val)
				((int32)((mBuffer[mPos] & 0xFF) << 24)) +
				((int32)((mBuffer[mPos+1] & 0xFF) << 16)) +
				((int32)((mBuffer[mPos+2] & 0xFF) << 8)) +
				((int32)((mBuffer[mPos+3] & 0xFF) << 0))
				);
			mPos += 4;
			val.resize(l);
			for (int i = 0; i < l; i++)
				operator>>(val[i]);
			return *this;
		}
	};
	class OutputStream {
		std::vector<int8> mBytes;
		int mCount;
	public:
		OutputStream() 
			: mBytes(32), mCount(0)
		{
		}
		~OutputStream()
		{
		}
		inline int length() const { return mCount; }
		std::vector<int8>::const_iterator begin() const { return mBytes.begin(); }
		std::vector<int8>::const_iterator end() const { return mBytes.begin() + mCount; }
		const std::vector<int8>& bytes() { return mBytes; }

		inline void ensureCapacity(int32 minCap) {
			if (mBytes.size() < minCap) {
				int newCap = (int)mBytes.capacity() << 1; //*2
				if (newCap < minCap)
					newCap = minCap;
				if (newCap < 1 || newCap >(INT32_MAX - 8))
					newCap = minCap;
				mBytes.resize(newCap);
			}
		}
		inline OutputStream& operator <<(const bool& val) {
			ensureCapacity(mCount + 1);
			mBytes[mCount++] = (int8)(val ? 1 : 0);
			return *this;
		}
		inline OutputStream& operator <<(const int8& val) {
			ensureCapacity(mCount + 1);
			mBytes[mCount++] = val;
			return *this;
		}
		inline OutputStream& operator <<(const int16& val) {
			ensureCapacity(mCount + 2);
			mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			return *this;
		}
		inline OutputStream& operator <<(const char& val) {
			ensureCapacity(mCount + 2);
			mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			return *this;
		}
		inline OutputStream& operator <<(const int32& val) {
			ensureCapacity(mCount + 4);
			mBytes[mCount++] = (int8)((val >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			return *this;
		}
		inline OutputStream& operator <<(const int64& val) {
			ensureCapacity(mCount + 8);
			mBytes[mCount++] = (int8)((val >> 56) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 48) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 40) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 32) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			return *this;
		}
		inline OutputStream& operator <<(const float& val) {
			ensureCapacity(mCount + 4);
			int32 ival = *((int32*)&val);
			mBytes[mCount++] = (int8)((ival >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 0) & 0xFF);
			return *this;
		}
		inline OutputStream& operator <<(const double& val) {
			ensureCapacity(mCount + 8);
			int64 ival = *((int64*)&val);
			mBytes[mCount++] = (int8)((ival >> 56) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 48) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 40) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 32) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((ival >> 0) & 0xFF);
			return *this;
		}
		inline OutputStream& operator <<(const std::string& val) {
			int32 s = (int32)val.length();
			ensureCapacity(mCount + 4 + (s*2));
			//operator<<(s);
			mBytes[mCount++] = (int8)((s >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 0) & 0xFF);
			for (int i = 0; i < s; i++){
				char c = val[i];
				//operator<<(val[i]);
				mBytes[mCount++] = (int8)((c >> 8) & 0xFF);
				mBytes[mCount++] = (int8)((c >> 0) & 0xFF);
			}
			return *this;
		}

		inline OutputStream& operator <<(const std::vector<bool>& lval) {
			int32 s = (int32)lval.size();
			ensureCapacity(mCount + 4 + (s));
			//operator<<(s)
			mBytes[mCount++] = (int8)((s >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 0) & 0xFF);
			for (int i = 0; i < s; i++){
				//operator<<(val[i]);
				bool val = lval[i];
				mBytes[mCount++] = (int8)(val ? 1 : 0);
			}
			return *this;
		}
		inline OutputStream& operator <<(const std::vector<int8>& lval) {
			int32 s = (int32)lval.size();
			ensureCapacity(mCount + 4 + (s));
			//operator<<(s)
			mBytes[mCount++] = (int8)((s >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 0) & 0xFF);

			memcpy(&mBytes[mCount], &lval[0], s);
			mCount += s;
			return *this;
		}
		inline OutputStream& operator <<(const std::vector<int16>& lval) {
			int32 s = (int32)lval.size();
			ensureCapacity(mCount + 4 + (s*2));
			//operator<<(s)
			mBytes[mCount++] = (int8)((s >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 0) & 0xFF);
			for (int i = 0; i < s; i++){
				//operator<<(val[i]);
				int val = lval[i];
				mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			}
			return *this;
		}
		inline OutputStream& operator <<(const std::vector<int32>& lval) {
			int32 s = (int32)lval.size();
			ensureCapacity(mCount + 4 + (s*4));
			//operator<<(s)
			mBytes[mCount++] = (int8)((s >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 0) & 0xFF);
			for (int i = 0; i < s; i++){
				//operator<<(val[i]);
				int val = lval[i];
				mBytes[mCount++] = (int8)((val >> 24) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 16) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			}
			return *this;
		}
		inline OutputStream& operator <<(const std::vector<int64>& lval) {
			int32 s = (int32)lval.size();
			ensureCapacity(mCount + 4 + (s*8));
			//operator<<(s)
			mBytes[mCount++] = (int8)((s >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 0) & 0xFF);
			for (int i = 0; i < s; i++){
				//operator<<(val[i]);
				int64 val = lval[i];
				mBytes[mCount++] = (int8)((val >> 56) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 48) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 40) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 32) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 24) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 16) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			}
			return *this;
		}
		inline OutputStream& operator <<(const std::vector<float>& lval) {
			int32 s = (int32)lval.size();
			ensureCapacity(mCount + 4 + (s*4));
			//operator<<(s)
			mBytes[mCount++] = (int8)((s >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 0) & 0xFF);
			for (int i = 0; i < s; i++){
				//operator<<(val[i]);
				int32 val = *((int32*)&lval[i]);
				mBytes[mCount++] = (int8)((val >> 24) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 16) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			}
			return *this;
		}
		inline OutputStream& operator <<(const std::vector<double>& lval) {
			int32 s = (int32)lval.size();
			ensureCapacity(mCount + 4 + (s*8));
			//operator<<(s)
			mBytes[mCount++] = (int8)((s >> 24) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 16) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 8) & 0xFF);
			mBytes[mCount++] = (int8)((s >> 0) & 0xFF);
			for (int i = 0; i < s; i++){
				//operator<<(val[i]);
				int64 val = *((int64*)&lval[i]);
				mBytes[mCount++] = (int8)((val >> 56) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 48) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 40) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 32) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 24) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 16) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 8) & 0xFF);
				mBytes[mCount++] = (int8)((val >> 0) & 0xFF);
			}
			return *this;
		}
		inline OutputStream& operator <<(const std::vector<std::string>& val) {
			int32 s = (int32)val.size();
			operator<<(s);
			for (int i = 0; i < s; i++)
				operator<<(val[i]);
			return *this;
		}

		inline void append(const std::vector<int8>& value) {
			append(value, 0, (int)value.size());
		}
		inline void append(const std::vector<int8>& value, int offset, int length) {
			ensureCapacity((int)(mCount + value.size()));
			mBytes.insert(mBytes.begin() + mCount, value.begin() + offset, value.begin() + offset + length);
			mCount += length;
		}
	};
}


#endif //MORA_STREAMS_H
