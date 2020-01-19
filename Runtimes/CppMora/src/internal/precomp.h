/*
 * precomp.h
 *
 *  Created on: 19.01.2020
 *      Author: sschweigert
 */

#ifndef RUNTIMES_CPPMORA_SRC_INTERNAL_PRECOMP_H_
#define RUNTIMES_CPPMORA_SRC_INTERNAL_PRECOMP_H_

#include <loguru.hpp>


#define LOG_DEBUG(...) VLOG_F(1 , __VA_ARGS__)

#define LOG_INFO(...) LOG_F(INFO, __VA_ARGS__)

#define LOG_WARN(...) LOG_F(WARNING, __VA_ARGS__)



#define SAFE_DELETE(ptr) {if (ptr) {delete ptr; ptr = NULL;} }




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

#endif /* RUNTIMES_CPPMORA_SRC_INTERNAL_PRECOMP_H_ */
