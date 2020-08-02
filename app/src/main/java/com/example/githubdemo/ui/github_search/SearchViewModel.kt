package com.example.githubdemo.ui.github_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubdemo.base.BaseResponseConsumer
import com.example.githubdemo.base.BaseViewModel
import com.example.githubdemo.network.RequestState
import com.example.githubdemo.network.models.OrgModel
import com.example.githubdemo.ui.GithubRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : BaseViewModel() {
    private val orgList = mutableListOf<OrgModel>()

    /**LiveData variables*/
    private val _orgList = MutableLiveData<List<OrgModel>>()
    val onOrganizationsList: LiveData<List<OrgModel>> = _orgList

    fun getAllOrganizations() {
        githubRepository.getOrganizations()
            .doOnSubscribe { disposable.add(it) }
            .subscribe(object : BaseResponseConsumer() {
                override fun loading() {
                    _loading.value = true
                }

                override fun <T> success(data: T) {
                    _loading.value = false
                    orgList.clear()
                    orgList.addAll(data as List<OrgModel>)
                    _orgList.value = orgList
                }

                override fun error(msg: String) {
                    _loading.value = false
                    _error.value = msg
                }
            })
    }

    fun addSearchOrganization(searchObs: Observable<String>) {
        searchObs
            .skip(1)
            .debounce(500, TimeUnit.MILLISECONDS)
            .flatMap { Observable.fromCallable { filterSearchResults(it) } }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { disposable.add(it) }
            .subscribe {
                _orgList.value = it
            }

    }

    private fun filterSearchResults(searchText: String): List<OrgModel> {
        if (searchText.isEmpty()) return orgList
        return orgList.filter { it.login?.contains(searchText, true) ?: false }
    }
}