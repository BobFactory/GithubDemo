package com.example.githubdemo.network

import com.example.githubdemo.network.models.RepositoriesModel

data class RepositoriesResponse(
    val incomplete_results: Boolean? = false,
    val repositoriesModels: List<RepositoriesModel>? = listOf(),
    val total_count: Int? = 0
)
