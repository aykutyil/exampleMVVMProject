package com.fups.examplecaseandroid.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val eventShowOrHideProgress = MutableLiveData<Boolean>()

    fun showLoading(){
        eventShowOrHideProgress.value = true
    }

    fun hideLoading(){
        eventShowOrHideProgress.value = false
    }
}