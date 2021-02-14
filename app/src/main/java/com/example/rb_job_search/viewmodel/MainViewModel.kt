package com.example.rb_job_search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rb_job_search.model.Job
import com.example.rb_job_search.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await
import retrofit2.awaitResponse

class MainViewModel : ViewModel() {
    companion object {
        private const val TAG = "ViewModel"
    }

    private val _jobs = MutableLiveData<List<Job>>()

    val jobs: LiveData<List<Job>>
        get() = _jobs

    fun fetchAPIJobs(description: String = "java", fullTime: Boolean = true, location: String = "sf") {
        viewModelScope.launch(Dispatchers.IO) {
            val response = Repository.getJobs(description, fullTime, location)
            if (response.isSuccessful) {
                _jobs.value = response.body()
            } else {
                Log.d(TAG, response.errorBody().toString())
            }
        }
    }

}