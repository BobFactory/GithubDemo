package com.example.githubdemo.ui.github_search

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
import com.example.githubdemo.network.models.OrgModel
import com.example.githubdemo.ui.DashboardActivity
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }
    private val organizationList = mutableListOf<OrgModel>()

    override fun onAttach(context: Context) {
        (activity as DashboardActivity).dashboardComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSearchResults.adapter = SearchAdapter(organizationList)

        viewModel.onError.observe(viewLifecycleOwner, Observer {
            (activity as AppCompatActivity).showToast(it)
        })

        viewModel.onLoadingStateChange.observe(viewLifecycleOwner, Observer {
            searchProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.onOrganizationsList.observe(viewLifecycleOwner, Observer {
            organizationList.clear()
            organizationList.addAll(it)
            rvSearchResults.adapter?.notifyDataSetChanged()
        })
    }

    override fun onStart() {
        super.onStart()
        if (organizationList.isEmpty()) {
            viewModel.getAllOrganizations()
        }
    }


}