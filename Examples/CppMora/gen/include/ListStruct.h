#ifndef LISTSTRUCT_H_
#define LISTSTRUCT_H_


#include<MoraPreReq.h>
#include<SerializeTypes.h>
#include<vector>


class ListStruct {
	GENERATED_LINE ListStruct();
	GENERATED_LINE ~ListStruct();
	
	GENERATED_LINE std::vector<bool> boolListValue
	GENERATED_LINE std::vector<::mora::int8> byteListValue
	GENERATED_LINE std::vector<::mora::int16> shortListValue
	GENERATED_LINE std::vector<::mora::int32> intListValue
	GENERATED_LINE std::vector<::mora::int64> longListValue
	GENERATED_LINE std::vector<float> floatListValue
	GENERATED_LINE std::vector<double> doubleListValue
	GENERATED_LINE std::vector<std::string> stringListValue
	GENERATED_LINE std::vector<MyEnum> enumListValue
	GENERATED_LINE std::vector<SimpleStruct*> structListValue
};
#endif //