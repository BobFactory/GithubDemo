package com.example.githubdemo.ext

import androidx.appcompat.app.AppCompatActivity
import com.example.githubdemo.GitHubApplication


fun AppCompatActivity.getAppComponent() =
    (this.application as GitHubApplication).applicationComponent