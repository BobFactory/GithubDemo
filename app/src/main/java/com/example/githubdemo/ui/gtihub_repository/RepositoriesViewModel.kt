package com.example.githubdemo.ui.gtihub_repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubdemo.network.RequestState
import com.example.githubdemo.network.models.RepositoriesModel
import com.example.githubdemo.ui.GithubRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject


class RepositoriesViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val disposable = CompositeDisposable()

    /**Live Data variable*/
    private val _error = MutableLiveData<String>()
    val onError: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val onLoadingStateChange: LiveData<Boolean> = _loading

    private val _repos = MutableLiveData<List<RepositoriesModel>>()
    val onRepoUpdated: LiveData<List<RepositoriesModel>> = _repos

    fun getRepositories() {
        githubRepository.getRepositoriesData()
            .doOnSubscribe { disposable.add(it) }
            .subscribe {
                when (it) {
                    is RequestState.Loading -> _loading.value = true
                    is RequestState.Error -> {
                        _loading.value = false
                        _error.value = it.message
                    }
                    is RequestState.Success<*> -> {
                        _loading.value = false
                        _repos.value = it.data as List<RepositoriesModel>
                    }
                }
            }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}