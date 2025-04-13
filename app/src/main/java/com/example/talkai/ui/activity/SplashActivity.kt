package com.example.talkai.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivitySplashBinding
import com.example.talkai.ui.activity.Sheets.IDLoginSheet
import com.example.talkai.ui.activity.Sheets.RegisterSheet
import com.example.talkai.network.RetrofitClient

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 跟随系统深色模式
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        // 尝试恢复 token 并设置
        restoreTokenIfExists()

        // 判断是否已登录，跳转主页面
        if (isUserLoggedIn()) {
            startActivity(Intent(this, ShowActivity::class.java))
            finish()
        }
    }

    override fun initViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initView() {
        // 登录按钮弹出 BottomSheet
        binding.btLg.setOnClickListener {
            IDLoginSheet(this).show()
        }

        // 注册按钮弹出 BottomSheet
        binding.tvRg.setOnClickListener {
            RegisterSheet(this).show()
        }
    }

    /**
     * 检查 SharedPreferences 中是否保存了登录状态
     */
    private fun isUserLoggedIn(): Boolean {
        val sharedPref = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isLoggedIn", false)
    }

    /**
     * 如果有 token，则设置到 RetrofitClient 中
     */
    private fun restoreTokenIfExists() {
        val sharedPref = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val token = sharedPref.getString("access_token", null)
        if (!token.isNullOrEmpty()) {
            RetrofitClient.setToken(token)
        }
    }
}
