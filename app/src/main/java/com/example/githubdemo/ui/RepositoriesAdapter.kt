package com.example.githubdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubdemo.R

class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

    class RepositoriesViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo_list, parent, false)
        return RepositoriesViewHolder(view)
    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
    }
}