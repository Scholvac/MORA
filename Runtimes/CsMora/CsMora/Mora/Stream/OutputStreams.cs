using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Mora.Stream
{
   

    public class MoraByteArrayOutputStream : IMoraOutputStream
    {
        private static int MAX_ARRAY_SIZE = Int32.MaxValue- 8;
        byte[] mBytes = null;
        int mSize = 0;


        public MoraByteArrayOutputStream(byte[] input)
        {
            mBytes = input;
            mSize = input.Length;
        }
        public MoraByteArrayOutputStream()
        {
            mBytes = new byte[10];
            mSize = 0;
        }

        public byte[] getBuffer()
        {
            return mBytes;
        }

        public int getSize()
        {
            return mSize;
        }

        public void Write(byte[] b, int off, int len)
        {
            
            if ((off < 0) || (off > b.Length) || (len < 0) ||
                ((off + len) - b.Length > 0))
            {
                throw new Exception("Array Out Of Bounds");
            }
            ensureCapacity(mSize + len);
            Array.Copy(b, off, mBytes, mSize, len);
            mSize += len;
        }
        private void ensureCapacity(int minCapacity)
        {
            // overflow-conscious code
            if (minCapacity - mBytes.Length > 0)
                grow(minCapacity);
        }
        private void grow(int minCapacity)
        {
            // overflow-conscious code
            int oldCapacity = mBytes.Length;
            int newCapacity = oldCapacity << 1;
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            if (newCapacity < 0)
                newCapacity = MAX_ARRAY_SIZE;
            
            Array.Resize(ref mBytes, newCapacity);
        }
        private void grow()
        {
            // overflow-conscious code
            int oldCapacity = mBytes.Length;
            int newCapacity = oldCapacity << 1;
            if (newCapacity < 0)
                newCapacity = MAX_ARRAY_SIZE;
            Array.Resize(ref mBytes, newCapacity);
        }

        private static int hugeCapacity(int minCapacity)
        {
            if (minCapacity < 0) // overflow
                throw new Exception("Array Size Overflow");
            return (minCapacity > MAX_ARRAY_SIZE) ?
                Int32.MaxValue : MAX_ARRAY_SIZE;
        }

        public void Write(bool value)
        {
            if (mSize + 1 > mBytes.Length)
                grow();
            mBytes[mSize++] = (byte)(value ? 1 : 0);
        }

        public void Write(byte value)
        {
            if (mSize + 1 > mBytes.Length)
                grow();
            mBytes[mSize++] = value;
        }

        public void Write(char value)
        {
            if (mSize + 2 > mBytes.Length)
                grow();
            mBytes[mSize++] = (byte)((value >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((value >> 0) & 0xFF);
        }

        public void Write(short value)
        {
            if (mSize + 2 > mBytes.Length)
                grow();
            mBytes[mSize++] = (byte)((value >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((value >> 0) & 0xFF);
        }

        public void Write(int value)
        {
            if (mSize + 4 > mBytes.Length)
                grow();
            mBytes[mSize++] = (byte)((value >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((value >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((value >>  8) & 0xFF);
            mBytes[mSize++] = (byte)((value >>  0) & 0xFF);
        }

        public void Write(long value)
        {
            if (mSize + 8 > mBytes.Length)
                grow();
            mBytes[mSize++] = (byte)((value >> 56) & 0xFF);
            mBytes[mSize++] = (byte)((value >> 48) & 0xFF);
            mBytes[mSize++] = (byte)((value >> 40) & 0xFF);
            mBytes[mSize++] = (byte)((value >> 32) & 0xFF);
            mBytes[mSize++] = (byte)((value >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((value >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((value >>  8) & 0xFF);
            mBytes[mSize++] = (byte)((value >>  0) & 0xFF);
        }


        public void Write(float value)
        {
            if (mSize + 4 > mBytes.Length)
                grow();
            byte[] tmp = BitConverter.GetBytes(value);
            mBytes[mSize++] = tmp[3];
            mBytes[mSize++] = tmp[2];
            mBytes[mSize++] = tmp[1];
            mBytes[mSize++] = tmp[0];
        }

        public void Write(double value)
        {
            if (mSize + 8 > mBytes.Length)
                grow();
            byte[] tmp = BitConverter.GetBytes(value);
            mBytes[mSize++] = tmp[7];
            mBytes[mSize++] = tmp[6];
            mBytes[mSize++] = tmp[5];
            mBytes[mSize++] = tmp[4];
            mBytes[mSize++] = tmp[3];
            mBytes[mSize++] = tmp[2];
            mBytes[mSize++] = tmp[1];
            mBytes[mSize++] = tmp[0];
        }

        public void Write(string value)
        {
            int length = value != null ? value.Length : 0;
            ensureCapacity(mSize + length * 2 + 4);
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                char c = value[i];
                mBytes[mSize++] = (byte)((c >> 8) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 0) & 0xFF);
            }
        }

        public void Write(List<bool> value)
        {
            int length = value.Count;
            ensureCapacity(mSize + length + 4); //using ensureCapacity as we may handle big lists, and this method also checks wheater the new capacity oversizes the minCapacity
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                bool le = value[i];
                mBytes[mSize++] = (byte)(le?1:0);
            }
        }

        public void Write(List<byte> value)
        {
            int length = value.Count;
            ensureCapacity(mSize + length + 4);
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                mBytes[mSize++] = value[i];
            }
        }

        public void Write(List<char> value)
        {
            int length = value.Count;
            ensureCapacity(mSize + length*2 + 4);
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                char c = value[i];
                mBytes[mSize++] = (byte)((c >> 8) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 0) & 0xFF);
            }
        }

        public void Write(List<short> value)
        {
            int length = value.Count;
            ensureCapacity(mSize + length * 2 + 4);
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                short c = value[i];
                mBytes[mSize++] = (byte)((c >> 8) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 0) & 0xFF);
            }
        }

        public void Write(List<int> value)
        {
            int length = value.Count;
            ensureCapacity(mSize + length * 4 + 4);
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                int c = value[i];
                mBytes[mSize++] = (byte)((c >> 24) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 16) & 0xFF);
                mBytes[mSize++] = (byte)((c >>  8) & 0xFF);
                mBytes[mSize++] = (byte)((c >>  0) & 0xFF);
            }
        }

        public void Write(List<long> value)
        {
            int length = value.Count;
            ensureCapacity(mSize + length * 8 + 4);
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                long c = value[i];
                mBytes[mSize++] = (byte)((c >> 56) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 48) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 40) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 32) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 24) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 16) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 8) & 0xFF);
                mBytes[mSize++] = (byte)((c >> 0) & 0xFF);
            }
        }

        public void Write(List<float> value)
        {
            int length = value.Count;
            ensureCapacity(mSize + length * 4 + 4); //we do allocate the correct size here, so we could minimize the amount of array-copies
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                Write(value[i]);
            }
        }

        public void Write(List<double> value)
        {
            int length = value.Count;
            ensureCapacity(mSize + length * 8 + 4); //we do allocate the correct size here, so we could minimize the amount of array-copies
            mBytes[mSize++] = (byte)((length >> 24) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 16) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 8) & 0xFF);
            mBytes[mSize++] = (byte)((length >> 0) & 0xFF);
            for (int i = 0; i < length; i++)
            {
                Write(value[i]);
            }
        }

        public void Write(List<string> value)
        {
            int length = value.Count;
            Write(length);
            for (int i = 0; i < length; i++)
                Write(value[i]);
        }

        
    }
}
