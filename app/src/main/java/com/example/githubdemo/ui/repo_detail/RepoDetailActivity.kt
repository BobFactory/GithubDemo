package com.example.githubdemo.ui.repo_detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubdemo.R
import com.example.githubdemo.di.subcomponents.RepoDetailComponent
import com.example.githubdemo.ext.getAppComponent
import com.example.githubdemo.ext.showToast
import com.example.githubdemo.network.models.IssuesModel
import com.example.githubdemo.network.models.RepositoriesModel
import com.example.githubdemo.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repo_detail.*
import kotlinx.android.synthetic.main.item_repo_list.*
import javax.inject.Inject

class RepoDetailActivity : AppCompatActivity() {
    private lateinit var repoDetailComponent: RepoDetailComponent
    private lateinit var repoData: RepositoriesModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RepoDetailViewModel by viewModels { viewModelFactory }
    private val issuesList = mutableListOf<IssuesModel>()

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

    override fun onStart() {
        super.onStart()
        viewModel.getRepoData(repoData.license?.url, repoData.issues_url?.replace("{/number}", ""))
    }

    private fun init() {
        repoData = intent.getParcelableExtra(EXTRA_REPO_DATA)!!

        tvOrgName.text = repoData.name
        tvRepoOwnerName.text = repoData.owner?.login
        tvIssuesCount.text =
            getString(R.string.text_issues_count, repoData.open_issues_count)
        tvDescription.text = repoData.description

        Picasso.get().load(repoData.owner?.avatar_url).transform(CircleTransform())
            .into(ivUserImage)

        //Hide the details in case it is missing from the repository
        if (repoData.license?.url.isNullOrEmpty()) {
            tvLicenseTitle.visibility = View.GONE
            tvLicenseUsageTitle.visibility = View.GONE
            tvLicenseConditionsTitle.visibility = View.GONE
            viewLicenseDivider.visibility = View.GONE
        }
    }

    private fun initObservers() {
        viewModel.onRepoData.observe(this, Observer { data ->
            tvLicenseConditionsTitle.visibility = View.VISIBLE
            tvLicenseUsageTitle.visibility = View.VISIBLE

            tvLicenseName.text = data.repoLicenseData.name
            tvLicenseDescription.text = data.repoLicenseData.description

            data.repoLicenseData.permissions?.apply {
                tvLicenseUsage.text = "- ${this.joinToString("\n- ")}"
            }

            data.repoLicenseData.conditions?.apply {
                tvLicenseConditions.text = "- ${this.joinToString("\n- ")}"
            }

            if (data.issues.isNotEmpty()) {
                tvIssuesTitle.visibility = View.VISIBLE
                issues_divider.visibility = View.VISIBLE
                rvIssuesList.visibility = View.VISIBLE

                issuesList.clear()
                issuesList.addAll(data.issues)
                rvIssuesList.layoutManager = LinearLayoutManager(this)
                rvIssuesList.adapter = IssuesAdapter(issuesList)
            }
        })

        viewModel.onLoadingStateChange.observe(this, Observer { loading ->
            progressBarRepoDetail.visibility = if (loading) View.VISIBLE else View.GONE
        })

        viewModel.onError.observe(this, Observer { error ->
            showToast(error)
        })
    }
}