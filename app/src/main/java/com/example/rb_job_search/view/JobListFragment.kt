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
import com.example.rb_job_search.adapter.JobAdapter
import com.example.rb_job_search.databinding.FragmentJobListBinding
import com.example.rb_job_search.model.Job
import com.example.rb_job_search.viewmodel.MainViewModel
import java.util.EnumSet.of

class JobListFragment : Fragment() {
    private val TAG = "Fragment"

    private lateinit var binding: FragmentJobListBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentJobListBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(MainViewModel::class.java)
        }

        viewModel.jobs.observe(viewLifecycleOwner, Observer {
            binding.rvJobList.adapter = JobAdapter(it)
            binding.rvJobList.layoutManager = LinearLayoutManager(binding.root.context)
        })
    }


    companion object {
        @JvmStatic
        fun newInstance() = JobListFragment()
    }
}