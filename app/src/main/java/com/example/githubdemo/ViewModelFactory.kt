package com.example.githubdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
   private val viewModelMap: Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if (viewModelMap.containsKey(modelClass)) {
           return viewModelMap[modelClass]!!.get() as T
       }

        throw IllegalArgumentException("invalid view-model key supplied")
    }

}