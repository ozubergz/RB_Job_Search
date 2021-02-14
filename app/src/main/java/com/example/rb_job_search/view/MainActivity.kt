package com.example.rb_job_search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.rb_job_search.R
import com.example.rb_job_search.databinding.ActivityMainBinding
import com.example.rb_job_search.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchAPIJobs("java")

//        viewModel.jobs.observe(this, Observer {
//            Log.d("MainActivity", it.toString())
//        })

        loadFragment()
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_job_list, JobListFragment())
            commit()
        }
    }
}