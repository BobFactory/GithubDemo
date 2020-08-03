package com.example.githubdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.githubdemo.R
import com.example.githubdemo.di.subcomponents.DashboardComponent
import com.example.githubdemo.ext.addFragment
import com.example.githubdemo.ext.getAppComponent
import com.example.githubdemo.ext.replaceFragment
import com.example.githubdemo.ui.github_search.SearchFragment
import com.example.githubdemo.ui.gtihub_repository.RepositoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class DashboardActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var dashboardComponent: DashboardComponent

    private val repoFragment = RepositoryFragment()
    private val searchFragment = SearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        dashboardComponent = getAppComponent().addDashboardComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        addFragment(R.id.container, repoFragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_navigation_repos -> {
                toolbar_title.text = getString(R.string.text_repositories)
                replaceFragment(R.id.container, repoFragment)
            }
            R.id.bottom_navigation_orgs -> {
                toolbar_title.text = getString(R.string.text_organizations)
                replaceFragment(R.id.container, searchFragment)
            }
        }
        return true
    }

    private fun initViews() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }
}
