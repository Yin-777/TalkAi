package com.example.talkai.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.talkai.R

import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivityMainBinding
import com.example.talkai.utils.LogUtils
import com.example.talkai.utils.ToastUtil

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.textview.text = "成功"
        ToastUtil.showToast("你好")
    }

}