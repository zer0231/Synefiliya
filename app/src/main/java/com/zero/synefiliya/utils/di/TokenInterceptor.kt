package com.zero.synefiliya.utils.di


import com.zero.synefiliya.utils.SharedPreferenceUtil
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private var userSharedPreference: SharedPreferenceUtil) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
//        val token = userSharedPreference.getPersonalToken()


        val modifiedRequest = originalRequest.newBuilder()
//            .addHeader("Authorization", "Bearer $token")
            .addHeader("User-Agent", "PostmanRuntime/7.32.2")
            .addHeader("Content-type", "application/json")
            .addHeader("Accept", "*/*")
            .addHeader("Connection", "keep-alive")
            .build()

        return chain.proceed(modifiedRequest)
    }
}