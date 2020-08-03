package com.example.githubdemo.ui.gtihub_repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubdemo.base.BaseResponseConsumer
import com.example.githubdemo.base.BaseViewModel
import com.example.githubdemo.network.models.RepositoriesModel
import com.example.githubdemo.ui.GithubRepository
import javax.inject.Inject


class RepositoriesViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : BaseViewModel() {

    private val _repos = MutableLiveData<List<RepositoriesModel>>()
    val onRepoUpdated: LiveData<List<RepositoriesModel>> = _repos

    fun getRepositories() {
        githubRepository.getRepositoriesData()
            .doOnSubscribe { disposable.add(it) }
            .subscribe(object : BaseResponseConsumer() {
                override fun loading() {
                    loading.value = true
                }

                override fun <T> success(data: T) {
                    loading.value = false
                    _repos.value = data as List<RepositoriesModel>
                }

                override fun error(msg: String) {
                    loading.value = false
                    error.value = msg
                }

            })
    }

}