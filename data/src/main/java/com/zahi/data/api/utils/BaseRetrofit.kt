package com.zahi.data.api.utils

import com.google.gson.Gson
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseRetrofit @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
) {

    val retrofit = Retrofit.Builder()
        .baseUrl("BuildConfig.BASE_URL")
		//Has to be on top of the other adapters
        .addCallAdapterFactory(RxJavaCustomCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()

}
