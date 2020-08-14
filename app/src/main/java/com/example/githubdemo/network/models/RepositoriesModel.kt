package com.example.githubdemo.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RepositoriesModel(
    val archive_url: String? = "",
    val archived: Boolean? = false,
    val assignees_url: String? = "",
    val blobs_url: String? = "",
    val branches_url: String? = "",
    val clone_url: String? = "",
    val collaborators_url: String? = "",
    val comments_url: String? = "",
    val commits_url: String? = "",
    val compare_url: String? = "",
    val contents_url: String? = "",
    val contributors_url: String? = "",
    val created_at: String? = "",
    val default_branch: String? = "",
    val deployments_url: String? = "",
    val description: String? = "",
    val disabled: Boolean? = false,
    val downloads_url: String? = "",
    val events_url: String? = "",
    val fork: Boolean? = false,
    val forks: Int? = 0,
    val forks_count: Int? = 0,
    val forks_url: String? = "",
    val full_name: String? = "",
    val git_commits_url: String? = "",
    val git_refs_url: String? = "",
    val git_tags_url: String? = "",
    val git_url: String? = "",
    val has_downloads: Boolean? = false,
    val has_issues: Boolean? = false,
    val has_pages: Boolean? = false,
    val has_projects: Boolean? = false,
    val has_wiki: Boolean? = false,
    val homepage: String? = "",
    val hooks_url: String? = "",
    val html_url: String? = "",
    val id: Int? = 0,
    val issue_comment_url: String? = "",
    val issue_events_url: String? = "",
    val issues_url: String? = "",
    val keys_url: String? = "",
    val labels_url: String? = "",
    val language: String? = "",
    val languages_url: String? = "",
    val license: License? = License(),
    val merges_url: String? = "",
    val milestones_url: String? = "",
    val mirror_url: String? = "",
    val name: String? = "",
    val node_id: String? = "",
    val notifications_url: String? = "",
    val open_issues: Int? = 0,
    val open_issues_count: Int = 0,
    val owner: UserModel? = UserModel(),
    val permissions: Permissions? = Permissions(),
    val `private`: Boolean? = false,
    val pulls_url: String? = "",
    val pushed_at: String? = "",
    val releases_url: String? = "",
    val score: Double? = 0.0,
    val size: Int? = 0,
    val ssh_url: String? = "",
    val stargazers_count: Int? = 0,
    val stargazers_url: String? = "",
    val statuses_url: String? = "",
    val subscribers_url: String? = "",
    val subscription_url: String? = "",
    val svn_url: String? = "",
    val tags_url: String? = "",
    val teams_url: String? = "",
    val trees_url: String? = "",
    val updated_at: String? = "",
    val url: String? = "",
    val watchers: Int? = 0,
    val watchers_count: Int? = 0
) : Parcelable

@Parcelize
data class License(
    val key: String? = "",
    val name: String? = "",
    val node_id: String? = "",
    val spdx_id: String? = "",
    val url: String? = ""
) : Parcelable


@Parcelize
data class Permissions(
    val admin: Boolean? = false,
    val pull: Boolean? = false,
    val push: Boolean? = false
) : Parcelable