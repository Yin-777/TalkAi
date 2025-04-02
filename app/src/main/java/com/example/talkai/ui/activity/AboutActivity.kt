package com.example.talkai.ui.activity

import android.content.Intent
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.talkai.R
import com.example.talkai.base.BaseActivity
import com.example.talkai.databinding.ActivityAboutBinding
import com.example.talkai.ui.fragment.MeFragment
import com.example.talkai.utils.ToastUtil

class AboutActivity : BaseActivity<ActivityAboutBinding>() {
    override fun initViewBinding(): ActivityAboutBinding {
        return ActivityAboutBinding.inflate(layoutInflater)
    }

    override fun initView() {
        Glide.with(this).load(
            R.drawable.image1
        ).into(binding.imageViewHead)
        binding.imgBack.setOnClickListener {
            this.finish()

        }
        binding.linearlayoutUser.setOnClickListener { //点击展示用户协议
            val intent = Intent(this,UserActivity::class.java)
            startActivity(intent)
        }
        binding.linearLayoutPolicy.setOnClickListener {
            binding.linearLayoutPolicy.setOnClickListener { //点击展示政策
                val intent = Intent(this, PrivateMainActivity::class.java)
                startActivity(intent)
            }
        }
    }

}