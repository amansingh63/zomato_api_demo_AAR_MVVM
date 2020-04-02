package com.amansingh63.zomatoapidemo.di.module.ui

import androidx.lifecycle.ViewModel
import com.amansingh63.zomatoapidemo.di.ViewModelBuilder
import com.amansingh63.zomatoapidemo.di.ViewModelKey
import com.amansingh63.zomatoapidemo.ui.MainActivity
import com.amansingh63.zomatoapidemo.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewmodel: MainViewModel): ViewModel
}

