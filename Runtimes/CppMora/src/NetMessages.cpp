
#include "internal/precomp.h"
#include "MORAExt.h"

#include "internal/Encoding.h"

#include "MoraStreams.h"

using namespace mora;
using namespace mora::net;

#define HEADER_SIZE 10
#define REQUEST_ACKNOWLEDGE 1
#define ACKNOWLEDGE_MESSAGE 0xA

InMsg::InMsg(int cap)
	: data(std::vector<int8>(cap)), magic(-1),
	length(-1), numberOfMessages(0), messageNumber(0), flags(-1),
	pos(0)
{
}

InMsg::~InMsg()
{
}


int InMsg::getRemainingCapcity() {
	return (int)data.capacity() - pos;
}
int8* InMsg::getInsertBuffer() {
	if (length < 0)
		return &data[0];
	return &data[pos];
}

bool InMsg::handleNextBytes(int bytesRead) {
	pos += bytesRead;
	if (length < 0 && pos > HEADER_SIZE) { //not yet initialized
		int o = 0;
		int8* buffer = &data[0];

		magic = data[o++];
		length = Encoding::readInt(buffer, o);
		flags = data[o++];
		numberOfMessages = Encoding::readShort(buffer, o);
		messageNumber = Encoding::readShort(buffer, o);
		if (length > data.capacity())
			data.resize(length + HEADER_SIZE);
	}
	return pos >= length;
}

bool InMsg::requiresAcknowledge(int8* buffer) {
	if ((flags & REQUEST_ACKNOWLEDGE) == REQUEST_ACKNOWLEDGE) {
		buffer[0] = ACKNOWLEDGE_MESSAGE;
		buffer[1] = magic;
		Encoding::writeShort(buffer, 2, messageNumber);
		return true;
	}
	return false;
}







InMsgCollection::InMsgCollection(InMsg* msg, const std::string& adr)
	: messageCounter(0), magic(msg->magic), stream(NULL), responseAdr(adr)
{
	messages.resize(msg->numberOfMessages, NULL);
	append(msg);
}
InMsgCollection::~InMsgCollection() {
	for (size_t i = 0; i < messages.size(); i++)
		delete messages[i];
	if (stream)
		delete stream;
}

void InMsgCollection::append(InMsg* msg) {
	int idx = msg->messageNumber;
	messages[idx] = msg;
	messageCounter++;
}
bool InMsgCollection::isComplete() {
	if (messageCounter == messages.size()) {
		if (stream == NULL) {
			int length = messages[0]->length;
			for (int i = 1; i < messageCounter; i++)
				length += messages[i]->length;
			stream = new InputStream(length);
			int offset = 0;
			for (size_t i = 0; i < messages.size(); i++)
				offset = stream->append(messages[i]->data, HEADER_SIZE, offset, messages[i]->length);
		}
		return true;
	}
	return false;
}








OutMsg::OutMsg(int16 numberOfMessages, int16 messageNumber, int8 messageMagic, std::vector<int8>::const_iterator data_start, std::vector<int8>::const_iterator data_end, int32 length)
	: mBuffer((10))
{
	int offset = 0;
	mBuffer[offset++] = messageMagic;
	Encoding::writeInt(&mBuffer[0], offset, length);
	offset += 4;
	mBuffer[offset++] = 0;//flag - may be changed later by the connection
	Encoding::writeShort(&mBuffer[0], offset, numberOfMessages);
	offset += 2;
	Encoding::writeShort(&mBuffer[0], offset, messageNumber);
	offset += 2;
	std::vector<int8>::iterator where = mBuffer.begin() + offset;
	mBuffer.insert(where, data_start, data_end);
}
OutMsg::~OutMsg() {

}

void OutMsg::requestAcknowledge(bool r) {
	mBuffer[5] = r ? REQUEST_ACKNOWLEDGE : 0;
}
bool OutMsg::checkAcknowledge(::mora::int8* ackBuffer) {
	if (ackBuffer[0] != ACKNOWLEDGE_MESSAGE ||
		ackBuffer[1] != mBuffer[0] ||
		ackBuffer[2] != mBuffer[8] ||
		ackBuffer[3] != mBuffer[9]
		)
	{
		return false;
	}
	return true;
}

int8 byteMagicCounter = 0;//this WILL overflow but that should not be an issue, since we expect the messages to be not that frequent that more than 255 messages will be send before the first message is received. 

std::vector<OutMsg> OutMsg::create(const OutputStream& stream, int32 maxSize) {
	int8 magic = byteMagicCounter++;
	int streamLength = stream.length();
	std::vector<OutMsg>	result;
	if (maxSize - HEADER_SIZE > streamLength) {
		result.push_back(OutMsg((int16)1, (int16)0, magic, stream.begin(), stream.begin() + streamLength, streamLength));
	}
	else {
		int mms = maxSize - HEADER_SIZE;
		int numMsg = (streamLength / mms) + 1;
		result.reserve(numMsg);
		for (int i = 0; i < numMsg; i++) {
			int from = i * mms;
			int to = from + mms;
			if (to > streamLength) to = streamLength;
			result.push_back(OutMsg((int16)numMsg, (int16)i, magic, stream.begin() + from, stream.begin() + to, (to - from)));
		}
	}
	return result;
}