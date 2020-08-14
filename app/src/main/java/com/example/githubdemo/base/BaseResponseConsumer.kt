package com.example.githubdemo.base

import com.example.githubdemo.network.RequestState
import io.reactivex.rxjava3.functions.Consumer

/**
 * A base response class to handle the states of RequestState object and use the
 * template design pattern to handle the implementation of the action by the child class.
 */
abstract class BaseResponseConsumer : Consumer<RequestState> {

    override fun accept(t: RequestState?) {
        when (t) {
            is RequestState.Loading -> loading()
            is RequestState.Error -> error(t.message)
            is RequestState.Success<*> -> success(t.data)
        }
    }

    abstract fun loading()
    abstract fun <T> success(data: T)
    abstract fun error(msg: String)
}