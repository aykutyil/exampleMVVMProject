package com.fups.examplecaseandroid.ui.campaigns

import com.fups.examplecaseandroid.R
import com.fups.examplecaseandroid.base.BaseAdapter
import com.fups.examplecaseandroid.databinding.ItemCampaignsBinding
import com.fups.examplecaseandroid.ui.campaigns.model.CampaignModel

class CampaignAdapter:BaseAdapter<ItemCampaignsBinding,CampaignModel>() {

    override val layoutId: Int = R.layout.item_campaigns

    override fun bind(binding: ItemCampaignsBinding, item: CampaignModel) {
        binding.apply {
            this.item = item
            executePendingBindings()
        }
    }
}