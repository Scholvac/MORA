
#include <TestSerializer/TestSerializerService.h>

#include <Mora.h>
#include <MoraStreams.h>
#include <TestUtils.h>



using namespace mora;
using namespace de::sos::mora::examples;



int main(int argc, char** argv) {
	mora::Options serverOpt;
	
	serverOpt.port = 9242;
	serverOpt.host = "127.0.0.1";
	serverOpt.protocol = mora::Protocol::TCP;

	mora::Communicator serverCom(serverOpt);
	serverCom.start();

	de::sos::mora::examples::IEchoManagerPtr mgrImpl{ new TestSerializerService{} };
	auto adapter = de::sos::mora::examples::EchoManagerAdapter::createAdapter(mgrImpl, "myEcho", serverCom);

	std::this_thread::sleep_for(std::chrono::seconds(200));
	return 0;
}