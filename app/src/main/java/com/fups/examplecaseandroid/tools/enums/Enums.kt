package com.fups.examplecaseandroid.tools.enums

enum class PhoneNumberLengthsByCountry(val length:Int,val mask:String){
    Turkish(13,"### ### ## ##")
}

enum class SuccessState{
    PhoneSuccess,
    PasswordSuccess
}