package com.example.githubdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubdemo.R
import com.example.githubdemo.ext.addFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(R.id.container, RepositoryFragment())
    }
}