package com.example.githubdemo

import android.app.Application
import com.example.githubdemo.di.ApplicationComponent
import com.example.githubdemo.di.DaggerApplicationComponent

class GitHubApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}