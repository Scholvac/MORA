using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Mora.Stream
{
    public interface IMoraInputStream
    {
        void Read(out bool value);
        void Read(out byte value);
        void Read(out char value);
        void Read(out short value);
        void Read(out int value);
        void Read(out long value);
        void Read(out float value);
        void Read(out double value);
        void Read(out string value);



        void Read(out List<bool> value);
        void Read(out List<byte> value);
        void Read(out List<char> value);
        void Read(out List<short> value);
        void Read(out List<int> value);
        void Read(out List<long> value);
        void Read(out List<float> value);
        void Read(out List<double> value);
        void Read(out List<string> value);

    }

    public interface IMoraOutputStream
    {
        void Write(bool value);
        void Write(byte value);
        void Write(char value);
        void Write(short value);
        void Write(int value);
        void Write(long value);
        void Write(float value);
        void Write(double value);
        void Write(string value);

        void Write(byte[] value, int offset, int length);


        void Write(List<bool> value);
        void Write(List<byte> value);
        void Write(List<char> value);
        void Write(List<short> value);
        void Write(List<int> value);
        void Write(List<long> value);
        void Write(List<float> value);
        void Write(List<double> value);
        void Write(List<string> value);
        byte[] getBuffer();
        int getSize();
    }
}
