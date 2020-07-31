package com.example.githubdemo.di.annotations

import androidx.lifecycle.ViewModel
import dagger.MapKey
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey (val key: KClass<out ViewModel>)