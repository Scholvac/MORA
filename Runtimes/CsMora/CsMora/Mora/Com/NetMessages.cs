using Mora.Stream;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;

namespace Mora.Com
{

    public class OutMsg
    {
        public byte[] data;

        public OutMsg(short numberOfMessages, short messageNumber, byte messageMagic, byte[] _data, int from, int to)
        {
            int length = to - from;
            data = new byte[Common.NET_MESSAGES__HEADER_LENGTH + length];
            Encoding.Write(messageMagic, data, 0);
            Encoding.Write(length, data, 1);
            Encoding.Write((byte)0, data, 5);
            Encoding.Write(numberOfMessages, data, 6);
            Encoding.Write(messageNumber, data, 8);
            Array.Copy(_data, from, data, Common.NET_MESSAGES__HEADER_LENGTH, length);
        }

        internal static List<OutMsg> split(byte[] data, int length, int maxMsgSize)
        {
            byte magic = Common.generateByteMagic();
            if (maxMsgSize - Common.NET_MESSAGES__HEADER_LENGTH > length)
            {
                OutMsg msg = new OutMsg((short)1, (short)0, magic, data, 0, length);
                var l = new List<OutMsg>(1);
                l.Add(msg);
                return l;
            }
            else
            {
                int mms = maxMsgSize - Common.NET_MESSAGES__HEADER_LENGTH;
                int numMsg = (length / mms) + 1;
                List<OutMsg> res = new List<OutMsg>(numMsg);
                for (int i = 0; i < numMsg; i++)
                {
                    int from = i * mms;
                    int to = from + mms;
                    if (to > length)
                        to = length;
                    res.Add(new OutMsg((short)numMsg, (short)i, magic, data, from, to));
                }
                return res;
            }
        }

        internal void RequestAcknowledge(bool requestAcknowledge)
        {
            if (requestAcknowledge)
                data[5] |= Common.NET_MESSAGES__REQUEST_ACKNOWLEDGE;
        }

        internal bool CheckResponse(byte[] buffer)
        {
            if (buffer[0] != Common.NET_MESSAGES__ACKNOWLEDGE_MESSAGE ||
                    buffer[1] != data[0] ||
                    buffer[2] != data[8] ||
                    buffer[3] != data[9]
                )
            {
                return false;
            }
            return true;
        }
    }
    public class InMsg
    {
        public byte flags = 0;

        public short numberOfMessages;
        public short messageNumber;
        public byte messageMagic;
        public int length;
        public byte[] data;

        private int pos = -1;

        public InMsg(byte[] buffer, int dataLength)
        {
            int offset = 0;
            //assert(buffer.length > HEADER SIZE)
            messageMagic = Encoding.decodeByte(buffer, offset);
            offset += Encoding.BYTE_LENGTH;
            length = Encoding.decodeInt(buffer, offset);
            offset += Encoding.INT_LENGTH;
            flags = Encoding.decodeByte(buffer, offset);
            offset += Encoding.BYTE_LENGTH;
            numberOfMessages = Encoding.decodeShort(buffer, offset);
            offset += Encoding.SHORT_LENGTH;
            messageNumber = Encoding.decodeShort(buffer, offset);
            offset += Encoding.SHORT_LENGTH;
            this.data = new byte[length];
            pos = dataLength - offset;
            Array.Copy(buffer, offset, data, 0, pos);
        }

        public byte[] Append(byte[] buffer, int bufferLength)
        { //returns the remaining bytes or null if no bytes remain
            int remainig = length - pos;
            if (bufferLength > remainig)
            {
                Array.Copy(buffer, 0, data, pos, remainig);
                pos += remainig;
                byte[] remBuffer = new byte[remainig];
                Array.Copy(buffer, remainig, remBuffer, 0, bufferLength - remainig);
                return remBuffer;
            }
            else
            {
                Array.Copy(buffer, 0, data, pos, bufferLength);
                pos += bufferLength;
                return null;
            }
        }

        public bool complete()
        {
            return pos == data.Length;
        }

        internal bool RequestsAck()
        {
            return (flags & Common.NET_MESSAGES__REQUEST_ACKNOWLEDGE) == Common.NET_MESSAGES__REQUEST_ACKNOWLEDGE;
        }
        internal byte[] AckMessage()
        {
            byte[] msg = new byte[4];
            msg[0] = Common.NET_MESSAGES__ACKNOWLEDGE_MESSAGE;
            msg[1] = messageMagic;
            Encoding.Write(messageNumber, msg, 2);
            return msg;
        }
    }



    public class InMessageCollection
    {
        private EndPoint mSourceEP;
        private int mMagic;
        private InMsg[] mMessages;
        private int mMessageCounter = 0;

        private MoraByteArrayInputStream stream;

        public IMoraInputStream Stream { get { return stream; } }
        public EndPoint SourceEP { get { return mSourceEP; } }

        public InMessageCollection(InMsg msg, EndPoint ep)
        {
            mSourceEP = ep;
            mMagic = msg.messageMagic;
            mMessages = new InMsg[msg.numberOfMessages];
            mMessages[msg.messageNumber] = msg;
            mMessageCounter++;
        }


        public void Append(InMsg msg)
        {
            if (msg.messageMagic != mMagic)
                throw new Exception("Message Magic does not match");
            if (mMessages[msg.messageNumber] == null)
            {
                if (mMessages[msg.messageNumber] == null)
                    mMessageCounter++;
                mMessages[msg.messageNumber] = msg;
            }
        }

        public bool IsComplete()
        {
            return mMessageCounter == mMessages.Length;
        }

        public void BuildStream()
        {
            stream = new MoraByteArrayInputStream(mMessages[0].data);
            for (int i = 1; i < mMessages.Length; i++)
                stream.Append(mMessages[i].data, 0, mMessages[i].data.Length);
        }
    }
}
