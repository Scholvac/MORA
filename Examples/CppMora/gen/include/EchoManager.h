#ifndef ECHOMANAGER_H_
#define ECHOMANAGER_H_


#include<MoraPreReq.h>
#include<SerializeTypes.h>
#include<vector>


class EchoManager {
public:
	GENERATED_LINE EchoManager();
	GENERATED_LINE virtual ~EchoManager();
	
	GENERATED_LINE bool echo(bool value);
	GENERATED_LINE ::mora::int8 echo(::mora::int8 value);
	GENERATED_LINE ::mora::int16 echo(::mora::int16 value);
	GENERATED_LINE ::mora::int32 echo(::mora::int32 value);
	GENERATED_LINE ::mora::int64 echo(::mora::int64 value);
	GENERATED_LINE float echo(float value);
	GENERATED_LINE double echo(double value);
	GENERATED_LINE std::string echo(std::string value);
	GENERATED_LINE MyEnum echo(MyEnum value);
	GENERATED_LINE SimpleStruct* echo(SimpleStruct* value);
	GENERATED_LINE ListStruct* echo(ListStruct* value);
	GENERATED_LINE std::vector<bool> echo1(std::vector<bool> value);
	GENERATED_LINE std::vector<::mora::int8> echo2(std::vector<::mora::int8> value);
	GENERATED_LINE std::vector<::mora::int16> echo3(std::vector<::mora::int16> value);
	GENERATED_LINE std::vector<::mora::int32> echo4(std::vector<::mora::int32> value);
	GENERATED_LINE std::vector<::mora::int64> echo5(std::vector<::mora::int64> value);
	GENERATED_LINE std::vector<float> echo6(std::vector<float> value);
	GENERATED_LINE std::vector<double> echo7(std::vector<double> value);
	GENERATED_LINE std::vector<std::string> echo8(std::vector<std::string> value);
	GENERATED_LINE std::vector<MyEnum> echo9(std::vector<MyEnum> value);
	GENERATED_LINE std::vector<SimpleStruct*> echo10(std::vector<SimpleStruct*> value);
	GENERATED_LINE std::vector<ListStruct*> echo11(std::vector<ListStruct*> value);
	GENERATED_LINE void setCallback(ICallbackPtr cb, float firstValue);
	GENERATED_LINE ICallbackPtr getCallback();
	GENERATED_LINE void throwUnknownException();
};
#endif //