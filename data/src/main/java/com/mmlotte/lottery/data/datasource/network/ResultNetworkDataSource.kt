package com.mmlotte.lottery.data.datasource.network

import com.mmlotte.lottery.data.entity.CheckLotterySettingsEntity
import com.mmlotte.lottery.data.entity.ResultEntity
import com.mmlotte.lottery.data.entity.ResultListingEntity

/**
 * Created by Vincent on 9/20/18
 */
interface ResultNetworkDataSource {

  fun getResult(): ResultListingEntity

  fun getSettingsForCheck(): CheckLotterySettingsEntity

  fun checkNumber(ticketNumber: String): ResultEntity

}