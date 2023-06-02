package com.fups.examplecaseandroid.repository

import com.fups.examplecaseandroid.R
import com.fups.examplecaseandroid.ui.campaigns.model.CampaignModel
import com.fups.examplecaseandroid.ui.main.model.LoginItem
import javax.inject.Singleton

@Singleton
class DataSource {

    fun getLoginList():ArrayList<LoginItem>{
        val list = ArrayList<LoginItem>()
        list.add(LoginItem("Sosyal Medya", "Sosyal Hesap’larını oluşturarak sevdiklerine para gönder, iste.", R.drawable.item_1))
        list.add(LoginItem("OYUN & E-PIN", "Oyun servislerine hızlıca para aktarın, vakit kaybetmeyin. para gönder, iste.", R.drawable.item_2))
        return list
    }

    fun getCampaigns():ArrayList<CampaignModel>{
        val list = ArrayList<CampaignModel>()
        list.add(CampaignModel("Netflix Kampanyası","30 Haziran 2021 tarihine kadar"))
        list.add(CampaignModel("Spotify Kampanyası","30 Haziran 2021 tarihine kadar"))
        list.add(CampaignModel("Youtube Kampanyası","30 Haziran 2021 tarihine kadar"))
        list.add(CampaignModel("Amazon Kampanyası","30 Haziran 2021 tarihine kadar"))
        return list
    }

}