package com.fups.examplecaseandroid.ui.campaigns

import com.fups.examplecaseandroid.base.BaseViewModel
import com.fups.examplecaseandroid.repository.DataSource
import com.fups.examplecaseandroid.tools.ioScope
import com.fups.examplecaseandroid.ui.campaigns.model.CampaignModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CampaignsViewModel @Inject constructor(
    private val dataSource: DataSource
) : BaseViewModel() {
    val campaignsList = MutableStateFlow<ArrayList<CampaignModel>>(ArrayList())

    init {
        ioScope {
            campaignsList.emit(dataSource.getCampaigns())
        }
    }

}