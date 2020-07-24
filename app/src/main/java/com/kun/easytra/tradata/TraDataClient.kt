package com.kun.easytra.tradata

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object TraDataClient {
    private const val TAG = "TraDataClient"

    // https://ptx.transportdata.tw/MOTC?t=Rail&v=2#!/TRA/TRAApi_Station
    private const val MOTC_URL = "https://ptx.transportdata.tw/MOTC/v2/"

    fun getApiClient(): TraApiService {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d(TAG, it) })
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(HttpUrl.parse(MOTC_URL)!!)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        return retrofit.create(TraApiService::class.java)
    }
}