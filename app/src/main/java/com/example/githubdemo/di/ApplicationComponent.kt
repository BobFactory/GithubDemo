package com.example.githubdemo.di

import android.content.Context
import com.example.githubdemo.di.module.NetworkModule
import com.example.githubdemo.di.module.ViewModelModule
import com.example.githubdemo.di.subcomponents.DashboardComponent
import com.example.githubdemo.di.subcomponents.RepoDetailComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, SubComponentsModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun addDashboardComponent(): DashboardComponent.Factory

    fun addRepoDetailComponent(): RepoDetailComponent.Factory
}

@Module(subcomponents = [DashboardComponent::class, RepoDetailComponent::class])
object SubComponentsModule