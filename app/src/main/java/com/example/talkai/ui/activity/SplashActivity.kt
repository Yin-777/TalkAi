package com.example.talkai.ui.activity

import LoginSheet
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivitySplashBinding
import com.example.talkai.ui.activity.Sheets.IDLoginSheet
import com.example.talkai.ui.activity.Sheets.RegisterSheet

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
            val loginBottomSheetDialog = IDLoginSheet(this)
            loginBottomSheetDialog.show()
        }
        binding.tvRg.setOnClickListener {
            val loginBottomSheetDialog = RegisterSheet(this)
            loginBottomSheetDialog.show()
        }
    }

    // 检查 SharedPreferences 中的登录状态
    private fun isUserLoggedIn(): Boolean {
        val sharedPref = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isLoggedIn", false)
    }
}