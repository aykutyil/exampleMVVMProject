package com.fups.examplecaseandroid.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.fups.examplecaseandroid.base.BaseActivity
import com.fups.examplecaseandroid.databinding.ActivityMainBinding
import com.fups.examplecaseandroid.tools.NavigationHandler
import com.fups.examplecaseandroid.tools.enums.SuccessState
import com.fups.examplecaseandroid.ui.main.model.StatesModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<LoginViewModel, ActivityMainBinding>() {

    override fun getVM(): Lazy<LoginViewModel> = viewModels()

    override fun getDataBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreateFinished(savedInstance: Bundle?) {
        binding.viewModel = viewModel
        binding.adapter = LoginAdapter()
        observeEvents()

        binding.tvAllCampaigns.setOnClickListener {
            NavigationHandler.instance.toCampaignsActivity(this)
        }

    }

    private fun observeEvents() {
        viewModel.isLoginError.observe(this){list->
            list.forEach { item->
                checkItem(item)
            }
        }

        viewModel.isLoginSuccess.observe(this){
            showToast("Success")
            binding.etPhone.setSuccess()
            binding.etPassword.setSuccess()
        }
    }

    private fun checkItem(item: StatesModel) {
        when(item.name){
            SuccessState.PhoneSuccess->{
                if (item.isSuccess.not())
                    binding.etPhone.setError()
                else{
                    binding.etPhone.setSuccess()
                }
            }
            SuccessState.PasswordSuccess->{
                if (item.isSuccess.not())
                    binding.etPassword.setError()
                else{
                    binding.etPassword.setSuccess()
                }
            }
        }
    }
}