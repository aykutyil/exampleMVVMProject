package com.fups.examplecaseandroid.ui.main.model

import com.fups.examplecaseandroid.base.ListAdapterItem

data class LoginItem(
    val title:String,
    val description:String,
    val image:Int
):ListAdapterItem {
    override val mId: String
        get() = title
}
