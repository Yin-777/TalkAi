package com.example.talkai.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {



    //泛型，activity继承基类都要都自身viewBinding的类型
    // ViewBinding 实例
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: throw IllegalStateException("ViewBinding is not initialized.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = initViewBinding()
        setContentView(binding.root)
        initView()
    }

    //在子类中进行viewBinding的初始化
    protected abstract fun initViewBinding(): VB

    // 初始化视图的逻辑，子类可以重写，这里写控件的逻辑代码
    protected open fun initView() {}

    override fun onDestroy() {
        super.onDestroy()
        // 释放 ViewBinding 引用，避免内存泄漏
        _binding = null
    }
}