package com.example.githubdemo.ui.repo_detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubdemo.R
import com.example.githubdemo.base.BaseViewModel
import com.example.githubdemo.di.annotations.ActivityScope
import com.example.githubdemo.network.RequestState
import com.example.githubdemo.network.models.IssuesModel
import com.example.githubdemo.network.models.LicenseModel
import com.example.githubdemo.ui.GithubRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


data class RepoDetails(val repoLicenseData: LicenseModel, val issues: List<IssuesModel>)

@ActivityScope
class RepoDetailViewModel @Inject constructor(
    private val context: Context,
    private val githubRepository: GithubRepository
) : BaseViewModel() {
    private val _repositoryData = MutableLiveData<RepoDetailViewModel>()
    val onRepoData: LiveData<RepoDetailViewModel> = _repositoryData

    /**
     * Demonstrates the user of zip observable which is combining the result of two different
     * types of Observables and producing a single response.
     */
    fun getRepoData() {
        Observable.zip<RequestState, RequestState, RequestState>(
            githubRepository.getLicenseDetails(""),
            githubRepository.getIssues(""),
            BiFunction { d1, d2 ->
                when {
                    d1 is RequestState.Error || d2 is RequestState.Error ->
                        RequestState.Error(context.getString(R.string.something_went_wrong))
                    d1 is RequestState.Loading || d2 is RequestState.Loading ->
                        RequestState.Loading
                    else -> {
                        val licenseData = (d1 as RequestState.Success<LicenseModel>).data
                        val issuesData = (d2 as RequestState.Success<List<IssuesModel>>).data

                        RequestState.Success(RepoDetails(licenseData, issuesData))
                    }
                }
            }
        )
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is RequestState.Loading -> loading.value = true
                    is RequestState.Error -> {
                        loading.value = false
                        error.value = it.message
                    }
                    is RequestState.Success<*> -> {
                        loading.value = false
                        _repositoryData.value = it.data as RepoDetailViewModel?
                    }
                }
            }
    }

}