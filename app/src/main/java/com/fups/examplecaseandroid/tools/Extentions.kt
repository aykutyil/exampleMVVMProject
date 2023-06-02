package com.fups.examplecaseandroid.tools

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fups.examplecaseandroid.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun ImageView.downloadFromUrl(url:Int?){
    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

private suspend fun CoroutineScope.executeBody(block: suspend CoroutineScope.() -> Unit) {
    try {
        block.invoke(this)
    } catch (e: Exception) {
        e.printStackTrace();
        log(e.message ?: "")
    }
}

fun ViewModel.ioScope(block: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch { withContext(Dispatchers.IO) { executeBody(block) } }

fun ViewModel.mainScope(block: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch { executeBody(block) }