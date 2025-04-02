package com.example.talkai.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.talkai.R
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivityUserBinding

class UserActivity : BaseActivity<ActivityUserBinding>() {

    override fun initViewBinding(): ActivityUserBinding {
        return ActivityUserBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.imgBack.setOnClickListener {
            this.finish()
        }
    }
}