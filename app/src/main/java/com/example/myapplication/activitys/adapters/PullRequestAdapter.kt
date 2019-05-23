package com.example.myapplication.activitys.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.activitys.model.GitResponse
import timber.log.Timber

class PullRequestAdapter: RecyclerView.Adapter<PullRequestAdapter.PullRequestHolder>() {
    private val data = mutableListOf<GitResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PullRequestHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.pull_request_text_view, parent, false) as TextView

        return  PullRequestHolder(textView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PullRequestHolder, position: Int) {
        holder.textView.text = data[position].title
    }

    fun setData(gitData: List<GitResponse>){
        Timber.d("setting adapter data")

        data.clear()
        data.addAll(gitData)

        notifyDataSetChanged()
    }

    class PullRequestHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
}

