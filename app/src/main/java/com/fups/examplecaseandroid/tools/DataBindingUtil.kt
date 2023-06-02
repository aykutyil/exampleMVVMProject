package com.fups.examplecaseandroid.tools

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.databinding.*
import androidx.recyclerview.widget.RecyclerView
import com.fups.examplecaseandroid.base.BaseAdapter
import com.fups.examplecaseandroid.base.ListAdapterItem
import com.fups.examplecaseandroid.tools.customComponents.CustomEditText

object DataBindingUtil {
    @InverseBindingMethods(
        InverseBindingMethod(type =
        CustomEditText::class,attribute = "bind:Text",event =
        "bind:textAttrChanged",method = "bind:getText")
    )
    class CustomEditTextBinder {
        companion object {
            @JvmStatic
            @BindingAdapter("android:textAttrChanged")
            fun setListener(editText: CustomEditText, listener: InverseBindingListener?) {
                if (listener != null) {
                    editText.editText.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            charSequence: CharSequence,
                            i: Int,
                            i1: Int,
                            i2: Int) {}

                        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                        override fun afterTextChanged(editable: Editable) {
                            listener.onChange()
                        }
                    })
                }
            }

            @JvmStatic
            @InverseBindingAdapter(attribute = "Text", event = "android:textAttrChanged")
            fun getText(nMe: CustomEditText): String {
                return nMe.editText.text.toString()
            }

            @JvmStatic
            @BindingAdapter("Text")
            fun setText(editText: CustomEditText, text: String?) {
                text?.let {
                    if (it != editText.editText.text.toString()) {
                        editText.editText.setText(it)
                    }
                }
            }
        }
    }

    @BindingAdapter("setAdapter")
    @JvmStatic
    fun setAdapter(
        recyclerView: RecyclerView,
        adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?
    ) {
        adapter?.let {
            recyclerView.adapter = it
        }
    }

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("submitList")
    @JvmStatic
    fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
        val adapter = recyclerView.adapter as? BaseAdapter<ViewDataBinding, ListAdapterItem>?
        adapter?.differ?.submitList(list)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun downloadImage(view: ImageView, url: Int?){
        view.downloadFromUrl(url)
    }
}