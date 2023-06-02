package com.fups.examplecaseandroid.tools.customComponents

import android.R.attr.password
import android.content.Context
import android.text.InputFilter
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.core.widget.addTextChangedListener
import com.fups.examplecaseandroid.R
import com.fups.examplecaseandroid.tools.enums.PhoneNumberLengthsByCountry
import com.fups.examplecaseandroid.tools.helper.MaskWatcher
import com.fups.examplecaseandroid.tools.hide
import com.fups.examplecaseandroid.tools.show
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class CustomEditText : LinearLayout {

    lateinit var editText: TextInputEditText
    private lateinit var editTextLayout: TextInputLayout
    private lateinit var llError: LinearLayout
    private lateinit var tvErrorText:TextView

    constructor(context: Context) : super(context){
        initLayout(context,null,null)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){
        initLayout(context,attributeSet,null)
    }

    constructor(context: Context, attributeSet: AttributeSet, style: Int) : super(context, attributeSet, style){
        initLayout(context,attributeSet,style)
    }

    private fun initLayout(context: Context, attributeSet: AttributeSet?, style: Int?) {
        inflate(context, R.layout.custom_phone_number,this)
        editText = findViewById(R.id.etCustomPhoneNumber)
        editTextLayout = findViewById(R.id.tlCustomPhoneNumber)
        llError = findViewById(R.id.llError)
        tvErrorText = findViewById(R.id.tvError)
        val layoutAttribute = context.obtainStyledAttributes(attributeSet, R.styleable.CustomPhoneEditText)
        if (layoutAttribute.getBoolean(R.styleable.CustomPhoneEditText_passwordEdittext,false)){
            setPasswordEditText(
                layoutAttribute.getString(R.styleable.CustomPhoneEditText_hintText),
                layoutAttribute.getString(R.styleable.CustomPhoneEditText_passwordErrorText)
            )
        }else{
            setEditTextMask(PhoneNumberLengthsByCountry.Turkish)
        }
    }

    private fun setPasswordEditText(text: String?, errorText: String?) {
        editTextLayout.hint = text
        tvErrorText.text = errorText
        editText.transformationMethod = PasswordTransformationMethod()
        editText.addTextChangedListener {  }

        editText.doOnLayout {
            editText.setTextAppearance(R.style.hintText)
        }
    }

    private fun setEditTextMask(phoneNumberEnum:PhoneNumberLengthsByCountry) {
        editText.addTextChangedListener(
            MaskWatcher(phoneNumberEnum.mask)
        )

        editText.filters += InputFilter.LengthFilter(phoneNumberEnum.length)

        editText.doOnLayout {
            editText.setTextAppearance(R.style.hintText)
        }
    }

    fun setError(){
        editTextLayout.background = ContextCompat.getDrawable(editTextLayout.context,R.drawable.phone_error_state)
        llError.show()
    }

    fun setSuccess(){
        editTextLayout.background = ContextCompat.getDrawable(editTextLayout.context,R.drawable.phone_bg)
        llError.hide()
    }
}