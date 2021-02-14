package com.example.rb_job_search.adapter

import com.example.rb_job_search.model.Job

interface ClickListener {
    fun itemClick(item: Job)
}