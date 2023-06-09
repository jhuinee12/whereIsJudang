package com.zahi.data.api.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TIMEOUT = 5L

@Singleton
class BaseHttpClient @Inject constructor(
    @ApplicationContext appContext: Context,
) {

    val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {

            }
        )
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

}
