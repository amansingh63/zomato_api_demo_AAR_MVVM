package com.amansingh63.zomatoapidemo.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<V : ViewBinding> : DaggerAppCompatActivity() {

    @LayoutRes
    abstract fun layoutRes(): Int

    protected lateinit var binding: V

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewDataBinding: ViewDataBinding = DataBindingUtil.setContentView(this, layoutRes())
        viewDataBinding.lifecycleOwner = this
        binding = viewDataBinding as V
    }
}