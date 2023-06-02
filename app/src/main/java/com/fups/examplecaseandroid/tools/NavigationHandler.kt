package com.fups.examplecaseandroid.tools

import android.content.Context
import android.content.Intent
import com.fups.examplecaseandroid.ui.campaigns.CampaignsActivity

class NavigationHandler {

    companion object{
        val instance = NavigationHandler()
    }

    fun toCampaignsActivity(context: Context){
        val intent = Intent(context,CampaignsActivity::class.java)
        context.startActivity(intent)
    }

}