package com.example.talkai.ui.activity

import LoginSheet
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivitySplashBinding
import com.example.talkai.ui.activity.Sheets.RegisterSheet
import com.google.android.material.bottomsheet.BottomSheetDialog

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 使其跟随系统主题颜色
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    override fun initViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.btLg.setOnClickListener {
            val loginBottomSheetDialog = LoginSheet(this)
            loginBottomSheetDialog.show()
        }
        binding.tvRg.setOnClickListener {
            val loginBottomSheetDialog = RegisterSheet(this)
            loginBottomSheetDialog.show()
        }
    }
}