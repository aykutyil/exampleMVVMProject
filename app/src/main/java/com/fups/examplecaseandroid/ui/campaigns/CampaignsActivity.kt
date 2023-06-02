package com.fups.examplecaseandroid.ui.campaigns

import android.os.Bundle
import androidx.activity.viewModels
import com.fups.examplecaseandroid.base.BaseActivity
import com.fups.examplecaseandroid.databinding.ActivityCampaignsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CampaignsActivity : BaseActivity<CampaignsViewModel,ActivityCampaignsBinding>() {

    override fun getVM(): Lazy<CampaignsViewModel> = viewModels()

    override fun getDataBinding(): ActivityCampaignsBinding =
        ActivityCampaignsBinding.inflate(layoutInflater)

    override fun onCreateFinished(savedInstance: Bundle?) {
        binding.viewModel = viewModel
        binding.adapter = CampaignAdapter()

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

}