package com.example.githubdemo.ui.repo_detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubdemo.R
import com.example.githubdemo.di.subcomponents.RepoDetailComponent
import com.example.githubdemo.ext.getAppComponent
import com.example.githubdemo.ext.showToast
import com.example.githubdemo.network.models.RepositoriesModel
import com.example.githubdemo.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repo_detail.*
import kotlinx.android.synthetic.main.item_repo_list.*
import kotlinx.android.synthetic.main.item_repo_list.view.*
import javax.inject.Inject

class RepoDetailActivity : AppCompatActivity() {
    private lateinit var repoDetailComponent: RepoDetailComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RepoDetailViewModel by viewModels { viewModelFactory }

    companion object {
        const val EXTRA_REPO_DATA = "extra_repo_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        repoDetailComponent = getAppComponent().addRepoDetailComponent().create()
        repoDetailComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        init()
        initObservers()
    }

    private fun init() {
        val repoData = intent.getParcelableExtra<RepositoriesModel>(EXTRA_REPO_DATA)

        tvOrgName.text = repoData?.name
        tvRepoOwnerName.text = repoData?.owner?.login
        tvIssuesCount.text =
            getString(R.string.text_issues_count, repoData?.open_issues_count)
        tvDescription.text = repoData?.description

        Picasso.get().load(repoData?.owner?.avatar_url).transform(CircleTransform())
            .into(ivUserImage)
    }

    private fun initObservers() {
        viewModel.onRepoData.observe(this, Observer { data ->
            //TODO: do something here.
        })

        viewModel.onLoadingStateChange.observe(this, Observer { loading ->
            progressBarRepoDetail.visibility = if (loading) View.VISIBLE else View.GONE
        })

        viewModel.onError.observe(this, Observer { error ->
            showToast(error)
        })
    }
}