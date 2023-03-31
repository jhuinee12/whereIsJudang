package com.zahi.data.api.utils

import okhttp3.Interceptor
import okhttp3.Response

class ConnectionInterceptor(
	private val connectivity: Connectivity
): Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		if (!connectivity.isConnected()) {
			throw NoConnectivityException("Network Not Connected", "네트워크가 연결되지 않았습니다. 확인 후 다시 시도해 주세요.")
		}
		val builder = chain.request().newBuilder()
		return chain.proceed(builder.build())
	}

}
