package com.example.githubdemo.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubdemo.ViewModelFactory
import com.example.githubdemo.di.annotations.ViewModelKey
import com.example.githubdemo.ui.GithubRepository
import com.example.githubdemo.ui.github_search.SearchViewModel
import com.example.githubdemo.ui.gtihub_repository.RepositoriesViewModel
import com.example.githubdemo.ui.repo_detail.RepoDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun providesViewModelFactory(
        map: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }

    @Provides
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    fun providesRepositoriesViewModel(repository: GithubRepository): ViewModel {
        return RepositoriesViewModel(repository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun providesSearchViewModel(repository: GithubRepository): ViewModel {
        return SearchViewModel(repository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(RepoDetailViewModel::class)
    fun providesRepoDetailViewModel(
        context: Context,
        githubRepository: GithubRepository
    ): ViewModel {
        return RepoDetailViewModel(context, githubRepository)
    }
}