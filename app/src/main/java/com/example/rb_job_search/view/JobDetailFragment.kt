package com.example.rb_job_search.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rb_job_search.R
import com.example.rb_job_search.databinding.FragmentJobDetailBinding
import com.example.rb_job_search.viewmodel.MainViewModel


class JobDetailFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = FragmentJobDetailBinding.inflate(inflater, container, false)
            .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(MainViewModel::class.java)
        }

        viewModel.job.observe(viewLifecycleOwner, Observer {
            binding.tvJobTitle.text = it.title
            binding.tvCompanyName.text = it.company
            binding.tvJobLocation.text = it.location
            binding.tvUrlLink.text = it.companyUrl

            if(it.companyLogo != null)
                Glide.with(binding.root.context).load(it.companyLogo).into(binding.ivLogo)

            it.description?.let { html ->
                binding.webView.loadData(html, "text/html", "UTF-8")
            }

        })

        //reveals toolbar back button
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

}