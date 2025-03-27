package com.example.talkai.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.talkai.R
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivityVipMainBinding

class VipMainActivity : BaseActivity<ActivityVipMainBinding>() {
    override fun initViewBinding(): ActivityVipMainBinding {
        return ActivityVipMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.imgBack.setOnClickListener {
            this.finish()
        }
        Glide.with(this).load(R.drawable.img)
            .apply(RequestOptions.circleCropTransform()).into(binding.imageView)
        //TODO:将ui完善好
    }
}
