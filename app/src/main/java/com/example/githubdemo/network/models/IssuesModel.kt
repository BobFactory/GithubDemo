package com.example.githubdemo.network.models

data class IssuesModel(
    val author_association: String? = "",
    val body: String? = "",
    val comments: Int? = 0,
    val comments_url: String? = "",
    val created_at: String? = "",
    val events_url: String? = "",
    val html_url: String? = "",
    val id: Int? = 0,
    val number: Int? = 0,
    val repository_url: String? = "",
    val state: String? = "",
    val title: String? = "",
    val updated_at: String? = "",
    val url: String? = "",
    val user: UserModel? = UserModel()
)
