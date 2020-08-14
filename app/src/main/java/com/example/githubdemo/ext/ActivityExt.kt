package com.example.githubdemo.ext

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.githubdemo.GitHubApplication
import com.example.githubdemo.R
import kotlin.reflect.KClass


fun AppCompatActivity.getAppComponent() =
    (this.application as GitHubApplication).applicationComponent


fun AppCompatActivity.addFragment(containerId: Int, fragment: Fragment, tag: String) {
    this.supportFragmentManager.beginTransaction().add(containerId, fragment, tag).commit()
}

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment, tag: String) {
    this.supportFragmentManager.beginTransaction().replace(containerId, fragment, tag).commit()
}

fun AppCompatActivity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}


