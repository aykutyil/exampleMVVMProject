package com.fups.examplecaseandroid.tools.customComponents

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.fups.examplecaseandroid.R

class CustomButton:ConstraintLayout {

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
        inflate(context, R.layout.custom_button,this)
        val layoutAttribute = context.obtainStyledAttributes(attributeSet, R.styleable.CustomButton)
        setTextView(layoutAttribute.getString(R.styleable.CustomButton_button_name))
    }

    private fun setTextView(text: String?) {
        val textView = findViewById<TextView>(R.id.tvCustomButtonLabel)
        textView.text = text
    }

}