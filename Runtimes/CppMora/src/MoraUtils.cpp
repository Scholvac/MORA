
#include "internal/precomp.h"
#include "MORA.h"

#include <sstream>

using namespace mora;


std::string MoraUtils::createRandomIdentifier(int length) {
	static const char alphanum[] =
		"0123456789"
		"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		"abcdefghijklmnopqrstuvwxyz";
	std::stringstream ss;
	for (int i = 0; i < length; ++i) {
		ss << alphanum[rand() % (sizeof(alphanum) - 1)];
	}
	return ss.str();
}
std::string MoraUtils::toString(const Protocol prot) {
	switch (prot) {
	case Protocol::TCP:
		return "tcp";
	case Protocol::UDP:
		return "udp";
	}
	throw "Unknown Protocol";
}
Protocol MoraUtils::toProtocol(const std::string& p) {
	if (p.compare("tcp") == 0 || p.compare("TCP") == 0)
		return Protocol::TCP;
	else if (p.compare("udp") == 0 || p.compare("UDP") == 0)
		return Protocol::UDP;
	throw p + " is an unknown protocol";
}

Protocol MoraUtils::toProtocol(int8 p) {
	switch (p) {
	case 0: return Protocol::TCP;
	case 1: return Protocol::UDP;
	}
	throw "Unknown Protocol";
}
int8 MoraUtils::toByte(const Protocol p) {
	switch (p) {
	case Protocol::TCP: return 0;
	case Protocol::UDP: return 1;
	}
	throw "Unknown Protocol";
}


static int16 sShortMagic{ 0 };

int16 MoraUtils::generateShortMagic() {
	return ++sShortMagic;
}