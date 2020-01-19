#ifndef SIMPLESTRUCT_H_
#define SIMPLESTRUCT_H_


#include<MoraPreReq.h>
#include<SerializeTypes.h>


class SimpleStruct {
	GENERATED_LINE SimpleStruct();
	GENERATED_LINE ~SimpleStruct();
	
	GENERATED_LINE bool boolValue
	GENERATED_LINE ::mora::int8 byteValue
	GENERATED_LINE ::mora::int16 shortValue
	GENERATED_LINE ::mora::int32 intValue
	GENERATED_LINE ::mora::int64 longValue
	GENERATED_LINE float floatValue
	GENERATED_LINE double doubleValue
	GENERATED_LINE std::string stringValue
	GENERATED_LINE MyEnum enumValue
	GENERATED_LINE SimpleStruct* structValue
};
#endif //