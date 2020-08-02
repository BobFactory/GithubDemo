package com.example.githubdemo.network.services

import com.example.githubdemo.network.RepositoriesResponse
import com.example.githubdemo.network.models.OrgModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface GithubServices {

    @GET("search/repositories?q=stars:>=1000+language:kotlin&sort=stars")
    fun getAllRepositories(): Observable<RepositoriesResponse>

    @GET("organizations")
    fun getOrganizations() : Observable<List<OrgModel>>


}