package com.example.githubdemo.ui.repo_detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubdemo.R
import com.example.githubdemo.base.BaseViewModel
import com.example.githubdemo.di.annotations.ActivityScope
import com.example.githubdemo.network.RequestState
import com.example.githubdemo.network.models.IssuesModel
import com.example.githubdemo.network.models.LicenseModel
import com.example.githubdemo.ui.GithubRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


data class RepoDetails(val repoLicenseData: LicenseModel, val issues: List<IssuesModel>)

@ActivityScope
class RepoDetailViewModel @Inject constructor(
    private val context: Context,
    private val githubRepository: GithubRepository
) : BaseViewModel() {
    private val _repositoryData = MutableLiveData<RepoDetails>()
    val onRepoData: LiveData<RepoDetails> = _repositoryData

    /**
     * Demonstrates the user of zip observable which is combining the result of two different
     * types of Observables and producing a single response.
     * The following will make sure that the result from both api's is fetched and both have a
     * success on them.
     */
    fun getRepoData(licenseUrl: String?, issuesUrl: String?) {
        val observables = mutableListOf<Observable<RequestState>>()

        if (!licenseUrl.isNullOrEmpty()) {
            observables.add(githubRepository.getLicenseDetails(licenseUrl))
        } else {
            observables.add(
                Observable.just<RequestState>(RequestState.Success(LicenseModel()))
                    .startWithItem(RequestState.Loading)
            )
        }

        if (!issuesUrl.isNullOrEmpty()) {
            observables.add(githubRepository.getIssues(issuesUrl))
        } else {
            observables.add(
                Observable.just<RequestState>(RequestState.Success(IssuesModel()))
                    .startWithItem(RequestState.Loading)
            )
        }

        Observable.zip<RequestState, RequestState>(observables, initZipper)
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
                        _repositoryData.value = it.data as RepoDetails
                    }
                }
            }
    }

    //Zipper function that combines the results of the two requests and emits the results.
    private val initZipper = { data: Array<Any> ->
        when {
            data[0] is RequestState.Error || data[1] is RequestState.Error ->
                RequestState.Error(context.getString(R.string.something_went_wrong))

            data[0] is RequestState.Loading || data[1] is RequestState.Loading ->
                RequestState.Loading

            else -> {
                val licenseData = (data[0] as RequestState.Success<LicenseModel>).data
                val issuesData = (data[1] as RequestState.Success<List<IssuesModel>>).data

                RequestState.Success(RepoDetails(licenseData, issuesData))
            }
        }
    }


}