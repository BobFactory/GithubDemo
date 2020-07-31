package com.example.githubdemo.network

sealed class RequestState {
    object Loading : RequestState()
    data class Error(val message: String) : RequestState()
    data class Success<T>(val data: T) : RequestState()
}
