package com.mmlotte.lottery.data.datasource

import com.mmlotte.lottery.data.datasource.network.ResultNetworkDataSource
import com.mmlotte.lottery.data.entity.CheckLotterySettingsEntity
import com.mmlotte.lottery.data.entity.ResultEntity
import com.mmlotte.lottery.data.entity.ResultListingEntity
import com.squareup.moshi.Moshi
import javax.inject.Inject

/**
 * Created by Vincent on 12/6/18
 */
class FakeResultNetworkDataSource @Inject constructor(private val moshi: Moshi) :
  ResultNetworkDataSource {

  override fun getSettingsForCheck(): CheckLotterySettingsEntity {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun checkNumber(ticketNumber: String): ResultEntity {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getResult(): ResultListingEntity {
    return moshi.adapter(ResultListingEntity::class.java).fromJson(FakeData.resultLatestResponse)!!
  }
}