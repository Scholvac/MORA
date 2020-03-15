/*
 * TestUtils.h
 *
 *  Created on: 06.03.2020
 *      Author: sschweigert
 */

#ifndef TESTS_TESTUTILS_H_
#define TESTS_TESTUTILS_H_

#include <MoraPreReq.h>
#include <MoraUtils.h>
#include <vector>

namespace tests{



	template<typename T> inline T iRand(T min, T max) { return min + (rand() % (max - min)); }

	template<typename T>
	inline T create() {
		return iRand(std::numeric_limits<T>::min(), std::numeric_limits<T>::max());
	}
	template<> inline int create(){ return rand();}
	template<> inline bool create() { return rand() % 1; }
	//template<> static int8 create() { return create<int8>(); }
	template<> inline float create() { return (float)rand() / (float)RAND_MAX; }
	template<>  inline double create() { return (double)rand() / (double)RAND_MAX; }
	template<> inline std::string create() {
		int num = iRand(10, 100);
		return mora::MoraUtils::createRandomIdentifier(num);
	}




	template<typename T>
	std::vector<T> inline create(int minCount, int maxCount) {
		int c = minCount + (rand() % (maxCount - minCount));
		std::vector<T> out(c);
		for (int i = 0; i < c; i++)
			out[i] = create<T>();
		return out;
	}






	template<typename TYPE, typename INST, typename FUNC>
	bool createSendAndCompareLists(INST* proxy, FUNC func) {
		std::vector<TYPE> tmp = ::tests::create<TYPE>(20, 100);
		std::vector<TYPE> res = (proxy->*func)(tmp);
		CHECK_EQ(tmp.size(), res.size());

		for (size_t i = 0; i < tmp.size(); i++) {
			if (!(tmp[i] == res[i])) {
				LOG_ERROR("compareList failed at index: %i", (int)i);
//				CHECK(tmp[i] == res[i]);
//				return false;
			}
		}

		return true;
	}

	template<typename TYPE, typename INST, typename FUNC>
	bool createSendAndCompareListsPtr(INST* proxy, FUNC func) {
		std::vector<TYPE> tmp = ::tests::create<TYPE>(10, 20);
		std::vector<TYPE> res = (proxy->*func)(tmp);
		CHECK_EQ(tmp.size(), res.size());

		for (size_t i = 0; i < tmp.size(); i++) {
			if (!(*tmp[i] == *res[i])) {
				LOG_ERROR("compareListPtr failed at index: %i", (int)i);
				CHECK(*tmp[i] == *res[i]);
				return false;
			}
		}

		return true;
	}
}


#endif /* TESTS_TESTUTILS_H_ */
