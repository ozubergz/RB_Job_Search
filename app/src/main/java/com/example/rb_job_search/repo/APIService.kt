package com.example.rb_job_search.repo

import com.example.rb_job_search.model.Job
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    //END POINT
    //positions.json?description=python&full_time=true&location=ny

    @GET("positions.json")
    suspend fun getJobs(
            @Query("description") description: String,
            @Query("full_time") full_time: Boolean,
            @Query("location") location: String
    ): Response<List<Job>>

}