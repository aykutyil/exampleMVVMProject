package com.fups.examplecaseandroid.ui.main

import com.fups.examplecaseandroid.R
import com.fups.examplecaseandroid.base.BaseAdapter
import com.fups.examplecaseandroid.databinding.ItemLoginBinding
import com.fups.examplecaseandroid.ui.main.model.LoginItem

class LoginAdapter:BaseAdapter<ItemLoginBinding,LoginItem>() {
    override val layoutId: Int = R.layout.item_login

    override fun bind(binding: ItemLoginBinding, item: LoginItem) {
        binding.apply {
            this.item = item
            executePendingBindings()
        }
    }
}