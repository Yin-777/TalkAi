package com.example.talkai.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.talkai.R
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivityMainBinding
import com.example.talkai.databinding.ActivityShowBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShowActivity : BaseActivity<ActivityShowBinding>() {
    override fun initViewBinding(): ActivityShowBinding {
        return ActivityShowBinding.inflate(layoutInflater)
    }

    override fun initView() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation1)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.itemIconTintList = null


    }
}