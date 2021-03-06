package com.example.rb_job_search.view

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.rb_job_search.R
import com.example.rb_job_search.adapter.ClickListener
import com.example.rb_job_search.databinding.ActivityMainBinding
import com.example.rb_job_search.model.Job
import com.example.rb_job_search.viewmodel.MainViewModel
import org.w3c.dom.Text
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadJobListFragment()
        loadListeners()
    }

    private fun loadListeners() {
        var fullTime = false
        
        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            fullTime = !fullTime
        }
        
        binding.btnSubmit.setOnClickListener {
            binding.tvBottom.visibility = View.GONE

            val description = binding.etDescription.text.toString()
            val location = binding.etLocation.text.toString()
            viewModel.fetchAPIJobs(description, fullTime, location)
            removeJobDetailFragment()
        }
    }

    private fun loadJobListFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, JobListFragment())
            commit()
        }
    }

    private fun removeJobDetailFragment() {
        val manager = supportFragmentManager
        manager.beginTransaction().apply {
            remove(JobDetailFragment())
            commit()
        }
        manager.popBackStack()
    }

    // override actionbar back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        removeJobDetailFragment()
        return true
    }

}