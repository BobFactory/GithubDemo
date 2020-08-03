package com.example.githubdemo.ui.gtihub_repository

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubdemo.R
import com.example.githubdemo.ext.showToast
import com.example.githubdemo.network.models.RepositoriesModel
import com.example.githubdemo.ui.DashboardActivity
import kotlinx.android.synthetic.main.fragment_repositories.*
import javax.inject.Inject

class RepositoryFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RepositoriesViewModel by viewModels { viewModelFactory }

    private val repoList = mutableListOf<RepositoriesModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as DashboardActivity).dashboardComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRepositories.adapter = RepositoriesAdapter(repoList)

        initObservables()
    }

    override fun onStart() {
        super.onStart()
        if (repoList.isEmpty())
            viewModel.getRepositories()
    }

    private fun initObservables() {
        viewModel.onError.observe(viewLifecycleOwner, Observer {
            (activity as AppCompatActivity).showToast(it)
        })

        viewModel.onLoadingStateChange.observe(viewLifecycleOwner, Observer { loading ->
            repoProgress.visibility = if (loading) View.VISIBLE else View.GONE
        })

        viewModel.onRepoUpdated.observe(viewLifecycleOwner, Observer {
            repoList.clear()
            repoList.addAll(it)
            rvRepositories.adapter?.notifyDataSetChanged()
        })
    }


}