package com.fups.examplecaseandroid.ui.main

import androidx.lifecycle.MutableLiveData
import com.fups.examplecaseandroid.base.BaseViewModel
import com.fups.examplecaseandroid.repository.DataSource
import com.fups.examplecaseandroid.tools.enums.PhoneNumberLengthsByCountry
import com.fups.examplecaseandroid.tools.enums.SuccessState
import com.fups.examplecaseandroid.tools.ioScope
import com.fups.examplecaseandroid.ui.main.model.LoginItem
import com.fups.examplecaseandroid.ui.main.model.StatesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataSource: DataSource
) : BaseViewModel() {

    val phone = MutableStateFlow("")
    val password = MutableStateFlow("")
    val adapterList = MutableStateFlow<ArrayList<LoginItem>>(ArrayList())
    val isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginError = MutableLiveData<ArrayList<StatesModel>>()
    private var isLoginClicked = false
    private val successList = ArrayList<StatesModel>()

    init {
        setSuccessOrErrorList()
        setAdapterList()

        ioScope {
            phone.collectLatest { text ->
                if (text.length == PhoneNumberLengthsByCountry.Turkish.length) {
                    successList.find { item -> item.name == SuccessState.PhoneSuccess }?.isSuccess =
                        true
                    if (isLoginClicked) isLoginError.postValue(successList)
                } else {
                    successList.find { item -> item.name == SuccessState.PhoneSuccess }?.isSuccess =
                        false
                }
            }
        }

        ioScope {
            password.collectLatest { text ->
                if (text.length !in 0..4) {
                    successList.find { item -> item.name == SuccessState.PasswordSuccess }?.isSuccess =
                        true
                    if (phone.value.length == PhoneNumberLengthsByCountry.Turkish.length) isLoginError.postValue(successList)
                } else {
                    successList.find { item -> item.name == SuccessState.PasswordSuccess }?.isSuccess =
                        false
                }
            }
        }
    }

    private fun setAdapterList() {
        ioScope {
            adapterList.emit(dataSource.getLoginList())
        }
    }

    private fun setSuccessOrErrorList() {
        successList.add(StatesModel(SuccessState.PhoneSuccess, false))
        successList.add(StatesModel(SuccessState.PasswordSuccess, false))
    }

    fun login() {
        showLoading()
        isLoginClicked = true
        var haveError = false
        val list = ArrayList<StatesModel>()
        successList.forEach { item ->
            if (item.isSuccess.not()) {
                haveError = true
                list.add(item)
            }
        }
        if (haveError) {
            isLoginError.postValue(list)
        } else {
            isLoginSuccess.postValue(true)
        }
        hideLoading()
    }
}