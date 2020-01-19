#ifndef MORA_PRE_REQ_H_
#define MORA_PRE_REQ_H_

#include <inttypes.h>
#include <exception>

#define GENERATED_REGION(NAME)

#define GENERATED_LINE

namespace mora {
	
	typedef int8_t	int8;
	typedef int16_t int16;
	typedef int32_t int32;
	typedef int64_t int64;


	const int8 STRUCT_START = (int8)0xAF;
	const int8 STRUCT_END = (int8)0xFF;
	const int8 STRUCT_NULL = (int8)0x00;




	template <class F>
	class final_act
	{
	public:
		explicit final_act(F f) noexcept
			: f_(std::move(f)), invoke_(true) {}

		final_act(final_act&& other) noexcept
			: f_(std::move(other.f_)),
			invoke_(other.invoke_)
		{
			other.invoke_ = false;
		}

		final_act(const final_act&) = delete;
		final_act& operator=(const final_act&) = delete;

		~final_act() noexcept
		{
			if (invoke_) f_();
		}

	private:
		F f_;
		bool invoke_;
	};

	template <class F>
	inline final_act<F> finally(const F& f) noexcept
	{
		return final_act<F>(f);
	}

	template <class F>
	inline final_act<F> finally(F&& f) noexcept
	{
		return final_act<F>(std::forward<F>(f));
	}
}


#endif //MORA_PRE_REQ_H_