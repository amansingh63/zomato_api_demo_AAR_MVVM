package com.amansingh63.zomatoapidemo.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.amansingh63.zomatoapidemo.ui.BaseViewModel
import com.amansingh63.zomatoapidemo.util.setupSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<V : ViewBinding> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun layoutRes(): Int

    protected lateinit var binding: V

    abstract fun setViewModel()

    abstract fun getViewModel(): BaseViewModel

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewDataBinding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        binding = viewDataBinding as V
        setViewModel()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackbar()
    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, getViewModel().snackbarText, Snackbar.LENGTH_SHORT)
    }

}