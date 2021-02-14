package com.example.rb_job_search.repo

import com.example.rb_job_search.model.Job
import retrofit2.Call
import retrofit2.Response

object Repository {

    private val apiService = RetrofitInstance.apiService

    suspend fun getJobs(description: String, fullTime: Boolean?, location: String?): Response<List<Job>> {
        return apiService.getJobs(description, fullTime, location)
    }
}