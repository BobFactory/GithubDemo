package com.example.githubdemo.network.models

data class LicenseModel(
    val body: String? = "",
    val conditions: List<String>? = null,
    val description: String? = "",
    val featured: Boolean? = false,
    val html_url: String? = "",
    val implementation: String? = "",
    val key: String? = "",
    val limitations: List<String>? = listOf(),
    val name: String? = "",
    val node_id: String? = "",
    val permissions: List<String>? = null,
    val spdx_id: String? = "",
    val url: String? = ""
)