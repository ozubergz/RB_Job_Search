package com.example.rb_job_search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rb_job_search.databinding.JobItemBinding
import com.example.rb_job_search.model.Job

class JobAdapter(private val dataSet: List<Job>, private val listener: ClickListener) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    class ViewHolder(private val binding: JobItemBinding, private val listener: ClickListener):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Job){
            binding.tvJobTitle.text = item.title
            binding.tvCompanyName.text = item.company
            binding.tvJobLocation.text = item.location

            if(item.companyLogo != null)
                Glide.with(this.itemView).load(item.companyLogo).into(binding.ivLogo)

            binding.cvJobItem.setOnClickListener {
                listener.itemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = JobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size
}