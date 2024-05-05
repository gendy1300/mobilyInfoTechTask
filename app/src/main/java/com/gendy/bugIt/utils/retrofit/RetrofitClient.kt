package com.gendy.bugIt.utils.retrofit

import android.content.Context
import com.gendy.bugIt.utils.retrofit.interceptor.AuthInterceptor
import com.gendy.bugIt.utils.retrofit.interceptor.NetworkInterceptor
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitClient @Inject constructor(
    @ApplicationContext val context: Context
) {
    fun <T> buildApi(api: Class<T>): T {
        val baseUrl =
            "https://script.google.com/macros/s/AKfycbwbDysPSVAb_j9GYv7-wzl5WFtUng6GAwfq1lsF2ZXofsFVqjhLQPA3Eo1dMAXRbr_5/"
        val gson = GsonBuilder()
            .setLenient()
            .create()


        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)



        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(NetworkInterceptor(context))
//                    .addInterceptor(AuthInterceptor())
                    .also {
                        it.addInterceptor(logging)
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(api)


    }
}



