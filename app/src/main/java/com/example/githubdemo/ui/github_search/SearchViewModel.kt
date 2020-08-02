package com.example.githubdemo.ui.github_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubdemo.base.BaseResponseConsumer
import com.example.githubdemo.base.BaseViewModel
import com.example.githubdemo.network.RequestState
import com.example.githubdemo.network.models.OrgModel
import com.example.githubdemo.ui.GithubRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val githubRepository: GithubRepository
): BaseViewModel() {

    /**LiveData variables*/
    private val _orgList = MutableLiveData<List<OrgModel>>()
    val onOrganizationsList: LiveData<List<OrgModel>> = _orgList

    fun getAllOrganizations() {
        githubRepository.getOrganizations()
            .doOnSubscribe { disposable.add(it) }
            .subscribe(object: BaseResponseConsumer() {
                override fun loading() {
                    _loading.value = true
                }

                override fun <T> success(data: T) {
                   _loading.value = false
                    _orgList.value = data as List<OrgModel>
                }

                override fun error(msg: String) {
                    _loading.value = false
                    _error.value = msg
                }
            })
    }
}