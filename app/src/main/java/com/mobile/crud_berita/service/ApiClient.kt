package com.mobile.crud_berita.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://192.168.243.75/beritaDb//"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(interceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //buat method interceptor
    fun interceptor() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    //panggil product service
    val beritaService : BeritaService by lazy {
        retrofit.create(BeritaService::class.java)
    }
}