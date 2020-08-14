package com.example.githubdemo.di.subcomponents

import com.example.githubdemo.di.annotations.ActivityScope
import com.example.githubdemo.ui.repo_detail.RepoDetailActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface RepoDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RepoDetailComponent
    }

    fun inject(activity: RepoDetailActivity)
}