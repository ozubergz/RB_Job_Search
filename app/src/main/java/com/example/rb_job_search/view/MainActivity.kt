package com.example.rb_job_search.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rb_job_search.R
import com.example.rb_job_search.adapter.ClickListener
import com.example.rb_job_search.databinding.ActivityMainBinding
import com.example.rb_job_search.model.Job
import com.example.rb_job_search.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchAPIJobs("java")

        loadFragment()
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, JobListFragment())
            commit()
        }
    }

}