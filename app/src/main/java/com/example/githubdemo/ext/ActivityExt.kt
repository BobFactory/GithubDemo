package com.example.githubdemo.ext

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.githubdemo.GitHubApplication


fun AppCompatActivity.getAppComponent() =
    (this.application as GitHubApplication).applicationComponent


fun AppCompatActivity.addFragment(containerId: Int, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().add(containerId, fragment).commit()
}

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().replace(containerId, fragment).commit()
}

fun AppCompatActivity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}