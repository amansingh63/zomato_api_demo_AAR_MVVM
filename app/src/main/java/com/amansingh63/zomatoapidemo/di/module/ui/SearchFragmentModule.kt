package com.amansingh63.zomatoapidemo.di.module.ui

import androidx.lifecycle.ViewModel
import com.amansingh63.zomatoapidemo.di.ViewModelBuilder
import com.amansingh63.zomatoapidemo.di.ViewModelKey
import com.amansingh63.zomatoapidemo.ui.search.SearchFragment
import com.amansingh63.zomatoapidemo.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SearchFragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun bindSearchFragment(): SearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindViewModel(viewmodel: SearchViewModel): ViewModel
}
