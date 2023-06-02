package com.fups.examplecaseandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.ViewDataBinding
import com.fups.examplecaseandroid.R

abstract class BaseActivity<VM : BaseViewModel, VB: ViewDataBinding>: AppCompatActivity() {

    protected var loadingView: View? = null
    private var viewGroup: ViewGroup? = null
    protected lateinit var viewModel: VM
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) //To make the screen on all the time
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = getDataBinding()
        viewModel = getVM().value
        binding.lifecycleOwner = this
        observeData()
        setContentView(binding.root)
        onCreateFinished(savedInstanceState)
    }


    abstract fun getVM(): Lazy<VM>

    abstract fun getDataBinding() : VB

    protected abstract fun onCreateFinished(savedInstance: Bundle?)

    private fun observeData(){
        viewModel.eventShowOrHideProgress.observe(this) { isShow ->
            if (isShow) showProgress() else hideProgress()
        }
    }

    private val inflate: LayoutInflater
        get() = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private fun setupLoadingView(viewGroupToAttach: ViewGroup?) {
        loadingView = inflate.inflate(R.layout.view_loading, viewGroupToAttach, false)
    }

    @JvmOverloads
    fun showProgress(viewGroupToAttach: ViewGroup? = null) {
        if(loadingView==null) {
            viewGroup = viewGroupToAttach ?: findViewById(android.R.id.content)
            setupLoadingView(viewGroup)
            viewGroup?.addView(loadingView)
        }
    }

    fun hideProgress() {
        loadingView?.let {
            viewGroup?.removeView(loadingView)
            loadingView = null
        }
    }

    fun showToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}