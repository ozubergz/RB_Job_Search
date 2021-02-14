package com.example.rb_job_search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rb_job_search.databinding.JobItemBinding
import com.example.rb_job_search.model.Job

class JobAdapter(private val dataSet: List<Job>) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    class ViewHolder(private val binding: JobItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Job){
            Log.d("JobAdapter", item.toString())
            binding.tvJobTitle.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = JobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size
}