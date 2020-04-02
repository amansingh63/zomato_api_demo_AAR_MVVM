package com.amansingh63.zomatoapidemo.ui

import com.amansingh63.zomatoapidemo.data.AppRepositoryImpl
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl
) : BaseViewModel() {}