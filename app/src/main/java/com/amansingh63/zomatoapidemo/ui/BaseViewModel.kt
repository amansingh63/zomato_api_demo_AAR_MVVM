package com.amansingh63.zomatoapidemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amansingh63.zomatoapidemo.Event
import com.amansingh63.zomatoapidemo.models.ApiError

open class BaseViewModel : ViewModel() {

    protected val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    protected val _noDataFound = MutableLiveData<Boolean>()
    val noDataFound: LiveData<Boolean> = _noDataFound

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    protected val _apiError = MutableLiveData<ApiError>()
    val apiError: LiveData<ApiError> = _apiError

    public fun showSnackbarMessage(message: String) {
        _snackbarText.value = Event(message)
    }

}