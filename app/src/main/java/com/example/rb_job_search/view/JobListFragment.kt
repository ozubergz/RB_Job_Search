package com.example.rb_job_search.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_job_search.R
import com.example.rb_job_search.adapter.ClickListener
import com.example.rb_job_search.adapter.JobAdapter
import com.example.rb_job_search.databinding.FragmentJobListBinding
import com.example.rb_job_search.model.Job
import com.example.rb_job_search.viewmodel.MainViewModel
import java.text.FieldPosition
import java.util.EnumSet.of

class JobListFragment : Fragment(), ClickListener {
    private val TAG = "Fragment"

    private lateinit var binding: FragmentJobListBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentJobListBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(MainViewModel::class.java)
        }

        viewModel.jobs.observe(viewLifecycleOwner, Observer {
            binding.rvJobList.adapter = JobAdapter(it, this)
        })

        binding.rvJobList.layoutManager = LinearLayoutManager(binding.root.context)
    }

    companion object {
        @JvmStatic
        fun newInstance() = JobListFragment()
    }

    override fun itemClick(job: Job) {
        viewModel.setJobData(job)
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, JobDetailFragment())
            commit()
        }
    }
}