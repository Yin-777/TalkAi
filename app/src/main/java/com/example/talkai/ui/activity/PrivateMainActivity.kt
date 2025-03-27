package com.example.talkai.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.talkai.R
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivityPrivateMainBinding

class PrivateMainActivity : BaseActivity<ActivityPrivateMainBinding>() {

    override fun initViewBinding(): ActivityPrivateMainBinding {
        return ActivityPrivateMainBinding.inflate(layoutInflater)

    }

    override fun initView() {
        binding.imgBack.setOnClickListener {
            this.finish()
        }
    }
}