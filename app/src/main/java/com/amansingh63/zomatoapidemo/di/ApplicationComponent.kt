package com.amansingh63.zomatoapidemo.di

import android.content.Context
import com.amansingh63.zomatoapidemo.MyApplication
import com.amansingh63.zomatoapidemo.di.module.data.RepositoryModule
import com.amansingh63.zomatoapidemo.di.module.ui.MainActivityModule
import com.amansingh63.zomatoapidemo.di.module.ui.SearchFragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Main component for the application.
 *
 * See the `TestApplicationComponent` used in UI tests.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        MainActivityModule::class,
        SearchFragmentModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}