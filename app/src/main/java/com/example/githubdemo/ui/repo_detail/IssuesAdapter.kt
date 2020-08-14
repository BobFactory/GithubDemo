package com.example.githubdemo.ui.repo_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubdemo.R
import com.example.githubdemo.network.models.IssuesModel
import com.example.githubdemo.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_issues_list.view.*

class IssuesAdapter(
    private val issuesList: List<IssuesModel>
) : RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder>() {

    class IssuesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: IssuesModel) {
            itemView.tvIssueName.text = data.title
            itemView.tvIssueBody.text = data.body

            Picasso.get().load(data.user?.avatar_url).transform(CircleTransform())
                .into(itemView.ivUserImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_issues_list, parent, false)
        return IssuesViewHolder(view)
    }

    override fun getItemCount(): Int = issuesList.size

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        holder.bind(issuesList[position])
    }
}