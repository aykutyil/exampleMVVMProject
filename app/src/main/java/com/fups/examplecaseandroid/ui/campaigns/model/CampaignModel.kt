package com.fups.examplecaseandroid.ui.campaigns.model

import com.fups.examplecaseandroid.base.ListAdapterItem

data class CampaignModel(
    val title:String,
    val description:String,
    override val mId: String = title
):ListAdapterItem