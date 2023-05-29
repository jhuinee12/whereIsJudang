package com.zahi.heyjudaeng.base

sealed class ViewState() {
	data class Success<out T: Any, out U: Any?>(val state: T, val result: U): ViewState()
	data class Failure<out T: Any, out U: String?>(val state: T, val result: U): ViewState()
}
