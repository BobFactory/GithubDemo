package com.example.githubdemo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * A base class for all view models to handle basic functionality similar to all screens.
 */
open class BaseViewModel: ViewModel() {
    protected val disposable = CompositeDisposable()

    protected val error = MutableLiveData<String>()
    val onError: LiveData<String> = error

    protected val loading = MutableLiveData<Boolean>()
    val onLoadingStateChange: LiveData<Boolean> = loading

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}