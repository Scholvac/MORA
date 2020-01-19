using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Mora.Stream
{
    
    public class MoraByteArrayInputStream : IMoraInputStream
    {
        private static int MAX_ARRAY_SIZE = Int32.MaxValue- 8;
        byte[] mBytes = null;
        int mInputCount = 0;

        int mReadCounter = 0;

        public MoraByteArrayInputStream(byte[] input)
        {
            mBytes = input;
            mInputCount = input.Length;
        }

        public void Append(byte[] b, int off, int len)
        {
            
            if ((off < 0) || (off > b.Length) || (len < 0) ||
                ((off + len) - b.Length > 0))
            {
                throw new Exception("Array Out Of Bounds");
            }
            ensureCapacity(mInputCount + len);
            Array.Copy(b, off, mBytes, mInputCount, len);
            mInputCount += len;
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
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            if (newCapacity - MAX_ARRAY_SIZE > 0)
                newCapacity = hugeCapacity(minCapacity);
            Array.Resize(ref mBytes, newCapacity);
        }

        private static int hugeCapacity(int minCapacity)
        {
            if (minCapacity < 0) // overflow
                throw new Exception("Array Size Overflow");
            return (minCapacity > MAX_ARRAY_SIZE) ?
                Int32.MaxValue : MAX_ARRAY_SIZE;
        }


        public void Read(out bool value)
        {
            try
            {
                value = mBytes[mReadCounter++] == 1;
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out byte value)
        {
            try
            {
                value = mBytes[mReadCounter++];
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out char value)
        {
            try
            { 
                int ch1 = mBytes[mReadCounter++] & 0xFF;
                int ch2 = mBytes[mReadCounter++] & 0xFF;
                value = (char)((ch1 << 8) + (ch2 << 0));
            }catch(Exception e) { 
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out short value)
        {
            try
            {
                int ch1 = mBytes[mReadCounter++] & 0xFF;
                int ch2 = mBytes[mReadCounter++] & 0xFF;
                value = unchecked((short)((ch1 << 8) + (ch2 << 0)));
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out int value)
        {
            try
            {
                value = (int)(
                    ((int)((mBytes[mReadCounter++] & 0xFF) << 24)) +
                    ((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
                    ((int)((mBytes[mReadCounter++] & 0xFF) << 8)) +
                    ((int)((mBytes[mReadCounter++] & 0xFF) << 0))
                    );
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out long value)
        {
            try
            {
                value = (((long)mBytes[mReadCounter++] << 56) +
                   ((long)(mBytes[mReadCounter++] & 255) << 48) +
                   ((long)(mBytes[mReadCounter++] & 255) << 40) +
                   ((long)(mBytes[mReadCounter++] & 255) << 32) +
                   ((long)(mBytes[mReadCounter++] & 255) << 24) +
                   ((mBytes[mReadCounter++] & 255) << 16) +
                   ((mBytes[mReadCounter++] & 255) << 8) +
                   ((mBytes[mReadCounter++] & 255) << 0));
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        byte[] _floatBuffer = new byte[4];
        public void Read(out float value)
        {
            try
            {
                _floatBuffer[0] = mBytes[mReadCounter + 3];
                _floatBuffer[1] = mBytes[mReadCounter + 2];
                _floatBuffer[2] = mBytes[mReadCounter + 1];
                _floatBuffer[3] = mBytes[mReadCounter + 0];
                value = BitConverter.ToSingle(_floatBuffer, 0);
                mReadCounter +=4;
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }
        byte[] _doubleBuffer = new byte[8];
        public void Read(out double value)
        {
            try
            {
                _doubleBuffer[0] = mBytes[mReadCounter + 7];
                _doubleBuffer[1] = mBytes[mReadCounter + 6];
                _doubleBuffer[2] = mBytes[mReadCounter + 5];
                _doubleBuffer[3] = mBytes[mReadCounter + 4];
                _doubleBuffer[4] = mBytes[mReadCounter + 3];
                _doubleBuffer[5] = mBytes[mReadCounter + 2];
                _doubleBuffer[6] = mBytes[mReadCounter + 1];
                _doubleBuffer[7] = mBytes[mReadCounter + 0];
                value = BitConverter.ToDouble(_doubleBuffer, 0);
                mReadCounter += 8;
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out string value)
        {
            try
            {
                int length; Read(out length);
                char[] chars = new char[length];
                for (int i = 0; i < length; i++)
                    Read(out chars[i]);
                value = new string(chars);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<bool> value)
        {
            try
            {
                Read(out int length);
                bool[] array = new bool[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<bool>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<byte> value)
        {
            try
            {
                Read(out int length);
                byte[] array = new byte[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<byte>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<char> value)
        {
            try
            {
                Read(out int length);
                char[] array = new char[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<char>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<short> value)
        {
            try
            {
                Read(out int length);
                short[] array = new short[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<short>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<int> value)
        {
            try
            {
                Read(out int length);
                int[] array = new int[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<int>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<long> value)
        {
            try
            {
                Read(out int length);
                long[] array = new long[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<long>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<float> value)
        {
            try
            {
                Read(out int length);
                float[] array = new float[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<float>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<double> value)
        {
            try
            {
                Read(out int length);
                double[] array = new double[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<double>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }

        public void Read(out List<string> value)
        {
            try
            {
                Read(out int length);
                string[] array = new string[length];
                for (int i = 0; i < length; i++)
                    Read(out array[i]);
                value = new List<string>(array);
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
        }
    }
}
