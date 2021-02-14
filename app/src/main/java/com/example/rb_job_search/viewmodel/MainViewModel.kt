package com.example.rb_job_search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rb_job_search.model.Job
import com.example.rb_job_search.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    companion object {
        private const val TAG = "ViewModel"
    }

    private val _jobs = MutableLiveData<List<Job>>()
    private val _job = MutableLiveData<Job>()

    val jobs: LiveData<List<Job>>
        get() = _jobs

    val job: LiveData<Job>
        get() = _job

    fun setJobData(value: Job) {
        _job.postValue(value)
    }

    fun fetchAPIJobs(description: String, fullTime: Boolean? = null, location: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = Repository.getJobs(description, fullTime, location)
            if (response.isSuccessful) {
                _jobs.postValue(response.body())
            } else {
                Log.d(TAG, response.errorBody().toString())
            }
        }
    }

}