package com.example.githubdemo.ui

import android.content.Context
import android.util.Log
import com.example.githubdemo.R
import com.example.githubdemo.network.RequestState
import com.example.githubdemo.network.services.GithubServices
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GithubRepository @Inject constructor(
    private val context: Context,
    private val githubServices: GithubServices
) {

    fun getRepositoriesData(): Observable<RequestState> =
        githubServices.getAllRepositories()
            .map { it.repositoriesModels }
            .flatMap<RequestState> { Observable.just(RequestState.Success(it)) }
            .startWithItem(RequestState.Loading)
            .onErrorReturn {
                RequestState.Error(
                    it.localizedMessage ?: context.getString(R.string.something_went_wrong)
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun getOrganizations(): Observable<RequestState> =
        githubServices.getOrganizations()
            .flatMap<RequestState> { Observable.just(RequestState.Success(it)) }
            .startWithItem(RequestState.Loading)
            .onErrorReturn {
                RequestState.Error(
                    it.localizedMessage ?: context.getString(R.string.something_went_wrong)
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun getLicenseDetails(licenseUrl: String): Observable<RequestState> =
        githubServices.getLicenseDetails(licenseUrl)
            .map<RequestState> { RequestState.Success(it) }
            .startWithItem(RequestState.Loading)
            .onErrorReturn {
                RequestState.Error(
                    it.localizedMessage ?: context.getString(R.string.something_went_wrong)
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    fun getIssues(issuesUrl: String) : Observable<RequestState> =
        githubServices.getIssues(issuesUrl)
            .map<RequestState> { RequestState.Success(it) }
            .startWithItem(RequestState.Loading)
            .onErrorReturn {
                RequestState.Error(
                    it.localizedMessage ?: context.getString(R.string.something_went_wrong)
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}