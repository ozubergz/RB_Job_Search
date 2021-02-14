package com.example.rb_job_search.repo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://jobs.github.com/"

    private val client = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }.let {
                OkHttpClient.Builder().addInterceptor(it).build()
            }

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

    //creating API Service singleton instance
    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }

}