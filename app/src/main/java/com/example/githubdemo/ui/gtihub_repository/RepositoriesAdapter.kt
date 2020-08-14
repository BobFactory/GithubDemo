package com.example.githubdemo.ui.gtihub_repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubdemo.R
import com.example.githubdemo.network.models.RepositoriesModel
import com.example.githubdemo.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo_list.view.*

class RepositoriesAdapter(
    private val repos: List<RepositoriesModel>,
    private val onRepoPress: (RepositoriesModel, View) -> Unit
) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

    inner class RepositoriesViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(data: RepositoriesModel) {
            itemView.tvOrgName.text = data.name
            itemView.tvRepoOwnerName.text = data.owner?.login
            itemView.tvIssuesCount.text =
                itemView.context.getString(R.string.text_issues_count, data.open_issues_count)
            itemView.tvDescription.text = data.description

            Picasso.get().load(data.owner?.avatar_url)
                .transform(CircleTransform())
                .into(itemView.ivUserImage)

            itemView.setOnClickListener { onRepoPress.invoke(data, itemView) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo_list, parent, false)
        return RepositoriesViewHolder(view)
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bind(repos[position])
    }
}