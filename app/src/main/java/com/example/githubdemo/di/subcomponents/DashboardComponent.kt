package com.example.githubdemo.di.subcomponents

import com.example.githubdemo.di.annotations.ActivityScope
import com.example.githubdemo.ui.gtihub_repository.RepositoryFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface DashboardComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DashboardComponent
    }

    fun inject(fragment: RepositoryFragment)

}