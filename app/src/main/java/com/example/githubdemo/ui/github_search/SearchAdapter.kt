package com.example.githubdemo.ui.github_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubdemo.R
import com.example.githubdemo.network.models.OrgModel
import com.example.githubdemo.network.models.RepositoriesModel
import com.example.githubdemo.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo_list.view.*
import kotlinx.android.synthetic.main.item_repo_list.view.ivUserImage
import kotlinx.android.synthetic.main.item_repo_list.view.tvDescription
import kotlinx.android.synthetic.main.item_repo_list.view.tvOrgName
import kotlinx.android.synthetic.main.item_search_list.view.*

class SearchAdapter(
    private val orgs: List<OrgModel>
) : RecyclerView.Adapter<SearchAdapter.RepositoriesViewHolder>() {

    class RepositoriesViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(data: OrgModel) {
            itemView.tvOrgName.text = data.login
            itemView.tvDescription.text =
                if (data.description.isNullOrEmpty()) "No description available." else data.description

            Picasso.get().load(data.avatar_url)
                .transform(CircleTransform())
                .into(itemView.ivUserImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_list, parent, false)
        return RepositoriesViewHolder(
            view
        )
    }

    override fun getItemCount() = orgs.size

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bind(orgs[position])
    }
}