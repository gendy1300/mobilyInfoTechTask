package com.gendy.bugIt.utils.retrofit.interceptor

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {


        val request = chain.request().url.newBuilder()
            .addQueryParameter("key", "AIzaSyAaT8ecEj6K8AtDJBFO9xuXMAJzqZop49E")
            .build()


        val builder: Request.Builder = chain.request().newBuilder().url(request)


        builder.build()


        return chain.proceed(builder.build())
    }
}