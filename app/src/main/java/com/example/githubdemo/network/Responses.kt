package com.example.githubdemo.network

import com.example.githubdemo.network.models.RepositoriesModel
import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
    val incomplete_results: Boolean? = false,
    @SerializedName("items")
    val repositoriesModels: List<RepositoriesModel>? = listOf(),
    val total_count: Int? = 0
)
